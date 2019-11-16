ThisBuild / scalaVersion := "2.12.10"

libraryDependencies ++= Seq(
  "org.scalactic" %% "scalactic" % "2.2.6",
  "org.scalatest" % "scalatest_2.13" % "3.0.8" % "test"
)

scalaSource in Compile := baseDirectory.value / "src/main"
scalaSource in Compile := baseDirectory.value / "src/test"
