package com.nanlagger.blacksails.entities.game.fields

import com.nanlagger.blacksails.entities.game.fields.Field.TypeIncome
import com.nanlagger.blacksails.utils.math.CubePosition
import com.nanlagger.blacksails.views.actors.{Drawable, FieldActor, GameActor}

import scala.collection.mutable

/**
 * Created by NaNLagger on 31.03.15.
 * @author Stepan Lyashenko
 */

object Field {
  def apply(position: CubePosition, typeIncome: TypeIncome): Field = {
    new Field(position, typeIncome)
  }

  object TypeField extends Enumeration {
    val LAND, WATER = Value
  }

  case class TypeIncome(id: Int, name: String, second: TypeField.Value)

  object TypeIncome {
    private var currentValue = 0
    private val values: mutable.Map[Int, TypeIncome] = new mutable.HashMap[Int, TypeIncome]()

    val OCEAN = value("OCEAN", TypeField.WATER)
    val SEA = value("SEA", TypeField.WATER)
    val REEFS = value("REEFS", TypeField.WATER)
    val GRASS = value("GRASS", TypeField.LAND)
    val HILL = value("HILL", TypeField.LAND)
    val MOUNT = value("MOUNT", TypeField.LAND)

    def getType(id: Int): TypeIncome = {
      values(id)
    }

    def size = values.size

    private def value(name: String, typeField: TypeField.Value): TypeIncome = {
      val id = nextValue
      val element = new TypeIncome(id, name, typeField)
      values += id -> element
      element
    }

    private def nextValue: Int = {
      currentValue += 1
      currentValue
    }
  }
}

class Field(val position: CubePosition, val typeField: TypeIncome) extends Drawable {
  var visionFlag = false
  var income = 1

  override val actor: GameActor = new FieldActor(this)

  def setVision(flag: Boolean) = {
    visionFlag = flag
    actor match {
      case x: FieldActor => x.fogActor.setVisible(!visionFlag)
      case _ => ()
    }
  }

}

