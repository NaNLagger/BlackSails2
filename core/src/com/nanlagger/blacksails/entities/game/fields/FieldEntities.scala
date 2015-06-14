package com.nanlagger.blacksails.entities.game.fields

import com.nanlagger.blacksails.utils.math.OffsetPosition
/**
 * Created by NaNLagger on 31.03.15.
 * @author Stepan Lyashenko
 */
object FieldEntities {

  private var fields: Array[Array[Field]] = null

  def rows = fields(0).length
  def columns = fields.length

  def apply(size: Int) = {
    fields = GeneratorMap.generate(size, size)
  }

  def apply(columns: Int, rows: Int) = {
    fields = GeneratorMap.generate(columns, rows)
  }

  def contains(position: OffsetPosition) = {
    position.row >= 0 && position.row < rows && position.col >= 0 && position.col < columns
  }

  def getAll: Array[Field] = {
    for(column <- fields; item <- column) yield item
  }

  def getField(position: OffsetPosition) = {
    if(contains(position))
      fields(position.col)(position.row)
    else
      null
  }

  def getField(row: Int, column: Int) = fields(row)(column)
}

