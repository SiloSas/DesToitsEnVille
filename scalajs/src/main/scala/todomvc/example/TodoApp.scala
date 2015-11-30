package todomvc.example

import com.greencatsoft.angularjs.Angular

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport


@JSExport
object TodoApp extends JSApp {

  override def main() {

    val module = Angular.module("todomvc", Seq("ngAnimate", "ngAria", "ngMaterial", "mm.foundation", "ngRoute", "ngMap"))

    module
      .controller[TodoCtrl]
      .controller[RoomPagesController]
      .controller[RoomController]
      .controller[ModalController]
      .controller[ResearchController]
      .directive[SearchBarDirective]
      .directive[RoomsNavDirective]
      .directive[RoomMinDirective]
      .factory[TaskServiceFactory]
      .factory[RoomServiceFactory]
      .filter[StatusFilter]
      .config(RoutingConfig)
  }
}
