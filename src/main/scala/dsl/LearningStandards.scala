package ccssi
package dsl

import java.lang.String
import scala.collection.Traversable

case class LearningStandards(
  version: String,
  items: Traversable[LearningStandard]
) extends json.Json4sWriter

object LearningStandards extends xml.LearningStandardsXml
