ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.4"

lazy val root = (project in file("."))
  .settings(
    name := "Herding-Cats-2.0"
  )

libraryDependencies ++= Seq(
  // Cats
  "org.typelevel"     %% "cats-core"          % "2.9.0",
  "org.typelevel"     %% "cats-effect"        % "3.6-0142603",

  // Scala Test
  "org.scalactic"     %% "scalactic"          % "3.2.15"        % Test,
  "org.scalatest"     %% "scalatest"          % "3.2.15"        % Test,

  // Cats Testing
  "org.typelevel"     %% "cats-laws"          % "2.9.0"         % Test,
)