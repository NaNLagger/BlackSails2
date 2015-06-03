package com.nanlagger.blacksails.views.actors.ships

import com.badlogic.gdx.graphics.g2d.Batch
import com.nanlagger.blacksails.entities.game.ships.Ship
import com.nanlagger.blacksails.views.actors.UnitActor
import com.nanlagger.blacksails.views.utils.TextureLoader

/**
 * Created by NaNLagger on 25.05.15.
 * @author Stepan Lyashenko
 */
class ExpeditionShipActor(link: Ship) extends UnitActor(link) {
  setSize(150, 150)
  setOrigin(getWidth/2, getHeight/2)
  override def draw(batch: Batch, parentAlpha: Float) = {
    if(link.angleRotate <= 45 || link.angleRotate > 315) {
      batch.draw(TextureLoader.listTexture("expedition_ship_r"), getX, getY, getWidth, getHeight)
    }
    if(link.angleRotate > 45 && link.angleRotate <= 135) {
      batch.draw(TextureLoader.listTexture("expedition_ship_t"), getX, getY, getWidth, getHeight)
    }
    if(link.angleRotate > 135 && link.angleRotate <= 225) {
      batch.draw(TextureLoader.listTexture("expedition_ship_l"), getX, getY, getWidth, getHeight)
    }
    if(link.angleRotate > 225 && link.angleRotate <= 315) {
      batch.draw(TextureLoader.listTexture("expedition_ship_b"), getX, getY, getWidth, getHeight)
    }
    super.draw(batch, parentAlpha)
  }

}
