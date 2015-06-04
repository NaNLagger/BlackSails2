package com.nanlagger.blacksails.utils.math

import scala.collection.mutable

/**
 * Created by NaNLagger on 05.06.15.
 * @author Stepan Lyashenko
 */
class Graph(mStart: CubePosition, mSize: Int, mBlocked: Set[CubePosition], mCost: (CubePosition, CubePosition) => Int) {
  private val cameFrom = mutable.HashMap[CubePosition, Option[CubePosition]]() //вектор для востановления пути
  private val costSoFar = mutable.HashMap[CubePosition, Int]() //стоимости вершин
  dijkstra(mStart, mSize, mBlocked, mCost)

  def getWays: Map[CubePosition, Int] = costSoFar.toMap

  def getWay(start: CubePosition, finish: CubePosition): Array[CubePosition] = {
    var current = finish
    val res = new mutable.ArrayBuffer[CubePosition]()
    if(cameFrom.contains(start) && cameFrom.contains(finish)) {
      res += finish
      while (current != start) {
        current = cameFrom(current).get
        res += current
      }
      res.reverse.toArray
    } else {
      Array()
    }
  }

  private def dijkstra(start: CubePosition, movement: Int, blocked: Set[CubePosition], cost: (CubePosition, CubePosition) => Int): Array[CubePosition] = {
    var visited = Set[CubePosition](start) //посещенные вершины
    costSoFar(start) = 0
    cameFrom(start) = None

    val fringes = new mutable.ArrayBuffer[Set[CubePosition]]()
    fringes += Set(start)

    for(k <- 1 to movement) {
      val ring = fringes(k - 1).flatMap((current) => {
        val array = current.neighbor.filter((x) => {!visited.contains(x) && !blocked.contains(x)})
        for (next <- array) {
          val new_cost = costSoFar(current) + cost(current, next)
          if(new_cost < costSoFar.getOrElse(next, Int.MaxValue)) {
            costSoFar(next) = new_cost
            cameFrom(next) = Some(current)
          }
        }
        visited += current
        array
      })
      fringes += ring
    }
    visited.toArray
  }
}
