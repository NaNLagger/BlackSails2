package com.nanlagger.blacksails.entities.game.ships

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Timer
import com.badlogic.gdx.utils.Timer.Task
import com.nanlagger.blacksails.entities.game.GameUnit
import com.nanlagger.blacksails.entities.{FieldEntities, Graph, UnitEntities}
import com.nanlagger.blacksails.utils.Utils
import com.nanlagger.blacksails.utils.math.Position
import com.nanlagger.blacksails.views.actors.WayActor

import scala.collection.mutable.ArrayBuffer

/**
 * Created by NaNLagger on 31.03.15.
 * @author Stepan Lyashenko
 */
abstract class Ship(mIdPlayer: Int, mPosition: Position) extends GameUnit(mIdPlayer, mPosition) {
  val movementPoints: Int
  val attackRange: Int
  var graph: Graph = null
  var currentMP : Int = movementPoints
  var state: Boolean = true

  class MoveTask(start:Position, finish: Position) extends Task {
    val way = graph.getWay(start, finish)
    var s = Utils.positionToPoint(way(0))
    val coordWay = new ArrayBuffer[Vector2]()
    for(p <- way) {
      val c = Utils.positionToPoint(p)
      val temp = new Vector2(c)
      temp.sub(s)
      temp.limit(temp.len()/10)
      if(c != s) {
        for (i <- 1 to 10) {
          coordWay += new Vector2(s.add(temp))
        }
      }
      s = c
    }
    var current = 0

    override def run(): Unit = {
      try {
        actor.setPosition(coordWay(current).x, coordWay(current).y)
        current += 1
        actor.setVisible(FieldEntities.getField(Utils.pointToPosition(new Vector2(actor.getX + actor.getWidth/2, actor.getY + actor.getHeight/2))).visionFlag)
      } catch {
        case x: IndexOutOfBoundsException => this.cancel()
      }
    }
  }

  def move(row: Int, column: Int): Unit = {
    this.move(new Position(row, column))
  }

  def move(pos: Position): Unit = {
    if(UnitEntities.isAttackedUnit(pos, idPlayer)) {
      if(state) {
        UnitEntities.getUnit(pos).hit(damage)
        currentMP = 0
      }
      state = false
    } else {
      val w = graph.getWays(position).getOrElse(pos, Int.MaxValue)
      if(w <= currentMP) {
        currentMP -= w
        Timer.schedule(new MoveTask(position, pos), 0.1f, 0.1f, 20)
        this.position = pos
      }
    }
    WayActor.reset()
  }

  def startMove() = {
    graph = Graph.createGraph(position, movementPoints)
    WayActor.setPositions(graph.getWays(position).filter((x) => x._2 <= currentMP))
    WayActor(graph)
    if(state)
      startAttack()
    else
      WayActor.setAttacked(Array())
  }

  def startAttack() = {
    WayActor.setAttacked(Graph.getPosition(position, attackRange).filter(UnitEntities.isAttackedUnit(_, idPlayer)))
  }

  override def reset(): Unit = {
    currentMP = movementPoints
    state = true
  }
}
