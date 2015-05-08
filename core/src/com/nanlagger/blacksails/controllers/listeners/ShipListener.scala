package com.nanlagger.blacksails.controllers.listeners

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.{InputEvent, InputListener}
import com.badlogic.gdx.utils.Timer
import com.badlogic.gdx.utils.Timer.Task
import com.nanlagger.blacksails.controllers.GameController
import com.nanlagger.blacksails.controllers.GameController.CtrlState
import com.nanlagger.blacksails.entities.game.ships.Ship
import com.nanlagger.blacksails.views.actors.GameActor

/**
 * Created by NaNLagger on 23.04.15.
 * @author Stepan Lyashenko
 */
class ShipListener extends InputListener {
  val task = new Task {
    override def run(): Unit = {
      if (GameController.state == CtrlState.ObjectDetected) {
        GameController.state = CtrlState.ObjectDragged
        GameController.focusUnit match {
          case x: Ship => x.startMove();
          case _ => {}
        }
      }
    }
  }

  override def touchDown (event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean = {
    GameController.state = CtrlState.ObjectDetected
    GameController.focusUnit = event.getListenerActor match {
      case x: GameActor => x.linkObject match {
        case z: Ship => z
        case _ => null
      }
    }
    if(GameController.checkPlayer()) {
      Timer.schedule(task, 0.5f)
      true
    } else {
      false
    }
  }

  override def touchUp (event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Unit = {
    task.cancel()
  }

  override def touchDragged (event: InputEvent, x: Float, y: Float, pointer: Int) {

  }
}
