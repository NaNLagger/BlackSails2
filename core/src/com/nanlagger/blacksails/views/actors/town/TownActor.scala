package com.nanlagger.blacksails.views.actors.town

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.{Color, GL20}
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.scenes.scene2d.{InputEvent, InputListener}
import com.nanlagger.blacksails.controllers.GameController
import com.nanlagger.blacksails.controllers.GameController.CtrlState
import com.nanlagger.blacksails.entities.PlayerEntities
import com.nanlagger.blacksails.entities.game.Town
import com.nanlagger.blacksails.utils.Utils
import com.nanlagger.blacksails.views.GameScreen
import com.nanlagger.blacksails.views.actors.{UnitActor, GameActor}
import com.nanlagger.blacksails.views.actors.hud.{HudCreator, ButtonActor, WindowActor}
import com.nanlagger.blacksails.views.utils.TextureLoader

/**
 * Created by NaNLagger on 05.05.15.
 * @author Stepan Lyashenko
 */
class TownActor(link: Town) extends UnitActor(link) {
  setSize(150, 150)
  setOrigin(getWidth/2, getHeight/2)

  val townWindow = TownWindow(link)
  GameScreen.hudGroup.addActor(townWindow)
  setVisible(true)

  trait Hexagon {

  }
  private val shapeRenderer = new ShapeRenderer

  override def draw(batch: Batch, parentAlpha: Float) = {
    super.draw(batch, parentAlpha)
    shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix)
    shapeRenderer.setTransformMatrix(batch.getTransformMatrix)
    batch.end()
    Gdx.gl20.glEnable(GL20.GL_BLEND)
    shapeRenderer.begin(ShapeType.Filled)
    val color: Color = PlayerEntities.getPlayer(link.idPlayer).getColorPlayer().cpy()
    shapeRenderer.setColor(color.mul(1, 1, 1, 0.2f))
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
    val portCoord = Utils.positionToPoint(link.portPosition)
    batch.draw(TextureLoader.listTexture("port"), portCoord.x + Utils.widthField/2 - getWidth/4, portCoord.y + Utils.heightField/2 - getHeight/4, getWidth/2, getHeight/2)
  }
}
