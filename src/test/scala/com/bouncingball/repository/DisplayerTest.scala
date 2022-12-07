package com.bouncingball.repository

import com.bouncingball.entities.Grid
import com.bouncingball.interactor.Serializer
import org.scalamock.scalatest.MockFactory
import org.scalatest.flatspec.AnyFlatSpec

class DisplayerTest extends AnyFlatSpec with MockFactory {

  "show" should "produce the expected output" in {

    val grid = Grid(2, 2)

    val serializer = mock[Serializer]
    (serializer.serialize _)
      .expects(grid)
      .returning("X\nX")

    val printer = mock[PrinterResource]
    (printer
      .print(_: String))
      .expects("X\nX")

    val displayer = Displayer(serializer, printer)
    displayer.show(grid)

  }

  "clear" should "produce the expected output - 1" in {

    val grid = Grid(4, 4)

    val serializer = mock[Serializer]

    val printer = mock[PrinterResource]
    (printer
      .print(_: String))
      .expects("\u001b[2A")
    (printer
      .print(_: String))
      .expects("\u001b[2K")

    val displayer = Displayer(serializer, printer)
    displayer.clear(grid)

  }

  "clear" should "produce the expected output - 2" in {

    val grid = Grid(12, 10)

    val serializer = mock[Serializer]

    val printer = mock[PrinterResource]
    (printer
      .print(_: String))
      .expects("\u001b[5A")
    (printer
      .print(_: String))
      .expects("\u001b[2K")

    val displayer = Displayer(serializer, printer)
    displayer.clear(grid)

  }

}
