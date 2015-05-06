package com.nanlagger.blacksails.controllers.listeners

import com.badlogic.gdx.scenes.scene2d.{InputEvent, InputListener}

/**
 * Created by NaNLagger on 05.05.15.
 * @author Stepan Lyashenko
 */
class TownListener extends InputListener {

  override def touchDown (event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean = {
    true
  }

  override def touchUp (event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Unit = {
  }

  override def touchDragged (event: InputEvent, x: Float, y: Float, pointer: Int) {

  }
}
