package todomvc.example

import com.greencatsoft.angularjs.Angular

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport


@JSExport
object TodoApp extends JSApp {

  override def main() {

    val module = Angular.module("todomvc", Seq("ngMaterial", "mm.foundation", "ngRoute", "ngMap"))

    module
      .controller[TodoCtrl]
      .controller[RoomPagesController]
      .directive[SearchBarDirective]
      .filter[StatusFilter]
      .factory[TaskServiceFactory]
      .config(RoutingConfig)
  }
}
