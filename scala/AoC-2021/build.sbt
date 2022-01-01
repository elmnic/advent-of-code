ThisBuild / scalaVersion := "3.0.2"

ThisBuild / libraryDependencies ++= Seq(
  "org.scalaj"    %% "scalaj-http" % "2.4.2" cross CrossVersion.for3Use2_13,
  "org.scalactic" %% "scalactic"   % "3.2.10",
  "org.scalatest" %% "scalatest"   % "3.2.10" % "test")
scalacOptions ++= Seq("-deprecation", "-feature")

externalResolvers := Seq(DefaultMavenRepository)
