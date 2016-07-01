package ccssi
package dsl

import java.lang.String
import scala.collection.Traversable

case class LearningStandard(
  refId: String,
  refUri: String,
  standardHierarchyLevel: StandardHierarchyLevel,
  statementCodes: Traversable[String],
  statements: Traversable[String],
  gradeLevels: Traversable[String],
  learningStandardDocumentRefId: String,
  related: Traversable[RelatedLearningStandard],
  children: Traversable[String]
)

object LearningStandard extends xml.LearningStandardXml
