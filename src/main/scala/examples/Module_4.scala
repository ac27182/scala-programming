package examples
import cats.syntax.`package`.flatMap

object Module_4 {
  // custom option trait
  sealed trait Option[+A] {
    def map[B](f: A => B): Option[B] = this match {
      case Some(get) => Some(f(get))
      case None      => None
    }

    def getOrElse[B >: A](default: => B): B = this match {
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

  def variance(xs: Seq[Double]): Option[Double] = {
    mean(xs) flatMap (m => mean(xs map (x => math pow (x - m, 2))))
  }

  def main(args: Array[String]): Unit = {
    val x = Seq(1.0, 2.0, 3.0, 4.0)
    val y = Seq()

    println(variance(x))
    println(variance(x))

  }
}
