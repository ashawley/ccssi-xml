package ccssi
package xml

import scala._
import scala.Predef._

import org.scalacheck.Properties
 import org.scalacheck.Prop.AnyOperators

object LearningStandardsXmlSpec extends Properties("LearningStandardsXml") {

  val o = new LearningStandardsXml { }

  val emptyStd =
    """|<LearningStandardItems>
       |  <CoreStandardVersion/>
       |</LearningStandardItems>
       |""".stripMargin

  val emptyXml: scala.xml.Elem = scala.xml.XML.loadString(emptyStd)

  property("fromXml(emptyXml)") = {
    o.fromXml(emptyXml) ?= dsl.LearningStandards(
      "",
      Iterable.empty[dsl.LearningStandard]
    )
  }
}
