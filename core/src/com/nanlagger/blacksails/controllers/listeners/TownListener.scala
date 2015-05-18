package com.nanlagger.blacksails.controllers.listeners

import com.badlogic.gdx.scenes.scene2d.{InputEvent, InputListener}
import com.nanlagger.blacksails.controllers.GameController
import com.nanlagger.blacksails.controllers.GameController.CtrlState
import com.nanlagger.blacksails.entities.game.Town
import com.nanlagger.blacksails.views.actors.GameActor

/**
 * Created by NaNLagger on 05.05.15.
 * @author Stepan Lyashenko
 */
class TownListener extends InputListener {

  override def touchDown (event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean = {
      GameController.focusUnit = event.getListenerActor match {
        case x: GameActor => x.linkObject match {
          case z: Town => z
          case _ => null
        }
      }
    if(GameController.checkPlayer())
      GameController.state = CtrlState.ObjectDetected
    GameController.checkPlayer()
  }

  override def touchUp (event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Unit = {
  }

  override def touchDragged (event: InputEvent, x: Float, y: Float, pointer: Int) {

  }
}
