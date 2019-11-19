package chapter24_Metaprogramming_Macros_and_Reflection
import reflect.runtime.universe._
import reflect.runtime.currentMirror
import tools.reflect.ToolBox
import scala.reflect.macros.blackbox.Context

object P4_Macros {

//  object invariant {
//    case class InvariantFailure(msg: String) extends RuntimeException(msg)
//    def apply[T](predicate: => Boolean)(block: => T): T = macro impl
//
//    def impl(c: Context)(predicate: c.Tree)(block: c.Tree): c.universe.Tree = {
//      import c.universe._
//      val predStr = showCode(predicate)
//      val q"..$stmts" = block
//      val invariantStmts = stmts.flatMap{ stmt =>
//        val msg = s"FAILURE! $predStr == false, for statement: " + showCode(stmt)
//        val tif = q"throw new metaprogramming.invariant.InvariantFailure($msg)"
//        val predq2 = q"if (false == $predicate) $tif"
//        List(q"{val tmp = $stmt; $predq2; tmp}")
//      }
//
//      val tif = q"throw new metaprogramming.invariant.InvariantFailure($predStr)"
//      val predq = q"if (false == $predicate) $tif"
//      q"$predq; ..$invariantStmts"
//    }
//
//  }
//
//  object invariant2 { //
//    case class InvariantFailure(msg: String) extends RuntimeException(msg)
//    def apply[T](predicate: => Boolean)(block: => T): T = macro impl //
//    def impl(c: Context)(predicate: c.Tree)(block: c.Tree) = { //
//      import c.universe._ //
//      val predStr = showCode(predicate) //
//      val q"..$stmts" = block //
//      val invariantStmts = stmts.flatMap { stmt => //
//        val msg = s"FAILURE! $predStr == false, for statement: " + showCode(stmt)
//        val tif = q"throw new metaprogramming.invariant.InvariantFailure($msg)"
//        val predq2 = q"if (false == $predicate) $tif"
//        List(q"{ val tmp = $stmt; $predq2; tmp };")
//      }
//      val tif = q"throw new metaprogramming.invariant.InvariantFailure($predStr)"
//      val predq = q"if (false == $predicate) $tif"
//      q"$predq; ..$invariantStmts" //
//    }
//  }

  def main(args: Array[String]): Unit = {

//    val toolBox = currentMirror.mkToolBox()
//    val C = q"case class C(s: String)"
//    println(s"showCode ${showCode(C)}")
//    println(s"showRaw ${showRaw(C)}")

//    Seq(tq"Int", tq"String") map {
//      param =>
//        q"case class C(s: $param)"
//    } foreach { q =>
//      println(showRaw(q))
//    }

//    val list = Seq(1, 2, 3, 4)
//    val fmt = "%d, %d, %d, %d"
//    val printq = q"println($fmt, ..$list)"
//    printq.foreach(q => println(showCode(q)))
//    println(showCode(printq))
//
//    val q"${i: Int} + ${d: Double}" = q"1 + 3.14"
//    val res = Array(i, d)
//    res.foreach(println)

  }
}
