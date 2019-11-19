package chapter14_Type_System_2

object P1_Path_Depenent_Types {

  class Service { //
    class Logger {
      def log(message: String): Unit = println(s"log: $message") //
    }
    val logger: Service#Logger = new Logger
  }

  class C1 {
    var x = "1"
    def setX1(x:String): Unit = this.x = x
    def setX2(x:String): Unit = C1.this.x = x
  }

  trait T1 {
    class C
    val c1: C = new C
    val c2: C = new this.C
  }

  trait X {
    def setXX(x:String): Unit = {} // Do Nothing!
  }
  class C2 extends C1
  class C3 extends C2 with X {
    def setX3(x:String): Unit = super.setX1(x)
    def setX4(x:String): Unit = C3.super.setX1(x)
    def setX5(x:String): Unit = C3.super[C2].setX1(x)
    def setX6(x:String): Unit = C3.super[X].setXX(x)
    // def setX7(x:String): Unit = C3.super[C1].setX1(x) // ERROR
    // def setX8(x:String): Unit = C3.super.super.setX1(x) // ERROR
  }

  class C4 {
    class C5
  }
  class C6 extends C4 {
    val c5a: C5 = new C5
    val c5b: C5 = new super.C5
  }



  def main(args: Array[String]): Unit = {

    val s1 = new Service

    val s2 = new s1.Logger

    val s3 = new Service { override val logger = s1.logger }


  }

}
