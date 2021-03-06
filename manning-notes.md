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

`first class loops:` people sometimes call streams first class loops

`corecursion:` corecursive algorithms use the data that they themselves produce, bit by bit, as they become available, and needed, to produce further bits of data

### anecdotes

> we can write higher orde functions that abstract out common patterns of error handline and recovers, a functional solution is safer and retains referential trasnparency.

> referential transparency does not depend on context

> exceptions break referential transparency and introduce context dependence

> exceptions are not type safe

> feel free to use for comprehensions in place of explicit calls to flatmap

> map | lift | sequence | traverse

> we can represent failures and exceptions with ordinary values

> we can think of Either as the union of two disjoint types

> tracing involcves watching the program as it interacts with the operating system

> be careful not to write expressions that arent stack safe

> recursive algorithms consume data, corecursive algorithms produce data

> corecursion is sometimes called guarded recursion

> productivity is sometimes called `codetermination`

```scala
// don't evaluate unless needed
// B muust be a supertype of A
def getOrElse[B >: A](default: => B): B = ???

```

## C_5: strictness and laziness

### definitions

`non-strict:` choosing not to evaluate one or more of a functions arguments

`thunk:` unevaluated form of an expression

### annecdotes

> we can accomplish loop fusion using laziness

> separation of concerns

## C_6: pureley functional state

## Definitions

`linear congruential generator`: an algorithm that yields a sequence of pseudo-randomized numbers calculated with a discontinuous piecewise linear equation

`state actions`

`combinatory logic:` a notition used to eliminate the need for quantified variables in mathematical logic

`combinator:`a combinator is an expression with no free variable. such that it is either a constant or a lambda expression which only refers to its bound variables.

`free variable:` a variable that is not bound to a storage location.

`type alias`

## Annecdotes

# P_2: functional design and combinator libraries

## C_7: pureley functional parallelism

### definitions

`race condition:` A race condition or race hazard is the condition of an electronics, software, or other system where the system's substantive behavior is dependent on the sequence or timing of other uncontrollable events

`inexplicit:` not explicit

`forking:` fork is an operation whereby a process creates a copy of itself

### annecdotes

> laws have consequences

> read `theorems for free` by philip wadler

> separation of concerns

## C_8: property based testing

### definitions

`test case minimization:` in the event of a failing test the framework tries smaller and smaller sizes until it finds the smallest test case that fails, which is more illuminating for debugging purposes.

`exhaustive test case generation:` for a sufficiently small testing domain we may exhaustively test all of the values, rather than generate sample values. If the property holds for all values in the domain we have an actual proof, rather than just `the absence of evidence to the contrary`

`type alias:` a name that refers to a previously defined type

### annecdotes

> falsified

> generators

> properties

> when thinking about properties to test, think of identity, the existence of zero, commutativity

> this section will be another messy and iterative process...

> type alias

## C_9: parser combinators

`parser:` a parser is a specialised perogram that takes unstructured data (text, streams of symbols, numbers, tokens) as input and outputs a structured representation of the data

`algebra:` we define an algebra to mean a collection of functions operating over some data types along witha set of laws specifying relationships between these functions

`algebreic design:` = ???

`endofunctor:` an endofunctor is a functor from one category back to the same category

# P_3: common structures in functional design

> conceptual integration
> "x has a monoid"

## C_9: Monoids

# P_4: effects and I/O

# misc

- nG : go to the nth page
- https://github.com/fpinscala/fpinscala

> https://alvinalexander.com/scala/fp-book/functional-programming-is-like-algebra

> covariant, contravariant, bivariant, variant, invariant

> fold, zip, map, foldMap

> functional programming simplified - alvin alexander

> the scala cookbook - alvin alexander

> functional and reactive domain modelling

> category theory for programmers - bartosz milowski

> programming in scala - martin odersky

> from mathematics to generic programming

> becomming functional

> real world haskell

> learn you a haskell for great good
