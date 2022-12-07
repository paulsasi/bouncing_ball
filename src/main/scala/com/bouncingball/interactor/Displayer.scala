package com.bouncingball.interactor

import com.bouncingball.entities.Grid
import com.bouncingball.interactor.Serializer

import java.io.PrintStream

case class Displayer(
    serializer: Serializer,
    printer: PrintStream
) {
  def show(grid: Grid): Unit = {
    printer.print(serializer.serialize(grid))
  }

  def clear(grid: Grid): Unit = {
    val count = grid.nRows / 2;
    printer.print(String.format("\u001b[%dA", count)); // Move up
    printer.print(" \u001b[2K"); // Erase line content
  }

}
