package com.bouncingball.entities

import scala.annotation.targetName
import scala.collection.mutable

case class Grid(cells: mutable.ArraySeq[mutable.ArraySeq[Cell]]) {

  private val nRows: Int = this.cells.length
  private val nCols: Int = this.cells(0).length

  private def getCells: mutable.ArraySeq[mutable.ArraySeq[Cell]] = this.cells

  @targetName("equality")
  def ==(other: Grid): Boolean = this.cells == other.getCells

  def insert(ball: Ball): Unit = {

    // Boundary box
    val xBoundaryMin = ball.center.x - ball.radius
    val xBoundaryMax = ball.center.x + ball.radius
    val yBoundaryMin = ball.center.y - ball.radius
    val yBoundaryMax = ball.center.y + ball.radius

    if (xBoundaryMin < 0 ||  this.nCols <= xBoundaryMax || yBoundaryMin < 0 || this.nRows <= yBoundaryMax) {
      throw new RuntimeException("Cannot insert ball outside grid!! Please " +
        "select a valid ball starting point")
    }


    for (x <- xBoundaryMin to xBoundaryMax) {
      for (y <- yBoundaryMin to yBoundaryMax) {
        val candidateCell = this.cells(y)(x)

        if (ball.contains(candidateCell.point))
          this.cells(y).update(x, candidateCell.copy(status = CellStatus.ACTIVE))

      }
    }


  }

  def serialize(): String = {
    this.cells.reverse
      .map { row =>
        val rowStr = row.map { cell =>
          cell match
            case Cell(_, CellStatus.EMPTY) => "."
            case Cell(_, CellStatus.ACTIVE) => "@"
            case _ => throw new RuntimeException("Impossible to serialize cell" +
              "status.")
        }
        rowStr.reduce((x, y) => x + y) + sys.props("line.separator")
      }.reduce((x, y) => x + y)
  }

}

object Grid {

  def apply(width: Int, height: Int): Grid = {

    if (width == 0 || height == 0) throw new RuntimeException("Grid dimensions" +
      "cannot equal 0")

    val cellsArray = new Array[Array[Cell]](height)
    (0 until height).foreach { y =>
      val row = new Array[Cell](width)
      (0 until width).foreach { x =>
        row(x) = Cell(Point(x, y), CellStatus.EMPTY)
      }

      cellsArray(y) = row
    }

    // Array to ArraySeq
    val cells =
      mutable.ArraySeq.make(cellsArray.map(row => mutable.ArraySeq.make(row)))

    new Grid(cells = cells)
  }
}
