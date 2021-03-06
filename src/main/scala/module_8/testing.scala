package module_8

/*
The library developed in this chapter goes through several iterations. This file is just the
shell, which you can fill in and modify while working through the chapter.
 */

object testing extends App {
  import Gen._
  import Prop._
  import java.util.concurrent.{Executors, ExecutorService}

  trait Prop {
    def check: Boolean
    def &&(p: Prop): Prop = new Prop {
      def check = Prop.this.check && p.check
    }
  }

  object Prop {
    def forAll[A](gen: Gen[A])(f: A => Boolean): Prop = ???
  }

  object Gen {
    def unit[A](a: => A): Gen[A] = ???

    def listOf[A](a: Gen[A]): Gen[List[A]] = ???

    def listOfN[A](n: Int, a: Gen[A]): Gen[List[A]] = ???
  }

  trait Gen[A] {
    def map[A, B](f: A => B): Gen[B] = ???
    def flatMap[A, B](f: A => Gen[B]): Gen[B] = ???
  }

  trait SGen[+A] {}

}
