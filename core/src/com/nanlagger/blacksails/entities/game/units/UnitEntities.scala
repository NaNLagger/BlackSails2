package com.nanlagger.blacksails.entities.game.units

import com.nanlagger.blacksails.entities.game.fields.FieldEntities
import com.nanlagger.blacksails.entities.game.units.ships.Ship

import scala.collection.mutable

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
      unit match {
        case ship: Ship => ship.actor.setVisible(FieldEntities.getField(unit.position).visionFlag)
        case _ => ()
      }
    }
  }
}
