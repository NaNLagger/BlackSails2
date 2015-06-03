package com.nanlagger.blacksails.entities.game.towns

import com.nanlagger.blacksails.entities._
import com.nanlagger.blacksails.entities.game.GameUnit
import com.nanlagger.blacksails.utils.math.Position
import com.nanlagger.blacksails.views.actors.town.TownActor

/**
 * Created by NaNLagger on 05.05.15.
 * @author Stepan Lyashenko
 */
class Town(mIdPlayer: Int, mPosition: Position, val portPosition: Position) extends GameUnit(mIdPlayer, mPosition) {
  override var healthPoint: Int = 100

  override def reset(): Unit = {}

  override val damage: Int = 10
  override val visionRange: Int = 2
  val name = TownsName.getRandomName()
  val townFields = Graph.getPosition(position, 1).filter(FieldEntities.contains(_))
  FieldEntities.getField(position).income += 1
  FieldEntities.getField(portPosition).income += 1

  override val actor: TownActor = new TownActor(this) // last !important

  def buyShip(typeShip: UnitCreator.ShipType.Value) = {
    if(!UnitEntities.isUnit(portPosition) && UnitCreator.getCost(typeShip) < PlayerEntities.getPlayer(idPlayer).money) {
      PlayerEntities.getPlayer(idPlayer).money -= UnitCreator.getCost(typeShip)
      UnitCreator.createShip(typeShip, idPlayer, portPosition)
    }
  }

  def showWindow(): Unit = {
    actor.townWindow.show()
  }

  def getIncome(): Int = {
    townFields.map(FieldEntities.getField(_).income).sum
  }
}
