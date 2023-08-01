ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.4"

lazy val root = (project in file("."))
  .settings(
    name := "POSOTemplate"
  )

libraryDependencies ++= Seq(

  // Scala Test
  "org.scalactic"     %% "scalactic"          % "3.2.15",
  "org.scalatest"     %% "scalatest"          % "3.2.15"        % Test,
)