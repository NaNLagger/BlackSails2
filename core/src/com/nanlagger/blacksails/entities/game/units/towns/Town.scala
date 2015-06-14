package com.nanlagger.blacksails.entities.game.units.towns

import com.nanlagger.blacksails.entities.game.fields.FieldEntities
import com.nanlagger.blacksails.entities.game.players.PlayerEntities
import com.nanlagger.blacksails.entities.game.units.{UnitEntities, UnitCreator, GameUnit}
import com.nanlagger.blacksails.utils.math.CubePosition
import com.nanlagger.blacksails.views.actors.town.TownActor

/**
 * Created by NaNLagger on 05.05.15.
 * @author Stepan Lyashenko
 */
class Town(mIdPlayer: Int, mPosition: CubePosition, val portPosition: CubePosition) extends GameUnit(mIdPlayer, mPosition) {
  override var healthPoint: Int = 100

  override def reset(): Unit = {}

  override val damage: Int = 10
  override val visionRange: Int = 2
  val name = TownsName.getRandomName()
  val townFields = position.reachable(1, Set()).filter(FieldEntities.contains(_))
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
