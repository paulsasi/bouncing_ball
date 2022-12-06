package com.bouncingball.main

import com.bouncingball.entities._
import com.bouncingball.interactor.Serializer
object Main extends App {

  private val gridWidth: Int = 64
  private val gridHeight: Int = 32
  val grid = Grid(gridWidth, gridHeight)

  private val ball = Ball(Point(15, 15), 15)
  grid.insert(ball)

  println(Serializer().serialize(grid))

}


