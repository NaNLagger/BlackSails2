package com.nanlagger.blacksails.views.actors.ships

import com.badlogic.gdx.graphics.g2d.{TextureRegion, Batch}
import com.badlogic.gdx.scenes.scene2d.Actor
import com.nanlagger.blacksails.views.utils.TextureLoader

/**
 * Created by NaNLagger on 17.05.15.
 * @author Stepan Lyashenko
 */
class ShipIcon extends Actor {
  setSize(100, 100)
  setOrigin(getWidth/2, getHeight/2)
  var texture: TextureRegion = TextureLoader.listTexture("ship")

  override def draw(batch: Batch, parentAlpha: Float) = {
    super.draw(batch, parentAlpha)
    batch.draw(texture, getX, getY, getWidth, getHeight)
  }
}
