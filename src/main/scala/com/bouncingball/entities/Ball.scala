package com.bouncingball.entities

import math.pow

case class Ball(center: Point, radius: Int) {
  
  def contains(point: Point): Boolean = {
    pow(point.x - this.center.x, 2) + pow(point.y - this.center.y, 2) <= pow(
      this.radius,
      2)
  }
}
