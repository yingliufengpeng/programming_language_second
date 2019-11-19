package chapter24_Metaprogramming_Macros_and_Reflection

import scala.reflect.ClassTag

object P2_Runtime_Reflection {

  trait T[A] {
    val vT: A
    def mT = vT
  }

  class C(foo: Int) extends T[String] {
    val vC = "C"
    override val vT: String = "T"
    def mC: String = vC

    class C2
  }

  def main(args: Array[String]): Unit = {

      // section 1
//    val c = new C(3)
//    val clazz = classOf[C]
//    println(s"clazz is $clazz")
//    val clazz2 = c.getClass
//    println(s"clazz2 is $clazz2")
//    val name = clazz.getName
//    println(s"name is $name")
//    val methods = clazz.getMethods
//    println(s"methods is $methods")
//    val ctors = clazz.getConstructors
//    println(s"ctors is $ctors")
//    val fields = clazz.getFields
//    println(s"fields is $fields")
//    val annos = clazz.getAnnotations
//    println(s"annos is $annos")
//    val parentInterfaces = clazz.getInterfaces
//    println(s"parentINterfaces is $parentInterfaces")
//    val superClass = clazz.getSuperclass
//    println(s"superClass is $superClass")
//    val typeParams = clazz.getTypeParameters
//    println(s"typeParams is $typeParams")
//
//    val res = c.isInstanceOf[String]
//    println(s"res is $res")
//    val res2 = c.isInstanceOf[C]
//    println(s"res2 is $res2")

    // section 2
//    Seq(
//      Seq(5.5,5.6,5.7),
//      Seq("a", "b"), //
//      Seq("222", 3, 3.14),
//      Nil) foreach {
//      case seq: Seq[_] => println("%20s: %s".format(seq, check(seq)))
//      case x => println("%20s: %s".format(x, "unknown!"))
//    }

    val r = mkArray(1,2,3)
    println(s"r is ${r.toList}")
    val r2 = mkArray("one", "two", "three")
    println(s"r2 is ${r2.toList}")
    val r3 = mkArray(1, "two", 3.14)
    println(s"r3 is ${r3.toList}")
  }

  /**
   * Another important usage for  ClassTag is to construct Java  Array s of the correct  Any
   * Ref subtype. Here is an example adapted from the Scaladoc page for  ClassTag
   *
   */
    def mkArray[T: ClassTag](elems: T*): Array[T] = Array[T](elems:_*)


  def useClassTag[T: ClassTag](seq: Seq[T]): String = seq match {
    case Nil => "Nothing"
    case head +: _ => implicitly(head).getClass.toString // 其实的确不能够忘记,implicitly本省的隐式参数也是可以显式传输!!!
  }

  def check(seq: Seq[_]): String = s"Seq: ${useClassTag(seq)}"

}
