# Pattern Matching

Pattern matching is one of Scala's most powerful features. It goes far beyond Java's `switch` statement by allowing you to match against types, structures, and even specific data inside objects.

## Basic Matching

```scala
val x: Int = 3

val result = x match
  case 1 => "one"
  case 2 => "two"
  case 3 => "three"
  case _ => "other"     // wildcard, catches everything
```

### Basic Breakdown

- **`x match`**: Opens the pattern matching block, comparing `x` to the cases below.
- **`case 1 => "one"`**: If `x` equals `1`, return the string `"one"`.
- **`case _ =>`**: The underscore `_` is a wildcard. It acts as a "catch-all" or `default` case. If you forget to include it and `x` is `4`, the program will crash with a MatchError.

## Guards

Guards allow you to add `if` statements directly into your cases.

```scala
x match
  case n if n < 0  => "negative"
  case n if n == 0 => "zero"
  case n if n > 0  => "positive"
```

### Guards Breakdown

- **`case n`**: This binds the incoming value (`x`) to a new temporary variable named `n`.
- **`if n < 0`**: Only execute this case *if* the temporary variable `n` is less than zero.
- **Why no `_` catch-all?**: Because mathematically, `n < 0`, `n == 0`, and `n > 0` cover every possible integer, so the compiler knows it cannot crash!

## Type Matching

```scala
def describe(x: Any): String = x match
  case i: Int    => s"Int: $i"
  case s: String => s"String of length ${s.length}"
  case _: Double => "some Double"
  case null      => "null"
  case _         => "unknown"
```

### Type Breakdown

- **`x: Any`**: `x` can literally be anything.
- **`case i: Int`**: If `x` happens to be an `Int`, assign it to variable `i` (so we can use `i` on the right side of the `=>`).
- **`case _: Double`**: If `x` is a `Double`, match it! But notice the `_:` instead of a variable name. This means we don't actually care about using the number itself, we just care about its type.
- **`case null`**: Yes, you can even safely match against `null` without NullPointerExceptions!

> [!warning] Type erasure
> Generic types are erased at runtime on the JVM. `case l: List[Int]` won't check the element type — it only checks for `List`. Use `case l: List[_]` to avoid warnings.

## Case Class Destructuring

This is where pattern matching shines. You can pull data out of objects directly.

```scala
case class Address(street: String, city: String, zip: String)
case class Person(name: String, age: Int, address: Address)

val p = Person("Alice", 30, Address("123 Main", "Springfield", "12345"))

p match
  case Person(name, age, _) if age >= 18 =>
    s"$name is an adult"
  case Person(name, _, Address(_, city, _)) =>
    s"$name lives in $city"
```

### Destructuring Breakdown

- **`case Person(name, age, _)`**: Scala automatically looks inside the `Person` object. It extracts the first property into a local variable `name`, the second into `age`, and completely ignores the address (`_`).
- **`Address(_, city, _)`**: Destructuring can be deeply nested! This grabs the person's name, throws away their age, dives inside their Address, throws away their street, saves their `city` as a variable, and throws away their zip code.

## Tuple Matching

```scala
(1, "hello", true) match
  case (1, msg, _)    => s"Got message: $msg"
  case (n, _, true)   => s"Number $n is true"
  case _              => "no match"
```

### Tuple Breakdown

- **`case (1, msg, _)`**: Matches only if the first element of the tuple is exactly the number `1`. If so, it saves the second element into a variable called `msg`.
- **`case (n, _, true)`**: Matches only if the last element is exactly `true`. Captures the first element into `n`.

## Sequence / List Matching

```scala
val xs = List(1, 2, 3, 4, 5)

xs match
  case Nil              => "empty"
  case head :: Nil      => s"single element: $head"
  case head :: tail     => s"head=$head, tail=$tail"

xs match
  case List(1, 2, _*)   => "starts with 1, 2"
  case List(_, _, 3, _*) => "third element is 3"
  case _                 => "other"
```

### List Breakdown

- **`case Nil`**: `Nil` is the empty list in Scala.
- **`case head :: tail`**: The `::` ("cons") operator splits a list in two. `head` becomes the very first element (1), and `tail` becomes a list of everything else (`List(2, 3, 4, 5)`).
- **`case List(1, 2, _*)`**: `_*` means "zero or more elements". This says: "Match any list as long as the first two elements are exactly 1 and 2."

