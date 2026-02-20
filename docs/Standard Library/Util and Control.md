# Util and Control

Additional advanced utilities from `scala.util` and `scala.util.control`.

## scala.util.chaining — pipe and tap

```scala
import scala.util.chaining.*

// pipe — thread a value through a function
val result = 42
  .pipe(_ * 2)
  .pipe(_.toString)
// "84"

// tap — side effect, returns original value
val debugged = List(1, 2, 3)
  .tap(xs => println(s"Before: $xs"))
  .map(_ * 2)
```

### Chaining Breakdown

- **`.pipe` / `.tap`**: These are fully covered with detailed breakdowns in [[Function Composition]]. They allow you to pass data linearly through functions top-to-bottom, and debug mid-chain without breaking anything.

## scala.util.control.NonFatal

A massive safeguard for catching errors securely.

```scala
import scala.util.control.NonFatal

try
  riskyOperation()
catch
  case NonFatal(e) => println(s"Handled: $e")
```

### NonFatal Breakdown

- **`case NonFatal(e)`**: When a program crashes, you usually want to catch the error. BUT, if you catch *every single error* blindly (`case e: Exception => ...`), you might accidentally catch and hide fatal system failures (like your computer literally running completely out of RAM). `NonFatal` is a smart scanner. It catches normal, predictable code errors, but deliberately ignores world-ending JVM system errors so the app can panic and die safely instead of hiding the problem.

> [!tip] Best Practice
> Always absolutely use `NonFatal` instead of catching raw `Throwable` or `Exception`.

## scala.util.control.Breaks

Scala strongly prefers purely functional loops without standard `break` rules. However, sometimes you dramatically need it.

```scala
import scala.util.control.Breaks.*

breakable {
  for i <- 1 to 100 do
    if i > 5 then break()
    println(i)
}
// prints 1 through 5
```

### Breaks Breakdown

- **`breakable { ... }`**: You *must* wrap your loop inside this safety fence.
- **`break()`**: Instantly halts the loop and jumps out to the fence edge. Internally, Scala actually throws a secret, hidden Exception to forcefully make this work, which is why functional programming purists dislike it heavily!

## boundary / break (Scala 3)

The modern, ultra-fast, exception-free replacement for early loop exits.

```scala
import scala.util.boundary, boundary.break

val result = boundary:
  for i <- 1 to 100 do
    if i * i > 50 then break(i)
  -1   // default backup value if no break occurs
```

### Boundary Breakdown

- **`boundary:`**: Replaces the old clunky `breakable` block.
- **`break(i)`**: This is the real magic. It doesn't just halt the loop. It actually takes the value `i` and instantly returns it as the final computed result of the entire `boundary` block! If the loop successfully finishes naturally without breaking, it returns the backup `-1` instead.

## scala.util.Using (Resource Management)

```scala
import scala.util.Using
import scala.io.Source

val lines = Using(Source.fromFile("data.txt")) { source =>
  source.getLines().toList
}
```

### Using Breakdown

- **`Using(...)`**: Safely manages external pipelines connecting your app to the hard drive or remote network. It mathematically guarantees that memory pipelines are permanently shut down and closed after the code block runs, even if massive exceptions occur inside the block. Fully detailed in [[IO and Files]].

## See Also

- [[Function Composition]]
- [[Error Handling]]
- [[IO and Files]]

**Tags:** #scala #util #control #breaks #NonFatal
