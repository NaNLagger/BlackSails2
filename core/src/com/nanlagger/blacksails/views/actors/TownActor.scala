package com.nanlagger.blacksails.views.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.nanlagger.blacksails.entities.Graph
import com.nanlagger.blacksails.entities.game.Town
import com.nanlagger.blacksails.utils.Utils
import com.nanlagger.blacksails.views.utils.TextureLoader

/**
 * Created by NaNLagger on 05.05.15.
 * @author Stepan Lyashenko
 */
class TownActor(link: Town) extends GameActor(link) {
  setSize(150, 150)
  setOrigin(getWidth/2, getHeight/2)
  trait Hexagon {

  }
  val shapeRenderer = new ShapeRenderer
  override def draw(batch: Batch, parentAlpha: Float) = {
    shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix)
    shapeRenderer.setTransformMatrix(batch.getTransformMatrix)
    batch.end()
    Gdx.gl20.glEnable(GL20.GL_BLEND)
    shapeRenderer.begin(ShapeType.Filled)
    shapeRenderer.setColor(1, 0, 0, 0.2f)
    val mPositions = link.townFields
    try {
      for (p <- mPositions) {
        val circle = Utils.positionToPoint(p)
        shapeRenderer.circle(circle.x+Utils.widthField/2, circle.y+Utils.heightField/2, Utils.widthField/2.5f)
      }
    } catch {
      case e: Exception => {}
    }
    shapeRenderer.end()
    Gdx.gl20.glDisable(GL20.GL_BLEND)
    batch.begin()
    batch.draw(TextureLoader.listTexture("town"), getX, getY, getWidth, getHeight)
  }

}
