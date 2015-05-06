package com.nanlagger.blacksails.views

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.{Gdx, Screen}
import com.badlogic.gdx.graphics.{FPSLogger, GL20, Texture, OrthographicCamera}
import com.badlogic.gdx.scenes.scene2d.{Actor, Stage}
import com.nanlagger.blacksails.controllers.GameController
import com.nanlagger.blacksails.controllers.listeners.MainSceneInputListener
import com.nanlagger.blacksails.utils.Utils

/**
 * Created by NaNLagger on 04.04.15.
 * @author Stepan Lyashenko
 */
object GameScreen extends Screen {
  val stage: Stage = new Stage()
  val camera: OrthographicCamera = new OrthographicCamera(Utils.SCREEN_WIDTH, Utils.SCREEN_HEIGHT)
  stage.getViewport.setCamera(camera)
  val mainGroup = new Group
  mainGroup.setPosition(-100, -100)
  stage.addActor(mainGroup)
  mainGroup.addListener(new MainSceneInputListener)
  val hudGroup = new Group
  hudGroup.setPosition(-Utils.SCREEN_WIDTH/2, -Utils.SCREEN_HEIGHT/2)
  stage.addActor(hudGroup)

  override def show(): Unit = {
    Gdx.input.setInputProcessor(stage)
  }

  override def hide(): Unit = {

  }

  override def resize(width: Int, height: Int): Unit = {

  }

  override def dispose(): Unit = {
    stage.dispose()
  }

  override def pause(): Unit = {}

  override def render(delta: Float): Unit = {
    Gdx.gl.glClearColor(0.01176f, 0.18039f, 0.3451f, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

    stage.act(delta)
    stage.draw
  }

  override def resume(): Unit = {
    Gdx.input.setInputProcessor(stage)
  }
}
