package com.nanlagger.blacksails.controllers.listeners

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.{InputEvent, InputListener}
import com.nanlagger.blacksails.controllers.{InputCommand, GameController}
import com.nanlagger.blacksails.controllers.GameController.CtrlState
import com.nanlagger.blacksails.entities.game.ships.Ship
import com.nanlagger.blacksails.views.actors.GameActor

/**
 * Created by NaNLagger on 06.04.15.
 * @author Stepan Lyashenko
 */
class MainSceneInputListener extends InputListener {
  private var temp: Vector2 = null

  override def touchDown (event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean = {
    temp = new Vector2(x, y)
    GameController.doCommand(InputCommand.touchDown, x, y)
    true
  }

  override def touchUp (event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Unit = {
    GameController.doCommand(InputCommand.touchUp, x, y)
  }

  override def touchDragged (event: InputEvent, x: Float, y: Float, pointer: Int) {
    GameController.doCommand(InputCommand.touchDragged, x, y)
    GameController.changePositionMap(new Vector2(temp.x - x, temp.y - y))
  }
}
