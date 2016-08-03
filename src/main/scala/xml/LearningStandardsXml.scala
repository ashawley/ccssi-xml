package ccssi
package xml

trait LearningStandardsXml {

  def fromXml(elem: scala.xml.Elem): dsl.LearningStandards = {

    dsl.LearningStandards(

      version = (elem \ "CoreStandardVersion").head.text,

      items = for {
        item <- (elem \ "LearningStandardItem")
      } yield {
        dsl.LearningStandard.fromXml(item)
      }
    )
  }
}
