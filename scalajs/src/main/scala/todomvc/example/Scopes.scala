package todomvc.example

import com.greencatsoft.angularjs.core.{Location, Scope}

import scala.scalajs.js


trait TodoScope extends Scope {

  var todos: js.Array[Task] = js.native

  var newTitle: String = js.native

  var allChecked: Boolean = js.native

  var remainingCount: Int = js.native

  var location: Location = js.native

  var statusFilter: js.Dynamic = js.native
}

trait TodoItemScope extends Scope {

  var title: String = js.native

  var editing: Boolean = js.native

  def todo: Task = js.native

  def fireOnRemove(): Unit = js.native

  def fireOnChange(): Unit = js.native
}

trait RoomScope extends Scope {

  var rooms: js.Array[Room] = js.native

  var room: Room = js.native

  var activeRoom: ActiveRoom = js.native

  var roomsNavIsOpen: Boolean = js.native

  var availableRooms: js.Array[Room] = js.native

  var start: js.Date = js.native

  var end: js.Date = js.native
}
trait CommentsScope extends Scope {

  var comments: js.Array[Comment] = js.native

  var newComment: NewComment = js.native

}

trait NewComment extends Scope {
  var id: String = js.native

  var title: String = js.native

  var comment: String = js.native

  var userName: String = js.native

  var rate: Int = js.native
}

trait AgreementsScope extends Scope {
  var agreements: js.Array[Agreement] = js.native
}

