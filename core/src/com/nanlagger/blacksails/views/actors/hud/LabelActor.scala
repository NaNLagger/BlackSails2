package com.nanlagger.blacksails.views.actors.hud

import com.badlogic.gdx.graphics.g2d.{TextureRegion, Batch}
import com.badlogic.gdx.scenes.scene2d.Actor
import com.nanlagger.blacksails.views.utils.FontLoader

/**
 * Created by NaNLagger on 08.05.15.
 * @author Stepan Lyashenko
 */
class LabelActor extends Actor {
  var label = ""
  var font = FontLoader.getFont(20)

  override def draw(batch: Batch, parentAlpha: Float) {
    val bounds = font.getBounds(label)
    font.draw(batch, label, getX, getY)
  }
}
