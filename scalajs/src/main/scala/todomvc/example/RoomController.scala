package todomvc.example

import com.greencatsoft.angularjs.core.{RouteParams, Timeout}
import com.greencatsoft.angularjs.{AbstractController, injectable}
import org.scalajs.dom.console

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js
import scala.scalajs.js.JSConverters.JSRichGenTraversableOnce
import scala.scalajs.js.annotation.JSExport
import scala.util.{Failure, Success}


@JSExport
@injectable("roomController")
class RoomController(scope: RoomScope, timeout: Timeout, service: RoomService, $routeParams: RouteParams) extends AbstractController[RoomScope](scope) {

  val id = $routeParams.get("id").toString
  println(id)
  service.findById(id) onComplete {
    case Success(room) =>
      scope.$apply {
        scope.room = room
        scope.activeImage = scope.room.images.head
      }

      changeActiveImage(scope.room.images.tail)
    case Failure(t) => handleError(t)
  }

  def changeActiveImage(images: Seq[String]): Any = {
    images.headOption match {
      case Some(image) =>
        scope.activeImage = image
        timeout(fn = () => {
          changeActiveImage(images.tail)
        },
          delay = 10000,
          invokeApply = true
        )
      case _ =>
        changeActiveImage(scope.room.images)
    }
  }

  private def handleError(t: Throwable) {
    console.error(s"An error has occured: $t")
  }
}
