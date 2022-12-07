package com.bouncingball.repository

import com.bouncingball.entities.Grid
import com.bouncingball.interactor.Serializer

case class Displayer(
    serializer: Serializer,
    printer: PrinterResource
) {
  def show(grid: Grid): Unit = {
    printer.print(serializer.serialize(grid))
  }

  def clear(grid: Grid): Unit = {
    val count = grid.nRows / 2
    printer.print("\u001b[%dA" format count) // Move up
    printer.print("\u001b[2K"); // Erase line content
  }

}
