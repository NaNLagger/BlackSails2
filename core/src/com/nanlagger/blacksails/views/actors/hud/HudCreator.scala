package com.nanlagger.blacksails.views.actors.hud

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.{InputEvent, InputListener}
import com.nanlagger.blacksails.controllers.GameController
import com.nanlagger.blacksails.entities.PlayerEntities
import com.nanlagger.blacksails.utils.Utils
import com.nanlagger.blacksails.views.GameScreen
import com.nanlagger.blacksails.views.actors.FPSActor
import com.nanlagger.blacksails.views.utils.{FontLoader, TextureLoader}

/**
 * Created by NaNLagger on 17.05.15.
 * @author Stepan Lyashenko
 */
object HudCreator {
  val endTurnButton = new ButtonActor
  val moneyBar = new ButtonActor

  def create(): Unit = {
    
    endTurnButton.label = "End Turn"
    endTurnButton.setPosition(0,0)
    endTurnButton.setSize(100,40)
    endTurnButton.addListener(new InputListener {
      override def touchDown (event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean = {
        GameController.endTurn()
        update()
        true
      }
    })

    moneyBar.label = "Money: " + PlayerEntities.getCurrentPlayer.money + " +" + PlayerEntities.getCurrentPlayer.getIncome()
    moneyBar.font = FontLoader.getFont(16, new Color(1, 1, 0, 1))
    moneyBar.background = TextureLoader.getTexture("button1")
    moneyBar.setSize(200, 30)
    moneyBar.setPosition(0, Utils.SCREEN_HEIGHT - moneyBar.getHeight)

    GameScreen.hudGroup.addActor(endTurnButton)
    GameScreen.hudGroup.addActor(moneyBar)
    GameScreen.hudGroup.addActor(new FPSActor)
  }

  def update(): Unit = {
    moneyBar.label = "Money: " + PlayerEntities.getCurrentPlayer.money + " +" + PlayerEntities.getCurrentPlayer.getIncome()
  }
}
