package com.nanlagger.blacksails.entities.game.units.ships

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Timer
import com.badlogic.gdx.utils.Timer.Task
import com.nanlagger.blacksails.entities.game.units.{UnitEntities, GameUnit}
import com.nanlagger.blacksails.utils.Utils
import com.nanlagger.blacksails.utils.math.{Graph, CubePosition}

import scala.collection.mutable.ArrayBuffer

/**
 * Created by NaNLagger on 31.03.15.
 * @author Stepan Lyashenko
 */
abstract class Ship(mIdPlayer: Int, mPosition: CubePosition) extends GameUnit(mIdPlayer, mPosition) {
  val movementPoints: Int
  val attackRange: Int
  var graph: Graph = new Graph(position, movementPoints, Set(), (s: CubePosition, f: CubePosition) => 1) //Attention!
  var currentMP : Int = movementPoints
  var state: Boolean = true
  var angleRotate: Float = 0

  class MoveTask(start:CubePosition, finish: CubePosition) extends Task {
    val way = graph.getWay(start, finish)
    var s = way(0).toPoint
    val coordWay = new ArrayBuffer[Vector2]()
    for(p <- way) {
      val c = Utils.positionToPoint(p)
      val temp = new Vector2(c)
      temp.sub(s)
      temp.limit(temp.len()/10)
      angleRotate = temp.angle()
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
      } catch {
        case x: IndexOutOfBoundsException => this.cancel()
      }
    }
  }

  def move(position: CubePosition): Unit = {
    this.move(position)
  }

  def move(pos: CubePosition): Unit = {
    if(UnitEntities.isAttackedUnit(pos, idPlayer)) {
      if(state) {
        UnitEntities.getUnit(pos).hit(damage)
        currentMP = 0
      }
      state = false
    } else {
      val w = graph.getWays(position)
      if(w <= currentMP) {
        currentMP -= w
        Timer.schedule(new MoveTask(position, pos), 0.1f, 0.1f, 20)
        this.position = pos
      }
    }
    WayActor.reset()
  }

  def startMove() = {
    /*graph = new Graph(position, movementPoints, Set(), d)
    WayActor.setPosition(actor.getX, actor.getY)
    WayActor.setPositions(graph.getWays(position).filter((x) => x._2 <= currentMP))
    WayActor(graph)
    if(state)
      startAttack()
    else
      WayActor.setAttacked(Array())*/
  }

  def startAttack() = {
    //WayActor.setAttacked(Graph.getPosition(position, attackRange).filter(UnitEntities.isAttackedUnit(_, idPlayer)))
  }

  override def reset(): Unit = {
    currentMP = movementPoints
    state = true
  }
}
