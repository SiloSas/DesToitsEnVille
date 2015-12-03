package todomvc.example

import org.squeryl

case class Room(id: String, name: String, presentation: String, header: String, images: Array[String], isAnApartment: Boolean, price: String )

object RoomSchema  extends squeryl.Schema {

}
