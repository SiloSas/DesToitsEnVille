package todomvc.example

import com.greencatsoft.angularjs.core.{RouteParams, Timeout}
import com.greencatsoft.angularjs.extensions.{ModalService, ModalInstance}
import com.greencatsoft.angularjs.{AbstractController, injectable}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport
import scala.util.{Failure, Success}
import org.scalajs.dom.console

@JSExport
@injectable("modalController")
class ModalController(scope: RoomScope)
  extends AbstractController[RoomScope](scope) {

  /*println(scope.room)
  println(scope)*/
  scope.rooms = js.Array[Room]()
  private def handleError(t: Throwable) {
    console.error(s"An error has occured: $t")
  }
}
