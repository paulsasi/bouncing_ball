lazy val root = (project in file("."))
  .settings(
    name := "bouncing_ball",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := "2.13.10",
    Compile / run / mainClass := Some("com.bouncingball.main.Main")
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.14" % Test
libraryDependencies += "org.scalamock" %% "scalamock" % "5.2.0" % Test
