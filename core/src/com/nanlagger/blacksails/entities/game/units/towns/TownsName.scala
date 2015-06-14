package com.nanlagger.blacksails.entities.game.units.towns

import scala.util.Random

/**
 * Created by NaNLagger on 25.05.15.
 * @author Stepan Lyashenko
 */
object TownsName {
  val names = Array("Port Royale", "Tortuga", "Port-au-Prince", "Martinica", "Nassau", "San Juan", "Isabella", "Providence")

  def getRandomName(): String = {
    names(Math.abs(Random.nextInt()) % names.length)
  }
}
