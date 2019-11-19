package chapter24_Metaprogramming_Macros_and_Reflection
import scala.reflect.runtime.universe
import scala.reflect.runtime.universe._
object P3_Scala_Advanced_Runtime_Reflection_API {

  def toType2[T](t: T)(implicit tag: TypeTag[T]): Type = tag.tpe
  def toType[T: TypeTag](t: T): universe.Type = typeOf[T]

  class CSuper { def msuper(): Unit = println("CSuper")}
  class C extends CSuper { def m(): Unit = println("C")}
  class CSub extends C { def msub(): Unit = println("Csub")}

  type K = Int

  def main(args: Array[String]): Unit = {
    // section 1
      // We can compare typs for equality or parent-child relationships
//    val m = toType(1) =:= typeOf[Int]
//    println(s"m is $m")
//    val m2 = toType("1") =:= typeOf[String]
//    println(s"m2 is $m2")
//
//    val m4 = typeOf[Seq[Int]] =:= typeOf[Seq[Any]]
//    val m5 = typeOf[Seq[Int]] <:< typeOf[Seq[Any]]
//    println(s"m4 is $m4, m5 is $m5")

    // section 2
//
//    val n = typeOf[K] == typeOf[Int]
//    println(s"n is $n")

//    typeOf[C => C ] =:= typeOf[C => C] // true
//    typeOf[CSuper => CSub ] =:= typeOf[C => C] // false
//    typeOf[CSub => CSuper] =:= typeOf[C => C] // false
//    typeOf[C => C ] <:< typeOf[C => C] // true
//    typeOf[CSuper => CSub ] <:< typeOf[C => C] // true
//    typeOf[CSub => CSuper] <:< typeOf[C => C] // false

//    val r = toTypeRefInfo(1)
//    println(s"r is $r")
//
//    val r2 = toTypeRefInfo(true)
//    println(s"r is $r2")
//
//    val r3 = toTypeRefInfo(Seq(1, true, 3.14))
//    println(s"r is $r3")
//
//    val r4 = toTypeRefInfo((i: Int) => i.toString)
//    println(s"r is $r4")

    val ts = toType(Seq(1, true, 3.14))
    println(s"ts is $ts")

    println(s"ts.typeSymbol ${ts.typeSymbol}")
    println(s"ts.erasure ${ts.erasure}")
    println(s"ts.baseClasses ${ts.baseClasses}")
    println(s"ts.companion ${ts.companion}")
    println(s"ts.decls ${ts.decls}")
    println(s"ts.members ${ts.members}")
  }

  def toTypeRefInfo[T: TypeTag](x: T): (Type, Symbol, Seq[Type]) = {
    val TypeRef(pre, typeName, parems) = toType(x)
    (pre, typeName, parems)
  }






}
