package week2.bank

import akka.actor.Actor
import akka.event.LoggingReceive
import week2.bank.BankAccount.{Deposit, Done, Failed, Withdraw}

object BankAccount {
  case class Deposit(amount: BigInt) {
    require(amount > 0)
  }
  case class Withdraw(amount: BigInt) {
    require(amount > 0)
  }
  case object Done
  case object Failed
}

class BankAccount extends Actor {
  var balance = BigInt(0)
  override def receive = LoggingReceive {
    case Deposit(amount) => balance += amount
                            sender ! Done
    case Withdraw(amount) if amount <= balance => balance -= amount
                            sender ! Done
    case _ => Failed
  }
}

