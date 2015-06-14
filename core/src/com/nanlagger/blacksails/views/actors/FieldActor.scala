package com.nanlagger.blacksails.views.actors

import com.badlogic.gdx.graphics.g2d.Batch
import com.nanlagger.blacksails.entities.game.fields.Field
import Field.{IncomeType, TypeField}
import com.nanlagger.blacksails.entities.game.fields.Field
import com.nanlagger.blacksails.views.utils.TextureLoader

/**
 * Created by NaNLagger on 06.04.15.
 * @author Stepan Lyashenko
 */
class FieldActor(link: Field) extends GameActor(link) {
  val texture = TextureLoader.listTexture("field")
  setSize(150, 150)
  val fogActor = new FogActor
  fogActor.setVisible(true)
  fogActor.setPosition(getX, getY)

  override def draw(batch: Batch, parentAlpha: Float) = {
    val tempColor = batch.getColor
    batch.setColor(this.getColor)
    batch.draw(texture, getX, getY, getWidth, getHeight)
    if(fogActor.isVisible)
      fogActor.draw(batch, parentAlpha)
    batch.setColor(tempColor)
  }
}
