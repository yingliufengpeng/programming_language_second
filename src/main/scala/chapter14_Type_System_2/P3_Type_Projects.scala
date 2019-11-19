package chapter14_Type_System_2

object P3_Type_Projects {

  /**
   * Finally, all the simpler type specifications we write every day are called type designa‐
   * tors. They are actually shorthand forms for type projections. Here are a few examples
   * of designators and their longer projections, adapted from The Scala Language
   * Specification, Section 3.2:
   * Int // scala.type#Int
   * scala.Int // scala.type#Int
   * package pkg {
   * class MyClass {
   * type t // pkg.MyClass.type#t
   * }
   * }
   */


  def main(args: Array[String]): Unit = {

//    val L1: Service1#Log = new ConsoleLogger
//
//    val m = new MM.MyClass
//    println(m)
    printFoo(Foo)
  }

  def printFoo(foo: Foo.type): Unit = println(foo)

  case object Foo {
    override def toString: String = "Foo says Hello!"
  }

  object MM {
    class MyClass {
      type t
    }
  }

  trait Logger {
    def log(message: String): Unit
  }

  class ConsoleLogger extends Logger {
    override def log(message: String): Unit = println(s"log: $message")
  }

  trait Service {
    type Log <: Logger
    val logger: Log
  }

  class Service1 extends Service {
    type Log = ConsoleLogger
    override val logger: ConsoleLogger = new ConsoleLogger
  }








}
