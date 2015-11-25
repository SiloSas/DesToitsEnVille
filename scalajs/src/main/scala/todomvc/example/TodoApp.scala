package todomvc.example

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

import com.greencatsoft.angularjs.{ Angular, injectable }

@JSExport
object TodoApp extends JSApp {

  override def main() {
    val module = Angular.module("todomvc", Seq("ngMaterial", "mm.foundation"))

    module
      .controller[TodoCtrl]
      .controller[RoomPagesController]
      .directive[SearchBarDirective]
      .filter[StatusFilter]
      .factory[TaskServiceFactory]
  }
}
