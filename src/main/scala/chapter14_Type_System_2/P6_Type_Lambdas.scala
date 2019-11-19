package chapter14_Type_System_2
import scala.language.higherKinds

object P6_Type_Lambdas {
  trait Functor[A, +M[_]] {
    def map2[B](f: A => B): M[B]  // functor 函子
  }

  object Functor {
    implicit class SeqFunctor[A](seq: Seq[A]) extends Functor[A, Seq] {
      override def map2[B](f: A => B): Seq[B] = seq map f
    }

    implicit class OptionFunctor[A](opt: Option[A]) extends Functor[A, Option] {
      override def map2[B](f: A => B): Option[B] = opt map f
    }

    implicit class MapFunctor[K,V1](mapKV1: Map[K,V1]) //
      extends Functor[V1,({type λ[α] = Map[K,α]})#λ] { //
      def map2[V2](f: V1 => V2): Map[K,V2] = mapKV1 map {
        case (k,v) => (k,f(v))
      }
    }
  }


  def main(args: Array[String]): Unit = {
    import Functor._

    val m = Map("one" -> 1, "two" -> 2, "three" -> 3)
    val n = m map2 (_ * 2)
    println(s"n is $n")
  }
}
