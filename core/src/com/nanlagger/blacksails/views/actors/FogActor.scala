package com.nanlagger.blacksails.views.actors

import com.badlogic.gdx.graphics.g2d.Batch
import com.nanlagger.blacksails.views.utils.TextureLoader

/**
 * Created by NaNLagger on 05.05.15.
 * @author Stepan Lyashenko
 */
class FogActor extends GameActor(null) {
  setSize(150, 150)

  override def draw(batch: Batch, parentAlpha: Float) = {
    batch.draw(TextureLoader.getTexture("not_vision"), getX, getY, getWidth, getHeight)
  }
}
