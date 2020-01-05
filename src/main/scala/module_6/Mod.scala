package module_6
import module_6.Mod.RNG.Simple
import module_5.module_5.Empty
import cats.instances.`package`.map
import scala.collection.immutable.Nil

object Mod {

  trait RNG {
    def nextInt: (Int, RNG)
  }

  object RNG {

    case class Simple(seed: Long) extends RNG {
      def nextInt: (Int, RNG) = {
        val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL // `&` is bitwise AND. We use the current seed to generate a new seed.
        val nextRNG = Simple(newSeed) // The next state, which is an `RNG` instance created from the new seed.
        val n = (newSeed >>> 16).toInt // `>>>` is right binary shift with zero fill. The value `n` is our new pseudo-random integer.
        (n, nextRNG) // The return value is a tuple containing both a pseudo-random integer and the next `RNG` state.
      }
    }

    // exercise 6.1
    def nonNegativeInt(rng: RNG): (Int, RNG) = {
      val (i, r) = rng.nextInt
      (if (i < 0) -(i + 1) else i, r)
    }

    // exercise 6.2
    def double(rng: RNG): (Double, RNG) = {
      val (i, r) = nonNegativeInt(rng)
      (i / (Int.MaxValue.toDouble + 1), r)
    }

    // exercises 6.3
    def intDouble(rng: RNG): ((Int, Double), RNG) = {
      val (i1, r1) = nonNegativeInt(rng)
      val (d1, r2) = double(r1)
      ((i1, d1), r2)
    }

    def doubleInt(rng: RNG): ((Double, Int), RNG) = {
      val (d1, r1) = double(rng)
      val (i1, r2) = nonNegativeInt(r1)
      ((d1, i1), r2)
    }

    def double3(rng: RNG): ((Double, Double, Double), RNG) = {
      val (d1, r1) = double(rng)
      val (d2, r2) = double(r1)
      val (d3, r3) = double(r2)
      ((d1, d2, d3), r3)
    }

    //exercise 6.4
    def ints(count: Int)(rng: RNG): (List[Int], RNG) =
      count match {
        case 0 =>
          (List(), rng)
        case _ =>
          val (x, r1) = rng.nextInt
          val (xs, r2) = ints(count - 1)(r1)
          (x :: xs, r2)
      }

    type Rand[+A] = RNG => (A, RNG)

    val int: Rand[Int] = _.nextInt

    def unit[A](a: A): Rand[A] =
      rng => (a, rng)

    def map[A, B](s: Rand[A])(f: A => B): Rand[B] =
      rng => {
        val (a, rng2) = s(rng)
        (f(a), rng2)
      }

    // exercise 6.5
    val _double: Rand[Double] =
      map(nonNegativeInt)(_ / (Int.MaxValue.toDouble + 1))

    // exercise 6.6
    def map2[A, B, C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C] =
      rng => {
        val (a, rng2) = ra(rng)
        val (b, rng3) = rb(rng2)
        (f(a, b), rng3)
      }

    // exercise 6.7
    def sequence[A](fs: List[Rand[A]]): Rand[List[A]] =
      fs.foldRight(unit(List[A]()))((f, acc) => map2(f, acc)(_ :: _))

    // exercise 6.8
    def flatMap[A, B](f: Rand[A])(g: A => Rand[B]): Rand[B] =
      rng => {
        val (a, r1) = f(rng)
        g(a)(r1)
      }

    def nonNegativeLessThan(n: Int): Rand[Int] = {
      flatMap(nonNegativeInt) { i =>
        val mod = i % n
        if (i + (n - 1) - mod >= 0) unit(mod) else nonNegativeLessThan(n)
      }
    }

    // exercise 6.9
    def _map[A, B](s: Rand[A])(f: A => B): Rand[B] =
      flatMap(s)(a => unit(f(a)))

    def _map2[A, B, C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C] =
      flatMap(ra)(a => flatMap(rb)(b => unit(f(a, b))))
  }

  import State._
  case class State[S, +A](run: S => (A, S)) {

    def map[B](f: A => B): State[S, B] =
      flatMap(a => unit(f(a)))

    def map2[B, C](sb: State[S, B])(f: (A, B) => C): State[S, C] =
      flatMap(a => sb.map(b => f(a, b)))

    def flatMap[B](f: A => State[S, B]): State[S, B] =
      State(s => {
        val (a, s1) = run(s)
        f(a) run s1
      })
  }

  object State {
    type Rand[A] = State[RNG, A]

    def unit[S, A](a: A): State[S, A] =
      State(s => (a, s))

    def sequence[S, A](sas: List[State[S, A]]): State[S, List[A]] = {
      def go(s: S, actions: List[State[S, A]], acc: List[A]): (List[A], S) = {
        actions match {
          case h :: t =>
            h.run(s) match { case (a, s2) => go(s2, t, a :: acc) }
          case Nil =>
            (acc.reverse, s)
        }
      }
      State((s: S) => go(s, sas, List()))
    }

    def modify[S](f: S => S): State[S, Unit] =
      for {
        s <- get // Gets the current state and assigns it to `s`.
        _ <- set(f(s)) // Sets the new state to `f` applied to `s`.
      } yield ()

    def get[S]: State[S, S] = State(s => (s, s))

    def set[S](s: S): State[S, Unit] = State(_ => ((), s))
  }

  sealed trait Input
  case object Coin extends Input
  case object Turn extends Input

  case class Machine(locked: Boolean, candies: Int, coins: Int)

  object Candy {
    def update =
      (i: Input) =>
        (s: Machine) =>
          (i, s) match {
            case (_, Machine(_, 0, _)) => s
            case (Coin, Machine(true, candy, coins)) =>
              Machine(false, candy, coins)
          }

    def simulateMachine(inputs: List[Input]): State[Machine, (Int, Int)] = ???
  }

  def main(args: Array[String]): Unit = {
    val rng = Simple(100000).nextInt._2
    println(RNG.nonNegativeInt(rng))
    println(RNG.double(rng))
    println(RNG.double3(rng))
    println(5 :: Nil)
  }
}
