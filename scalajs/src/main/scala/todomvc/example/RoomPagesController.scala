package todomvc.example

import com.greencatsoft.angularjs.core.Timeout
import com.greencatsoft.angularjs.extensions.material.Sidenav
import com.greencatsoft.angularjs.{AbstractController, injectable}
import org.scalajs.dom.console
import com.greencatsoft.angularjs.extensions.material
import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js
import scala.scalajs.js.JSConverters.JSRichGenTraversableOnce
import scala.scalajs.js.annotation.JSExport
import scala.util.{Failure, Success}


@JSExport
@injectable("roomPagesController")
class RoomPagesController(scope: RoomScope, timeout: Timeout, service: RoomService) extends AbstractController[RoomScope](scope) {

  scope.rooms = js.Array[Room]()
  scope.roomsNavIsOpen = true

  service.findAll() onComplete {
    case Success(rooms) =>
      scope.$apply {
        scope.rooms = rooms.toJSArray
        scope.activeRoom = ActiveRoom(name = scope.rooms.head.name, imagePath = scope.rooms.head.images.head)
      }

      changeActiveRoom(scope.rooms)
    case Failure(t) => handleError(t)
  }

  def changeActiveImage(images: Seq[String], rooms: Seq[Room]): Any = {
    images.headOption match {
      case Some(image) =>
        scope.activeRoom = ActiveRoom(name = rooms.head.name, imagePath = image)
        timeout(fn = () => {
          changeActiveImage(images.tail, rooms)
        },
          delay = 10000,
          invokeApply = true
        )
      case _ =>
        changeActiveRoom(rooms.tail)
    }
  }

  def changeActiveRoom(rooms: Seq[Room]): Any = {
    rooms.headOption match {
      case Some(room) =>
        changeActiveImage(room.images, rooms)
      case _ =>
        changeActiveRoom(scope.rooms)
    }
  }
  private def handleError(t: Throwable) {
    console.error(s"An error has occured: $t")
  }
}
