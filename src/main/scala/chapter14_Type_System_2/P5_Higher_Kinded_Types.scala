package chapter14_Type_System_2
import scala.language.higherKinds


object P5_Higher_Kinded_Types {

  trait Add[T] {
    def add(t1: T, t2: T): T
  }

  object Add {
    implicit val addInt: Add[Int] = (i1: Int, i2: Int) => i1 + i2
    implicit val addIntIntPair: Add[(Int, Int)] = (p1: (Int, Int), p2: (Int, Int)) => (p1._1 + p2._1, p1._2 + p2._2)
    implicit val addOptiion: Add[Option[Int]] = (t1: Option[Int], t2: Option[Int]) => {
      val res = (t1.getOrElse(0) + t2.getOrElse(0))
      Some(res)
    }
  }

  // 我们的数据则是自己去找隐式转换的作用域的规则
  def sumSeq[T: Add](seq: Seq[T]): T = seq.reduce(implicitly[Add[T]].add(_, _))

  trait Reduce[T, -M[_]] {
    def reduce(m: M[T])(f: (T, T) => T): T
  }

  object Reduce { //
    implicit def seqReduce[T]: Reduce[T, Seq] = new Reduce[T, Seq] {
      def reduce(seq: Seq[T])(f: (T, T) => T): T = seq reduce f
    }
    implicit def optionReduce[T]: Reduce[T, Option] = new Reduce[T, Option] {
      def reduce(opt: Option[T])(f: (T, T) => T): T = opt reduce f
    }
  }

  def sum[T: Add, M[_]](container: M[T])(implicit red: Reduce[T, M]): T = {
    red.reduce(container)(implicitly[Add[T]].add)
  }

  trait Reduce2[-M[_]] {
    def reduce[T](m: M[T])(f: (T, T) => T): T
  }

  object Reduce2 { //
    implicit val Reduce2: Reduce2[Seq] = new Reduce2[Seq] {
      override def reduce[T](m: Seq[T])(f: (T, T) => T): T = m reduce f
    }
    implicit val optionReduce2: Reduce2[Option] = new Reduce2[Option] {
      def reduce[T](opt: Option[T])(f: (T, T) => T): T = opt reduce f
    }
  }

  def sum2[T: Add, M[_]: Reduce2](container: M[T]): T = {
    implicitly[Reduce2[M]].reduce(container)(implicitly[Add[T]].add)
  }

  def main(args: Array[String]): Unit = {

//    sumSeq(1 to 10)
//    sumSeq(Seq(Option(4)))

    val r1 = sum(1 to 100)
    println(s"r1 is $r1")

    val r2 = sum(Vector(1 -> 10, 2 -> 30, 3 -> 30))
    println(s"r2 is $r2")

//    val r3 = sum[Int, Option](None)
//    println(s"r3 is $r3")


    val r12 = sum2(1 to 100)
    println(s"r12 is $r12")
  }

}
