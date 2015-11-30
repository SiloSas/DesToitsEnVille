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

  var activeImage: String = js.native

  var activeRoom: ActiveRoom = js.native

  var roomsNavIsOpen: Boolean = js.native

  var availableRooms: js.Array[Room] = js.native
}
