package com.nanlagger.blacksails.utils

import com.badlogic.gdx.math.Vector2
import com.nanlagger.blacksails.utils.math.Position

/**
 * Created by NaNLagger on 01.04.15.
 * @author Stepan Lyashenko
 */
object Utils {
  val SCREEN_WIDTH = 1024
  val SCREEN_HEIGHT = 578
  val widthField: Int = 150
  val heightField: Int = 150
  val CONS_SCALE: Float = 0.76f
  val CONS_SCALE_Y: Float = 1.01f

  def positionToPoint(pos: Position): Vector2 = {
    val x = pos.column * widthField + (if(pos.row%2 == 1) widthField/2 else 0)
    val y = pos.row * heightField * CONS_SCALE
    new Vector2(x, y)
  }

  def pointToPosition(point: Vector2): Position = {
    val row: Int = point.y / (heightField * CONS_SCALE) toInt
    val column: Int = (point.x - (if(row%2 == 1) widthField/2 else 0)) / widthField toInt;
    Position(row, column)
  }
}
