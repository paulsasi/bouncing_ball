package com.bouncingball.entities

import scala.annotation.targetName

case class Point(x: Int, y: Int) {

  @targetName("addition")
  def +(other: Point): Point = Point(this.x + other.x, this.y + other.y)

  @targetName("multiplication")
  def *(scalar: Int): Point = Point(scalar * this.x, scalar * this.y)
}
