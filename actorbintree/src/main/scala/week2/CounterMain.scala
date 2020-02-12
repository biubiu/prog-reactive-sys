package week2

import akka.actor.{Actor, Props}
import week2.bank.Counter

class CounterMain extends Actor{

  val counter = context.actorOf(Props[Counter], "counter")

  counter ! "incr"
  counter ! "incr"
  counter ! "get"

  override def receive: Receive = {
    case count: Int =>
      println(s"count $count")
      context.stop(self)
  }
}
