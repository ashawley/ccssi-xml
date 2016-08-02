package ccssi
package xml

import scala._
import scala.Predef._

import org.scalacheck.Properties
import org.scalacheck.Prop.AnyOperators

object LearningStandardXmlSpec extends Properties("LearningStandardXml") {

  val o = new LearningStandardXml { }

  val emptyStd =
    """|<LearningStandardItem>
       |  <RefURI/>
       |  <StandardHierarchyLevel>
       |    <number>0</number>
       |    <description/>
       |  </StandardHierarchyLevel>
       |  <LearningStandardDocumentRefId/>
       |</LearningStandardItem>
       |""".stripMargin

  val emptyXml: scala.xml.Node = scala.xml.XML.loadString(emptyStd)

  property("fromXml(emptyXml)") = {
    o.fromXml(emptyXml) ?= dsl.LearningStandard(
      "",
      "",
      dsl.StandardHierarchyLevel(
        0,
        ""
      ),
      Traversable.empty[String],
      Traversable.empty[String],
      Traversable.empty[String],
      "",
      Traversable.empty[dsl.RelatedLearningStandard],
      Traversable.empty[String]
    )
  }
}
