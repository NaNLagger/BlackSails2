package com.nanlagger.blacksails.controllers.ai

import com.badlogic.gdx.Gdx
import com.nanlagger.blacksails.entities.{Ship, UnitEntities, Player}

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
          ship.startMove()
          val array_move = ship.graph.getWays(ship.position).filter((x) => x._2 <= ship.currentMP).keys.toArray
          Gdx.app.log("AI", array_move.mkString(" ") + " | " + ship.currentMP)
          array_move.length
          ship.move(array_move(Math.abs(Random.nextInt()) % array_move.length))
        }
      }
    }
  }
}
