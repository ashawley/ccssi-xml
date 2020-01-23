package ccssi
package dsl

import java.lang.String
import scala.collection.Iterable

case class LearningStandards(
  version: String,
  items: Iterable[LearningStandard]
) extends json.Json4sWriter

object LearningStandards extends xml.LearningStandardsXml
