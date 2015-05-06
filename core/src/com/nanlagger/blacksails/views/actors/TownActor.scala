package com.nanlagger.blacksails.views.actors

import com.badlogic.gdx.graphics.g2d.Batch
import com.nanlagger.blacksails.entities.Town
import com.nanlagger.blacksails.views.utils.TextureLoader

/**
 * Created by NaNLagger on 05.05.15.
 * @author Stepan Lyashenko
 */
class TownActor(link: Town) extends GameActor(link) {
  setSize(100, 100)
  override def draw(batch: Batch, parentAlpha: Float) = {
    batch.draw(TextureLoader.listTexture("fish"), getX, getY, getWidth, getHeight)
  }
}
