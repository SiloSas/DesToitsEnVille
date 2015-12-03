package controllers

import java.util.UUID

import play.api.mvc.{Action, Controller}
//import prickle.Pickler
import todomvc.example.Agreement
import upickle.default._

object AgreementsController extends Controller {

  val imageBasePath = "assets/images/"

  val agreement = new Agreement(id = "3", title = "Salon de jardin", description = "desc", image = "assets/images/desToits1.jpg")
  val agreement1 = new Agreement(id = "2", title = "Terrasse", description = "desc", image = "assets/images/terrasse.jpg")
  val agreement2 = new Agreement(id = "2", title = "Parking", description = "desc", image = "assets/images/parking.jpg")
  val agreement3= new Agreement(id = "2", title = "Plateau repas", description = "desc", image = "assets/images/plateau-repas.jpg")

  val agreements = Seq(agreement, agreement1, agreement2, agreement3)
  def findAll() = Action {
    Ok(write(agreements))
  }

}




