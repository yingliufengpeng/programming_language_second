package chapter14_Type_System_1
import scala.language.implicitConversions


object P2_View_Bounds2 {
  object Serialization {
    case class Ram(value: Any) {
      def serialized: String = s"-- $value --" //
    }
    type Writable[T] = T => Ram
    implicit val fromInt: Writable[Int] = e => Ram(e)
    implicit val fromFloat: Writable[Float] = Ram(_)
    implicit val fromString: Writable[String] = Ram(_)
    implicit val fromTuple2: Writable[(Int, Int)] = Ram(_)
  }
  import Serialization._
  object RemoteConnection { //
//    def write[T](t: T)(implicit ev$1: T => Writable): Unit = //
    def write[T: Writable](t: T): Unit = // 其实回到原来的思路中则是需要Writeable[T]这样的类型
//    def write[T <% Writable](t: T): Unit = //
      println(t.serialized) // Use stdout as the "remote connection"
  }
  RemoteConnection.write(100) // Prints -- 100 --
  RemoteConnection.write(3.14f) // Prints -- 3.14 --
  RemoteConnection.write("hello!") // Prints -- hello! --
  RemoteConnection.write((1, 2))


  def main(args: Array[String]): Unit = {

  }
}
