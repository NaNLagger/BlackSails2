package com.nanlagger.blacksails.entities.game

import com.badlogic.gdx.graphics.Color
import com.nanlagger.blacksails.entities.{FieldEntities, UnitEntities}
import com.nanlagger.blacksails.utils.math.Position

/**
 * Created by NaNLagger on 31.03.15.
 * @author Stepan Lyashenko
 */
object Player {
  var idGenerator = 0
  val colorArray = Array(new Color(1,0,0,1), new Color(0,1,0,1), new Color(0,0,1,1))
  def getIdGenerator() = {
    idGenerator += 1
    idGenerator
  }
}

class Player {
  val id: Int = Player.getIdGenerator
  var visionField: Array[Position] = null
  var money: Int = 100

  def resetVision() = {
    visionField = UnitEntities.getPlayerUnit(id).flatMap(_.getVision())
    FieldEntities.resetVision(visionField)
  }

  def resetUnit(): Unit = {
    money += getIncome()
    for(unit <- UnitEntities.getPlayerUnit(id)) {
      unit.reset()
    }
  }

  def getIncome(): Int = {
    (for(unit <- UnitEntities.getPlayerUnit(id)) yield {
      unit match {
        case x: Town => x.getIncome()
        case _ => 0
      }
    }).sum
  }

  def getColorPlayer(): Color = {
    Player.colorArray(id - 1)
  }
}
