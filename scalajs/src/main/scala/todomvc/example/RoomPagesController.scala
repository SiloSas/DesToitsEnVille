package todomvc.example

import org.scalajs.dom
import dom.document
import scala.scalajs.concurrent.JSExecutionContext.Implicits.runNow
import scala.scalajs.js
import scala.scalajs.js.Any.{ fromBoolean, fromFunction1, fromString, jsArrayOps, wrapArray }
import scala.scalajs.js.Dynamic.literal
import scala.scalajs.js.JSConverters.JSRichGenTraversableOnce
import scala.scalajs.js.UndefOr
import scala.scalajs.js.UndefOr.undefOr2ops
import scala.scalajs.js.annotation.JSExport
import scala.util.{ Failure, Success }
import org.scalajs.dom.console
import com.greencatsoft.angularjs.{ inject, injectable }
import com.greencatsoft.angularjs.AbstractController
import com.greencatsoft.angularjs.core.{Timeout, Location, Scope}


@JSExport
@injectable("roomPagesController")
class RoomPagesController(scope: RoomScope, timeout: Timeout) extends AbstractController[RoomScope](scope) {

  scope.rooms = js.Array[Room]()

  val imageBasePath = "assets/images/"

  val room1 = new Room(
    name = "nameeeeeeeeee eeee",
    presentation = "presentation bla b;ab bal presentation bla b;ab presentation bla b;ab presentation bla b;ab presentation bla b;ab ",
    images = Seq(imageBasePath + "blueBed.jpg"))

  val room2 = new Room(
    name = "nameeeeeeeeee eeee",
    presentation = "presentation bla b;ab bal presentation bla b;ab presentation bla b;ab presentation bla b;ab presentation bla b;ab ",
    images = Seq("rdfdfdfd"))


  scope.rooms :+= room1

//  println("lklkjlkj " + scope.rooms.head.name)

  scope.activeRoom = ActiveRoom(name = scope.rooms.head.name, imagePath = scope.rooms.head.images.head)



  //document.body.style.backgroundImage = """url("../assets/images/blueBed.jpg")"""

  println("scope = " + scope.activeRoom)
}
