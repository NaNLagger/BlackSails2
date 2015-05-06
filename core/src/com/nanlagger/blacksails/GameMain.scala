package com.nanlagger.blacksails

import com.badlogic.gdx.Game
import com.nanlagger.blacksails.controllers.GameController
import com.nanlagger.blacksails.views.GameScreen

/**
 * Created by NaNLagger on 04.04.15.
 * @author Stepan Lyashenko
 */
object GameMain extends Game {
  override def create(): Unit = {
    GameController.initNewGame()
    setScreen(GameScreen)
  }
}
