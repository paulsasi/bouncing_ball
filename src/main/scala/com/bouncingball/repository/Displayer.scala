package com.bouncingball.repository

import com.bouncingball.entities.Grid
import com.bouncingball.interactor.Serializer
import com.bouncingball.main.Main.grid

case class Displayer(
    serializer: Serializer
) {
  def show(grid: Grid): Unit = {
    print(serializer.serialize(grid))
  }

  def clear(grid: Grid): Unit = {
    val count = grid.nRows / 2;
    System.out.print(String.format("\u001b[%dA", count)); // Move up
    System.out.print(" \u001b[2K"); // Erase line content
  }

}
