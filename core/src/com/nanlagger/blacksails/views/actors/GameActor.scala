package com.nanlagger.blacksails.views.actors

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.nanlagger.blacksails.entities.game.fields.Field
import com.nanlagger.blacksails.entities.game.units.GameUnit
import com.nanlagger.blacksails.utils.Utils
import com.nanlagger.blacksails.views.utils.FontLoader

/**
 * Created by NaNLagger on 04.04.15.
 * @author Stepan Lyashenko
 */
class GameActor(val linkObject: AnyRef) extends Actor {
  linkObject match {
    case gameUnit: GameUnit => {
      val v = Utils.positionToPoint(gameUnit.position)
      setPosition(v.x, v.y)
    }
    case field: Field => {
      val v = Utils.positionToPoint(field.position)
      setPosition(v.x, v.y)
    }
    case _ => setPosition(0, 0)
  }
}
