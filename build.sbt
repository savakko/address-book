// Turn this project into a Scala.js project by importing these settings
enablePlugins(ScalaJSPlugin)

name := "Address Book"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.7"

//persistLauncher in Compile := true

//persistLauncher in Test := false

libraryDependencies ++= Seq(
    "org.scala-js" %%% "scalajs-dom" % "0.8.1",
    "com.lihaoyi" %%% "scalatags" % "0.4.6"
)