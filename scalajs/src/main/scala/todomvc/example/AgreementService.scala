package todomvc.example

import com.greencatsoft.angularjs.core.HttpService
import com.greencatsoft.angularjs.{Factory, Service, injectable}
import org.scalajs.dom.ext.Ajax

import scala.scalajs.js
import scala.scalajs.js.JSON
import upickle.default._

import scala.scalajs.js.annotation.JSExport

//import prickle.{Pickler, Unpickle}

import scala.concurrent.Future
import scala.scalajs.concurrent.JSExecutionContext.Implicits.runNow
//import scala.scalajs.js.JSConverters._
//import scala.scalajs.js.JSON
import scala.util.{Failure, Success, Try}
//import derive.key


@injectable("agreementService")
class AgreementService(http: HttpService) extends Service {
  require(http != null, "Missing argument 'http'.")

  //  implicit val roomPickler: Pickler[Room] = Pickler.materializePickler[Room]
  @JSExport
  def findAll(): Future[Seq[Agreement]] = /*flatten*/ {
    // Append a timestamp to prevent some old browsers from caching the result.
    http.get[js.Any]("/agreements")
      .map {JSON.stringify(_)}
      .map { read[Seq[Agreement]] }
  }


  protected def flatten[T](future: Future[Try[T]]): Future[T] = future flatMap {
    case Success(s) => Future.successful(s)
    case Failure(f) => Future.failed(f)
  }
  protected def parameterizeUrl(url: String, parameters: Map[String, Any]): String = {
    require(url != null, "Missing argument 'url'.")
    require(parameters != null, "Missing argument 'parameters'.")

    parameters.foldLeft(url)((base, kv) =>
      base ++ { if (base.contains("?")) "&" else "?" } ++ kv._1 ++ "=" + kv._2)
  }
}


@injectable("agreementService")
class AgreementsServiceFactory(http: HttpService) extends Factory[AgreementService] {

  override def apply(): AgreementService = new AgreementService(http)
}
