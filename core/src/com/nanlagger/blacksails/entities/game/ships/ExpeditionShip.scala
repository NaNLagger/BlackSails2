package com.nanlagger.blacksails.entities.game.ships

import com.nanlagger.blacksails.entities.UnitCreator.ShipType
import com.nanlagger.blacksails.entities.{UnitEntities, UnitCreator, Graph, FieldEntities}
import com.nanlagger.blacksails.entities.game.Field.TypeField
import com.nanlagger.blacksails.utils.math.Position
import com.nanlagger.blacksails.views.actors.GameActor
import com.nanlagger.blacksails.views.actors.ships.TestShipActor

/**
 * Created by NaNLagger on 07.05.15.
 * @author Stepan Lyashenko
 */
class ExpeditionShip(idPlayer: Int, mPosition: Position) extends Ship(idPlayer, mPosition) {
  override val movementPoints: Int = 1
  override val attackRange: Int = 1
  override val damage: Int = 3
  override var healthPoint: Int = 21
  override val visionRange: Int = 2
  override val actor: GameActor = new TestShipActor(this)

  override def move(pos: Position) = {
    val arrayP = Graph.getPosition(position, 1).filter(FieldEntities.getField(_).typeField == TypeField.LAND)
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
