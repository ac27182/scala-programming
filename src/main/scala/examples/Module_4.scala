package examples
import cats.syntax.`package`.flatMap
import scala.util.Try

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

  // exercise 4.2
  def variance(xs: Seq[Double]): Option[Double] = {
    mean(xs) flatMap (m => mean(xs map (x => math pow (x - m, 2))))
  }

  // listing 4.3
  // def parseInsuranceRateQuota(
  //     age: String,
  //     numberOfSpeedingTickets: String
  // ): Option[Double] = {
  //   val optAge: Option[Int] = Try(age.toInt)
  //   val optTickets: Option[Int] = Try(numberOfSpeedingTickets.toInt)

  //   insuranceRateQuote(optAge, optTickets)

  // }

  // exercise 4.3
  def map2[X, Y, Z](x: Option[X], y: Option[Y])(f: (X, Y) => Z): Option[Z] =
    x flatMap (`x` => y map (`y` => f(`x`, `y`)))

  def main(args: Array[String]): Unit = {
    val x = Seq(1.0, 2.0, 3.0, 4.0)
    val y = Seq()

    println(variance(x))
    println(variance(x))

  }
}
