package com.nanlagger.blacksails.entities

import com.nanlagger.blacksails.controllers.listeners.{TownListener, ShipListener}
import com.nanlagger.blacksails.entities.game.Town
import com.nanlagger.blacksails.entities.game.ships.{ExpeditionShip, TestShip}
import com.nanlagger.blacksails.utils.math.Position
import com.nanlagger.blacksails.views.GameScreen

/**
 * Created by NaNLagger on 07.05.15.
 * @author Stepan Lyashenko
 */
object UnitCreator {

  object UnitType extends Enumeration {
    val TestShip, ExpeditionShip, Town = Value
  }

  def createUnit(unitType: UnitType.Value, idPlayer: Int, position: Position): Boolean = {
    unitType match {
      case UnitType.TestShip => {
        val nUnit = new TestShip(idPlayer, position)
        nUnit.actor.addListener(new ShipListener)
        UnitEntities.addUnit(nUnit)
        GameScreen.mainGroup.addActor(nUnit.actor)
      }
      case UnitType.Town => {
        val nUnit = new Town(idPlayer, position)
        nUnit.actor.addListener(new TownListener)
        UnitEntities.addUnit(nUnit)
        GameScreen.mainGroup.addActor(nUnit.actor)
      }
      case UnitType.ExpeditionShip => {
        val nUnit = new ExpeditionShip(idPlayer, position)
        nUnit.actor.addListener(new ShipListener)
        UnitEntities.addUnit(nUnit)
        GameScreen.mainGroup.addActor(nUnit.actor)
      }
    }
    true
  }

}
