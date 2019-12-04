package module_5
import scala.collection.immutable.Nil

object module_5 {

  sealed trait Stream[+A] {
    // exercise 5.1
    def toList: List[A] =
      this match {
        case Empty      => List()
        case Cons(h, t) => h() :: (t() toList)
      }

    // exercise 5.2
    def take(n: Int): Stream[A] =
      this match {
        case Cons(h, t) if n > 0 => cons(h(), t() take (n - 1))
        case _                   => this
      }

    // exercise 5.2
    def drop(n: Int): Stream[A] =
      this match {
        case Cons(_, t) if n > 0 => t() drop (n - 1)
        case _                   => this
      }

    // exercise 5.3
    def takeWhile(p: A => Boolean): Stream[A] =
      this match {
        case Cons(h, t) if p(h()) => cons(h(), t() takeWhile p)
        case _                    => this
      }

    // exercise 5.4
    def foldRight[B](z: => B)(f: (A, => B) => B): B =
      this match {
        case Cons(h, t) => f(h(), t().foldRight(z)(f))
        case Empty      => z
      }

    //exercise 5.5
    def forAll(p: A => Boolean): Boolean =
      foldRight(true)((a, b) => p(a) && b)

    def cons[A](hd: => A, tl: => Stream[A]): Stream[A] = {
      lazy val head = hd
      lazy val tail = tl
      Cons(() => head, () => tail)
    }

    def empty[A]: Stream[A] = Empty

    def apply[A](as: A*): Stream[A] =
      if (as.isEmpty) empty
      else cons(as.head, apply(as.tail: _*))

  }
  case object Empty extends Stream[Nothing]
  case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]

  // object Stream {
  // }

  def main(agrs: Array[String]): Unit = {
    println("> module 5: strictness and lazyness")
    println(Stream(1, 2, 3, 4, 5, 6, 7) takeWhile (n => n != 5) toList)
  }

}
