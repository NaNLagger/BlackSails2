package com.nanlagger.blacksails.views.actors.ships

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.{TextureRegion, Batch}
import com.nanlagger.blacksails.views.actors.GameActor
import com.nanlagger.blacksails.views.utils.TextureLoader

/**
 * Created by NaNLagger on 06.04.15.
 * @author Stepan Lyashenko
 */
class TestShipActor(link: AnyRef) extends GameActor(link) {
  setSize(100, 80)
  setOrigin(getWidth/2, getHeight/2)
  override def draw(batch: Batch, parentAlpha: Float) = {
    batch.draw(TextureLoader.listTexture("ship"), getX, getY, getWidth, getHeight)
  }

  override def setPosition(x: Float, y: Float): Unit = {
    super.setPosition(x + getOriginX/2, y + getOriginY)
  }
}
