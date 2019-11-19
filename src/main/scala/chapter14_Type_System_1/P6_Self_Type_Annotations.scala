package chapter14_Type_System_1

import chapter14_Type_System_1.P6_Self_Type_Annotations.ButtonSubjectObserver.ObservableButton

object P6_Self_Type_Annotations {

  def main(args: Array[String]): Unit = {
    // buttons 则是我们要说的对象
    val buttons = Vector(new ObservableButton("one"), new ObservableButton("two"))

    // 这些则是我们去设定的观察者
    val observer = new ButtonClickObserver
    buttons foreach (_.addObserver(observer))
    for (_ <- 0 to 2) buttons(0).click()
    for (_ <- 0 to 4) buttons(1).click()
    println(observer.clicks)

  }

  abstract class SubjectObserver {  // 把对象观察者放在一个类型之中
    type S <: Subject       // 要观察的对象
    type O <: Observer      // 观察者  这种写法确实可以,但是我们可以通过自我引用类型来继续做限定的的操作
    trait Subject {
      self: S =>    // 自我引用类型的使用,需要思考这种依赖注射的思路与原理的作用机理
      private var observers = List[O]()   // 看来是一个对象可以有多个观察者,是这样的思路的情况的确定的事情
      def addObserver(observer: O): Unit = observers ::= observer
      def notifyObservers(): Unit = observers.foreach(_.receiveUpdate(self))
    }
    trait Observer { //
      def receiveUpdate(subject: S)
    }
  }

  case class Button(label: String) { //
    def click(): Unit = {}
  }

  // 该模式下具体使用的实例的思考的基础
  object ButtonSubjectObserver extends SubjectObserver { //
    type S = ObservableButton
    type O = ButtonObserver
    class ObservableButton(label: String) extends Button(label) with Subject {
      override def click(): Unit = {
        super.click()
        notifyObservers()
      }
    }
    trait ButtonObserver extends Observer {
      def receiveUpdate(button: ObservableButton)
    }
  }
  import ButtonSubjectObserver._
  class ButtonClickObserver extends ButtonObserver { //
    val clicks = new scala.collection.mutable.HashMap[String,Int]()
    def receiveUpdate(button: ObservableButton): Unit = {
      val count = clicks.getOrElse(button.label, 0) + 1
      clicks.update(button.label, count)
    }
  }

}
