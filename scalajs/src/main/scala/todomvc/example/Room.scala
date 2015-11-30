package todomvc.example

import jdk.nashorn.internal.scripts.JS
import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportAll


@JSExportAll
case class Room(id: String, name: String, presentation: String, images: js.Array[String], isAnApartment: Boolean, price: Double )
