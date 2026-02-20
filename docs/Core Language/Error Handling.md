# Error Handling

Scala provides three main types for safe error handling: `Option`, `Try`, and `Either`. They are all wrappers that contain either a successful result or some indication of failure, forcing the programmer to handle both cases securely without crashing the application.

## Option — May or May Not Have a Value

Use `Option` when a value might be missing, and you don't need a specific error message as to *why*.

```scala
val some: Option[Int] = Some(42)
val none: Option[Int] = None

// Getting the value
some.getOrElse(0)        // 42
none.getOrElse(0)        // 0

// Pattern matching
some match
  case Some(value) => s"Got $value"
  case None        => "Nothing"
```

### Option Breakdown

- **`Option[Int]`**: The wrapper type. It says "this box might contain an Integer, or it might be empty."
- **`Some(42)`**: The box has something inside. `Some` is a subclass of `Option`.
- **`None`**: The box is empty. `None` is the other subclass of `Option`.
- **`getOrElse(0)`**: A safe way to extract a value. It says "open the box. If there is a number, give it to me. If the box is empty (`None`), just give me `0` instead."
- **`match ... case Some(value)`**: We can use pattern matching to safely unwrap the value only if it exists.

## Try — May Succeed or Fail with an Exception

Use `Try` when you are executing code that might throw an exception (e.g., parsing a string to a number, opening a file, or a network call), and you want to catch the exception safely.

```scala
import scala.util.{Try, Success, Failure}

val result: Try[Int] = Try("42".toInt)        // Success(42)
val failed: Try[Int] = Try("abc".toInt)       // Failure(NumberFormatException)

// Pattern matching
result match
  case Success(n) => s"Parsed: $n"
  case Failure(e) => s"Error: ${e.getMessage}"
```

### Try Breakdown

- **`Try("42".toInt)`**: `Try` is a block that catches any exceptions thrown inside it.
- **`Success(42)`**: If the code executes perfectly, the result is wrapped in a `Success`.
- **`Failure(...)`**: If the code throws an exception, `Try` catches it and wraps the exception object in a `Failure`. The app does not crash.
- **`match ... case Failure(e)`**: Pattern matching allows you to grab the exception `e` and decide what to do with it (like logging it or returning a default value).

## Either — One of Two Possible Values

Use `Either` when you want to return a specific error type or message (e.g., a custom validation error) instead of a standard Exception. By convention, `Left` is the error and `Right` is the success.

```scala
def divide(a: Int, b: Int): Either[String, Int] =
  if b == 0 then Left("Cannot divide by zero")
  else Right(a / b)

divide(10, 2)   // Right(5)
divide(10, 0)   // Left("Cannot divide by zero")

// Pattern matching
divide(10, 3) match
  case Right(result) => s"Result: $result"
  case Left(error)   => s"Error: $error"
```

### Either Breakdown

- **`Either[String, Int]`**: The wrapper type. It says "this box will either contain a `String` (on the Left side) OR an `Int` (on the Right side)".
- **`Right(a / b)`**: By convention, `Right` is "right" (correct/success). We wrap the successfully divided number in a `Right`.
- **`Left("...")`**: We wrap our custom error message in a `Left`.
- **`match ... case Right(result)`**: Pattern matching cleanly exposes either the successful result or the error string.

## Transforming Wrappers (map and for-comprehensions)

You can chain operations on all three wrappers using `map` or for-comprehensions without constantly checking if they are empty or failed.

```scala
val a = Some(1)
val b = Some(2)

// Map
a.map(x => x * 10)  // Result: Some(10)

// For-comprehension
val result = for
  x <- a
  y <- b
yield x + y         // Result: Some(3)
```

### Transformations Breakdown

- **`.map(x => ...)`**: This safely asks the question: "If there is a value in the box, apply this function to it. If the box is empty, do nothing and keep it empty."
- **`for (x <- a; y <- b)`**: This allows you to safely extract values from multiple boxes at once. If *any* of the boxes (like `a` or `b`) happen to be `None` or `Failure` or `Left`, the entire chain stops immediately and returns the empty/failed box without crashing.

## When to Use What

| Type | Use When | Example |
| --- | --- | --- |
| `Option` | Value may be absent, no error info needed | `def findUser(id: Int): Option[User]` |
| `Try` | Wrapping code that may throw exceptions | `def parseJson(raw: String): Try[Json]` |
| `Either` | Need to return a specific error type/message | `def validateAge(age: Int): Either[ValidationError, Int]` |

> [!tip] Avoid exceptions for control flow
> Use `Option`, `Try`, or `Either` instead of throwing exceptions. Reserve exceptions for truly exceptional situations.

## See Also

- [[Control Flow]]
- [[Pattern Matching]]
- [[Collection Operations]]

**Tags:** #scala #error-handling #option #try #either
