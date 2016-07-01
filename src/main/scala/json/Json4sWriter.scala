package ccssi
package json

import org.json4s._
import org.json4s.native.Serialization
import org.json4s.native.Serialization.writePretty

trait Json4sWriter {

  implicit val formats = Serialization.formats(NoTypeHints)

  def toJson = writePretty(this)
}
