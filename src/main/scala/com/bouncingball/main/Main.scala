package com.bouncingball.main

import com.bouncingball.entities.*
import com.bouncingball.interactor.Serializer
import com.bouncingball.repository
import com.bouncingball.repository.Displayer

object Main extends App {

  private val background = '.'

  private val gridWidth: Int = 64
  private val gridHeight: Int = 16
  val grid = Grid(gridWidth, gridHeight)

  private val ballCenterBeg = Point(5, 5)
  private val ballRadius = 3
  private val ball = Ball(ballCenterBeg, ballRadius)
  grid.insert(ball)

  private val serializer = Serializer(background)
  private val displayer = repository.Displayer(serializer)

  displayer.show(grid)

  private val FPS = 15

  private val velocity = Point(1, 0)

  (1 to 30).foreach { aFrame =>
    val nextBall = Ball(ballCenterBeg + velocity * aFrame, ballRadius)

    grid.flash()
    grid.insert(nextBall)

    displayer.clear(grid)
    displayer.show(grid)

    Thread.sleep(1000 / FPS)
  }

}
