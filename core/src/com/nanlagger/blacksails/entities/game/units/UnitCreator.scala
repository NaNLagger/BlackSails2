package com.nanlagger.blacksails.entities.game.units

import com.nanlagger.blacksails.controllers.listeners.{ShipListener, TownListener}
import com.nanlagger.blacksails.entities.Graph
import com.nanlagger.blacksails.entities.game.units.ships.{BattleShip, ExpeditionShip, TestShip}
import com.nanlagger.blacksails.entities.game.units.towns.Town
import com.nanlagger.blacksails.views.GameScreen
import com.nanlagger.blacksails.views.actors.hud.HudCreator

/**
 * Created by NaNLagger on 07.05.15.
 * @author Stepan Lyashenko
 */
object UnitCreator {

  object ShipType extends Enumeration {
    val TestShip, ExpeditionShip, BattleShip = Value
  }

  def createShip(unitType: ShipType.Value, idPlayer: Int, position: Position): Boolean = {
    unitType match {
      case ShipType.TestShip => {
        val nUnit = new TestShip(idPlayer, position)
        nUnit.actor.addListener(new ShipListener)
        UnitEntities.addUnit(nUnit)
        GameScreen.mainGroup.addActor(nUnit.actor)
      }
      case ShipType.ExpeditionShip => {
        val nUnit = new ExpeditionShip(idPlayer, position)
        nUnit.actor.addListener(new ShipListener)
        UnitEntities.addUnit(nUnit)
        GameScreen.mainGroup.addActor(nUnit.actor)
      }
      case ShipType.BattleShip => {
        val nUnit = new BattleShip(idPlayer, position)
        nUnit.actor.addListener(new ShipListener)
        UnitEntities.addUnit(nUnit)
        GameScreen.mainGroup.addActor(nUnit.actor)
      }
    }
    true
  }

  def isTownSpawn(position: Position): Boolean = {
    Graph.getPosition(position, 2).filter(UnitEntities.isUnit(_)).map(UnitEntities.getUnit(_)).filter(_.isInstanceOf[Town]).length == 0
  }

  def createTown(idPlayer: Int, position: Position, portPosition: Position): Boolean = {
    if(!isTownSpawn(position)) {
      false
    } else {
      val nUnit = new Town(idPlayer, position, portPosition)
      nUnit.actor.addListener(new TownListener)
      UnitEntities.addUnit(nUnit)
      GameScreen.mainGroup.addActor(nUnit.actor)
      HudCreator.update()
      true
    }
  }

  def getCost(unitType: ShipType.Value): Int = {
    unitType match {
      case ShipType.TestShip => 100
      case ShipType.ExpeditionShip => 270
      case ShipType.BattleShip => 120
      case _ => 0
    }
  }

}
