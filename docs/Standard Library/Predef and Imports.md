# Predef and Auto-Imports

Scala automatically imports several packages and the `Predef` object into every single file you write. These give you a ton of helper methods "for free" without having to write `import` statements at the top of your files.

## What's Auto-Imported

Every single Scala file implicitly behaves as if you typed this at the top:

```scala
import scala.*                    // core types (List, Option, etc.)
import scala.Predef.*             // utility methods, conversions
import java.lang.*                // String, Integer, Thread, etc.
```

## Built-in Methods

Because `Predef` is auto-imported, all of its helpful global functions are available everywhere instantly.

### Console Output

```scala
println("Hello")         // print with newline
print("Hello")           // print without newline
```

### Assertions (Safety Checks)

```scala
// assert — throws an error if false
assert(x > 0, "x must be positive!")

// require — throws IllegalArgumentException 
def sqrt(x: Double): Double =
  require(x >= 0, "Cannot take sqrt of negative number!")
  math.sqrt(x)
```

### Assertions Breakdown

- **`assert`**: A way to sanity-check your code. If the condition is false, the program crashes immediately.
- **`require`**: Specifically designed to be placed at the very top of a function to validate that the caller passed in valid inputs (like ensuring a bank withdrawal amount isn't negative).

### Placeholder for Unimplemented Code

```scala
def todoFeature(): Unit = ???
```

### Placeholder Breakdown

- **`???`**: Yes, three question marks is actual, valid Scala code! It throws a `NotImplementedError` if run. It is perfect for sketching out the shell of a massive application before actually writing the internal logic. It satisfies the compiler completely.

### The Arrow Operator

```scala
val pair = "key" -> "value"    
val myMap = Map("a" -> 1, "b" -> 2)
```

### Arrow Breakdown

- **`->`**: The arrow is not a core piece of Scala syntax! It is merely a helper function built into `Predef`. It takes almost any object on its left side and binds it to the object on its right side, returning a Tuple exactly like `("key", "value")`.

## Auto-Imported Types

These are perfectly legal to use anywhere without `import` statements:

| Type | From |
| --- | --- |
| `Int`, `Double`, `Boolean` | `scala.*` |
| `String` | `java.lang.String` |
| `List`, `Vector`, `Map`, `Set` | `scala.collection.immutable.*` |
| `Option`, `Some`, `None` | `scala.*` |
| `Either`, `Left`, `Right` | `scala.*` |

## String Interpolation

The magic `s"..."` interpolator works via an object in `Predef`.

```scala
// When you write:
val greeting = s"Hello, $name"

// The compiler actually rewrites it into this method call:
StringContext("Hello, ", "").s(name)
```

### Interpolation Breakdown

- **`StringContext`**: Scala's compiler splits your string literal apart wherever it finds a `$variable`, and passes the pieces into the `StringContext.s()` engine, which glues them securely back together.

## See Also

- [[Keywords]]
- [[Variables and Types]]
- [[Functions]]

**Tags:** #scala #predef #imports #basics
