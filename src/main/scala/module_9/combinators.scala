package module_9

object combinators extends App {
  trait Monoid[A] {
    def op(a1: A, a2: A): A
    def zero: A

  }
  // object Monoid {
  //   def op[A](a1: A, a2: A)(implicit monoidInstance: Monoid[A]): A =
  //     monoidInstance.op(a2, a2)
  //   def zero[A](implicit monoidInstance: Monoid[A]): A =
  //     monoidInstance.zero
  // }
  object Monoid {

    val stringMonoid = new Monoid[String] {
      def op(a1: String, a2: String): String = a1 + a2
      def zero: String                       = ""
    }

    def listMonoid[A] = new Monoid[List[A]] {
      def op(a1: List[A], a2: List[A]) = a1 ++ a2
      val zero                         = Nil
    }

    // exercise 10.1
    val intMonoidAddition = new Monoid[Int] {
      def op(a1: Int, a2: Int): Int = a1 + a2
      def zero: Int                 = 0
    }

    val intMonoidMultiplication = new Monoid[Int] {
      def op(a1: Int, a2: Int): Int = a1 * a2
      def zero: Int                 = 0
    }
    val booleanOrMonoid = new Monoid[Boolean] {
      def op(a1: Boolean, a2: Boolean): Boolean = a1 || a2
      def zero: Boolean                         = true
    }
    val booleanAndMonoid = new Monoid[Boolean] {
      def op(a1: Boolean, a2: Boolean): Boolean = a1 && a2
      def zero: Boolean                         = true
    }
    // exercise 10.2
    def optionMonoid[A]: Monoid[Option[A]] = new Monoid[Option[A]] {
      def op(a1: Option[A], a2: Option[A]): Option[A] = a1 orElse a2
      def zero: Option[A]                             = None
    }

    // exercise 10.3
    def endofunctorMonoid[A]: Monoid[A => A] = new Monoid[A => A] {
      def op(f: A => A, g: A => A): A => A = f compose g
      val zero                             = (a: A) => a
    }

    // def foldRight[A, B](z: B)(f: (A, B) => B): B = ???
    // def foldLeft[A, B](z: B)(f: (B, A) => B): B  = ???

    // def foldRight[A](z: A)(f: (A => A) => A): A = ???
    // def foldLeft[A](z: A)(f: (A => A) => A): A  = ???

    def concatenate[A](as: List[A], m: Monoid[A]): A =
      as.foldLeft(m.zero)(m.op)

    // exercise 10.5
    def foldMap[A, B](as: List[A], m: Monoid[B])(f: A => B): B =
      as.foldLeft(m.zero)((b, a) => m.op(b, f(a)))

    // Exercise 10.6

    // exercise 10.7
    def foldMapV[A, B](as: IndexedSeq[A], m: Monoid[B])(f: A => B): B =
      if (as.length == 0) {
        m.zero
      } else if (as.length == 1) {
        f(as(0))
      } else {
        val (l, r) = as.splitAt(as.length / 2)
        m.op(foldMapV(l, m)(f), foldMapV(r, m)(f))
      }

  }

}
