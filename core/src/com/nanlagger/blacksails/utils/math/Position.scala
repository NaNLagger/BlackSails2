package com.nanlagger.blacksails.utils.math

/**
 * Created by NaNLagger on 01.04.15.
 * @author Stepan Lyashenko
 */
case class Position(row: Int, column: Int) {
  def this() = this(0,0)
  override def toString(): String = "(" + row + " " + column + ")"
}

/*object Position {
  //implicit def position2cube(position: Position) =>
}*/
