package com.nanlagger.blacksails.views.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.utils.TimeUtils
import com.nanlagger.blacksails.utils.Utils
import com.nanlagger.blacksails.views.actors.hud.LabelActor

/**
 * Created by NaNLagger on 19.05.15.
 * @author Stepan Lyashenko
 */
class FPSActor extends LabelActor {
  setPosition(Utils.SCREEN_WIDTH - 100, Utils.SCREEN_HEIGHT)

  override def draw(batch: Batch, parentAlpha: Float) = {
    label = Gdx.graphics.getFramesPerSecond.toString
    super.draw(batch, parentAlpha)
  }
}
