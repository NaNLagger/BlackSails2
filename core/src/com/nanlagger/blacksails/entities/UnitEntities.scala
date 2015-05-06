package com.nanlagger.blacksails.entities

import com.nanlagger.blacksails.entities.FieldEntities
import com.nanlagger.blacksails.utils.math.Position

import scala.collection.mutable
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer

/**
 * Created by NaNLagger on 31.03.15.
 * @author Stepan Lyashenko
 */
object UnitEntities {
  private val units = new mutable.HashMap[Int, GameUnit]()

  def getAllUnit = {
    units.values.toArray
  }

  def getUnit(id: Int) = {
    units(id)
  }

  def getPlayerUnit(player: Int) = {
    units.values.toArray.filter(_.idPlayer == player)
  }

  def removeUnit(unit: GameUnit): Unit = {
    units.remove(unit.id)
  }

  def addUnit(unit: GameUnit) = units += unit.id -> unit

  def isUnit(pos: Position): Boolean = {
    for((id,unit) <- units) {
      if(unit.position == pos)
        return true
    }
    false
  }

  def isUnit(pos: Position, idPlayer: Int): Boolean = {
    for((id,unit) <- units) {
      if(unit.position == pos && unit.idPlayer == idPlayer)
        return true
    }
    false
  }

  def getUnit(pos: Position): GameUnit = {
    for((id,unit) <- units) {
      if(unit.position == pos)
        return unit
    }
    null
  }

  def isAttackedUnit(pos: Position, idPlayer: Int): Boolean = {
    for((id,unit) <- units) {
      if(unit.position == pos && unit.idPlayer != idPlayer)
        return true
    }
    false
  }

  def resetUnit(): Unit = {
    for((id, unit) <- units) {
      if(FieldEntities.getField(unit.position).visionFlag) {
        unit.actor.setVisible(true)
      } else {
        unit.actor.setVisible(false)
      }
    }
  }
}
