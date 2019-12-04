// package examples
// import scala.annotation.tailrec
// import cats.instances.`package`.boolean

// object Module_3 {
//   // import List._
//   import Tree._
//   def main(args: Array[String]): Unit = {
//     val x = Branch(Leaf("allan"), Branch(Leaf("steve"), Leaf("alex")))
//     println("")
//     println(size(x))
//     println("")

//   }
// }

// //list datatype parametererized on some type A
// sealed trait List[+A]
// // a list constructor representing the empty list
// final case object Nil extends List[Nothing] // identity
// final case class Cons[+A](head: A, tail: List[A]) extends List[A]

// // companion object
// object List {

//   def sum(ints: List[Int]): Int = ints match {
//     case Nil              => 0
//     case Cons(head, tail) => head + sum(tail)
//   }

//   def product(ds: List[Double]): Double = ds match {
//     case Nil              => 1.00
//     case Cons(0.0, _)     => 0.00
//     case Cons(head, tail) => head * product(tail)
//   }

//   // exercise 3.2
//   def tail[A](l: List[A]): List[A] = l match {
//     case Nil           => l
//     case Cons(_, tail) => tail
//   }

//   // exercise 3.3
//   def newHead[A](h: A, l: List[A]): List[A] =
//     Cons(h, tail(l))

//   // exercise 3.4
//   def drop[A](l: List[A], n: Int): List[A] =
//     if (n <= 0) l
//     else
//       l match {
//         case Nil           => l
//         case Cons(_, tail) => drop(tail, n - 1)
//       }

//   // exercise 3.5
//   def dropWhile[A](l: List[A])(f: A => Boolean): List[A] =
//     l match {
//       case Nil                           => l
//       case Cons(head, tail) if (f(head)) => dropWhile(tail)(f)
//       case _                             => l
//     }

//   def length[A](l: List[A]): Int = {
//     def loop(l: List[A], i: Int): Int = l match {
//       case Nil              => i
//       case Cons(head, tail) => loop(tail, i + 1)
//     }
//     loop(l, 0)
//   }

//   // exercise 3.6
//   def init[A](l: List[A]): List[A] = {
//     def loop(l: List[A], i: Int): List[A] =
//       if (i <= 1) l
//       else loop(tail(l), i - 1)
//     loop(l, length(l))
//   }

//   // listing 3.2

//   def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B =
//     as match {
//       case Nil              => z
//       case Cons(head, tail) => f(head, foldRight(tail, z)(f))
//     }

//   @tailrec
//   def foldLeft[X, Y](list: List[X], acc: Y)(f: (Y, X) => Y): Y = {
//     list match {
//       case Nil              => acc
//       case Cons(head, tail) => foldLeft(tail, f(acc, head))(f)
//     }
//   }
//   def sum2(ns: List[Int]) =
//     foldRight(ns, 0)(_ + _)

//   def product2(ns: List[Double]) =
//     foldRight(ns, 1.0)(_ * _)

//   // exercise 3.9
//   def length2[A](ns: List[A]) =
//     foldRight(ns, 0)((_, acc) => acc + 1)

//   def concat[A](xs: List[A], ys: List[A]) = foldRight(xs, List[A]())(Cons(_, _))

//   // exercise 3.18
//   def map[A, B](as: List[A])(f: A => B): List[B] = as match {
//     case Nil              => List[B]()
//     case Cons(head, tail) => Cons(f(head), map(tail)(f))
//   }

//   def filter[A](xs: List[A])(f: A => Boolean): List[A] = xs match {
//     case Nil                           => xs
//     case Cons(head, tail) if (f(head)) => Cons(head, filter(tail)(f))
//     case Cons(head, tail)              => filter(tail)(f)
//   }

//   // exercise 3.20
//   def flatMap[A, B](xs: List[A])(f: A => List[B]): List[B] = xs match {
//     case Nil              => List[B]()
//     case Cons(head, tail) => concat(f(head), flatMap(xs)(f))
//   }

//   def apply[A](arr: A*): List[A] =
//     if (arr.isEmpty) Nil; else Cons(arr.head, apply(arr.tail: _*))
// }

// sealed trait Tree[+T]
// case class Leaf[T](value: T) extends Tree[T]
// case class Branch[T](left: Tree[T], right: Tree[T]) extends Tree[T]

// object Tree {

//   // exercise 3.25
//   def size[T](t: Tree[T]): Int = t match {
//     case Leaf(value)         => 1
//     case Branch(left, right) => size(left) + size(right)
//   }

//   // exercise 3.26
//   def maximum(t: Tree[Int]): Int = t match {
//     case Leaf(value)         => value
//     case Branch(left, right) => maximum(left) max maximum(right)
//   }

//   // exercise 3.27
//   def depth[T](t: Tree[T]): Int = t match {
//     case Leaf(value)         => 0
//     case Branch(left, right) => (1 + depth(t)).max(1 + depth(t))
//   }

//   // exercise 3.28
//   def treeMap[X, Y](t: Tree[X])(implicit f: X => Y): Tree[Y] = t match {
//     case Leaf(value)         => Leaf(f(value))
//     case Branch(left, right) => Branch(treeMap(left), treeMap(right))
//   }

//   // exercise 3.29
//   def treeFold[X, Y](
//       t: Tree[X]
//   )(f: X => Y)(g: (Y, Y) => Y): Y =
//     t match {
//       case Branch(left, right) =>
//         g(treeFold(left)(f)(g), treeFold(right)(f)(g))
//       case Leaf(value) => f(value)
//     }
// }
