package com.nanlagger.blacksails.views.actors

import com.badlogic.gdx.graphics.g2d.Batch

/**
 * Created by NaNLagger on 04.04.15.
 * @author Stepan Lyashenko
 */
trait Drawable {
  val actor: GameActor
  def draw(batch: Batch, parentAlpha: Float) = actor.draw(batch, parentAlpha)
}
