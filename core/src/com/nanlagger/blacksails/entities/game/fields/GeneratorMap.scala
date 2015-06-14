package com.nanlagger.blacksails.entities.game.fields

import com.nanlagger.blacksails.utils.math.OffsetPosition

import scala.util.Random

/**
 * Created by NaNLagger on 07.06.15.
 * @author Stepan Lyashenko
 */
object GeneratorMap {

  def generate(columns: Int, rows: Int): Array[Array[Field]] = {
    val fields = Array.ofDim[Field](columns, rows)
    for(i <- 0 until columns; j <- 0 until rows) {
      fields(i)(j) = generateField(OffsetPosition(i, j))
    }
    fields
  }

  private def generateField(offsetPosition: OffsetPosition): Field = {
    val idType: Int = Math.abs(Random.nextInt()) % Field.TypeIncome.size + 1
    val incomeType = Field.TypeIncome.getType(idType)
    Field.apply(offsetPosition, incomeType)
  }

}
