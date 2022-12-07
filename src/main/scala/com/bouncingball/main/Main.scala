package com.bouncingball.main

import com.bouncingball.entities._
import com.bouncingball.interactor.Serializer
import com.bouncingball.repository
import com.bouncingball.repository.{Displayer, PrinterResource, PrinterStdOut}

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
  private val printer: PrinterResource = new PrinterStdOut()
  private val displayer = repository.Displayer(serializer, printer)

  displayer.show(grid)

  private val FPS = 60

  private val velocity = Point(1, 0)

  print("a")
  (1 to 30).foreach { aFrame =>
    val nextBall = Ball(ballCenterBeg + velocity * aFrame, ballRadius)

    grid.flash()
    grid.insert(nextBall)

    displayer.clear(grid)
    displayer.show(grid)

    Thread.sleep(1000 / FPS)
  }

}
