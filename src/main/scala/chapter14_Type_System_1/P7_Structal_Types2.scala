package chapter14_Type_System_1

import scala.language.reflectiveCalls //
object P7_Structal_Types2 {
  def main(args: Array[String]): Unit = {

    val observer: Int => Unit = (state: Int) => println("got one! " + state)

    val subject = new SubjectFunc { //
      type State = Int
      protected var count = 0
      def increment(): Unit = {
        count += 1
        notifyObservers(count)
      }
    }
    subject.increment()
    subject.increment()
    subject.addObserver(observer)
    subject.increment()
    subject.increment()
    subject.increment()
  }

  trait SubjectFunc { //
    type State
    type Observer = State => Unit // 现在收集的则是一系列的function使用,这种用法或许是对我来说使用起来最佳的一种方案
    private var observers: List[Observer] = Nil
    def addObserver(observer:Observer): Unit =
      observers ::= observer
    def notifyObservers(state: State): Unit = //
      observers foreach (o => o(state))
  }


}
