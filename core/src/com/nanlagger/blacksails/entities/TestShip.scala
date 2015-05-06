package com.nanlagger.blacksails.entities

import com.nanlagger.blacksails.utils.math.Position
import com.nanlagger.blacksails.views.actors.GameActor
import com.nanlagger.blacksails.views.actors.ships.TestShipActor

/**
 * Created by NaNLagger on 01.04.15.
 * @author Stepan Lyashenko
 */
class TestShip(idPlayer: Int, position: Position) extends Ship(idPlayer, position) {
  override val movementPoints: Int = 2
  override val attackRange: Int = 2
  override var healthPoint: Int = 34
  override val visionRange: Int = 3
  override val damage: Int = 10
  override val actor: GameActor = new TestShipActor(this)
  reset()
}
