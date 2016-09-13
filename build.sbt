name := "webservicess-with-scala"

version := "1.0"

scalaVersion := "2.11.8"



libraryDependencies ++= Seq(
  "net.databinder.dispatch" % "dispatch-core_2.11" % "0.11.3",
  "org.json4s" %% "json4s-jackson" % "3.3.0",
  "org.specs2" %% "specs2-core" % "3.8.4" % "test"
)

scalacOptions in Test ++= Seq("-Yrangepos")

    
