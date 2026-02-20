# Type Classes

Type classes are an advanced, magical pattern in Scala. They allow you to add new behaviors to existing objects *without having to modify the original object's code*.

## What Is a Type Class?

Imagine you have a `Cat` and a `Dog`, and you want to be able to sort them. But you don't own the code for Cat and Dog, so you can't edit them to implement a `sortable` trait. A Type Class lets you build an external sorting rule book, and hand it to the compiler!

## Ordering

`Ordering` is the built-in type class for comparing things.

```scala
// Already provided for Int, String, Double, etc.
List(3, 1, 4).sorted          // List(1, 3, 4) 

// Custom Ordering
case class Person(name: String, age: Int)

given Ordering[Person] = Ordering.by(_.age)
List(Person("Bob", 30), Person("Alice", 25)).sorted
// List(Person(Alice,25), Person(Bob,30))
```

### Ordering Breakdown

- **`.sorted`**: This function actually requires an `Ordering` "rule-book" to run! Scala silently provides rule-books for Numbers and Strings automatically.
- **`given Ordering[Person]`**: We define our own rule-book for Persons. The `given` keyword registers it globally. We tell it: "Sort people purely by checking their `.age`".
- Now, when you call `.sorted` on a List of Persons, the compiler silently grabs your custom `given` rule-book and applies it!

## Numeric

Provides generic arithmetic. If you write a generic math function, it has no idea if the user will pass in `Int`, `Double`, or `BigInt`. You must ask for a `Numeric` rule-book.

```scala
import scala.math.Numeric

// Works on any Numeric type
def sumAll[A: Numeric](xs: List[A]): A =
  val num = summon[Numeric[A]]
  xs.foldLeft(num.zero)(num.plus)

sumAll(List(1, 2, 3))        // 6
sumAll(List(1.5, 2.5))       // 4.0
```

### Numeric Breakdown

- **`[A: Numeric]`**: This syntax means "I accept any Type A, but ONLY if there is a `Numeric` rule-book legally defined for it."
- **`summon[Numeric[A]]`**: Reaches into the invisible ether and physically grabs the correct rule-book. If the user passed in `Ints`, it grabs the `Int` rule-book. If `Doubles`, it grabs the `Double` rule-book.
- **`num.zero` / `num.plus`**: We use the generic rules to calculate the sum, totally agnostic to what type of number we are actually adding together!

## CanEqual (Scala 3 Strict Equality)

Normally the compiler lets you compare anything, like `Cat == Dog`, which will compile fine and just return `false` at runtime. Strict Equality forces the compiler to scream at you if you accidentally compare apples to oranges.

```scala
import scala.language.strictEquality

case class Cat(name: String) derives CanEqual
case class Dog(name: String) derives CanEqual

Cat("Felix") == Cat("Felix")  // OK
// Cat("Felix") == Dog("Rex") // compile error!
```

### CanEqual Breakdown

- **`derives CanEqual`**: A blazing fast shortcut. It tells the compiler to auto-generate a `given` rule-book that strictly verifies equality for this specific class.
- Now, attempting to compare a Cat and Dog completely halts the compiler with a syntax error, preventing huge bugs.

## Writing Your Own Type Class

```scala
// 1. Define the type class interface
trait Show[A]:
  def show(a: A): String

// 2. Provide instances
given Show[Int] with
  def show(a: Int) = a.toString

// 3. Convenience method
def printBeautifully[A](a: A)(using s: Show[A]): String = s.show(a)

printBeautifully(42)   // "42"
```

### Custom Type Class Breakdown

- **`trait Show[A]`**: The empty template interface for your custom rule-book.
- **`given Show[Int] with`**: The actual concrete rule-book for Integers.
- **`(using s: Show[A])`**: A method asserting that it demands to be handed the rule-book automatically by the compiler before it will securely compile.

## See Also

- [[Implicits and Givens]]
- [[Traits]]

**Tags:** #scala #type-classes #ordering #numeric #advanced
