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
    presentation = "La chambre, au premier étage de notre maison d’hôtes, calme et spacieuse, bénéficie d’un accès indépendant. " +
      "Avec lit double en 160 cm, salle de bain avec baignoire et WC privatif indépendant, coin collation, bouilloire et cafetière électrique.",
    header = "Vous apprécierez les moments de partage autour d’un généreux petit-déjeuner sur la terrasse ou dans notre salle à manger.",
    images = Array(imageBasePath + "passesimple.jpg", imageBasePath + "checkeredDoubleBed.jpg"),
    isAnApartment = false,
    price = "65 € / Nuit")

  val room2 = new Room(
    id = "2",
    name = "Pied à Terre",
    presentation = "Maecenas id mattis ipsum. Quisque dictum dolor dolor, a tincidunt nisl tincidunt id. Pellentesque " +
      "vel dolor est. Mauris quis arcu at nibh viverra dignissim in ullamcorper nisl. Curabitur lacinia elementum velit, " +
      "sit amet malesuada nisl volutpat ut. ",
    header = "Vous apprécierez les moments de partage autour d’un généreux petit-déjeuner sur la terrasse ou dans notre salle à manger.",
    images = Array(imageBasePath + "doubleBed.jpg"),
    isAnApartment = true,
    price = "65 € / Nuit")


  val room3 = new Room(
    id = "1",
    name = "L’Escale",
    presentation = "In non ante sed libero venenatis ullamcorper non sed lorem. Suspendisse venenatis massa tellus, " +
      "id imperdiet orci convallis eu. Quisque at bibendum ante. Curabitur gravida gravida turpis, at dignissim purus " +
      "commodo quis. .",
    header = "Vous apprécierez les moments de partage autour d’un généreux petit-déjeuner sur la terrasse ou dans notre salle à manger.",
    images = Array(imageBasePath + "blueBed2.jpg"),
    isAnApartment = true,
    price = "65 € / Nuit")

  val room4 = new Room(
    id = "4",
    name = "La terrasse",
    presentation = "Appartement T1bis au 2ème étage d'une maison d'hôtes comprenant 2 autres appartements et une " +
      "chambre d'hôtes, avec salon et couchage en 140, petite alcove pour accueillir une 3ème personne ou un enfant. " +
      "Grande terrasse \"sur le toit\" avec salon de jardin. Cuisine équipée indépendante. Parking en supplément.",
    header = "Ce lumineux appartement de 35 m² composé d'une chambre spacieuse et confortable vous séduira par sa tranquillité ",
    images = Array(imageBasePath + "blueBed.jpg"),
    isAnApartment = true,
    price = "65 € / Nuit - 364 € / Semaine")

//  implicit val placeWrites = Json.writes[Room]

//  implicit val roomPickler: Pickler[Room] = Pickler.materializePickler[Room]

  val rooms = Seq(room1, room2, room3, room4)
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




