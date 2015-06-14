package com.nanlagger.blacksails.views

import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.{OrthographicCamera, GL20}
import com.badlogic.gdx.scenes.scene2d._
import com.badlogic.gdx.{Gdx, Screen}
import com.nanlagger.blacksails.GameMain
import com.nanlagger.blacksails.entities.game.fields.FieldEntities
import com.nanlagger.blacksails.utils.Utils
import com.nanlagger.blacksails.views.actors.hud.ButtonActor
import com.nanlagger.blacksails.views.utils.TextureLoader

/**
 * Created by NaNLagger on 19.05.15.
 * @author Stepan Lyashenko
 */
object MainMenuScreen extends Screen {
  val maps = Array("data/maps/main_map.json", "data/maps/random_map.json")
  var current = 0
  val stage: Stage = new Stage()
  val camera: OrthographicCamera = new OrthographicCamera(Utils.SCREEN_WIDTH, Utils.SCREEN_HEIGHT)
  stage.getViewport.setCamera(camera)
  stage.addActor(new Actor {
    override def draw(batch: Batch, parentAlpha: Float): Unit = {
      batch.draw(TextureLoader.getTexture("mainhud"), -Utils.SCREEN_WIDTH/2, -Utils.SCREEN_HEIGHT/2)
    }
  })
  stage.addActor(new ButtonActor {
    label = "New Game"
    setSize(250,100)
    setPosition(-getWidth/2,100)
    addListener(new InputListener {
      override def touchDown (event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean = {
        GameMain.startGame()
        true
      }
    })
  })
  stage.addActor(new ButtonActor {
    label = "Next Map"
    setSize(140, 50)
    setPosition(160, -100)
    addListener(new InputListener {
      override def touchDown (event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean = {
        current += 1
        if(current >= maps.length) {
          current = 0
        }
        loadMap(maps(current))
        true
      }
    })
  })

  stage.addActor(new ButtonActor {
    label = "Prev Map"
    setSize(140, 50)
    setPosition(-160 - getWidth, -100)
    addListener(new InputListener {
      override def touchDown (event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean = {
        current -= 1
        if(current < 0) {
          current = maps.length - 1
        }
        loadMap(maps(current))
        true
      }
    })
  })

  val miniMap = new Group
  miniMap.setScale(0.1f)
  miniMap.setPosition(-150, -Utils.SCREEN_HEIGHT/3)
  stage.addActor(miniMap)
  loadMap(maps(0))


  def loadMap(filename: String): Unit = {
    miniMap.clear()
    val file: FileHandle = Gdx.files.internal(filename)
    val map: String = file.readString()
    FieldEntities(20)
    for(field <- FieldEntities.getAll) {
      miniMap.addActor(field.actor)
      field.setVision(true)
    }
  }

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

  override def pause(): Unit = {

  }

  override def render(delta: Float): Unit = {
    Gdx.gl.glClearColor(0.01176f, 0.18039f, 0.3451f, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

    stage.act(delta)
    stage.draw()
  }

  override def resume(): Unit = {
    Gdx.input.setInputProcessor(stage)
  }
}
