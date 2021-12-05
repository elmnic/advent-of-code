ThisBuild / scalaVersion := "2.13.6"
ThisBuild / libraryDependencies ++= Seq(
  "org.scalaj" %% "scalaj-http" % "2.4.2",
  "org.scalactic" %% "scalactic" % "3.2.10",
  "org.scalatest" %% "scalatest" % "3.2.10" % "test")
scalacOptions ++= Seq("-deprecation", "-feature")
