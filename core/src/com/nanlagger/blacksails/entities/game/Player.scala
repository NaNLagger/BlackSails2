package com.nanlagger.blacksails.entities.game

import com.nanlagger.blacksails.entities.{FieldEntities, UnitEntities}
import com.nanlagger.blacksails.utils.math.Position

/**
 * Created by NaNLagger on 31.03.15.
 * @author Stepan Lyashenko
 */
object Player {
  var idGenerator = 0
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
    for(unit <- UnitEntities.getPlayerUnit(id)) {
      unit.reset()
    }
  }
}
