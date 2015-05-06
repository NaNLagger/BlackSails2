package com.nanlagger.blacksails

import com.nanlagger.blacksails.utils.math.Position
import org.junit.Test
import org.junit.Assert._
import com.nanlagger.blacksails.entities.{FieldEntities, Graph}

/**
 * Created by NaNLagger on 03.04.15.
 * @author Stepan Lyashenko
 */
class GraphTest {
  @Test//(timeout = 1000)
  def testFindPosition(): Unit = {
    val death = 7
    val array = Graph.getPosition(Position(10,10), death)
    assertEquals(array.length, 1+ 6*(1 to death).sum)

  }
}
