# Implicits and Givens

Scala has a powerful mechanism for passing context automatically behind the scenes. Scala 3 replaced the old `implicit` keyword with `given` and `using` for clarity, to help programmers clearly distinguish between *providing* a value and *requesting* a value.

## Scala 3: Given / Using

### Given Instances

A `given` instance provides a value that the compiler can automatically pass around whenever a function asks for it.

```scala
trait Ordering[A]:
  def compare(a: A, b: A): Int

// Named given
given intOrdering: Ordering[Int] with
  def compare(a: Int, b: Int) = a - b

// Anonymous given
given Ordering[String] with
  def compare(a: String, b: String) = a.compareTo(b)
```

### Given Breakdown

- **`trait Ordering[A]`**: A blueprint requiring a `compare` method. Check the [[Traits]] doc for more.
- **`given intOrdering:`**: This tells the compiler: "I am providing a default contextual value named `intOrdering`."
- **`Ordering[Int] with`**: The type is `Ordering[Int]`. The `with` keyword means "I am about to implement the required methods of this trait right here."
- **`def compare(a, b) = a - b`**: The actual implementation of the sorting rule. Fulfills the trait's contract.
- **`given Ordering[String] with`**: You don't have to name your givens. You can anonymously provide a rule for Strings, and the compiler will still find it.

### Using Clauses

A `using` clause requests a `given` instance as a parameter.

```scala
def max[A](a: A, b: A)(using ord: Ordering[A]): A =
  if ord.compare(a, b) >= 0 then a else b

max(3, 5)                     // uses intOrdering automatically
max(3, 5)(using intOrdering)  // explicit
```

### Using Breakdown

- **`(using ord: Ordering[A])`**: The `using` keyword tells the compiler to look in the background scope for exactly one `given` that matches `Ordering[A]`. If it finds it, it automatically injects it into the variable `ord`.
- **`max(3, 5)`**: Notice we only passed `3` and `5`. The compiler automatically found our `intOrdering` from earlier and passed it invisibly into the second parameter list.
- **`(using intOrdering)`**: You can still explicitly pass a value if you want to override the automatic behavior.

### Summoning a Given

Sometimes you want to grab an invisible `given` value without using function parameters.

```scala
val ord = summon[Ordering[Int]]  // retrieves the given Ordering[Int]
```

### Summon Breakdown

- **`summon[Ordering[Int]]`**: A built-in command that asks the compiler: "Please find the `given` instance for `Ordering[Int]` and hand it to me directly so I can save it to the `ord` variable."

## Scala 2: Implicits

Before Scala 3, `given` and `using` were both just called `implicit`. You will see this everywhere in older Scala code.

### Implicit Parameters

```scala
// Scala 2 equivalent
def max[A](a: A, b: A)(implicit ord: Ordering[A]): A =
  if (ord.compare(a, b) >= 0) a else b

implicit val intOrd: Ordering[Int] = new Ordering[Int] {
  def compare(a: Int, b: Int) = a - b
}
```

### Implicit Parameters Breakdown

- **`(implicit ord: Ordering[A])`**: This is exactly equivalent to `(using ord: Ordering[A])`.
- **`implicit val intOrd = ...`**: This is exactly equivalent to `given intOrdering ...`. We declare a value and mark it `implicit` so the compiler knows it can inject it.

### Implicit Conversions

Implicit conversions tell the compiler how to secretly translate one type into another type if it gets stuck.

```scala
// Scala 2
implicit def intToString(i: Int): String = i.toString

val s: String = 42  // automatically converts via intToString
```

### Implicit Conversions Breakdown

- **`implicit def ...`**: We define a function from `Int` to `String`, and mark it `implicit`.
- **`val s: String = 42`**: The compiler sees we are trying to stuff an Integer into a String variable. Normally this crashes. But the compiler checks its toolkit, sees `intToString`, and invisibly runs `intToString(42)` to save the day.

