package com.bouncingball.entities

case class Point(x: Int, y: Int) {

  def +(other: Point): Point = Point(this.x + other.x, this.y + other.y)

  def *(scalar: Int): Point = Point(scalar * this.x, scalar * this.y)
}
