package ccssi

import scala._
import scala.Predef._
import scala.util.matching.Regex

object Main extends scala.App {

  val extMatcher: Regex = "\\.xml\\Z".r

  def loadXml(file: String): scala.xml.Elem = {
    val src: scala.io.BufferedSource = scala.io.Source.fromFile(file)
    scala.xml.XML.load(src.bufferedReader)
  }

  for {
    file <- args
  } yield {
    extMatcher.findFirstIn(file) match {

      case Some(_: String) => {

        val jsonFilename: String = extMatcher.replaceFirstIn(file, ".json")
        val outFile = new java.io.File(jsonFilename)
        val writer = new java.io.PrintWriter(outFile)

        try {

          val stds = dsl.LearningStandards.fromXml(loadXml(file))
          writer.write(stds.toJson)
          println(s"Wrote JSON to file $outFile")

        } catch {
          case e: org.xml.sax.SAXParseException =>
            println(s"$file:${e.getLineNumber}:${e.getColumnNumber}:${e.getMessage}")
          case e: java.io.FileNotFoundException =>
            println(s"File not found: ${e.getMessage}")
          case e: Throwable => throw e
        } finally {
          writer.close
        }
      }
      case _ => println(s"$file: missing .xml extension")
    }
  }
}
