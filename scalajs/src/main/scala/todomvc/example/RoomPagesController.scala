package todomvc.example

import com.greencatsoft.angularjs.core.Timeout
import com.greencatsoft.angularjs.{AbstractController, injectable}

import scala.scalajs.js
import scala.scalajs.js.Any.jsArrayOps
import scala.scalajs.js.annotation.JSExport


@JSExport
@injectable("roomPagesController")
class RoomPagesController(scope: RoomScope, timeout: Timeout) extends AbstractController[RoomScope](scope) {

  scope.rooms = js.Array[Room]()
  scope.roomsNavIsOpen = false

  val imageBasePath = "assets/images/"

  val room1 = new Room(
    name = "Passe Simple",
    presentation = "« Passé Simple » à Villeurbanne au 1er étage de notre Maison d’hôtes, " +
      "cette agréable chambre d’hôtes calme et spacieuse bénéficie d’un accès strictement indépendant , lit double en " +
      "160 cm, salle de bain avec baignoire et WC privatif indépendant, coin collation avec réfrigérateur, micro-onde, " +
      "bouilloire et cafetière électrique.\nA votre disposition une terrasse privative dans le jardin, idéale pour le " +
      "petit déjeuner en été.\n\nIdéalement située à proximité des accès routier (proche périphérique) et à 100mètres " +
      "du Métro pour un accès direct au centre historique de Lyon en 10 minutes.",
    images = js.Array(imageBasePath + "passesimple.jpg"), false)

  val room2 = new Room(
    name = "Pied à Terre",
    presentation = "«Pied à Terre» est un appartement de 40 m² composé de trois pièces et d’une  terrasse indépendante " +
      "de plein pied donnant sur le jardin.\n\nIl est situé au rez-de chaussée de notre maison d’hôtes villeurbannaise, " +
      "il peut accueillir jusqu’à 3 personnes.\nCet appartement est équipé d’un lit double en 140 dans la chambre ainsi " +
      "que d’un lit simple dans le séjour.\nFacile d’accès, cet appartement est implanté au cœur d’un quartier commerçant, " +
      "à 100m de la station de métro « Cusset » (ligne A) et de l’arrêt de bus (ligne C17), soit à 15mn de la Gare de la " +
      "Part Dieu, du Campus universitaire de la Doua et de la navette pour l’aéroport Saint-Exupéry »\n\n« Pied à Terre » " +
      "constitue l’un des appartements de notre Maison d’hôtes ayant une capacité totale d’hébergement de 10 personnes, " +
      "que vous trouverez au 23 petite rue Pasteur à Villeurbanne.",
    images = js.Array(imageBasePath + "doubleBed.jpg"), true)


  val room3 = new Room(
    name = "L’Escale",
    presentation = "« L’Escale » est un appartement calme et confortable de 35 m². Situé au 1er étage de notre maison " +
      "d’hôtes villeurbannaise, il est composé de 2 pièces exposées à l’ est et à l’ouest . Il peut accueillir jusqu’à " +
      "2 personnes.\n\nCe meublé est équipé de deux lits de 90 cm, afin mieux l’adapter à la colocation des stagiaires " +
      "en formation par exemple.\n\nFacile d’accès cet appartement est situé à 100m de la station de métro Cusset " +
      "(ligne A) et de l’arrêt de bus (ligne C17) q, soit à 15mn à la gare de la Part Dieu et du campus universitaire " +
      "de la Doua et de l’INSA.\n\nL’escale est l’un des appartements de notre maison d’hôtes, sise au 23 petite rue " +
      "Pasteur à Villeurbanne, ayant une capacité totale d’hébergement de 10 personnes.\n\nVous pouvez louer la totalité " +
      "de la maison si vous le souhaitez.",
    images = js.Array(imageBasePath + "blueBed1.jpg"), true)

  scope.rooms :+= room1
  scope.rooms :+= room2

  println(scope.rooms)


  scope.activeRoom = ActiveRoom(name = scope.rooms.head.name, imagePath = scope.rooms.head.images.head)

  changeActiveRoom(scope.rooms)

  def changeActiveImage(images: Seq[String], rooms: Seq[Room]): Any = {
    images.headOption match {
      case Some(image) =>
        scope.activeRoom = ActiveRoom(name = rooms.head.name, imagePath = image)
        timeout(fn = () => {
          changeActiveImage(images.tail, rooms)

        },
          delay = 10000,
          invokeApply = true
        )
      case _ =>
        changeActiveRoom(rooms.tail)
    }
  }

  def changeActiveRoom(rooms: Seq[Room]): Any = {
    rooms.headOption match {
      case Some(room) =>
        changeActiveImage(room.images, rooms)
      case _ =>
        changeActiveRoom(scope.rooms)
    }
  }

}
