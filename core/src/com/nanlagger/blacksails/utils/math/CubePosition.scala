package com.nanlagger.blacksails.utils.math

import scala.collection.mutable.ArrayBuffer

/**
 * Created by NaNLagger on 03.06.15.
 * @author Stepan Lyashenko
 */
case class CubePosition(x: Int, y: Int, z: Int) {
  def toOffset: OffsetPosition = this

  def toAxial: AxialPosition = this

  def distance(a: CubePosition, b: CubePosition): Int = {
    Array(Math.abs(a.x - b.x), Math.abs(a.y - b.y), Math.abs(a.z - b.z)).max
  }

  def neighbor: Array[CubePosition] = {
    val directions = Array(
      CubePosition(+1, -1,  0), CubePosition(+1,  0, -1), CubePosition( 0, +1, -1),
      CubePosition(-1, +1,  0), CubePosition(-1,  0, +1), CubePosition( 0, -1, +1)
    )
    directions.map(_.sum(this))
  }

  def diagonal_neighbor: Array[CubePosition] = {
    val diagonals = Array(
      CubePosition(+2, -1, -1), CubePosition(+1, +1, -2), CubePosition(-1, +2, -1),
      CubePosition(-2, +1, +1), CubePosition(-1, -1, +2), CubePosition(+1, -2, +1)
    )
    diagonals.map(_.sum(this))
  }

  def sum(other: CubePosition): CubePosition = {
    CubePosition(x + other.x, y + other.y, z + other.z)
  }

  def range(range: Int): Array[CubePosition] = {
    val results =
      for(dx <- -range to range;
          dy <- -range to range;
          dz <- -range to range
          if(dx + dy + dz == 0)
      )
        yield sum(CubePosition(dx, dy, dz))
    results.toArray
  }

  def reachable(movement: Int, blocked: Set[CubePosition]): Array[CubePosition] = {
    var visited = Set[CubePosition](this)
    val fringes = new ArrayBuffer[Array[CubePosition]]()
    fringes += Array(this)
    for(k <- 1 to movement) {
      val array = fringes(k - 1).flatMap((cube) => {
        cube.neighbor.filter((x) => {!visited.contains(x) && !blocked.contains(x)})
      })
      fringes += array
      visited ++= array.toSet
    }
    visited.toArray
  }
}

object CubePosition {
  implicit def cube2axial(cubePosition: CubePosition): AxialPosition = {
    AxialPosition(cubePosition.x, cubePosition.z)
  }
  implicit def cube2offset(cubePosition: CubePosition): OffsetPosition = {
    val x = cubePosition.x
    val z = cubePosition.z
    OffsetPosition(x + (z - (z&1)) / 2, z)
  }
}