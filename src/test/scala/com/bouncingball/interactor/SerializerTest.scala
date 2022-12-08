package com.bouncingball.interactor

import com.bouncingball.entities.{Ball, Grid, Point}
import org.scalatest.flatspec.AnyFlatSpec

class SerializerTest extends AnyFlatSpec{

  "Serialize" should "produce expected output - 1" in {

    val grid = Grid(10, 8)

    val expected =
      """XXXXXXXXXX
        |XXXXXXXXXX
        |XXXXXXXXXX
        |XXXXXXXXXX
        |""".stripMargin

    val output = Serializer(background = 'X').serialize(grid)

    assert(output == expected)
  }

  "Serialize" should "produce expected output - 2" in {

    val grid = Grid(10, 8)
    grid.insert(Ball(Point(3, 3), 2))

    val expected =
      """..........
        |.._C_.....
        |.^CCC^....
        |...^......
        |""".stripMargin

    val output = Serializer(background = '.').serialize(grid)

    assert(output == expected)

  }

}
