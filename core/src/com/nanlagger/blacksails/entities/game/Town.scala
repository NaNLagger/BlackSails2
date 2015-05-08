package com.nanlagger.blacksails.entities.game

import com.nanlagger.blacksails.entities.{FieldEntities, Graph, UnitCreator}
import com.nanlagger.blacksails.entities.UnitCreator.UnitType
import com.nanlagger.blacksails.utils.math.Position
import com.nanlagger.blacksails.views.actors.{GameActor, TownActor}

/**
 * Created by NaNLagger on 05.05.15.
 * @author Stepan Lyashenko
 */
class Town(mIdPlayer: Int, mPosition: Position) extends GameUnit(mIdPlayer, mPosition) {
  override var healthPoint: Int = 100

  override def reset(): Unit = {}

  override val damage: Int = 10
  override val visionRange: Int = 2
  override val actor: GameActor = new TownActor(this)
  val name = "NewTown " + id
  val townFields = Graph.getPosition(position, 1).filter(FieldEntities.contains(_))

  def buyShip(typeShip: Int) = {
    typeShip match {
      case _ => UnitCreator.createUnit(UnitType.TestShip, idPlayer, position)
    }
  }
}
