package ccssi
package json

import java.lang.String
import org.json4s._
import org.json4s.native.Serialization
import org.json4s.native.Serialization.writePretty

trait Json4sWriter {

  implicit val formats: org.json4s.Formats = Serialization.formats(NoTypeHints)

  def toJson: String = writePretty(this)
}
