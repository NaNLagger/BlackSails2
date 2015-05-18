package com.nanlagger.blacksails.views.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.scenes.scene2d.Actor
import com.nanlagger.blacksails.entities.Graph
import com.nanlagger.blacksails.utils.Utils
import com.nanlagger.blacksails.utils.math.Position

/**
 * Created by NaNLagger on 23.04.15.
 * @author Stepan Lyashenko
 */
object WayActor extends Actor {
  private var mGraph: Graph = null
  private var way: Array[Position] = null
  private var mPositions: Map[Position, Int] = null
  private var mAttacked: Array[Position] = null
  private var stateAttack: Boolean = false
  val shapeRenderer = new ShapeRenderer()

  def apply(graph: Graph) = {
    mGraph = graph
  }

  def apply(start: Position, finish: Position): Unit = {
    if(mAttacked.contains(finish)) {
      stateAttack = true
      way = Array(start, finish)
    } else {
      stateAttack = false
      way = mGraph.getWay(start, finish)
    }
  }

  def reset(): Unit = {
    mGraph = null
    way = null
    mPositions= null
    mAttacked = null
    stateAttack = false
  }

  def setPositions(positions: Map[Position, Int]) = {
    mPositions = positions
  }

  def setAttacked(positions: Array[Position]) = {
    mAttacked = positions
  }

  private def drawWay(): Unit = {
    shapeRenderer.begin(ShapeType.Line)
    shapeRenderer.setColor(1, 1, 0, 1)
    try {
      var start = way(0)
      for (pos <- way; if(mPositions.contains(pos))) {
        val vectorStart = Utils.positionToPoint(start)
        val vectorFinish = Utils.positionToPoint(pos)
        shapeRenderer.line(vectorStart.x+Utils.widthField/2, vectorStart.y+Utils.heightField/2, vectorFinish.x+Utils.widthField/2, vectorFinish.y+Utils.heightField/2)
        start = pos
      }
      val circle = Utils.positionToPoint(start)
      shapeRenderer.circle(circle.x+Utils.widthField/2, circle.y+Utils.heightField/2, Utils.widthField/4);
    } catch {
      case e: Exception => {

      }
    }
    shapeRenderer.end()
  }

  private def drawAttack(): Unit = {
    shapeRenderer.begin(ShapeType.Line)
    shapeRenderer.setColor(1, 0, 0, 1)
    val vectorStart = Utils.positionToPoint(way(0))
    val vectorFinish = Utils.positionToPoint(way(1))
    shapeRenderer.line(vectorStart.x+Utils.widthField/2, vectorStart.y+Utils.heightField/2, vectorFinish.x+Utils.widthField/2, vectorFinish.y+Utils.heightField/2)
    shapeRenderer.end()
  }

  private def drawMovePoints(): Unit = {
    shapeRenderer.begin(ShapeType.Filled)
    shapeRenderer.setColor(0, 1, 1, 0.5f)
    try {
      for (p <-mPositions) {
        val circle = Utils.positionToPoint(p._1)
        shapeRenderer.circle(circle.x+Utils.widthField/2, circle.y+Utils.heightField/2, Utils.widthField/3);
      }
    } catch {
      case e: Exception => {}
    }
    shapeRenderer.end()
  }

  private def drawAttackPoints(): Unit = {
    shapeRenderer.begin(ShapeType.Filled)
    shapeRenderer.setColor(1, 0, 0, 0.5f)
    try {
      for (p <-mAttacked) {
        val circle = Utils.positionToPoint(p)
        shapeRenderer.circle(circle.x+Utils.widthField/2, circle.y+Utils.heightField/2, Utils.widthField/3);
      }
    } catch {
      case e: Exception => {}
    }
    shapeRenderer.end()
  }

  override def draw(batch: Batch, parentAlpha: Float) = {
    shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix)
    shapeRenderer.setTransformMatrix(batch.getTransformMatrix)
    batch.end()
    Gdx.gl20.glEnable(GL20.GL_BLEND)
    if (!stateAttack)
      drawWay()
    else
      drawAttack()
    drawMovePoints()
    drawAttackPoints()
    Gdx.gl20.glDisable(GL20.GL_BLEND)
    batch.begin()
  }
}
