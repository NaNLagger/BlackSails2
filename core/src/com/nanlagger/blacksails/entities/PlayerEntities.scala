package com.nanlagger.blacksails.entities

/**
 * Created by NaNLagger on 31.03.15.
 * @author Stepan Lyashenko
 */
object PlayerEntities {
  private var currentPlayer: Int = 0
  private var players: Array[Player] = null

  def apply(size: Int): Unit = {
    players = (for(i <- 0 until size) yield new Player()).toArray
  }

  def getPlayer(id: Int): Player = {
    players.find(_.id == id).get
  }

  def getCurrentPlayer: Player = players(currentPlayer)

  def nextPlayer: Player = {
    currentPlayer += 1
    if(currentPlayer >= players.length)
      currentPlayer = 0
    getCurrentPlayer
  }
}
