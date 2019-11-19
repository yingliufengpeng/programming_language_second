package chapter14_Type_System_1
import scala.language.reflectiveCalls //
object P7_Structal_Types {
  def main(args: Array[String]): Unit = {

    object observer { //
      def receiveUpdate(state: Any): Unit = println("got one! " + state)
    }

    val subject = new Subject { //
      type State = Int
      protected var count = 0
      def increment(): Unit = {
        count += 1
        notifyObservers(count)
      }
    }   // 现在明白一些refinement 类型的意思了,正如subject没有加类型注释一样,
                                      // 在运行时期,有些方法只能通过反射角度来解决此类的问题!!!
    subject.increment()
    subject.increment()
    subject.addObserver(observer)
    subject.increment()
    subject.increment()
    subject.increment()
  }

  trait Subject { //
    type State //
    // 继续对我们的代码就行结构的策略的要求
    type Observer = { def receiveUpdate(state: Any): Unit } //
//    type Observer = State => Unit

    private var observers: List[Observer] = Nil //
    def addObserver(observer:Observer): Unit =
      observers ::= observer
    def notifyObservers(state: State): Unit =
      observers foreach (_.receiveUpdate(state))
  }


}
