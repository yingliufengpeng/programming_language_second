package chapter14_Type_System_1
import scala.reflect.runtime.universe
import scala.reflect.runtime.universe._
object P1_Parameterized_Types {
  def main(args: Array[String]): Unit = {
//    val m = (1 to 10).toList
//    m.foreach(println)
//    val n = m +: (1 to 20)
//    n.foreach(println)

//    val op1: Option[Parent] = Option(new Child(1)) //  Some(Child(1))
//    val p1: Parent = op1.getOrElse(new Parent(10))


//    val op2: Option[Parent] = Option[Parent](null) //  None
//    val p2a  = op2.getOrElse(new Parent(10)) // Result: Parent(10)
//    val p2b = op2.getOrElse(new Child(100))
//
//    val op3: Option[Parent] = Option[Child](null)

//    val mylist = MyList(List(3, 4, 5))
//    val n = mylist.sortBy1(e => -e)
//    val m = mylist.sortBy2(-_)
//
//    n.foreach(println)
//    m.foreach(println)

    val p = new Person3[AnyRef]
    val child = new Person3[String]

    p.test(333)
    child.test("Kk")

    val p2: Person3[AnyRef] = child
    p2.test(33)

    val i = 0

  }
  class Person {
    def show(): Unit = {
      println("Person show")
    }
  }
  class SubPerson extends Person {
    override def show(): Unit = {
      println("SubPerson show")
    }
  }

  class Person3[+A: TypeTag] {

    def test[B >: A : TypeTag](b: B): Unit = {
      println(s"this class is ${getTypeTag(this)}")
    }
  }

  def getTypeTag[T: TypeTag](t: T): universe.Type = typeOf[TypeTag[T]]

  case class MyList[A](list: List[A]) {
    def sortBy1[B](f: A => B)(implicit ord: Ordering[B]): List[A] =
      list.sortBy(f)(ord)
    def sortBy2[B : Ordering](f: A => B): List[A] =
      list.sortBy(f)(implicitly[Ordering[B]])
  }

  class Upper
  class Middle1 extends Upper
  class Middle2 extends Middle1
  class Lower extends Middle2
  case class C[A >: Lower <: Upper](a: A)
  //case class C2[A <: Upper >: Lower](a: A) // Does not compile


  class Parent(val value: Int) { //
    override def toString = s"${this.getClass.getName}($value)"
  }
  class Child(value: Int) extends Parent(value)
}


