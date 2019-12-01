# manning notes

# P_1: Introduction to functional programming

## C_2: Getting started with functional programming in scala

---

### definitions

---

`Pure Function:` A pure function is a function with zero side effects, where a side effect is anything other than returning a result.

`Referential Transparency:` An expression is called 'referentially transparent' if it can be replace with its corresponding value, without changing the programs behavior. This requires that the expression be pure

`Expression:`

`Substitutional Model:`

`singleton:`

`higher order funtion:`

`tail elimiation:`

`monomorphic function:`

`parametric polymorphism:`

`type parameters:`

`type variables:`

`partial application:`

`currying:` currying is the technique of translating the evaluation of a function that takes multiple arguments into evaluating a sequence of functions, each with a single argument

`lifting:` lifting or eta expansion converts an expresion of method type to an expression of function type

---

### annecdotes

---

> "functional programming is a restriction on how we write programs not on what programms can express"

> "the discaplines of functional programming are tremendously benneficial because of the increase in modularity we gain from programming in pure functions"

> "because of their modularity pure functions are easy to test, resuse, parallelize generalize and reason about. Furthermore pure functions are less prone to bugs"

> "funtional programmers often speak of implementing programs with a pure core and a thin layer on the outside that handles effects"

> "an object is also know as a module"

> "the lhs of a fucntion ins scala is often referred to as a signature"

> "the rhs of a funtion in scala is often referred to as a definition"

> "the main method is an outer shell that calls the pure functions, often called a procedure"

> "every value in scala is an object"

> "in scala we can use any method as infix"

> "functions are values"

> "in scala sometimes we write inner functions"

> "abstracting over the type"

> "annonymous functions | function literals | lambda functions"

```bash
# compiles scala module
$ scalac MyModule.scala;

# runs scala mosule
$ scala MyModule
```

```scala
// throws a compile error if a function is not in tail position
@annotation.tailrec
```

```bash
# useful sbt commands
$ sbt compile
$ sbt reload
$ sbt update
$ sbt run
$ sbt runMain
```

## C_3: functional data stuctures

---

### definitions

---

`pattern matching:`

`function data structure:`

`companion object:`

`interface:`

`data constructors:`

`trait:` a trait is an abstract interface that may optionally contain implementations of some methods.

`companion object:` a module containing functions to operate on a trait

`convariant:` the property of a function retaining its form when the variables are linearly transformed

`data sharing:`

`tail call optimisation:` a tail call is a subroutine call performed as the final action of a procedure, if a tail call might lead to the same subroutine being called again later in the call chain, the subroutine is said to be tail recursive.

`algebreic data type:` an data type defined by one or more data constructors, each of which may contain zero or more arguments. We say that the data type is either the sum or the union of its ddata constructors, and each data type is the product of its arguments, hence alegbreic

<!-- `invariant:` the property of a a function  -->

### annecdotes

---

> const => constructs

> functional data stuctures are immutable

> final cases enable exhastivity checking

> bind a subexpression of the target

> "a pattern matches the target if there exists an assignment of variables in the pattern to subexpressions of the target that make it stucturally equivilent"

> scala type inference

> placing a function in its own argument gtoup, lets type inference determine the input types in a function

> underscore notation

> get into the habbit of looking for possible ways to generalize any explicit recursive functions if you write to process lists

> associates to the right

> be wary of loss of efficiency when composing list functions

```scala
// variadic function syntax
def apply[A](arr: A*): List[A] = ???

// convariance syntax
sealed trait List[+A]

// tailrecursion simple annotation
@tailrec

// useful functions
filter
flatMap
map
foldLeft
foldRight

```

### misc

## C_4: handling errors without exceptions

### definitions

`consolodation:`

`antipattern:` antipatterns are certain patterns in softaware development that are considered bad programming practices

`partial function:`

`sentinal value:` a sentinal value is a special value in the context of an algorithm, which uses its presence as a condition of termination

`total function:` a function which is defined for all possible values of its input

### anecdotes

> we can write higher orde functions that abstract out common patterns of error handline and recovers, a functional solution is safer and retains referential trasnparency.

> referential transparency does not depend on context

> exceptions break referential transparency and introduce context dependence

> exceptions are not type safe

```scala
// don't evaluate unless needed
// B muust be a supertype of A
def getOrElse[B >: A](default: => B): B = ???

```

## C_5: strictness and laziness

## C_6: pureley functional state

# P_2: functional design and combinator libraries

## C_7: pureley functional parallelism

## C_8: property based testing

## C_9: parser conbinators

# P_3: common structures in functional design

# P_4: effects and I/O

# misc

- singly linked list
- equational resoning
- local reasoning
- composable
- nG : go to the nth page
- https://github.com/fpinscala/fpinscala
