package com.nanlagger.blacksails.entities

import com.nanlagger.blacksails.utils.math.Position

import scala.util.Random

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

  def rows = fields.length
  def columns = fields(0).length
  def contains(position: Position) = {
    position.row >= 0 && position.row < fields.length && position.column >= 0 && position.column < fields(0).length
  }
  def getAll(): Array[Field] = {
    for(row <- fields; item <- row) yield item
  }
  def getField(position: Position) = fields(position.row)(position.column)
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

