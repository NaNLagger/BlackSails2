package com.nanlagger.blacksails.views.actors.town

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.{Actor, Group, InputEvent, InputListener}
import com.nanlagger.blacksails.controllers.GameController
import com.nanlagger.blacksails.controllers.GameController.CtrlState
import com.nanlagger.blacksails.entities.{UnitCreator, FieldEntities}
import com.nanlagger.blacksails.entities.UnitCreator.ShipType
import com.nanlagger.blacksails.entities.game.ships.TestShip
import com.nanlagger.blacksails.entities.game.{Field, Town}
import com.nanlagger.blacksails.utils.Utils
import com.nanlagger.blacksails.utils.math.Position
import com.nanlagger.blacksails.views.GameScreen
import com.nanlagger.blacksails.views.actors.FieldActor
import com.nanlagger.blacksails.views.actors.hud.{HudCreator, LabelActor, ButtonActor, WindowActor}
import com.nanlagger.blacksails.views.actors.ships.{ShipIcon, TestShipActor}
import com.nanlagger.blacksails.views.utils.{FontLoader, TextureLoader}

/**
 * Created by NaNLagger on 08.05.15.
 * @author Stepan Lyashenko
 */
object TownWindow {
  def apply(town: Town): WindowActor = {
    val townWindow = new WindowActor {
      override def hide(): Unit = {
        super.hide()
        GameController.state = CtrlState.ClearFocus
      }
    }
    townWindow.setSize(700,500)
    townWindow.setPosition(Utils.SCREEN_WIDTH/2 - townWindow.getWidth/2,Utils.SCREEN_HEIGHT/2 - townWindow.getHeight/2)
    townWindow.title = town.name

    val groupsShip = UnitCreator.ShipType.values.map((x) => {
      shipWindow(town, x)
    })
    var margin = 0
    groupsShip.map((x) => {
      x.setPosition(margin, 0)
      margin += 100
      townWindow.addActor(x)
    })
    val fieldGroup = fieldWindow(town)
    fieldGroup.setPosition(0 + fieldGroup.getWidth/2, townWindow.getHeight - fieldGroup.getHeight/2)
    townWindow.addActor(fieldGroup)
    townWindow.hide()
    townWindow
  }

  private def shipWindow(town: Town, typeShip: UnitCreator.ShipType.Value) = {
    val groupShip = new Group()
    groupShip.addActor(new ShipIcon {
      setPosition(0, 80)
    })

    groupShip.addActor(new LabelActor {
      setPosition(0, 60)
      label = "Cost: " + UnitCreator.getCost(typeShip)
    })

    val buttonBuy = new ButtonActor
    buttonBuy.label = "Buy Ship"
    buttonBuy.setPosition(0,0)
    buttonBuy.setSize(100, 40)
    buttonBuy.addListener(new InputListener {
      override def touchDown (event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean = {
        true
      }

      override def touchUp (event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Unit = {
        town.buyShip(typeShip)
        town.actor.townWindow.hide()
        HudCreator.update()
      }
    })
    groupShip.addActor(buttonBuy)
    groupShip
  }

  private def fieldWindow(town: Town) = {
    val groupField = new Group()
    val pos = new Vector2(FieldEntities.getField(town.position).actor.getX, FieldEntities.getField(town.position).actor.getY)

    val actors = town.townFields.map(FieldEntities.getField(_))
      .map(new FieldActor(_))
      .map((x) => { x.fogActor.setVisible(false); x})
      .map((x) => { x.setPosition(x.getX - pos.x, x.getY - pos.y); x})
    actors.map(groupField.addActor(_))
    groupField.addActor(new Actor {
      setSize(150, 150)
      override def draw(batch: Batch, parentAlpha: Float) = {
        batch.draw(TextureLoader.listTexture("town"), getX, getY, getWidth, getHeight)
      }
    })

    class CoinActor(field: Field) extends Actor {
      private val v = Utils.positionToPoint(field.position)
      private val font = FontLoader.getFont(24, new Color(1, 1, 0 ,1))
      setPosition(v.x + 30, v.y + 30)
      setSize(30, 30)
      override def draw(batch: Batch, parentAlpha: Float) = {
        batch.draw(TextureLoader.listTexture("coin"), getX, getY, getWidth, getHeight)
        val bounds = font.getBounds("+" + field.income)
        font.draw(batch, "+" + field.income, getX + getWidth, getY + getHeight/2 + bounds.height/2)
      }
    }
    val coins = town.townFields
      .map(FieldEntities.getField(_))
      .map(new CoinActor(_))
      .map((x) => { x.setPosition(x.getX - pos.x, x.getY - pos.y); x})
    coins.map(groupField.addActor(_))

    groupField.setSize(360 * 0.7f, 620 * 0.7f)
    groupField.setScale(0.7f)
    groupField
  }
}
