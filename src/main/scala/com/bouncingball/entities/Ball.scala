package com.bouncingball.entities

import math.pow

case class Ball(center: Point, radius: Int) {

  def contains(point: Point): Boolean = {
    pow(point.x - this.center.x, 2) + pow(point.y - this.center.y, 2) <= pow(
      this.radius,
      2)
  }

  def isWithin(width: Int, height: Int): Boolean = {
    //TODO: Test me and do integration testing

    val isLeftWithin = center.x - radius >= 0
    val isRightWithin = center.x + radius <= width
    val isDownWithin = center.y - radius >= 0
    val isUpWithin = center.y + radius <= height

    isLeftWithin && isRightWithin && isDownWithin && isUpWithin

  }
}
