package todomvc.example

import com.greencatsoft.angularjs._
import com.greencatsoft.angularjs.core.{RouteParams, Timeout}
import com.greencatsoft.angularjs.extensions.{ModalOptions, ModalService}
import org.scalajs.dom.Element
import upickle.json

import scala.scalajs.js
import scala.scalajs.js.Dictionary
import scala.scalajs.js.annotation.JSExport

//@JSExport
//@injectable("todoItem")
//class TodoItemDirective extends ElementDirective with TemplatedDirective with IsolatedScope {
//
//  override val templateUrl = "assets/templates/todo-item.html"
//
//  bindings ++= Seq(
//    "todo" := "item",
//    "fireOnRemove" :& "onRemove",
//    "fireOnChange" :& "onChange")
//
//  @JSExport
//  def onEditStart(scope: TodoItemScope) {
//    scope.editing = true
//    scope.title = scope.todo.title
//  }
//
//  @JSExport
//  def onEditEnd(scope: TodoItemScope) {
//    scope.editing = false
//    scope.todo.title = scope.title
//
//    scope.fireOnChange()
//  }
//
//  @JSExport
//  def onEditCancel(scope: TodoItemScope) {
//    scope.editing = false
//    scope.title = scope.todo.title
//  }
//}
//
//@injectable("todoEscape")
//class EscapeDirective extends AttributeDirective {
//
//  override def link(scope: ScopeType, elems: Seq[Element], attrs: Attributes) {
//    elems.headOption.map(_.asInstanceOf[Html]) foreach { elem =>
//      elem.onkeydown = (event: KeyboardEvent) =>
//        if (event.keyCode == 27) scope.$apply(attrs("todoEscape"))
//    }
//  }
//}
//
/*@injectable("todoFocus")
class FocusDirective(timeout: Timeout) extends AttributeDirective {
  require(timeout != null, "Missing argument 'timeout'.")

  override def link(scope: ScopeType, elems: Seq[Element], attrs: Attributes) {
    elems.headOption.map(_.asInstanceOf[Html]) foreach { elem =>

      scope.$watch(attrs("todoFocus"),
        (newVal: UndefOr[js.Any]) => if (newVal.isDefined) timeout(() => elem.focus(), 0, false))
    }
  }
}*/

@JSExport
@injectable("searchBar")
class SearchBarDirective extends ElementDirective with TemplatedDirective {
  override val templateUrl = "assets/templates/searchBar.html"
}
@JSExport
@injectable("roomsNav")
class RoomsNavDirective extends ElementDirective with TemplatedDirective  {
  override val templateUrl = "assets/templates/roomsNav.html"
}
@JSExport
@injectable("roomMin")
class RoomMinDirective(timeout: Timeout, modal: ModalService, routeParams: RouteParams) extends ElementDirective with TemplatedDirective {
  override val templateUrl = "assets/templates/roomMin.html"

  override def link(scope: ScopeType, elements: Seq[Element], attrs: Attributes) {
    elements foreach { element =>
      def resize(): Any = {
        val image: Element = element.firstElementChild.firstElementChild
        val imageWidth = image.clientWidth
        println(imageWidth)
        if (imageWidth > 10) {
          image.setAttribute("style", "height: " + imageWidth * 0.66820276497 + "px")
          println(image)
        } else {
          timeout(() => resize(), 100, false)
        }
      }
      resize()
    }
  }
  @JSExport
  def openModal(room: Room): Unit = {
    val newModal: ModalOptions = new js.Object().asInstanceOf[ModalOptions]
    newModal.templateUrl = "assets/templates/modal.html"
    newModal.controller = "modalController"
    /*scope.room = room
    newModal.scope = scope*/
    /*
    newModal.resolve =  new js.Object().asInstanceOf[js.Dictionary[js.Any]]
    newModal.resolve("room") = 5
    newModal.resolve map(println)
    println(newModal.resolve("room"))*/
    modal.open(newModal)
  }

}
