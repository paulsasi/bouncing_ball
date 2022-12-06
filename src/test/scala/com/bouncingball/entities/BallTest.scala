package com.bouncingball.entities

import org.scalatest.flatspec.AnyFlatSpec

class BallTest extends AnyFlatSpec {

  "contains" should "produce the expected output - 1" in {
    val inputBall = Ball(Point(0, 0), 3)
    val inputPoint = Point(5, 5)

    assert(!inputBall.contains(inputPoint))
  }

  "contains" should "produce the expected output - 2" in {
    val inputBall = Ball(Point(0, 0), 3)
    val inputPoint = Point(0, 0)

    assert(inputBall.contains(inputPoint))
  }

  "contains" should "produce the expected output - 3" in {
    val inputBall = Ball(Point(0, 0), 3)
    val inputPoint = Point(3, 0)

    assert(inputBall.contains(inputPoint))
  }

  "contains" should "produce the expected output - 4" in {
    val inputBall = Ball(Point(3, 3), 3)
    val inputPoint = Point(6, 3)

    assert(inputBall.contains(inputPoint))
  }

}
