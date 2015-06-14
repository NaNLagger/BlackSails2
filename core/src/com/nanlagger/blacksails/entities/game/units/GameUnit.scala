package com.nanlagger.blacksails.entities.game.units

import com.nanlagger.blacksails.utils.math.{CubePosition, OffsetPosition}
import com.nanlagger.blacksails.views.actors.{Drawable, UnitActor}

/**
 * Created by NaNLagger on 31.03.15.
 * @author Stepan Lyashenko
 */
object GameUnit {
  private var idGenerator = 0
  private def getCurrentId = {
    idGenerator += 1
    idGenerator
  }
}

abstract class GameUnit(val idPlayer: Int, var position: OffsetPosition) extends Drawable {
  val id: Int = GameUnit.getCurrentId
  var healthPoint: Int
  val visionRange: Int
  val damage: Int

  def isLive = healthPoint > 0

  def hit(damage: Int) = {
    healthPoint -= damage
    actor.asInstanceOf[UnitActor].hit(damage)
    if (!isLive) {
      actor.remove()
      UnitEntities.removeUnit(this)
    }
    damage
  }

  def getVision: Array[CubePosition] = {
    position.reachable(visionRange, Set())
  }

  def == (other: GameUnit): Boolean = id == other.id

  override def equals(o: Any): Boolean = {
    o match {
      case that: GameUnit => id == that.id
      case _ => false
    }
  }

  def reset(): Unit
}
