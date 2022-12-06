package com.bouncingball.interactor

import com.bouncingball.entities.{Cell, CellStatus, Grid}

import scala.collection.mutable

case class Serializer(background: Char = ' ') {

  def serialize(grid: Grid): String = {
    val serialization = grid.getReverseCells
      .map { row =>
        val rowStr = row.map { cell =>
          cell match
            case Cell(_, CellStatus.EMPTY) => "."
            case Cell(_, CellStatus.ACTIVE) => "*"
            case _ => throw new RuntimeException("Impossible to serialize cell" +
              "status.")
        }
        rowStr.reduce((x, y) => x + y)
      }

    compress(serialization)
      .map(_ + sys.props("line.separator"))
      .reduce((x, y) => x + y)
  }

  private def compress(rows: mutable.ArraySeq[String]): mutable.ArraySeq[String] = {
    // . . * *
    // . * . *
    //   _ ^ C
    val result = (rows.indices by 2).map { pairNumber =>
      val row1 = rows(pairNumber)
      val row2 = rows(pairNumber + 1)
      (row1 zip row2).map {
        case ('.', '.') => background
        case ('.', '*') => '_'
        case ('*', '.') => '^'
        case ('*', '*') => 'C'
      }.mkString
    }.toArray

    mutable.ArraySeq.make(result)
  }

}
