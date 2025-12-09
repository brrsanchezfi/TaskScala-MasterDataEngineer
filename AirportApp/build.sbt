import sbt.Keys.libraryDependencies
import sbtassembly.AssemblyPlugin.defaultShellScript

ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.13.15"
ThisBuild / assemblyPrependShellScript := Some(defaultShellScript)

val mainClassName = "FlightsLoaderApp"

lazy val root = (project in file("."))
  .settings(
    name := "AirportApp",
    // Main class
    Compile / run / mainClass := Some(mainClassName),
    Compile / packageBin / mainClass := Some(mainClassName),
    assembly / mainClass := Some(mainClassName),

    // Assembly jar name
    assembly / assemblyJarName := "AirportApp.jar",

    libraryDependencies ++= Seq(
      "com.typesafe" % "config" % "1.4.3",
      "com.typesafe.akka" %% "akka-http-spray-json" % "10.5.2",
      "org.scalatest" %% "scalatest" % "3.2.17" % Test,
      "org.scala-lang" %% "toolkit-test" % "0.1.7" % Test
    )
  )
