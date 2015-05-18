package com.nanlagger.blacksails.entities

import com.badlogic.gdx.utils.Json
import com.nanlagger.blacksails.entities.game.Field
import com.nanlagger.blacksails.entities.game.Field.IncomeType
import com.nanlagger.blacksails.utils.math.Position

import scala.util.Random
import org.json.JSONArray
/**
 * Created by NaNLagger on 31.03.15.
 * @author Stepan Lyashenko
 */
object FieldEntities {
  private var fields: Array[Array[Field]] = null

  def apply(size: Int) = {
    fields = Array.ofDim[Field](size, size)
    for(i <- 0 until fields.length; j <- 0 until fields(0).length) {
      fields(i)(j) = Field(i, j, if(Random.nextInt()%2 == 1) Field.TypeField.LAND else Field.TypeField.OCEAN)
    }
  }

  def apply(rows: Int, columns: Int) = {
    fields = Array.ofDim[Field](rows, columns)
    for(i <- 0 until fields.length; j <- 0 until fields(0).length) fields(i)(j) = Field(i, j)
  }

  def apply(map: String): Unit = {
    val main = new JSONArray(map)
    val rows = main.length()
    val columns = main.getJSONArray(0).length()
    fields = Array.ofDim[Field](rows, columns)
    for(i <- 0 until fields.length; j <- 0 until fields(0).length) {
      val jsonObject = main.getJSONArray(i).getJSONObject(j)
      val fieldType = jsonObject.getString("typeField")
      val incomeType = jsonObject.getString("incomeType")
      val iT = IncomeType.values.find(_.toString == incomeType)
      fields(i)(j) = new Field(Position(i, j), if(fieldType.equals("LAND")) Field.TypeField.LAND else Field.TypeField.OCEAN, iT.getOrElse(IncomeType.LAND_GRASS))
    }
  }

  def rows = fields.length
  def columns = fields(0).length
  def contains(position: Position) = {
    position.row >= 0 && position.row < fields.length && position.column >= 0 && position.column < fields(0).length
  }
  def getAll(): Array[Field] = {
    for(row <- fields; item <- row) yield item
  }
  def getField(position: Position) = {
    if(contains(position))
      fields(position.row)(position.column)
    else
      null;
  }
  def getField(row: Int, column: Int) = fields(row)(column)
  @deprecated("not implemented")
  def getFields(position: Position, range: Int): Array[Field] = null
  @deprecated("not implemented")
  def getFields(row: Int, column: Int, range: Int): Array[Field] = null
  def resetVision(visionField: Array[Position]) = {
    for(i <- 0 until fields.length; j <- 0 until fields(0).length) {
      fields(i)(j).setVision(false)
    }
    visionField.filter(contains(_)).map(getField(_).setVision(true))
    UnitEntities.resetUnit()
  }
}

