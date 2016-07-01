package ccssi
package xml

trait LearningStandardsXml {

  def fromXml(elem: scala.xml.Elem): dsl.LearningStandards = {
    val items = (elem \ "LearningStandardItem")
    val version = (elem \ "CoreStandardVersion").head.text
    dsl.LearningStandards(
      version,
      for {
        item <- items
      } yield {
        dsl.LearningStandard.fromXml(item)
      }
    )
  }
}
