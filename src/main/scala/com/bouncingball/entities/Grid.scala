package com.bouncingball.entities

import scala.collection.mutable

case class Grid(cells: mutable.ArraySeq[mutable.ArraySeq[Cell]]) {

  val nRows: Int = this.cells.length
  val nCols: Int = this.cells(0).length

  private def getCells: mutable.ArraySeq[mutable.ArraySeq[Cell]] = this.cells

  def getReverseCells: mutable.ArraySeq[mutable.ArraySeq[Cell]] =
    this.cells.reverse

  def ==(other: Grid): Boolean = this.cells == other.getCells

  def insert(ball: Ball): Unit = {

    // Boundary box
    val xBoundaryMin = (ball.center.x - ball.radius).floor.toInt.max(0)
    val xBoundaryMax = (ball.center.x + ball.radius).ceil.toInt.min(nCols - 1)
    val yBoundaryMin = (ball.center.y - ball.radius).floor.toInt.max(0)
    val yBoundaryMax = (ball.center.y + ball.radius).ceil.toInt.min(nRows - 1)

    for (x <- xBoundaryMin to xBoundaryMax) {
      for (y <- yBoundaryMin to yBoundaryMax) {
        val candidateCell = this.cells(y)(x)

        if (ball.contains(candidateCell.point))
          this
            .cells(y)
            .update(x, candidateCell.copy(status = CellStatus.ACTIVE))

      }
    }

  }

  def flash(value: CellStatus.CellStatus = CellStatus.EMPTY): Unit = {
    for (i <- cells.indices) {
      for (j <- cells(i).indices) {
        val cellFlashed = cells(i)(j).copy(status = value)
        cells(i).update(j, cellFlashed)
      }
    }
  }

}

object Grid {

  def apply(width: Int, height: Int): Grid = {

    if (height % 2 != 0)
      throw new RuntimeException("Grid height must be divisible by two!")

    if (width == 0 || height == 0)
      throw new RuntimeException(
        "Grid dimensions" +
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
