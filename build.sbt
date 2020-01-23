// build.sbt --- Scala build tool settings

import sbt.complete.Parser
import sbt.complete.Parsers._

scalaVersion := "2.13.1"

// https://tpolecat.github.io/2014/04/11/scalac-flags.html
scalacOptions in (Compile) ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-unchecked",
  "-Xfatal-warnings",
  "-Xlint",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Yno-predef",
  "-Yno-imports"
)

libraryDependencies ++= List(
  "org.scalacheck" %% "scalacheck" % "1.14.3" % "test",
  "org.scala-lang.modules" %% "scala-xml" % "2.0.0-M1",
  "org.json4s" %% "json4s-native" % "3.6.7"
)

lazy val xmlToJson = inputKey[Unit]("Convert an XML standard to JSON")

// file("src/main/xml/ela-literacy.xml")
lazy val xmlFileParser = fileParser(file(".")).filter(
  (f: java.io.File) => !f.isFile || f.isFile && ".xml\\Z".r.findFirstIn(f.name).map(_ => true).getOrElse(false),
  (msg: String) => msg match {
    case _ => "Not an XML file"
  }
)

xmlToJson := Def.inputTaskDyn {

  val log = streams.value.log

  val xmlFile = xmlFileParser.parsed

  Def.taskDyn {

    log.info(s"Converting XML file to JSON...")

    val runTask = (run in Compile).toTask(s" $xmlFile")

    runTask
  }
}.evaluated

lazy val xmlToJsonDir = inputKey[Unit]("Convert XML standards to JSON")

// file("src/main/xml")
lazy val dirParser = fileParser(file(".")).filter(
  _.isDirectory,
  (msg: String) => msg match {
    case _ => "Not a directory"
  }
)

xmlToJsonDir := Def.inputTaskDyn {

  val log = streams.value.log

  val dir = dirParser.parsed

  Def.taskDyn {

    log.info(s"Loooking for XML files in $dir...")

    val xmlFiles = dir ** "*.xml"

    // Find only XML files with standards in them...
    val tagMatcher = "\\A<LearningStandards>".r

    val peekLines = 3 // First N lines to check...

    implicit def optionToBoolean(o: Option[_]): Boolean = o.isDefined

    def isLearningStandard(file: java.io.File): Boolean =
      scala.io.Source.fromFile(file).getLines.take(peekLines).find {
        line: String => tagMatcher.findPrefixOf(line)
      }

    val xmlMatches = xmlFiles filter (isLearningStandard _)

    log.info(s"Converting XML files to JSON...")

    val paths = xmlMatches.get.map(_.getPath).mkString(" ")

    val runTask = (run in Compile).toTask(s" $paths")

    runTask
  }
}.evaluated
