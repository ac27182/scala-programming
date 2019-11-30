package examples

object Module_3 {
  def main(args: Array[String]): Unit = {

    // exercise 3.1
    val x = List(1, 2, 3, 4, 5) match {
      case Cons(head, Cons(2, Cons(4, _)))             => head
      case Nil                                         => 42
      case Cons(head, Cons(tail, Cons(3, Cons(4, _)))) => head + tail
      case Cons(head, tail)                            => head + List.sum(tail)
      case _                                           => 101
    }
    println(x)
  }
}

//list datatype parametererized on some type A
sealed trait List[+A]
// a list constructor representing the empty list
final case object Nil extends List[Nothing] // identity
final case class Cons[+A](head: A, tail: List[A]) extends List[A]

// companion object
object List {

  def sum(ints: List[Int]): Int = ints match {
    case Nil              => 0
    case Cons(head, tail) => head + sum(tail)
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil              => 1.00
    case Cons(0.0, _)     => 0.00
    case Cons(head, tail) => head * product(tail)
  }

  def apply[A](arr: A*): List[A] =
    if (arr.isEmpty) Nil; else Cons(arr.head, apply(arr.tail: _*))
}
