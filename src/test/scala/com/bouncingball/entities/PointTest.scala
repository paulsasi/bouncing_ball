package com.bouncingball.entities

import org.scalatest.flatspec.AnyFlatSpec

class PointTest extends AnyFlatSpec{

  "addition" should "produce the expected results - 1" in {
    val point1 = Point(0, 0)
    val point2 = Point(1, 1)

    val expected = Point(1, 1)
    assert(point1 + point2 == expected)
  }

  "addition" should "produce the expected results - 2" in {
    val point1 = Point(4, 3)
    val point2 = Point(-1, 1)

    val expected = Point(3, 4)
    assert(point1 + point2 == expected)
  }

  "multiplication" should "produce the expected results - 1" in {
    val point = Point(1, 1)
    val scalar = 2

    val expected = Point(2, 2)
    assert(point * scalar == expected)
  }

  "multiplication" should "produce the expected results - 2" in {
    val point = Point(5, -4)
    val scalar = -3

    val expected = Point(-15, 12)
    assert(point * scalar == expected)
  }

}
