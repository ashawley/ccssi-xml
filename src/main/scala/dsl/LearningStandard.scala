package ccssi
package dsl

import java.lang.String
import scala.collection.Iterable

case class LearningStandard(
  refId: String,
  refUri: String,
  standardHierarchyLevel: StandardHierarchyLevel,
  statementCodes: Iterable[String],
  statements: Iterable[String],
  gradeLevels: Iterable[String],
  learningStandardDocumentRefId: String,
  related: Iterable[RelatedLearningStandard],
  children: Iterable[String]
)

object LearningStandard extends xml.LearningStandardXml
