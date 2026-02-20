# Standard Library Utilities

Welcome to the toolkit! Scala comes with a massive Swiss Army Knife of built-in utilities so you don't have to reinvent the wheel.

## scala.math

All the standard math functions you would expect.

```scala
import scala.math.*

abs(-5)          // 5
max(3, 7)        // 7
pow(2, 10)       // 1024.0
sqrt(16)         // 4.0

// BigInt / BigDecimal for arbitrary precision
val big = BigInt("123456789012345678901234567890")
big * big
```

### Math Breakdown

- **`abs` / `max` / `pow`**: Absolute value, maximum of two numbers, and power (2 to the 10th).
- **`BigInt`**: Standard `Int` crashes if a number gets too huge (over roughly 2 billion). `BigInt` allows for infinitely massive numbers without overflowing, doing the heavy lifting under the hood.

## scala.util.Random

```scala
import scala.util.Random

val rng = Random()
rng.nextInt(100)       // Random number from 0 to 99
rng.nextBoolean()      // true or false

val colors = List("red", "green", "blue")
rng.shuffle(colors)    // Randomizes the list order
```

### Random Breakdown

- **`rng.nextInt(100)`**: Always goes up to, but *never includes*, the top number.
- **`rng.shuffle(...)`**: Extremely useful for shuffling a deck of cards or randomizing a playlist.

## scala.sys.process — Running External Commands

Scala can easily run standard terminal commands (like `ls`, `mkdir`, or `echo`) directly from your code!

```scala
import scala.sys.process.*

// Run and capture output
val output = "ls -la".!!           // throws on non-zero exit
val exitCode = "ls -la".!          // returns exit code
```

### Process Breakdown

- **`"ls -la"`**: Just write your terminal command as a standard string!
- **`.!!`** (Two bangs): Runs the command and returns the text output to your variable so you can use it in your app. If the command fails, your app crashes.
- **`.!`** (One bang): Runs the command, but returns the *status code* (0 for success, 1 for failure). It dumps the text output straight to the terminal console without saving it.

## scala.util.matching.Regex

Regular Expressions (Regex) are a mini-language for finding patterns inside text.

```scala
val pattern = """\d{3}-\d{4}""".r

pattern.findFirstIn("Call 555-1234")           // Some("555-1234")
pattern.replaceAllIn("Call 555-1234", "XXX-XXXX") // "Call XXX-XXXX"
```

### Regex Breakdown

- **`"""..."""`**: A triple-quoted string. This is vital for regex because it ignores escape characters like `\`, allowing you to write clean patterns without endless double-slashes.
- **`.r`**: A magical utility that instantly turns any string into a compiled Regex Pattern object.
- **`\d{3}`**: Regex speak for "find exactly three numbers".

## Java Interop — scala.jdk

Scala runs on the JVM, which means it can use any Java library. But Java lists and Scala lists aren't identical.

```scala
import scala.jdk.CollectionConverters.*

// Java to Scala
val javaList: java.util.List[String] = java.util.Arrays.asList("a", "b")
val scalaList: List[String] = javaList.asScala.toList

// Scala to Java
val scalaMap = Map("a" -> 1, "b" -> 2)
val javaMap: java.util.Map[String, Int] = scalaMap.asJava
```

### Java Interop Breakdown

- **`.asScala` / `.asJava`**: The moment you import `CollectionConverters.*`, all standard Java and Scala collections magically gain these conversion methods, allowing you to easily pass data cleanly back and forth.

## scala.concurrent.duration

```scala
import scala.concurrent.duration.*

val d1 = 5.seconds
val d2 = 100.milliseconds
val d3 = 2.minutes
```

### Duration Breakdown

- **`5.seconds`**: Another amazing piece of Scala syntactic sugar. This creates a strongly-typed `Duration` object representing 5 seconds. It eliminates the deadly "wait, does this function take seconds or milliseconds?" bug entirely.

## See Also

- [[Function Composition]]
- [[Type Classes]]
- [[Collections Overview]]

**Tags:** #scala #stdlib #utilities