## Variable Binding with `@`

```scala
p match
  case person @ Person(_, age, _) if age > 25 =>
    s"Matched: $person"    // binds the whole object to `person`
```

### Binding Breakdown

- **`person @ Person(...)`**: Destructuring pulls data *out* of an object, which sometimes means you lose access to the object itself. The `@` symbol allows you to name the entire unwrapped object (`person`) while simultaneously inspecting its innards (`age`).

## Or Patterns

```scala
x match
  case 1 | 2 | 3 => "small"
  case 4 | 5 | 6 => "medium"
  case _          => "large"
```

### Or Breakdown

- **`case 1 | 2 | 3`**: The pipe `|` means "OR". If `x` is 1, 2, or 3, execute this case.

## Sealed Hierarchies (Exhaustive Matching)

```scala
sealed trait Expr
case class Num(value: Double) extends Expr
case class Add(left: Expr, right: Expr) extends Expr
case class Mul(left: Expr, right: Expr) extends Expr

def eval(e: Expr): Double = e match
  case Num(v)    => v
  case Add(l, r) => eval(l) + eval(r)
  case Mul(l, r) => eval(l) * eval(r)
  // Compiler warns if you miss a case
```

### Sealed Breakdown

- **`sealed trait Expr`**: As covered in Keywords, `sealed` means all children of `Expr` must be in this exact file.
- Because the compiler can read the whole file, it knows exactly how many `Expr` classes exist. If you write a `match` but forget to include `case Mul`, the compiler will throw a warning: "Match is not exhaustive! You forgot Mul!" This guarantees bug-free code.

## Pattern Matching in `val` and `for`

You don't always need the word `match`. Pattern matching works anywhere you assign variables.

```scala
// Destructuring assignment
val (first, second) = (1, "hello")
val Person(name, age, _) = p

// In for-comprehensions
val people = List(("Alice", 30), ("Bob", 25))
for (name, age) <- people do
  println(s"$name: $age")

// With filtering
val pairs = List((1, "a"), (2, "b"), (3, "c"))
for (num, letter) <- pairs if num > 1 do
  println(s"$num -> $letter")
```

### Context Breakdown

- **`val (first, second) = ...`**: You can pull tuples apart right when you declare them. `first` becomes `1`.
- **`val Person(name, ...`**: You can pull variables directly out of classes upon assignment.
- **`for (name, age) <- people`**: You can pull lists of tuples apart directly in a for-comprehension loop header.

## Regex Matching

```scala
val datePattern = """(\d{4})-(\d{2})-(\d{2})""".r

"2024-01-15" match
  case datePattern(year, month, day) =>
    s"Year: $year, Month: $month, Day: $day"
  case _ => "not a date"
```

### Regex Breakdown

- **`"""...""".r`**: The `.r` method converts a raw string into a Regular Expression object. The parentheses `()` inside the regex define "capture groups".
- **`case datePattern(year, month, day)`**: Scala automagically extracts the 3 capture groups from our regex and assigns them securely to `year`, `month`, and `day`.

## Custom Extractors (unapply)

Under the hood, `match` asks objects for their `unapply` method to pull data out of them. You can write your own custom destructuring logic!

```scala
object Even:
  def unapply(n: Int): Option[Int] =
    if n % 2 == 0 then Some(n) else None

object Odd:
  def unapply(n: Int): Option[Int] =
    if n % 2 != 0 then Some(n) else None

42 match
  case Even(n) => s"$n is even"
  case Odd(n)  => s"$n is odd"
```

### Extractors Breakdown

- **`def unapply(n: Int): Option[Int]`**: By providing this method in a singleton object, we have taught the compiler how to ask the `Even` object if a number is even. If it returns `Some(n)`, the match is successful.
- **`case Even(n)`**: When 42 hits this line, Scala secretly calls `Even.unapply(42)`. Since the answer is `Some(42)`, the case succeeds!

## See Also

- [[Control Flow]]
- [[Classes and Objects]]
- [[Error Handling]]

**Tags:** #scala #pattern-matching #core
