package com.bouncingball.repository

class PrinterStdOut extends PrinterResource {

  override def print(s: String): Unit = {
    System.out.print(s)
  }

}
