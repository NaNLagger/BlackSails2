package com.nanlagger.blacksails.entities.game

import com.nanlagger.blacksails.entities.game.Field.TypeField
import com.nanlagger.blacksails.utils.math.Position
import com.nanlagger.blacksails.views.actors.{Drawable, FieldActor, GameActor}

/**
 * Created by NaNLagger on 31.03.15.
 * @author Stepan Lyashenko
 */

object Field {
  def apply(position: Position): Field = {
    new Field(position)
  }

  def apply(row: Int, column: Int): Field = {
    new Field(new Position(row, column))
  }

  def apply(row: Int, column: Int, typeField: TypeField.Value): Field = {
    new Field(new Position(row, column), typeField)
  }

  def apply = new Field(new Position)

  object TypeField extends Enumeration {
    val LAND, OCEAN = Value
  }
}

class Field(val position: Position, val typeField: TypeField.Value = TypeField.OCEAN) extends Drawable {
  override val actor: GameActor = new FieldActor(this)
  var visionFlag = false
  def setVision(flag: Boolean) = {
    visionFlag = flag
    actor match {
      case x: FieldActor => x.fogActor.setVisible(!visionFlag)
      case _ => ()
    }
  }

}

