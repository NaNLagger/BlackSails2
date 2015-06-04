package com.nanlagger.blacksails.utils.math

import com.badlogic.gdx.math.Vector2

import scala.collection.mutable.ArrayBuffer

/**
 * Created by NaNLagger on 03.06.15.
 * @author Stepan Lyashenko
 */
case class CubePosition(x: Int, y: Int, z: Int) {

  def toOffset: OffsetPosition = this

  def toAxial: AxialPosition = this

  def distance(b: CubePosition): Int = {
    Array(Math.abs(x - b.x), Math.abs(y - b.y), Math.abs(z - b.z)).max
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
  //slow (recommended use reachable(range, Set()) for speed )
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
    val fringes = new ArrayBuffer[Set[CubePosition]]()
    fringes += Set(this)
    for(k <- 1 to movement) {
      val array = fringes(k - 1).flatMap((cube) => {
        cube.neighbor.filter((x) => {!visited.contains(x) && !blocked.contains(x)})
      })
      fringes += array
      visited ++= array.toSet
    }
    visited.toArray
  }

  private def lerp(b: CubePosition, t: Float): CubePosition = {
    FloatCube(x + (b.x - x) * t, y + (b.y - y) * t, z + (b.z - z) * t)
  }

  def line(b: CubePosition): Array[CubePosition] = {
    val N = distance(b)
    val result = for(i <- 0 to N) yield lerp(b, 1.0f / N * i)
    result.toArray
  }

  def toPoint: Vector2 = {
    val hex = this.toAxial
    val x: Float = Math.sqrt(3) * (hex.q + (hex.r.toFloat / 2f)) toFloat
    val y: Float = 3f / 2f * hex.r
    new Vector2(x, y)
  }

  def toPoint(size: Float): Vector2 = {
    this.toPoint.scl(size)
  }
}

case class FloatCube(x: Float, y: Float, z: Float) {

  def round: CubePosition = {
    var rx = Math.round(x)
    var ry = Math.round(y)
    var rz = Math.round(z)

    val x_diff = Math.abs(rx - x)
    val y_diff = Math.abs(ry - y)
    val z_diff = Math.abs(rz - z)

    if(x_diff > y_diff && x_diff > z_diff)
      rx = -ry-rz
    else if(y_diff > z_diff)
      ry = -rx-rz
    else
      rz = -rx-ry
    CubePosition(rx, ry, rz)
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
  implicit def cube2float(cubePosition: CubePosition): FloatCube = {
    FloatCube(cubePosition.x, cubePosition.y, cubePosition.z)
  }
  implicit def float2cube(floatCube: FloatCube): CubePosition = {
    floatCube.round
  }

  def pixel_to_cube(x: Float, y: Float, size: Float): CubePosition = {
    val q = (x * Math.sqrt(3) / 3f - y / 3f) / size toFloat
    val r = y * 2f / 3f / size
    FloatCube(q, -q-r, r)
  }
}