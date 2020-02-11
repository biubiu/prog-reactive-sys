//val f: String => String = {case "ping" => "pong"}


val f: PartialFunction[List[Int], String] = {
  case Nil => "one"
  case x::rest => rest match {
    case Nil => "two"
  }
}

f.isDefinedAt(List(1,2,3))

f.isDefinedAt(List(1))
f(List(1))