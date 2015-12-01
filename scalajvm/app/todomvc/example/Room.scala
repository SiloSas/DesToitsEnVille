package todomvc.example

import org.squeryl

case class Room(id: String, name: String, presentation: String, images: Array[String], isAnApartment: Boolean, price: Double )

object RoomSchema  extends squeryl.Schema {

}
