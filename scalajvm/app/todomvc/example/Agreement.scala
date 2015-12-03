package todomvc.example

import org.squeryl

case class Agreement(id: String, title: String, description: String, image: String)

object AgreementSchema  extends squeryl.Schema {

}
