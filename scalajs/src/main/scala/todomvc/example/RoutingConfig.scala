package todomvc.example

import com.greencatsoft.angularjs.core.{Route, RouteProvider}
import com.greencatsoft.angularjs.{inject, Config}


object RoutingConfig extends Config {

  @inject
  var routeProvider: RouteProvider = _

  override def initialize() {

    routeProvider
      .when(path = "/contact", route = Route(templateUrl = "/assets/templates/contact.html", title = "Contact"))
      .when(
        path = "/contact2",
        route = Route(
          templateUrl = "/assets/templates/contact.html",
          title = "Contact",
          controller = "roomPagesController"))
      .when(
        path = "/",
        route = Route(
          templateUrl = "/assets/templates/main.html",
          title = "Main",
          controller = "roomPagesController"))
  }
}

