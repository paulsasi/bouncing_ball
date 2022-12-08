package com.bouncingball.main

import com.bouncingball.repository.PrinterResource
import com.bouncingball.{Arguments, BallArgs, DisplayArgs, GridArgs}
import org.scalamock.scalatest.MockFactory
import org.scalatest.flatspec.AnyFlatSpec

import scala.concurrent.duration._

class BallBouncerTest extends AnyFlatSpec with MockFactory {

  "BallBouncer" should "produce the expected results" in {

    val testArgs = Arguments(
      GridArgs(
        width = 6,
        height = 6,
        gravity = (0, -1)
      ),
      BallArgs(
        position = (1, 4),
        radius = 1,
        velocity = (1, 0)
      ),
      DisplayArgs(
        background = 'X',
        fps = 1
      )
    )
    val deadline = 3.seconds.fromNow

    val frame1 = "_C_XXX\nX^XXXX\nXXXXXX\n"
    val frame2 = "XX_XXX\nX^C^XX\nXXXXXX\n"
    val frame3 = "XXXXXX\nXXX_XX\nXX^C^X\n"
    val frame4 = "XXXXXX\nXXXXXX\nXXXXXX\n"

    val printer = mock[PrinterResource]
    (printer.print _)
      .expects(frame1)
    (printer.print _)
      .expects(frame2)
    (printer.print _)
      .expects(frame3)
    (printer.print _)
      .expects(frame4)

    (printer.print _)
      .expects("\u001b[3A")
      .repeat(3)
    (printer.print _)
      .expects("\u001b[2K")
      .repeat(3)

    val ballBouncer = new BallBouncer(testArgs, printer)
    ballBouncer.run(deadline)

  }

}
