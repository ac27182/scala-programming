package examples
import scala.annotation.tailrec

object Recursion {
  // implement factorial function
  // @tailrec
  def factorial(n: Int): Int = n match {
    case 0 => 1
    case n => n * factorial(n - 1)
  }
}
