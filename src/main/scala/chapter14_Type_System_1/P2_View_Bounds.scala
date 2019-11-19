package chapter14_Type_System_1

object P2_View_Bounds {

  import scala.language.implicitConversions
  object Serialization {
    case class Writable(value: Any) {
      def serialized: String = s"-- $value --" //
    }
    implicit def fromInt(i: Int): Writable = Writable(i) //
    implicit def fromFloat(f: Float): Writable = Writable(f)
    implicit def fromString(s: String): Writable = Writable(s)
    implicit def fromString(s:(Int, Int)): Writable = {
      Writable(s)
    }
  }
  import Serialization._
  object RemoteConnection { //
//    def write[T](t: T)(implicit ev$1: T => Writable): Unit = //
    def write[T <% Writable](t: T): Unit = //
      println(t.serialized) // Use stdout as the "remote connection"
  }
  RemoteConnection.write(100) // Prints -- 100 --
  RemoteConnection.write(3.14f) // Prints -- 3.14 --
  RemoteConnection.write("hello!") // Prints -- hello! --
  RemoteConnection.write((1, 2))


  def main(args: Array[String]): Unit = {

  }
}
