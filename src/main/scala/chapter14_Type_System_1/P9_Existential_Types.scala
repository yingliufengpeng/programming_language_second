package chapter14_Type_System_1

object P9_Existential_Types {
  def main(args: Array[String]): Unit = {

  }

  object Doubler {
    // 由于类型擦除的原因,所以导致这样的问题的产生!!!
//    def double(seq: Seq[String]): Seq[Int] = double(seq map (_.toInt))
//    def double(seq: Seq[Int]): Seq[Int] = seq map (_*2)

    def double(seq: Seq[_]): Seq[Int] = seq match {
      case Nil => Nil
      case head +: tail => (toInt(head) * 2) +: double(tail)
    }
    private def toInt(x: Any): Int = x match {
      case i: Int => i
      case s: String => s.toInt
      case x => throw new RuntimeException(s"Unexpected list element $x")
    }

  }

}
