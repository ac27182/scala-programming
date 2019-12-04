package examples
import scala.collection.immutable.Nil

object Module_4 {
  // custom option trait
  sealed trait Option[+A] {
    def map[B](f: A => B): Option[B] =
      this match {
        case Some(get) => Some(f(get))
        case None      => None
      }

    def getOrElse[B >: A](default: => B): B =
      this match {
        case Some(get) => get
        case None      => default
      }

    def flatMap[B](f: A => Option[B]): Option[B] =
      map(f) getOrElse None

    def orElse[B >: A](ob: => Option[B]): Option[B] =
      this map (Some(_)) getOrElse ob

    def filter(f: A => Boolean): Option[A] =
      flatMap(x => if (f(x)) Some(x) else None)
  }
  final case class Some[+A](get: A) extends Option[A]
  final case object None extends Option[Nothing]

  def mean(xs: Seq[Double]): Option[Double] =
    if (xs.isEmpty) None
    else Some((xs sum) / (xs length))

  // exercise 4.2
  def variance(xs: Seq[Double]): Option[Double] =
    mean(xs) flatMap (m => mean(xs map (x => math pow (x - m, 2))))

  // exercise 4.3
  def map2[X, Y, Z](x: Option[X], y: Option[Y])(f: (X, Y) => Z): Option[Z] =
    x flatMap (`x` => y map (`y` => f(`x`, `y`)))

  def map3[A, B, C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] =
    for {
      aa <- a //flatmap
      bb <- b //map
    } yield f(aa, bb) //return

  // exercise 4.4
  def sequence[A](a: List[Option[A]]): Option[List[A]] =
    a match {
      case h :: t => h flatMap (hh => sequence(t) map (hh :: _))
      case Nil    => Some(Nil)
    }

  def main(args: Array[String]): Unit = {}
}

sealed trait Either[+E, +A] {
  // exercise 4.6
  def map[B](f: A => B): Either[E, B] =
    this match {
      case Left(value)  => Left(value)
      case Right(value) => Right(f(value))
    }

  // exercise 4.6
  def flatMap[EE >: E, B](f: A => Either[EE, B]): Either[EE, B] =
    this match {
      case Left(value)  => Left(value)
      case Right(value) => f(value)
    }

  // exercise 4.6
  def orElse[EE >: E, B >: A](b: => Either[EE, B]): Either[EE, B] =
    this match {
      case Left(_)      => b;
      case Right(value) => Right(value)
    }

  // exercise 4.6
  def map2[EE >: E, B, C](b: Either[EE, B])(f: (A, B) => C): Either[EE, C] =
    for { a0 <- this; b0 <- b } yield f(a0, b0)

  // exercise 4.7
  def sequence[E, A](es: List[Either[E, A]]): Either[E, List[A]] =
    traverse(es)(x => x)

  // exercise 4.7
  def traverse[E, A, B](as: List[A])(f: A => Either[E, B]): Either[E, List[B]] =
    as match {
      case head :: tail => (f(head) map2 traverse(tail)(f))(_ :: _)
      case Nil          => Right(Nil)
    }

}
case class Left[+E](value: E) extends Either[E, Nothing]
case class Right[+A](value: A) extends Either[Nothing, A]
