package com.nanlagger.blacksails.utils.math

/**
 * Created by NaNLagger on 01.04.15.
 * @author Stepan Lyashenko
 */
class Position(val row: Int, val column: Int) {
  def this() = this(0,0)

  override def toString(): String = "(" + row + " " + column + ")"
  def == (other: Position): Boolean = row == other.row && column == other.column
  override def equals(o: Any): Boolean = {
    o match {
      case that: Position => row == that.row && column == that.column
      case _ => false
    }
  }
  override def hashCode = toString().hashCode
}

object Position {
  def apply(row: Int, column: Int) = new Position(row, column)
  def apply() = new Position()
}
