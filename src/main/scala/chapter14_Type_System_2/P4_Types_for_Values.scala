package chapter14_Type_System_2

/**
 * For completeness, the value types are parameterized types, singleton types, type projec‐
 * tions, type designators, compound types, existential types, tuple types, function types, and
 * infix types. Let’s review the last three types, because they provide convenient syntax
 * alternatives to the conventional way of writing the types. We’ll also cover a few details
 * that we haven’t seen already.
 */

object P4_Types_for_Values {

  def main(args: Array[String]): Unit = {

    // Tuple Types
    val t1: Tuple3[String, Int, Double] = ("one", 2, 3.14)
    val t2: (String, Int, Double) = ("one", 2, 3.14)

    // Function Types
    val f1: Function2[Int,Double,String] = (i,d) => s"int $i, double $d"
    val f2: (Int,Double) => String = (i,d) => s"int $i, double $d"

    // Infix Types
    val left1: Either[String,Int] = Left("hello")
    val left2: String Either Int = Left("hello")
    val right1: Either[String,Int] = Right(1)
    val right2: String Either Int = Right(2)   // 中缀类型的type着实好用的感觉



  }

}
