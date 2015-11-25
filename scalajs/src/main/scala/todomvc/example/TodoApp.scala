package todomvc.example

import com.greencatsoft.angularjs.Angular

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport


@JSExport
object TodoApp extends JSApp {

  override def main() {
    val module = Angular.module("todomvc", Seq("ngAnimate", "ngAria", "ngMaterial", "mm.foundation", "ngRoute"))
    module
      .controller[TodoCtrl]
      .controller[RoomPagesController]
      .directive[SearchBarDirective]
      .directive[RoomsNavDirective]
      .filter[StatusFilter]
      .factory[TaskServiceFactory]
      .config(RoutingConfig)
  }
}
