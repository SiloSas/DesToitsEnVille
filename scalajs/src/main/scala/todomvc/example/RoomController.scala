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
class RoomController(scope: RoomScope, timeout: Timeout, service: RoomService, $routeParams: RouteParams, agreementsScope: AgreementsScope,
                     agreementsService: AgreementService)
  extends AbstractController[RoomScope](scope) {

  val id = $routeParams.get("id").toString

  agreementsScope.agreements = js.Array[Agreement]()

  agreementsService.findAll() onComplete {
    case Success(agreements) =>
      agreementsScope.agreements = agreements.toJSArray
    case Failure(t) => handleError(t)
  }

  service.findById(id) onComplete {
    case Success(room) =>
      scope.$apply {
        scope.room = room
        scope.activeRoom = ActiveRoom(step = 0, imagePath = scope.room.images.head, imagePath1 = scope.room.images.head)
      }
      timeout(fn = () => {
        changeActiveImage(scope.room.images.tail)
      },
        delay = 10000,
        invokeApply = true
      )
    case Failure(t) => handleError(t)
  }

  def changeActiveImage(images: Seq[String]): Any = {
    images.headOption match {
      case Some(image) =>
        val step =
          if (scope.activeRoom.step == 0) 1
          else 0

        val image0 =
          if (step == 0) image
          else scope.activeRoom.imagePath

        val image1 =
          if (step == 1) image
          else scope.activeRoom.imagePath1

        scope.activeRoom = ActiveRoom(step = step, imagePath = image0, imagePath1 = image1)

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
