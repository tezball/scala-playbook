# Function Composition

Scala treats functions as plain values. Because they are values, you can magically glue smaller functions together to build larger, complex functions.

## andThen

Chains functions left-to-right: apply `f` first, then pass its result directly into `g`.

```scala
val double = (x: Int) => x * 2
val addOne = (x: Int) => x + 1

val doubleThenAdd = double.andThen(addOne)
doubleThenAdd(5)   // 11  (5*2=10, 10+1=11)
```

### andThen Breakdown

- **`double.andThen(addOne)`**: This creates a brand new function! When we call `doubleThenAdd(5)`, it automatically pipes the 5 into `double` (getting 10), and then takes the 10 and pipes it into `addOne` (getting 11).

## PartialFunction Combinators

A PartialFunction is a function that only knows how to handle *some* inputs. (e.g., a division function that doesn't know how to divide by zero).

```scala
val handleInts: PartialFunction[Any, String] =
  case i: Int => s"Int: $i"

val handleStrings: PartialFunction[Any, String] =
  case s: String => s"String: $s"

// orElse — combine partial functions
val handle = handleInts.orElse(handleStrings)
handle(42)       // "Int: 42"
handle("hello")  // "String: hello"
```

### orElse Breakdown

- **`.orElse`**: We take two functions that only do half a job, and glue them together. If `handleInts` is handed a String, it fails, so `.orElse` instantly catches the failure and passes the String to `handleStrings` instead.

## pipe and tap (scala.util.chaining)

Utility for cleanly chaining logic in a straight line top-to-bottom.

```scala
import scala.util.chaining.*

// pipe — pass a value through a function
val result = 5
  .pipe(_ * 2)
  .pipe(_ + 1)
  .pipe(_.toString)
// "11"

// tap — perform a side effect, return original value
val debugged = List(1, 2, 3)
  .map(_ * 2)
  .tap(xs => println(s"Current list: $xs"))
  .filter(_ > 2)
```

### Pipe & Tap Breakdown

- **`.pipe`**: Takes whatever value is currently on the left side of the dot, and rams it into the function on the right side.
- **`.tap`**: The ultimate debugging tool. It takes your data, pipes it into your `println`, but then—critically—it *ignores* the result of the print, and passes your original pristine data down to the next step. It lets you print intermediate values without breaking a long chain of logic.

## identity

The built-in identity function — returns exactly what you gave it, completely unchanged.

```scala
val xs = List(Some(1), None, Some(2), None)
xs.flatMap(identity)           // List(1, 2)
```

### Identity Breakdown

- **`identity`**: Why would you use a function that does nothing? Sometimes an API *forces* you to hand it a function, but you don't actually want to transform the data! `identity` satisfies the compiler while perfectly preserving your data.

## Composing with Method References

```scala
val words = List("hello", "world", "scala")

// Method references can be used anywhere a function is expected
words.map(_.toUpperCase)           // lambda
words.map(_.length)                // lambda
```

### Method Reference Breakdown

- **`_.toUpperCase`**: Shorthand in Scala is king. This is incredibly clean syntactic sugar for `word => word.toUpperCase()`.

## See Also

- [[Functions]]
- [[Collection Operations]]

**Tags:** #scala #functions #composition #andThen #compose
