package com.bouncingball

import scopt.OptionParser

case class Arguments(
    gridArgs: GridArgs,
    ballArgs: BallArgs,
    displayArgs: DisplayArgs
)

case class GridArgs(
    width: Int = 136,
    height: Int = 64,
    gravity: (Float, Float) = (0, -50f),
    ceilDensity: Float = 0.95f,
    wallDensity: Float = 0.8f,
)

case class BallArgs(
    position: (Int, Int) = (20, 44),
    radius: Int = 20,
    velocity: (Float, Float) = (50, 0),
)

case class DisplayArgs(
    background: Char = ' ',
    fps: Int = 15
)

case object Arguments {

  def getArguments(args: Array[String]): Arguments = {
    val parser = new OptionParser[Arguments]("bouncing_ball") {

      opt[Unit]("grid")
        .action((_, c) => c.copy(gridArgs = GridArgs()))
        .children(
          opt[Int]("width")
            .text("Grid width")
            .action((in, self) => self.copy(self.gridArgs.copy(width = in))),
          opt[Int]("height")
            .text("Grid height")
            .action((in, self) => self.copy(self.gridArgs.copy(height = in))),
          opt[(String, String)]("gravity")
            .text("Grid gravity")
            .action((in, self) =>
              self.copy(
                self.gridArgs.copy(gravity = (in._1.toFloat, in._2.toFloat)))),
          opt[String]("ceilDensity")
            .text("Density of the ceil")
            .action((in, self) =>
              self.copy(self.gridArgs.copy(ceilDensity = in.toFloat))),
          opt[String]("wallDensity")
            .text("Density of the wall")
            .action((in, self) =>
              self.copy(self.gridArgs.copy(wallDensity = in.toFloat))),
        )

      opt[Unit]("ball")
        .action((_, c) => c.copy(ballArgs = BallArgs()))
        .children(
          opt[(Int, Int)]("position")
            .text("Ball starting position")
            .action((in, self) =>
              self.copy(ballArgs = self.ballArgs.copy(position = in))),
          opt[Int]("radius")
            .text("Ball radius")
            .action((in, self) =>
              self.copy(ballArgs = self.ballArgs.copy(radius = in))),
          opt[(String, String)]("velocity")
            .text("Ball starting position")
            .action((in, self) =>
              self.copy(ballArgs =
                self.ballArgs.copy(velocity = (in._1.toFloat, in._2.toFloat)))),
        )

      opt[Unit]("display")
        .action((_, c) => c.copy(displayArgs = DisplayArgs()))
        .children(
          opt[Char]("background")
            .text("Display background character")
            .action((in, self) =>
              self.copy(displayArgs = self.displayArgs.copy(background = in))),
          opt[Int]("fps")
            .text("Display FPS")
            .action((in, self) =>
              self.copy(displayArgs = self.displayArgs.copy(fps = in)))
        )
    }
    val arguments =
      parser.parse(args, Arguments(GridArgs(), BallArgs(), DisplayArgs()))

    if (arguments.isEmpty) sys.exit(1)
    arguments.get
  }

}
