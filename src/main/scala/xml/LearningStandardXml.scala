package ccssi
package xml

import scala.collection.immutable.StringOps

trait LearningStandardXml {

  def fromXml(std: scala.xml.Node): dsl.LearningStandard =

    dsl.LearningStandard(

      refId = (std \@ "RefID"),

      refUri = (std \ "RefURI").head.text,

      standardHierarchyLevel = {

        val level = (std \ "StandardHierarchyLevel").head

        dsl.StandardHierarchyLevel(
          number = new StringOps((level \ "number").text).toInt,
          description = (level \ "description").text
        )
      },

      statementCodes =
      for {
        codes <- (std \ "StatementCodes")
      } yield {
        (codes \ "StatementCode").head.text
      },

      statements =
        for {
          stmts <- (std \ "Statements")
          stmt <- (stmts \ "Statement")
        } yield {
          stmt.head.text
        },

      gradeLevels =
        for {
          levels <- (std \ "GradeLevels")
          level <- (levels \ "GradeLevel")
        } yield {
          level.head.text
        },

      learningStandardDocumentRefId =
        (std \ "LearningStandardDocumentRefId").head.text,

      related =
        for {
          items <- (std \ "RelatedLearningStandardItems")
          ref <- (items \ "LearningStandardItemRefId")
        } yield {
          dsl.RelatedLearningStandard(
            relation = ref \@ "RelationshipType",
            refId = ref.text
          )
        },

      children =
        for {
          childs <- (std \ "ChildrenAre")
          child <- (childs \ "Child")
        } yield {
          child.text
        }
    )
}
