package todomvc.example

import scala.scalajs.js.annotation.JSExportAll


@JSExportAll
case class Room(name: String, presentation: String, images: Seq[String])
