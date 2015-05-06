package com.nanlagger.blacksails.views.actors.hud

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.nanlagger.blacksails.views.utils.TextureLoader

/**
 * Created by NaNLagger on 26.04.15.
 * @author Stepan Lyashenko
 */
class ButtonActor extends Actor {
  var background = TextureLoader.getTexture("start2")
  var label = ""

  override def draw(batch: Batch, parentAlpha: Float) {
    batch.draw(background, getX, getY, getWidth, getHeight)
  }
}
