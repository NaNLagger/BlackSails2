package com.nanlagger.blacksails.entities

import com.nanlagger.blacksails.utils.math.Position
import com.nanlagger.blacksails.views.actors.{TownActor, GameActor}

/**
 * Created by NaNLagger on 05.05.15.
 * @author Stepan Lyashenko
 */
class Town(mIdPlayer: Int, mPosition: Position) extends GameUnit(mIdPlayer, mPosition) {
  override var healthPoint: Int = 100

  override def reset(): Unit = ???

  override val damage: Int = 10
  override val visionRange: Int = 2
  override val actor: GameActor = new TownActor(this)

  def buyShip(typeShip: Int) = {
    typeShip match {
      case _ => new TestShip(idPlayer, position)
    }
  }
}
