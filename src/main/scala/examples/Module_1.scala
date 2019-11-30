package examples

object Module_2 {
  def factorial(n: Int) = {

    @annotation.tailrec
    def loop(n: Int, acc: Int): Int = n compare 0 match {
      case 1 => loop(n - 1, n * acc)
      case _ => acc
    }

    loop(n, 1)
  }

  // execise 2.1
  def fibonacci(n: Int): Int = {

    @annotation.tailrec
    def loop(dec: Int)(n0: Int, n1: Int): Int =
      dec match {
        case n if (n <= 0)     => n0
        case dec if (dec <= 0) => n1
        case _                 => loop(dec - 1)(n1, n0 + n1)
      }

    loop(n)(0, 1)
  }

  private def formatResult(name: String, n: Int, f: Int => Int) =
    "the %s of %d is %d".format(name, n, f(n))

  def findFirst[A](arr: Array[A], cf: A => Boolean): Int = {
    @annotation.tailrec
    def loop(n: Int): Int = n match {
      case n if (n >= arr.length) => -1
      case n if (cf(arr(n)))      => n
      case _                      => loop(n + 1)
    }
    loop(0)
  }

  def isSorted[A](arr: Array[A], ordered: (A, A) => Boolean): Boolean = {
    @annotation.tailrec
    def loop(n: Int): Boolean = arr match {
      case arr if (n == arr.length)                     => true
      case arr if (ordered(arr(n), arr(n + 1)) == true) => loop(n + 1)
      case _                                            => false
    }
    loop(0)
  }

  // example higher order function
  def partial1[A, B, C](a: A, f: (A, B) => C): B => C =
    b => f(a, b)

  // exercise 2.3
  def curry[A, B, C](f: (A, B) => C): A => B => C =
    a => b => f(a, b)

  // exercise 2.4
  def uncurry[A, B, C](f: A => B => C): (A, B) => C =
    (a, b) => f(a)(b)

  // exercise 2.5
  def compose[A, B, C](f: B => C, g: A => B): A => C =
    a => f(g(a))

  def main(args: Array[String]): Unit = {}
}
