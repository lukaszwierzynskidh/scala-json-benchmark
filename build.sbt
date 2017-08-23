name := "scala-json-benchmark"

version := "1.0-SNAPSHOT"

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  "org.json4s" %% "json4s-jackson" % "3.5.3",
  "io.spray" %%  "spray-json" % "1.3.3",
  "net.liftweb" %% "lift-json" % "3.2.0-M1",
  "com.typesafe.play" %% "play-json" % "2.6.0"
)

