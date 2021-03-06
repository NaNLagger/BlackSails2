package com.nanlagger.blacksails.controllers

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.{InputEvent, InputListener}
import com.nanlagger.blacksails.controllers.ai.AIController
import com.nanlagger.blacksails.controllers.listeners.{TownListener, ShipListener}
import com.nanlagger.blacksails.entities.UnitCreator.ShipType
import com.nanlagger.blacksails.entities._
import com.nanlagger.blacksails.entities.game.ships.Ship
import com.nanlagger.blacksails.entities.game.{Town, GameUnit}
import com.nanlagger.blacksails.utils.Utils
import com.nanlagger.blacksails.utils.math.Position
import com.nanlagger.blacksails.views.GameScreen
import com.nanlagger.blacksails.views.actors.WayActor
import com.nanlagger.blacksails.views.actors.hud.{HudCreator, WindowActor, ButtonActor}

import scala.io.Source

/**
 * Created by NaNLagger on 04.04.15.
 * @author Stepan Lyashenko
 */
object GameController {

  var state = CtrlState.ClearFocus
  var focusUnit: GameUnit = null

  object CtrlState extends Enumeration {
    val ObjectDetected, ClearFocus, ObjectDragged = Value
  }

  def initNewGame() = {
    //FieldEntities(20)
    FieldEntities(Source.fromFile("data/maps/main_map.json").getLines().mkString(""))
    for(field <- FieldEntities.getAll())
      GameScreen.mainGroup.addActor(field.actor)
    PlayerEntities(3)
    UnitCreator.createShip(ShipType.ExpeditionShip, PlayerEntities.getCurrentPlayer.id, Position(2,2))
    UnitCreator.createShip(ShipType.ExpeditionShip, PlayerEntities.getPlayer(2).id, Position(4,4))
    UnitCreator.createShip(ShipType.ExpeditionShip, PlayerEntities.getPlayer(3).id, Position(6,6))
    //UnitCreator.createShip(ShipType.TestShip, PlayerEntities.getPlayer(2).id, Position(4,4))
    //UnitCreator.createShip(ShipType.TestShip, PlayerEntities.getPlayer(2).id, Position(4,6))
    HudCreator.create()
    GameScreen.mainGroup.addActor(WayActor)
    PlayerEntities.getCurrentPlayer.resetVision()
  }

  def changePositionMap(delta: Vector2): Unit = {
    if (state != CtrlState.ObjectDragged) {
      if(delta.len() > 10) {
        state = CtrlState.ClearFocus
      }
      val group = GameScreen.mainGroup
      group.setPosition(group.getX - delta.x, group.getY - delta.y)
      val left = -Utils.SCREEN_WIDTH/2
      if(group.getX > left)
        group.setX(left)
      val right = -(Utils.positionToPoint(new Position(FieldEntities.rows, FieldEntities.columns)).x + Utils.widthField/2 - Utils.SCREEN_WIDTH/2)
      if(group.getX < right)
        group.setX(right)
      val bottom = -Utils.SCREEN_HEIGHT/2
      if(group.getY > bottom)
        group.setY(bottom)
      val top = -(Utils.positionToPoint(new Position(FieldEntities.rows, FieldEntities.columns)).y + (Utils.heightField/2)*Utils.CONS_SCALE - Utils.SCREEN_HEIGHT/2)
      if(group.getY < top)
        group.setY(top)
    }
  }

  def checkPlayer(): Boolean = {
    focusUnit.idPlayer == PlayerEntities.getCurrentPlayer.id
  }

  def endTurn() = {
    val activePlayer = PlayerEntities.getCurrentPlayer.id
    PlayerEntities.nextPlayer
    PlayerEntities.getCurrentPlayer.resetUnit()
    while (PlayerEntities.getCurrentPlayer.id != activePlayer) {
      val ai = new AIController(PlayerEntities.getCurrentPlayer)
      ai.run()
      PlayerEntities.nextPlayer
      PlayerEntities.getCurrentPlayer.resetUnit()
    }
  }

  def doCommand(command: InputCommand.Value, x: Float, y:Float): Unit = {
    command match {
      case InputCommand.touchDown => touchDown(x,y)
      case InputCommand.touchDragged => touchDragged(x,y)
      case InputCommand.touchUp => touchUp(x,y)
    }
  }

  private def touchDown(x: Float, y:Float): Unit = {
    state match {
      case CtrlState.ObjectDetected => {

      }
      case _ => {}
    }

  }

  private def touchDragged(x: Float, y: Float): Unit = {
    state match {
      case CtrlState.ObjectDragged => {
        focusUnit match {
          case unit: Ship => {
            WayActor(unit.position, Utils.pointToPosition(new Vector2(x, y)))
          }
        }
      }
      case CtrlState.ClearFocus => {}
      case CtrlState.ObjectDetected => {}
    }
  }

  private def touchUp(x: Float, y: Float) {
    state match {
      case CtrlState.ObjectDragged => {
        state = CtrlState.ClearFocus
        focusUnit match {
          case unit: Ship => {
            unit.move(Utils.pointToPosition(new Vector2(x, y)))
            PlayerEntities.getCurrentPlayer.resetVision()
          }
          case _ => {}
        }
      }
      case CtrlState.ObjectDetected => {
        focusUnit match {
          case unit: Town => {
            unit.showWindow()
          }
          case _ => {}
        }
      }
      case _ => {
        state = CtrlState.ClearFocus
      }
    }
  }


}
