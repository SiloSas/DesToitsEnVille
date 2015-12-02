package todomvc.example

import javafx.scene.input.ScrollEvent

import com.greencatsoft.angularjs._
import com.greencatsoft.angularjs.core.{Window, RouteParams, Timeout}
import com.greencatsoft.angularjs.extensions.material.Sidenav
import com.greencatsoft.angularjs.extensions.{ModalOptions, ModalService}
import org.scalajs.dom.Element
import org.scalajs.dom.document
import org.scalajs.dom.html.Html
import org.scalajs.dom.raw.{ClientRect, ClientRectList, UIEvent}
import prickle.Pickle
import upickle.default._
import upickle.json
import upickle.default._

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
class RoomsNavDirective(sidenav: Sidenav) extends ElementDirective with TemplatedDirective  {
  override val templateUrl = "assets/templates/roomsNav.html"

  @JSExport
  def toggleLeft(): Any = {
    sidenav("left").toggle()
  }
}

@JSExport
@injectable("slideLeft")
class SlideLeft(sidenav: Sidenav) extends ClassDirective {
   @JSExport
  def close(): Any = {
     sidenav("left").close()
   }
}

@JSExport
@injectable("roomMin")
class RoomMinDirective(modal: ModalService) extends ElementDirective with TemplatedDirective {
  override val templateUrl = "assets/templates/roomMin.html"

  @JSExport
  def openModal(room: Room): Unit = {
    val room2 = Room2(room.id, room.name, room.presentation, room.images.toArray, room.isAnApartment, room.price)
    val newModal: ModalOptions = new js.Object().asInstanceOf[ModalOptions]
    newModal.templateUrl = "assets/templates/modal.html"
    newModal.controller = "modalController"
    newModal.windowClass = "bookingModal"

    newModal.resolve = new js.Object().asInstanceOf[js.Dictionary[js.Any]]
    newModal.resolve("room") = write(room2)
    modal.open(newModal)
  }
}

@JSExport
@injectable("roomImage")
class RoomImage(timeout: Timeout) extends ClassDirective {
  override def link(scope: ScopeType, elements: Seq[Element], attrs: Attributes) {
    elements.headOption.map(_.asInstanceOf[Html]) foreach { element =>
      def resize(): Any = {
        val imageWidth = element.clientWidth
        println(imageWidth)
        if (imageWidth > 10) {
          element.setAttribute("style", "height: " + imageWidth * 0.66820276497 + "px")
        } else {
          timeout(() => resize(), 100, false)
        }
      }
      resize()
    }
  }
}

@JSExport
@injectable("parallaxBackground")
class ParallaxBackground(window: Window) extends ClassDirective {

  override def link(scope: ScopeType, elements: Seq[Element], attrs: Attributes): Unit = {
    val newHeight = window.innerWidth * 0.75369458128
    elements.headOption.map(_.asInstanceOf[Html]) foreach { element =>
      if (newHeight < window.innerHeight) element.style.height = newHeight + "px"
      else element.style.height = window.innerHeight + "px"
    }
  }
}

@JSExport
@injectable("parallaxContent")
class ParallaxContent(window: Window) extends ClassDirective {

  override def link(scope: ScopeType, elements: Seq[Element], attrs: Attributes): Unit = {
    val newHeight = window.innerWidth * 0.75369458128
    elements.headOption.map(_.asInstanceOf[Html]) foreach { element =>
      if (newHeight < window.innerHeight) element.style.top = newHeight + "px"
      else element.style.top = window.innerHeight + "px"
    }
  }
}

@JSExport
@injectable("comments")
class CommentsDirective() extends ElementDirective with TemplatedDirective {
  override val templateUrl ="assets/templates/comments.html"
}
