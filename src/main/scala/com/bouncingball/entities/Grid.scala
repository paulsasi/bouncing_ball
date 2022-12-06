package com.bouncingball.entities

import scala.annotation.targetName
import scala.collection.mutable

case class Grid(cells: mutable.ArraySeq[mutable.ArraySeq[Cell]]) {

  private def getCells: mutable.ArraySeq[mutable.ArraySeq[Cell]] = this.cells

  @targetName("equality")
  def ==(other: Grid): Boolean = this.cells sameElements other.getCells

  def serialize(): String = {
    val z = this.cells.reverse
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
    z
  }

}

object Grid {

  def apply(width: Int, height: Int): Grid = {

    if (width == 0 || height == 0) return Grid(mutable.ArraySeq())

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
