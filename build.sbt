ThisBuild / version := "0.1.0"

ThisBuild / scalaVersion := "3.5.2"

lazy val root = (project in file("."))
  .settings(
    name := "theo"
  )
libraryDependencies ++= Seq(
  "org.scalameta" %% "munit" % "1.0.0-M10" % Test,
  "org.scalacheck" %% "scalacheck" % "1.15.4" % Test,

)

// Enable Scalafmt on Compile
scalafmtOnCompile := true