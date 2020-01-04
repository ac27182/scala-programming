package module_6
import module_6.Mod.RNG.Simple
import module_5.module_5.Empty

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

  }

  def main(args: Array[String]): Unit = {
    val rng = Simple(100000).nextInt._2
    println(RNG.nonNegativeInt(rng))
    println(RNG.double(rng))
    println(RNG.double3(rng))
    println(5 :: Nil)
  }
}
