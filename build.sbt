lazy val root = (project in file("."))
  .settings(
    name := "bouncing_ball",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := "3.2.1",
    Compile / run / mainClass := Some("com.bouncingball.main.Main")
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.14" % Test
