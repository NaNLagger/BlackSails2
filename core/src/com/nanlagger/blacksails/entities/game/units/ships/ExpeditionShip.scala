package com.nanlagger.blacksails.entities.game.units.ships

import com.nanlagger.blacksails.entities.game.units.{UnitEntities, UnitCreator}
import UnitCreator.ShipType
import com.nanlagger.blacksails.entities.game.fields.{FieldEntities, Field}
import Field.TypeField
import com.nanlagger.blacksails.utils.math.CubePosition
import com.nanlagger.blacksails.views.actors.GameActor
import com.nanlagger.blacksails.views.actors.ships.{ExpeditionShipActor, TestShipActor}

/**
 * Created by NaNLagger on 07.05.15.
 * @author Stepan Lyashenko
 */
class ExpeditionShip(idPlayer: Int, mPosition: CubePosition) extends Ship(idPlayer, mPosition) {
  override val movementPoints: Int = 1
  override val attackRange: Int = 1
  override val damage: Int = 3
  override var healthPoint: Int = 21
  override val visionRange: Int = 2
  override val actor: GameActor = new ExpeditionShipActor(this)

  override def move(pos: CubePosition) = {
    val arrayP = position.reachable(1, Set()).filter(FieldEntities.getField(_).typeField == TypeField.LAND)
    if(arrayP.contains(pos)) {
      if(UnitCreator.createTown(idPlayer, pos, position)) {
        actor.remove()
        UnitEntities.removeUnit(this)
      }
    } else {
      super.move(pos)
    }
  }
  reset()
}
