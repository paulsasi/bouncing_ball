package com.bouncingball.entities

import org.scalatest.flatspec.AnyFlatSpec

import scala.collection.mutable

class GridTest extends AnyFlatSpec {

  "Grid" should "correctly initialize object - 1" in {
    val inputWidth = 2
    val inputHeight = 4

    val expectedCells = mutable.ArraySeq(
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
      ),
      mutable.ArraySeq(
        Cell(Point(0, 3), CellStatus.EMPTY),
        Cell(Point(1, 3), CellStatus.EMPTY)
      )
    )
    val expected = new Grid(cells = expectedCells)

    val output = Grid(inputWidth, inputHeight)

    assert(expected == output)
  }

  "Grid" should "correctly initialize object - 2" in {
    val inputWidth = 0
    val inputHeight = 2

    assertThrows[RuntimeException](Grid(inputWidth, inputHeight))

  }

  "Insert ball" should "produce expected output - 1" in {
    val grid = Grid(6, 6)
    val inputBall = Ball(Point(3, 3), 2)

    val expected = new Grid(
      mutable.ArraySeq(
        mutable.ArraySeq(
          Cell(Point(0, 0), CellStatus.EMPTY),
          Cell(Point(1, 0), CellStatus.EMPTY),
          Cell(Point(2, 0), CellStatus.EMPTY),
          Cell(Point(3, 0), CellStatus.EMPTY),
          Cell(Point(4, 0), CellStatus.EMPTY),
          Cell(Point(5, 0), CellStatus.EMPTY)
        ),
        mutable.ArraySeq(
          Cell(Point(0, 1), CellStatus.EMPTY),
          Cell(Point(1, 1), CellStatus.EMPTY),
          Cell(Point(2, 1), CellStatus.EMPTY),
          Cell(Point(3, 1), CellStatus.ACTIVE),
          Cell(Point(4, 1), CellStatus.EMPTY),
          Cell(Point(5, 1), CellStatus.EMPTY)
        ),
        mutable.ArraySeq(
          Cell(Point(0, 2), CellStatus.EMPTY),
          Cell(Point(1, 2), CellStatus.EMPTY),
          Cell(Point(2, 2), CellStatus.ACTIVE),
          Cell(Point(3, 2), CellStatus.ACTIVE),
          Cell(Point(4, 2), CellStatus.ACTIVE),
          Cell(Point(5, 2), CellStatus.EMPTY)
        ),
        mutable.ArraySeq(
          Cell(Point(0, 3), CellStatus.EMPTY),
          Cell(Point(1, 3), CellStatus.ACTIVE),
          Cell(Point(2, 3), CellStatus.ACTIVE),
          Cell(Point(3, 3), CellStatus.ACTIVE),
          Cell(Point(4, 3), CellStatus.ACTIVE),
          Cell(Point(5, 3), CellStatus.ACTIVE)
        ),
        mutable.ArraySeq(
          Cell(Point(0, 4), CellStatus.EMPTY),
          Cell(Point(1, 4), CellStatus.EMPTY),
          Cell(Point(2, 4), CellStatus.ACTIVE),
          Cell(Point(3, 4), CellStatus.ACTIVE),
          Cell(Point(4, 4), CellStatus.ACTIVE),
          Cell(Point(5, 4), CellStatus.EMPTY)
        ),
        mutable.ArraySeq(
          Cell(Point(0, 5), CellStatus.EMPTY),
          Cell(Point(1, 5), CellStatus.EMPTY),
          Cell(Point(2, 5), CellStatus.EMPTY),
          Cell(Point(3, 5), CellStatus.ACTIVE),
          Cell(Point(4, 5), CellStatus.EMPTY),
          Cell(Point(5, 5), CellStatus.EMPTY)
        )
      )
    )

    grid.insert(inputBall)

    assert(grid == expected)
  }

  "Insert ball" should "produce expected output - 2" in {
    val grid = Grid(6, 6)
    val inputBall = Ball(Point(5, 5), 3)

    assertThrows[RuntimeException](grid.insert(inputBall))
  }

  "Flash" should "produce expected output - 1" in {
    val grid = Grid(4, 4)
    val inputBall = Ball(Point(2, 2), 1)

    val expected = new Grid(
      cells = mutable.ArraySeq(
        mutable.ArraySeq(
          Cell(Point(0, 0), CellStatus.EMPTY),
          Cell(Point(1, 0), CellStatus.EMPTY),
          Cell(Point(2, 0), CellStatus.EMPTY),
          Cell(Point(3, 0), CellStatus.EMPTY)
        ),
        mutable.ArraySeq(
          Cell(Point(0, 1), CellStatus.EMPTY),
          Cell(Point(1, 1), CellStatus.EMPTY),
          Cell(Point(2, 1), CellStatus.EMPTY),
          Cell(Point(3, 1), CellStatus.EMPTY)
        ),
        mutable.ArraySeq(
          Cell(Point(0, 2), CellStatus.EMPTY),
          Cell(Point(1, 2), CellStatus.EMPTY),
          Cell(Point(2, 2), CellStatus.EMPTY),
          Cell(Point(3, 2), CellStatus.EMPTY)
        ),
        mutable.ArraySeq(
          Cell(Point(0, 3), CellStatus.EMPTY),
          Cell(Point(1, 3), CellStatus.EMPTY),
          Cell(Point(2, 3), CellStatus.EMPTY),
          Cell(Point(3, 3), CellStatus.EMPTY)
        )
      ))

    grid.insert(inputBall)
    grid.flash()

    assert(grid == expected)
  }

}
