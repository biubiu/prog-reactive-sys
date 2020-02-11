/*
 * Copyright (C) 2019 Covata Limited or its affiliates
 *
 * Information contained within this file cannot be copied,
 * distributed and/or practised without the written consent of
 * Covata Limited or its affiliates.
 */

package example

import scala.annotation.tailrec

object MyModule {

  def abs(n: Int): Int =
    if (n < 0) -n
    else n

  private def formatAbs(x: Int) = {
    val msg = "The absolute value of %d is %d"
    msg.format(x, abs(x))
  }

  def factorial(n: Int): Int = {
    @tailrec
    def go(n: Int, acc: Int): Int = {
      if (n <= 0) acc
      else go(n - 1, n * acc)
    }
    go(n, 1)
  }

  def fib(n: Int): Int = {
    def go(n: Int, acc: Int): Int = {
      if (n <= 1) acc
      else go (n - 1, n + acc)
    }
    if (n <= 1) {
      n
    } else {
      go(n, 0)
    }
  }

  def formatResult(name: String, n: Int, f: Int => Int) = {
    val msg = "The %s of %d is %d."
    msg.format(name, n, f(n));
  }

  def isSorted[A](as: Array[A], ordered: (A, A) => Boolean) = {
    @tailrec
    def go(n: Int): Boolean =
      if(n >= as.length - 1) true
      else if (ordered (as(n + 1), as(n))) false
      else go(n + 1)

    go(0)
  }

  def partial1[A,B,C](a: A, f: (A, B) => C) : B => C = b => f(a, b)

  def curry[A,B,C](f: (A, B) => C): A => (B => C) = a => b => f(a, b)

  def uncurry[A,B,C](f: A => B => C): (A, B) => C = (a, b) => f(a)(b)

  def compose[A,B,C](f:B => C, g:A => B): A => C = a => f(g(a))

  def main(args: Array[String]) ={
    //println(formatAbs(-42))
    //println(factorial(3))
    //println(fib(3))
    //2.+(1)
    println(formatResult("absolute value", -42, abs));
    println(formatResult("fib value", 3, fib));
    val a = Array(1, 2, 3);

    println(isSorted(Array(1, 2, 3), (x: Int, y:Int) => x < y))

  }
}
