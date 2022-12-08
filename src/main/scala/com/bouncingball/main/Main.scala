package com.bouncingball.main

import com.bouncingball.Arguments
import com.bouncingball.entities._
import com.bouncingball.exceptions.BallPositionError
import com.bouncingball.interactor.{Displayer, Serializer}
import com.bouncingball.repository.{PrinterResource, PrinterStdOut}

import scala.concurrent.duration._
object Main {

  def main(args: Array[String]): Unit = {
    val arguments = Arguments.getArguments(args)

    // Repository
    val printer: PrinterResource = new PrinterStdOut()
    val ballBouncer = new BallBouncer(arguments, printer)

    val deadline = 5.minute.fromNow
    ballBouncer.run(deadline)
  }
}

class BallBouncer(args: Arguments, printer: PrinterResource) {

  private val Width = args.gridArgs.width
  private val Height = args.gridArgs.height

  private val Radius = args.ballArgs.radius

  private val Gravity = Point(args.gridArgs.gravity)

  private val DensityFloor = args.gridArgs.floorDensity
  private val DensityWall = args.gridArgs.wallDensity

  private val Fps = args.displayArgs.fps
  private val Dt = 1f / Fps

  private val Background = args.displayArgs.background
  private val serializer = Serializer(Background)

  private val displayer = Displayer(serializer, printer)

  def run(deadline: Deadline): Unit = {

    // Init grid
    val grid = Grid(Width, Height)

    // Init ball
    val initialPosition = Point.fromIntTuple(args.ballArgs.position)
    val initialBall = Ball(initialPosition, Radius)

    // Insert ball
    assertBallIsValid(initialBall)
    grid.insert(initialBall)

    displayer.show(grid)

    var position = initialPosition
    var velocity = Point(args.ballArgs.velocity)
    while (deadline.hasTimeLeft) {

      // Update velocity and position
      velocity += Gravity * Dt
      position += velocity * Dt

      // Check ball reached any corner
      if (position.y - Radius < 0) {
        velocity *= Point(1, -DensityFloor)
      } else if (position.x - Radius < 0) {
        velocity *= Point(-DensityWall, 1)
      } else if (position.x + Radius > Width) {
        velocity *= Point(-DensityWall, 1)
      }

      // Insert updated ball
      grid.flash()
      grid.insert(Ball(position, Radius))

      // Display updated grid
      displayer.clear(grid)
      displayer.show(grid)

      Thread.sleep(1000 / Fps)
    }

  }

  @throws[BallPositionError]
  private def assertBallIsValid(ball: Ball): Unit = {
    if (!ball.isWithin(Width, Height))
      throw new BallPositionError("Initial ball must be inside the grid!!")
  }

}
