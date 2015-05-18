package com.nanlagger.blacksails.entities

import com.nanlagger.blacksails.entities.game.Field
import Field.TypeField
import com.nanlagger.blacksails.utils.math
import com.nanlagger.blacksails.utils.math.Position
import com.sun.org.apache.bcel.internal.generic.LAND

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
 * Created by NaNLagger on 03.04.15.
 * @author Stepan Lyashenko
 */
object Graph {
  def getPosition(startPosition: Position, range: Int): Array[Position] = {
    findPosition(startPosition, range).toArray
  }

  private def findPosition(pos: Position, depth: Int = 1): Set[Position] = {
    val row = pos.row
    val column = pos.column
    val matrix = (for(i <- (row - 1) to (row + 1); j <- (column - 1) to (column + 1)) yield Position(i,j) ).toSet
    val setPosition: Set[Position] = {
      if (row % 2 == 1)
        matrix &~ Set(Position(row - 1, column - 1), Position(row + 1, column - 1))
      else
        matrix &~ Set(Position(row - 1, column + 1), Position(row + 1, column + 1))
    }
    if(depth == 1) {
      setPosition
    }
    else
      setPosition.flatMap(findPosition(_, depth - 1))
  }

  def createGraph(startPosition: Position, range: Int): Graph = {
    val graph = new Graph(range)
    addVertexes(graph, startPosition, range)
    graph
  }

  private def addVertexes(graph: Graph, pos: Position, depth: Int = 1): Unit = {
    val row = pos.row
    val column = pos.column
    if(FieldEntities.contains(pos)) {
      val matrix = (for(i <- (row - 1) to (row + 1); j <- (column - 1) to (column + 1)) yield Position(i,j) ).toSet
      var setPosition: Set[Position] = {
        if (row % 2 == 1)
          matrix &~ Set(Position(row - 1, column - 1), Position(row + 1, column - 1))
        else
          matrix &~ Set(Position(row - 1, column + 1), Position(row + 1, column + 1))
      }
      setPosition = for(item <- setPosition; if(FieldEntities.contains(item) && !UnitEntities.isUnit(item))) yield {
        val field = FieldEntities.getField(item)
        var w: Int = field.typeField match {
          case TypeField.LAND => Int.MaxValue
          case TypeField.OCEAN => 1
          case _ => Int.MaxValue
        }
        if(FieldEntities.getField(pos).typeField == TypeField.LAND)
          w = Int.MaxValue
        graph.setLink(pos, item, w)
        item
      }
      if(depth > 1)
        setPosition.map(addVertexes(graph, _, depth - 1))
    }
  }
}

class Graph(size: Int) {
  private val matrix = Array.ofDim[Int](1+ 6*(1 to size).sum, 1+ 6*(1 to size).sum)
  private val indexes = new mutable.HashMap[Position, Int]()
  private var current = 0
  private var state = false
  private val result = new mutable.HashMap[Position, Int]()
  private val resultP = new mutable.HashMap[Position, Position]()
  private val vectorP = new Array[Int](1+ 6*(1 to size).sum)
  private var startPosition = new Position()

  for(i <- 0 until matrix.length; j <- 0 until matrix.length) {
    if(i!=j)
      matrix(i)(j) = Int.MaxValue
  }

  private def setLink(s: Position, f: Position, w: Int) = {
    if(!indexes.contains(s)) {
      indexes += s -> current
      current += 1
    }
    if(!indexes.contains(f)) {
      indexes += f -> current
      current += 1
    }
    matrix(indexes(s))(indexes(f)) = w
    matrix(indexes(f))(indexes(s)) = w
  }

  def getWays(pos: Position): Map[Position, Int] = {
    if(!(state && startPosition == pos)) {
      startPosition = pos
      val ways = Dijkstra(indexes(pos))
      val reverseIndexes = indexes.map(m => m._2 -> m._1)
      for (i <- 0 until indexes.size) {
        result += reverseIndexes(i) -> ways(i)
        resultP += reverseIndexes(i) -> reverseIndexes(vectorP(i))
      }
      state = true
      result.toMap
    } else {
      result.toMap
    }
  }

  def getWay(start: Position, finish: Position): Array[Position] = {
    var current = finish
    val res = new ArrayBuffer[Position]()
    if(!resultP.contains(start) || !resultP.contains(finish)) {
      return Array()
    }
    res += finish
    while (current != start) {
      current = resultP(current)
      res += current
    }
    res.reverse.toArray
  }

  private def Dijkstra(start: Int): Array[Int] = {
    val size = indexes.size
    var min: Int = Int.MaxValue
    var temp: Int = 0
    val S: Array[Boolean] = new Array[Boolean](size)
    var w: Int = start
    val D: Array[Int] = matrix(w).clone()
    for(k <- 0 until size) {
      min = Int.MaxValue
      for(i <- 0 until size; if(!S(i) && D(i) < min)) {
        w = i
        min = D(i)
      }
      S(w) = true
      for(i <- 0 until size; if(!S(i))) {
        temp = if(D(w) == Int.MaxValue || matrix(w)(i) == Int.MaxValue) Int.MaxValue else D(w) + matrix(w)(i)
        if (D(i) > temp) {
          D(i) = temp
          vectorP(i) = w
        }
      }
    }
    D
  }
}
