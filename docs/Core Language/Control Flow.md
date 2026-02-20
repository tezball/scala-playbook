# Control Flow

## If / Else (Expressions)

In Scala, `if`/`else` is an **expression** that returns a value. This is different from languages like Java or C++ where `if` is just a statement that executes code.

```scala
// Scala 3 syntax (no parens required, uses `then`)
val result = if x > 0 then "positive" else "negative"

// Multi-line
val category =
  if x < 0 then
    "negative"
  else if x == 0 then
    "zero"
  else
    "positive"
```

### If/Else Breakdown

- **`val result = ...`**: We are declaring an immutable variable named `result` and assigning it the value that comes out of the `if` expression.
- **`if x > 0`**: Checks the condition. Notice there are no parentheses `()` around `x > 0` in Scala 3 syntax.
- **`then`**: A keyword in Scala 3 replacing the need for curly braces `{}`. It says "if the condition is true, return what comes next."
- **`"positive"` / `"negative"`**: The actual values being returned. Whichever branch is taken, that string is assigned directly to `result` or `category`.

> [!tip] Everything is an expression
> Unlike Java, `if`, `match`, `try`, and even blocks `{ }` return values in Scala.

## Match Expressions (Pattern Matching)

Scala's `match` is far more powerful than Java's `switch`. It allows you to match on values, types, and even destructure objects.

```scala
val description = x match
  case 0 => "zero"
  case 1 => "one"
  case n if n > 0 => s"positive: $n"   // guard
  case _ => "negative"                  // wildcard / default
```

### Match Breakdown

- **`x match`**: Takes the variable `x` and starts comparing it against the cases below.
- **`case 0 => "zero"`**: If `x` is exactly `0`, the whole match expression returns the string `"zero"`. The `=>` symbol means "returns" or "results in".
- **`case n if n > 0 => s"positive: $n"`**: This is called a **guard**. It says: "Bind the value of `x` to a new temporary variable `n`. If `n` is greater than 0, return the string `"positive: $n"`."
- **`case _ => "negative"`**: The underscore `_` acts as a **wildcard**. It matches absolutely anything that wasn't caught by the cases above. It's equivalent to `default` in a switch statement.

See [[Pattern Matching]] for a full deep-dive.

## For Comprehensions

For-comprehensions in Scala look like loops, but they are actually powerful tools for transforming data. They translate under the hood to operations like `map`, `flatMap`, and `filter`.

```scala
// Simple iteration (foreach under the hood)
for i <- 1 to 5 do
  println(i)

// With yield (produces a collection via map/flatMap)
val squares = for i <- 1 to 5 yield i * i
// Result: Vector(1, 4, 9, 16, 25)

// Filtering
val evens = for
  i <- 1 to 10
  if i % 2 == 0
yield i
```

### For Comprehensions Breakdown

- **`for i <- 1 to 5`**: This is a **generator**. It creates a sequence of numbers from 1 to 5, and one by one, assigns them to the temporary variable `i`. The `<-` symbol can be read as "drawn from".
- **`do println(i)`**: In Scala 3, `do` executes the following code for side-effects (like printing) without returning anything.
- **`yield i * i`**: Instead of just printing, `yield` transforms the comprehension into an expression that builds a *new collection*. For every `i`, it calculates `i * i` and adds it to a new collection.
- **`if i % 2 == 0`**: An inline filter. If the condition is false, `i` is skipped and nowhere to be seen in the final `yield`.

## While Loops

```scala
var i = 0
while i < 10 do
  println(i)
  i += 1
```

### While Loops Breakdown

- **`var i = 0`**: Declares a *mutable* variable. We must use `var` instead of `val` because we intend to change it.
- **`while i < 10 do`**: As long as the condition is true, execute the block. Similar to Java/C, but using the `do` keyword instead of `{}`.
- **`i += 1`**: Increments `i`. (Note: Scala does not have `i++`).

> [!warning] Prefer functional alternatives
> While loops require `var` and mutation. Prefer recursion, `map`, `fold`, or for-comprehensions.

## Try / Catch / Finally

Scala provides traditional `try/catch` block, but functional programming prefers wrapping risky code in a `Try` type instead.

```scala
import scala.util.{Try, Success, Failure}

// Functional style with Try (preferred)
val result: Try[Int] = Try("42".toInt)

result match
  case Success(n) => println(s"Got: $n")
  case Failure(e) => println(s"Failed: $e")
```

### Try/Catch Breakdown

- **`import scala.util.{Try, Success, Failure}`**: Brings in the necessary types from the standard library.
- **`Try("42".toInt)`**: The `Try()` block attempts to run the code inside it. If it succeeds, it wraps the answer in a `Success`. If it throws an exception (like passing `"abc"` to `toInt`), it catches the exception and wraps it in a `Failure`. It guarantees your app doesn't crash.
- **`result match`**: We pattern match on the wrapper.
- **`case Success(n)`**: Unwraps the `Success` wrapper, placing the successful integer into `n`.
- **`case Failure(e)`**: Unwraps the `Failure` wrapper, giving you access to the Exception `e` so you can handle or log it.

See [[Error Handling]] for more on `Try`, `Option`, and `Either`.

## See Also

- [[Pattern Matching]]
- [[Functions]]
- [[Collection Operations]]
- [[Error Handling]]

**Tags:** #scala #control-flow #basics
