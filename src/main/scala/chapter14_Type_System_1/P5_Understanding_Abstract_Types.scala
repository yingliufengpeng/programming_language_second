package chapter14_Type_System_1

import java.io.File


object P5_Understanding_Abstract_Types {
  def main(args: Array[String]): Unit = {
    example.show()
  }

  abstract class SubjectObserver {  // 把对象观察者放在一个类型之中
    type S <: Subject       // 要观察的对象
    type O <: Observer      // 观察者  这种写法确实可以,但是我们可以通过自我引用类型来继续做限定的的操作
    trait Subject {
      private var observers = List[Observer]()   // 看来是一个对象可以有多个观察者,是这样的思路的情况的确定的事情
      def addObserver(observer: Observer): Unit = observers ::= observer
      def notifyObservers(): Unit = observers.foreach(_.receiveUpdate(this))
    }
    trait Observer { //
      def receiveUpdate(subject: Subject)
    }
  }

  abstract class BulkReader {
    type In
    val source: In
    def read: String // Read and return a String
  }
  class StringBulkReader(val source: String) extends BulkReader {
    type In = String
    def read: String = source
  }
  class FileBulkReader(val source: File) extends BulkReader {
    type In = File
    def read: String = ""
  }

  trait exampleTrait {
    type t1 // t1 is unconstrained
    type t2 >: t3 <: t1 // t2 must be a supertype of t3 and a subtype of t1
    type t3 <: t1 // t3 must be a subtype of t1
    type t4 <: Seq[t1] // t4 must be a subtype of Seq of t1
    // type t5 = +AnyRef // ERROR: Can't use variance annotations
    val v1: t1 // Can't initialize until t1 defined.
    val v2: t2 // ditto...
    val v3: t3 // ...
    val v4: t4 // ...
  }

  trait T1 { val name1: String }
  trait T2 extends T1 { val name2: String }
  case class C(name1: String, name2: String) extends T2

  object example extends exampleTrait {
    type t1 = T1
    type t2 = T2
    type t3 = C
    type t4 = Vector[T1]
    val v1 = new T1 { val name1 = "T1"}
    val v2 = new T2 { val name1 = "T1"; val name2 = "T2" }
    val v3 = C("1", "2")
    val v4 = Vector(C("3", "4"))

    def show(): Unit = {
      val m = Array(v1, v2, v3, v4)
      m.foreach(println)
    }
  }

}
