package com.nanlagger.blacksails.views.actors.hud

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.{BitmapFont, Batch}
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter
import com.badlogic.gdx.scenes.scene2d.Actor
import com.nanlagger.blacksails.views.utils.{FontLoader, TextureLoader}

/**
 * Created by NaNLagger on 26.04.15.
 * @author Stepan Lyashenko
 */
class ButtonActor extends Actor {
  var background = TextureLoader.getTexture("button")
  var label = ""
  var font = FontLoader.getFont(20)

  override def draw(batch: Batch, parentAlpha: Float) {
    batch.draw(background, getX, getY, getWidth, getHeight)
    val bounds = font.getBounds(label)
    font.draw(batch, label, getX + getWidth/2 - bounds.width/2, getY + getHeight/2 + bounds.height/2)
  }
}
