package com.nanlagger.blacksails.views.actors.hud

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter
import com.badlogic.gdx.graphics.g2d.{BitmapFont, TextureRegion, Batch}
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.scenes.scene2d.{InputEvent, InputListener, Group, Actor}
import com.nanlagger.blacksails.views.utils.TextureLoader

/**
 * Created by NaNLagger on 07.05.15.
 * @author Stepan Lyashenko
 */
class WindowActor extends Group {
  var background = TextureLoader.getTexture("background")
  var title = "NewWindow"
  val closeButton = new ButtonActor
  closeButton.setPosition(getWidth - closeButton.getWidth, getHeight)
  closeButton.setSize(20,20)
  closeButton.addListener(new InputListener {
    override def touchDown (event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean = {
      close()
      true
    }
  })
  private val mainActor = new Actor {
    val generator: FreeTypeFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("data/font/TrebuchetMSRegular.ttf"))
    val parameter: FreeTypeFontParameter = new FreeTypeFontParameter()
    parameter.size = 20
    val font12: BitmapFont = generator.generateFont(parameter) // font size 12 pixels
    generator.dispose()

    val shapeRenderer = new ShapeRenderer()
    override def draw(batch: Batch, parentAlpha: Float) {
      batch.draw(background, getX, getY, getWidth, getHeight)
      shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix)
      shapeRenderer.setTransformMatrix(batch.getTransformMatrix)
      batch.end()
      shapeRenderer.begin(ShapeType.Filled)
      shapeRenderer.setColor(0, 0, 0, 1f)
      shapeRenderer.box(0, getHeight, 0, getWidth, -20, 0)
      shapeRenderer.end()
      batch.begin()
      font12.draw(batch, title, 10, getHeight)
    }
  }
  mainActor.setSize(100, 100)
  mainActor.setPosition(0,0)
  this.addActor(mainActor)
  this.addActor(closeButton)
  override def setSize(width: Float, height: Float): Unit = {
    super.setSize(width, height)
    mainActor.setSize(width, height)
    closeButton.setPosition(getWidth - closeButton.getWidth, getHeight - closeButton.getHeight)
  }

  def close(): Unit = {
    this.remove()
  }
}
