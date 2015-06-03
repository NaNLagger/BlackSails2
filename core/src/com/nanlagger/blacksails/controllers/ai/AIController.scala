package com.nanlagger.blacksails.controllers.ai

import com.badlogic.gdx.Gdx
import com.nanlagger.blacksails.entities.game.Field.TypeField
import com.nanlagger.blacksails.entities.game.Player
import com.nanlagger.blacksails.entities._
import com.nanlagger.blacksails.entities.game.ships.{ExpeditionShip, Ship}
import com.nanlagger.blacksails.entities.game.towns.Town

import scala.util.Random

/**
 * Created by NaNLagger on 27.04.15.
 * @author Stepan Lyashenko
 */
class AIController(val player: Player) {

  def run(): Unit = {
    val units = UnitEntities.getPlayerUnit(player.id)
    for(unit <- units) {
      unit match {
        case ship: Ship => {
          val flag = if(ship.isInstanceOf[ExpeditionShip]) {
            ship.startMove()
            val array_town = Graph.getPosition(ship.position, 1).filter(FieldEntities.getField(_).typeField == TypeField.LAND)
            if(array_town.length > 0) {
              val townSpawn = array_town(Math.abs(Random.nextInt()) % array_town.length)
              if (UnitCreator.isTownSpawn(townSpawn)) {
                ship.move(townSpawn)
                true
              } else {
                false
              }
            } else {
              false
            }
          } else {
            false
          }
          if(!flag) {
            val array_attack = Graph.getPosition(ship.position, ship.attackRange).filter(UnitEntities.isAttackedUnit(_, ship.idPlayer))
            if(array_attack.length > 0) {
              ship.move(array_attack(Math.abs(Random.nextInt()) % array_attack.length))
            }
            ship.startMove()
            val array_move = ship.graph.getWays(ship.position).filter((x) => x._2 <= ship.currentMP).keys.toArray
            ship.move(array_move(Math.abs(Random.nextInt()) % array_move.length))
          }
        }
        case town: Town => {
          town.buyShip(UnitCreator.ShipType.BattleShip)
        }
      }
    }
  }
}