> [!warning] Implicit conversions are discouraged
> They make code hard to understand because magic conversions happen without your knowledge. Scala 3 requires explicit opt-in with `import scala.language.implicitConversions`.

### Implicit Classes (Extension Methods in Scala 2)

We use implicit classes to "add" new methods to classes we don't own (like `Int`).

```scala
implicit class RichInt(val n: Int) extends AnyVal:
  def times(f: => Unit): Unit = (1 to n).foreach(_ => f)
  def isEven: Boolean = n % 2 == 0

3.times(println("hi"))  // prints "hi" three times
4.isEven                 // true
```

### Implicit Classes Breakdown

- **`implicit class RichInt(val n: Int)`**: Creates a wrapper class around an `Int`. Because it is marked `implicit`, the compiler knows it can wrap *any* integer into a `RichInt` automatically.
- **`4.isEven`**: `Int` does not have an `isEven` method. But the compiler secretly converts `4` to `RichInt(4).isEven` to make the code compile.

## Scala 3: Extension Methods (Preferred)

Scala 3 replaces `implicit class` with the much cleaner `extension` keyword.

```scala
extension (n: Int)
  def times(f: => Unit): Unit = (1 to n).foreach(_ => f)
  def isEven: Boolean = n % 2 == 0

3.times(println("hi"))
4.isEven
```

### Extension Methods Breakdown

- **`extension (n: Int)`**: This directly tells the compiler: "Add the following methods to the `Int` type, and refer to the integer being used as `n`." This requires no secret wrapper classes.

## Type Classes Pattern

This is a very common architecture pattern in Scala using givens. It allows you to add behavior to types without modifying the core class itself.

```scala
// 1. Define the type class
trait JsonEncoder[A]:
  def encode(a: A): String

// 2. Provide instances
given JsonEncoder[Int] with
  def encode(a: Int) = a.toString

given JsonEncoder[String] with
  def encode(a: String) = s"\"$a\""

given listEncoder[A](using enc: JsonEncoder[A]): JsonEncoder[List[A]] with
  def encode(as: List[A]) = as.map(enc.encode).mkString("[", ",", "]")

// 3. Use it
def toJson[A](a: A)(using enc: JsonEncoder[A]): String = enc.encode(a)

toJson(42)                    // "42"
toJson("hello")               // "\"hello\""
toJson(List(1, 2, 3))         // "[1,2,3]"
```

### Type Classes Breakdown

- **1. Define the type class**: We create a generic interface `JsonEncoder` that has one job: turning things into a JSON formatted string.
- **2. Provide instances**: We create `given` JSON encoders for standard Integers, Strings, and Lists.
- Notice the `listEncoder`. It says: "I can encode a List of type A, *only if* (`using`) you give me an encoder for type A first."
- **3. Use it**: `toJson(42)` goes searching for `JsonEncoder[Int]`, finds the given instance we just wrote, and successfully returns `"42"`.

## Context Bounds (Shorthand)

Context bounds are syntactic sugar that makes reading `using` clauses cleaner when dealing with Type Classes.

```scala
// These are equivalent:
def max[A](a: A, b: A)(using Ordering[A]): A = ???
def max[A: Ordering](a: A, b: A): A = ???
```

### Context Bounds Breakdown

- **`[A: Ordering]`**: This is read as "Type `A` must have an `Ordering` available in the contextual scope." Behind the scenes, the compiler literally translates this into `(using Ordering[A])`.

## Migration Cheat Sheet

| Scala 2 | Scala 3 |
| --- | --- |
| `implicit val` | `given` |
| `implicit def` (conversion) | `given Conversion[A, B]` |
| `implicit class` | `extension` |
| `implicit` parameter | `using` parameter |
| `implicitly[T]` | `summon[T]` |

## See Also

- [[Functions]]
- [[Traits]]
- [[Classes and Objects]]

**Tags:** #scala #implicits #givens #advanced
