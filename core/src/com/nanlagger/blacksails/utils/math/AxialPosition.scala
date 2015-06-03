package com.nanlagger.blacksails.utils.math

/**
 * Created by NaNLagger on 03.06.15.
 * @author Stepan Lyashenko
 */
case class AxialPosition(q: Int, r: Int) {
  def toCube: CubePosition = this
}

object AxialPosition {
  implicit def axial2cube(axialPosition: AxialPosition): CubePosition = {
    CubePosition(axialPosition.q, axialPosition.r, -axialPosition.q - axialPosition.r)
  }
}