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
      .controller[CommentsController]
      .controller[ModalController]
      .controller[ResearchController]
      .controller[ContactController]
      .directive[SearchBarDirective]
      .directive[RoomsNavDirective]
      .directive[RoomMinDirective]
      .directive[ngContactDirective]
      .directive[ParallaxGroup]
      .directive[SlideLeft]
      .directive[RoomImage]
      .directive[CommentsDirective]
      .directive[ParallaxBackground]
      .directive[ParallaxContent]
      .factory[TaskServiceFactory]
      .factory[RoomServiceFactory]
      .factory[AgreementsServiceFactory]
      .factory[ContactServiceFactory]
      .filter[StatusFilter]
      .config(RoutingConfig)
  }
}
