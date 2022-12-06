package com.bouncingball.main

import com.bouncingball.entities._
object Main extends App {

  private val gridWidth: Int = 128
  private val gridHeight: Int = 32
  val grid = Grid(gridWidth, gridHeight)

  private val ball = Ball(Point(20, 15), 15)
  grid.insert(ball)

  println(grid.serialize())

}
