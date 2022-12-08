package com.bouncingball.main

import com.bouncingball.entities._
import com.bouncingball.interactor.Serializer
import com.bouncingball.repository.{Displayer, PrinterResource, PrinterStdOut}

object Main extends App {

  private val background = '.'

  private val gridWidth: Int = 136
  private val gridHeight: Int = 64
  val grid = Grid(gridWidth, gridHeight)

  private var position = Point(20, 44)
  private val ballRadius = 20
  // Assert initial ball is in grid
  try {
    assert(position.x - ballRadius >= 0)
    assert(position.x + ballRadius <= gridWidth)
    assert(position.y - ballRadius >= 0)
    assert(position.y + ballRadius <= gridHeight)
  } catch {
    case _: AssertionError =>
      throw new RuntimeException(
        "Initial ball must be" +
          "inside the grid!!")
  }

  private var ball = Ball(position, ballRadius)
  grid.insert(ball)

  private val serializer = Serializer(background)
  private val printer: PrinterResource = new PrinterStdOut()
  private val displayer = Displayer(serializer, printer)

  displayer.show(grid)

  private val FPS = 15
  private val DT: Float = 1f / FPS

  private val DENSITY_CEIL = 0.95f
  private val DENSITY_WALL = 0.8f

  private val gravity = Point(0, -50f)
  private var velocity = Point(50, -1)

  while (true) {
    velocity = velocity + (gravity * DT)
    position = position + velocity * DT

    if (position.y - ballRadius < 0) {
      velocity *= Point(1, -DENSITY_CEIL)
    }

    if (position.x - ballRadius < 0) {
      velocity *= Point(-DENSITY_WALL, 1)
    }
    if (position.x + ballRadius > gridWidth) {
      velocity *= Point(-DENSITY_WALL, 1)
    }

    ball = Ball(position, ballRadius)

    grid.flash()
    grid.insert(ball)

    displayer.clear(grid)
    displayer.show(grid)

    Thread.sleep(1000 / FPS)
  }

}
