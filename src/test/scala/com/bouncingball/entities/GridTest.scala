package com.bouncingball.entities

import org.scalatest.flatspec.AnyFlatSpec

import scala.collection.mutable
class GridTest extends AnyFlatSpec {

  "Grid" should "correctly initialize object - 1" in {
    val inputWidth = 2
    val inputHeight = 3

    val cells = mutable.ArraySeq(
      mutable.ArraySeq(
        Cell(Point(0, 0), CellStatus.EMPTY),
        Cell(Point(1, 0), CellStatus.EMPTY)
      ),
      mutable.ArraySeq(
        Cell(Point(0, 1), CellStatus.EMPTY),
        Cell(Point(1, 1), CellStatus.EMPTY)
      ),
      mutable.ArraySeq(
        Cell(Point(0, 2), CellStatus.EMPTY),
        Cell(Point(1, 2), CellStatus.EMPTY)
      )
    )

    val expected = new Grid(cells = cells)

    val output = Grid(inputWidth, inputHeight)

    assert(expected == output)
  }

  "Grid" should "correctly initialize object - 2" in {
    val inputWidth = 0
    val inputHeight = 2

    val expected = new Grid(cells = mutable.ArraySeq())

    val output = Grid(inputWidth, inputHeight)

    assert(expected == output)
  }

}
