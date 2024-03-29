package chapter14_Type_System_1

object P2_Type_Bounds {

  class Parent(val value: Int) { //
    override def toString = s"${this.getClass.getName}($value)"
  }
  class Child(value: Int) extends Parent(value)

  def main(args: Array[String]): Unit = {

    val op1: Option[Parent] = Option(new Child(1)) //  Some(Child(1))
    val p1: Parent = op1.getOrElse(new Parent(10)) // Result: Child(1)
    val op2: Option[Parent] = Option[Parent](null) //  None
    val p2a: Parent = op2.getOrElse(new Parent(10)) // Result: Parent(10)
    val p2b: Parent = op2.getOrElse(new Child(100)) // Result: Child(100)
    val op3: Option[Parent] = Option[Child](null) //  None
    val p3a: Parent = op3.getOrElse(new Parent(20)) // Result: Parent(20)
    val p3b: Parent = op3.getOrElse(new Child(200)) // Result: Child(200)
  }

}
