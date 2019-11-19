package chapter24_Metaprogramming_Macros_and_Reflection
import scala.reflect.runtime.universe
import scala.reflect.runtime.universe._
object P1_Tools_for_Understanding_Types {
  def getType[T: TypeTag](t: T): universe.Type = typeOf[T]

  def main(args: Array[String]): Unit = {
    val r1 = if (true) false else 1.1
    val t = getType(r1)
    println(s"the type of t is $t")
  }
}
