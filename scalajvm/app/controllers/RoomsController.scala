package controllers

import java.util.UUID

import play.api.mvc.{Action, Controller}
//import prickle.Pickler
import todomvc.example.Room
import upickle.default._

object RoomsController extends Controller {

  val imageBasePath = "assets/images/"

  val room1 = new Room(
    id = "3",
    name = "Passe Simple",
    presentation = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi sit amet imperdiet augue. " +
      "Pellentesque luctus enim libero, quis sodales ex congue non. Duis turpis nulla, porta id velit non, " +
      "feugiat efficitur justo. Ut pellentesque est quis tellus accumsan dapibus. Pellentesque habitant morbi " +
      "tristique senectus et netus et malesuada fames ac turpis egestas. Duis sit amet ex fermentum, faucibus diam vel, " +
      "varius urna",
    images = Array(imageBasePath + "passesimple.jpg", imageBasePath + "checkeredDoubleBed.jpg"),
    isAnApartment = false,
    price = 10.0)

  val room2 = new Room(
    id = "2",
    name = "Pied à Terre",
    presentation = "Maecenas id mattis ipsum. Quisque dictum dolor dolor, a tincidunt nisl tincidunt id. Pellentesque " +
      "vel dolor est. Mauris quis arcu at nibh viverra dignissim in ullamcorper nisl. Curabitur lacinia elementum velit, " +
      "sit amet malesuada nisl volutpat ut. ",
    images = Array(imageBasePath + "doubleBed.jpg"),
    isAnApartment = true,
    price = 15.0)


  val room3 = new Room(
    id = "1",
    name = "L’Escale",
    presentation = "In non ante sed libero venenatis ullamcorper non sed lorem. Suspendisse venenatis massa tellus, " +
      "id imperdiet orci convallis eu. Quisque at bibendum ante. Curabitur gravida gravida turpis, at dignissim purus " +
      "commodo quis. .",
    images = Array(imageBasePath + "blueBed2.jpg"),
    isAnApartment = true,
    price = 12.5)

//  implicit val placeWrites = Json.writes[Room]

//  implicit val roomPickler: Pickler[Room] = Pickler.materializePickler[Room]

  val rooms = Seq(room1, room2, room3)
  def findAll() = Action {
    Ok(write(rooms))
  }

  def findAvailable(start: String, end: String) = Action {
    Ok(write(Seq(room1, room3)))
  }

  def findById(id: String) = Action {
    val room = rooms.find(_.id == id)
    room match {
      case Some(roomFound) =>
        println(write(roomFound))
        Ok(write(roomFound))
      case _ =>
        NotFound
    }
  }

}




