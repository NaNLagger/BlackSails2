package com.nanlagger.blacksails.views.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Timer
import com.badlogic.gdx.utils.Timer.Task
import com.nanlagger.blacksails.entities.FieldEntities
import com.nanlagger.blacksails.entities.game.GameUnit
import com.nanlagger.blacksails.utils.Utils
import com.nanlagger.blacksails.views.actors.hud.LabelActor
import com.nanlagger.blacksails.views.utils.FontLoader

/**
 * Created by NaNLagger on 16.05.15.
 * @author Stepan Lyashenko
 */
class UnitActor(link: GameUnit) extends GameActor(link) {

  private val healthLabel = new LabelActor
  private val hitLabel = new LabelActor
  setVisible(FieldEntities.getField(Utils.pointToPosition(new Vector2(getX + getWidth/2, getY + getHeight/2))).visionFlag)

  class HitTask(damage: Int) extends Task {
    hitLabel.label = "-" + damage.toString
    hitLabel.setPosition(UnitActor.this.getX, UnitActor.this.getY)
    hitLabel.setVisible(true)
    hitLabel.font.setColor(new Color(1, 0, 0, 1))
    var startPos = hitLabel.getY

    override def run(): Unit = {
      hitLabel.setY(hitLabel.getY - 1)
      hitLabel.font.setColor(hitLabel.font.getColor.mul(1, 1, 1, 0.9f))
      if(startPos - hitLabel.getY > 50) {
        hitLabel.setVisible(false)
      }
    }
  }

  override def draw(batch: Batch, parentAlpha: Float) = {
    healthLabel.label = link.healthPoint.toString
    healthLabel.setX(getX + 10)
    healthLabel.setY(getY + 10)
    healthLabel.draw(batch, parentAlpha)
    if(hitLabel.isVisible)
      hitLabel.draw(batch, parentAlpha)
  }

  def hit(damage: Int): Unit = {
    Timer.schedule(new HitTask(damage), 0.1f, 0.04f, 100)
  }
}
