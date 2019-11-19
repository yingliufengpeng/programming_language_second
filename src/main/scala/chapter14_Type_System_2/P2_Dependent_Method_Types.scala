package chapter14_Type_System_2

import scala.concurrent.{Await, Future} //
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object P2_Dependent_Method_Types {

  case class LocalResponse(statusCode: Int) //
  case class RemoteResponse(message: String)

  sealed trait Computation {
    type Response
    val work: Future[Response]
  }
  case class LocalComputation(
                               work: Future[LocalResponse]) extends Computation {
    type Response = LocalResponse
  }
  case class RemoteComputation(
                                work: Future[RemoteResponse]) extends Computation {
    type Response = RemoteResponse
  }

  object Service {
    def handle(computation: Computation): computation.Response = {
      val duration = Duration(2, SECONDS)
      Await.result(computation.work, duration)
    }
  }

  def main(args: Array[String]): Unit = {
    /**
     *  Await.result returns the  LocalRes
     * ponse or  RemoteResponse , corresponding to the input  Computation
     * */

    Service.handle(LocalComputation(Future(LocalResponse(0))))
    // Result: LocalResponse = LocalResponse(0)
    Service.handle(RemoteComputation(Future(RemoteResponse("remote call"))))
    // Result: RemoteResponse = RemoteResponse(remote call)

    val future = Future{
      Thread.sleep(3000)
      println("future")
    }

    Thread.sleep(4000)







  }
}
