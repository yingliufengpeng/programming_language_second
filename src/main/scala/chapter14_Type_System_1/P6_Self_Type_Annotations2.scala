package chapter14_Type_System_1


object P6_Self_Type_Annotations2 {

  def main(args: Array[String]): Unit = {
//    object obj extends App with Database with BizLogic with WebUI
//    obj.run()

    val c1 = new C1
    c1.talk("Hello") //
    c1.c2.c3.talk("World")
  }

  class C1 { self => //
    def talk(message: String): Unit = println("C1.talk: " + message)
    class C2 {
      class C3 {
        def talk(message: String): Unit = self.talk("C3.talk: " + message) //
      }
      val c3 = new C3
    }
    val c2 = new C2
  }

  trait Persistence { def startPersistence(): Unit } //
  trait Midtier { def startMidtier(): Unit }
  trait UI { def startUI(): Unit }


  trait Database extends Persistence { //
    def startPersistence(): Unit = println("Starting Database")
  }
  trait BizLogic extends Midtier {
    def startMidtier(): Unit = println("Starting BizLogic")
  }
  trait WebUI extends UI {
    def startUI(): Unit = println("Starting WebUI")
  }
  trait App { self: Persistence with Midtier with UI => // 自引用模型,并没有参与继承链条的关系
    def run(): Unit = {
      startPersistence()
      startMidtier()
      startUI()
    }
  }



}
