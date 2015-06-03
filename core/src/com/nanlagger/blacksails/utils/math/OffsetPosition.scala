package com.nanlagger.blacksails.utils.math

/**
 * Created by NaNLagger on 03.06.15.
 * @author Stepan Lyashenko
 */
case class OffsetPosition(col: Int, row: Int) {
  def toCube: CubePosition = this
}

object OffsetPosition {
  //odd-r
  implicit def offset2cube(offsetPosition: OffsetPosition): CubePosition = {
    val col = offsetPosition.col
    val row = offsetPosition.row
    val x = col - (row - (row&1)) / 2
    val z = row
    val y = -x-z
    CubePosition(x, y, z)
  }
}
