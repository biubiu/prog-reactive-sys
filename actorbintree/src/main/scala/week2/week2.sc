import akka.actor.{Actor, ActorRef, Props}

class BankAccount {
  private var balance = 0

  def deposit(amount: Int):Unit =
    if (amount > 0) balance = balance + amount

  def withdraw(amount: Int): Int = {
    if (0 < amount && amount <= balance) {
      balance = balance - amount
      balance
    } else throw new Error("Insufficient amount")
  }
}

class SyncBankAccount {
  private var balance = 0

  def deposit(amount: Int):Unit = this.synchronized {
    if (amount > 0) balance = balance + amount
  }

  def withdraw(amount: Int): Int = this.synchronized {
    if (0 < amount && amount <= balance) {
      balance = balance - amount
      balance
    } else throw new Error("Insufficient amount")
  }

  def transfer(from: SyncBankAccount, to: SyncBankAccount, amount: Int): Unit = {
    from.synchronized {
      to.synchronized {
        from.withdraw(amount)
        to.deposit(amount)
      }
    }
  }
}

class Counter extends Actor {
  var count = 0

  def receive: Receive = {
    case "incr" => count += 1
    case ("get", customer: ActorRef) => customer ! count
  }
}

class CounterMain extends Actor {
  val counter = context.actorOf(Props[Counter], "counter")

  counter ! "incr"
  counter ! "incr"
  counter ! "incr"
  counter ! "get"

  override def receive: Receive = {
    case count: Int =>
      println(s"count was, $count")
      context.stop(self)
  }
}