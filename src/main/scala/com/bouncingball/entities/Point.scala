package com.bouncingball.entities

case class Point(x: Float, y: Float) {

  def +(other: Point): Point = Point(this.x + other.x, this.y + other.y)

  def *(scalar: Float): Point = Point(scalar * this.x, scalar * this.y)

  def *(other: Point): Point = Point(this.x * other.x, this.y * other.y)

}
