package com.bouncingball.entities

case class Point(x: Int, y: Int) {

  def move(deltaX: Int = 0, deltaY: Int = 0): Point = {
    Point(x + deltaX, y + deltaY)
  }
}
