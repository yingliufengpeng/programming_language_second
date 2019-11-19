package chapter14_Type_System_2

object P7_Self_Recursive_Types_F_Bouned_Polymorhpism {

  trait Parent[T <: Parent[T]] {
    def make: T   // 这类类型很方便做树形/图结构的考量!!!
  }

  case class Child1(s: String) extends Parent[Child1] {
    override def make: Child1 = Child1(s"Child1: make: $s")
  }

  case class Child2(s: String) extends Parent[Child2] {
    override def make: Child2 = Child2(s"Child2: make: $s")
  }

  def main(args: Array[String]): Unit = {

    val c1 = Child1("c1")           // c1: Child1 = Child1(c1)
    val c2 = Child2("c2")           // c2: Child2 = Child2(c2)
    val c11 = c1.make               // c11: Child1 = Child1(Child1: make: c1)
    val c22 = c2.make               // c22: Child2 = Child2(Child2: make: c2)
    val p1: Parent[Child1] = c1     // p1: Parent[Child1] = Child1(c1)
    val p2: Parent[Child2] = c2     // p2: Parent[Child2] = Child2(c2)
    val p11 = p1.make               // p11: Child1 = Child1(Child1: make: c1)
    val p22 = p2.make               // p22: Child2 = Child2(Child2: make: c2)



  }
}
