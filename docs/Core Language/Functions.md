# Functions

Functions are the building blocks of Scala. Because Scala is a functional programming language, functions are treated as "first-class citizens," meaning they can be assigned to variables, passed as arguments, or returned from other functions.

## Defining Functions

```scala
// Basic function
def add(a: Int, b: Int): Int = a + b

// Return type can be inferred (but explicit is recommended for public APIs)
def multiply(a: Int, b: Int) = a * b

// Multi-line body
def factorial(n: Int): Long =
  if n <= 1 then 1L
  else n * factorial(n - 1)

// Unit return type (side effects)
def greet(name: String): Unit =
  println(s"Hello, $name")
```

### Basic Definition Breakdown

- **`def add`**: The `def` keyword declares a method/function named `add`.
- **`(a: Int, b: Int)`**: The parameter list. You must always explicitly state the types of incoming parameters (in this case, two Integers).
- **`: Int =`**: The expected return type of the function is an `Int`. The `=` sign connects the function signature to its body.
- **`def multiply... = a * b`**: Here, we completely left out the `: Int` return type. Scala's compiler is smart enough to figure out that `Int * Int` equals an `Int` (Type Inference).
- **`: Unit =`**: The `Unit` type is Scala's equivalent of `void` in Java/C. It means the function performs an action (like printing to the console) but does not return a meaningful value.

## Default & Named Parameters

```scala
def connect(host: String, port: Int = 8080, ssl: Boolean = false): Unit =
  println(s"Connecting to $host:$port (ssl=$ssl)")

connect("localhost")                      // uses defaults
connect("localhost", 443, true)           // positional
connect("localhost", ssl = true)          // named parameter
connect(port = 9090, host = "example.com") // all named, any order
```

### Parameters Breakdown

- **`port: Int = 8080`**: You can assign a default value right in the parameter list. If the caller doesn't provide a port, it will automatically use `8080`.
- **`ssl = true`**: When calling the function, you can explicitly name the parameter you are targeting. This is extremely helpful for jumping over default parameters or making your code highly readable.
- **`port = 9090, host = ...`**: If you use named parameters for everything, you can provide them in any order you like.

## Varargs

"Varargs" means a function can accept a *variable number* of arguments.

```scala
def sum(nums: Int*): Int = nums.sum

sum(1, 2, 3)           // 6
sum()                  // 0

// Spread a collection with *
val xs = List(1, 2, 3)
sum(xs*)               // Scala 3 syntax
```

### Varargs Breakdown

- **`nums: Int*`**: The asterisk `*` after the type tells Scala: "This function can accept zero, one, or many Integers." Inside the function, `nums` is treated as a Sequence (`Seq[Int]`).
- **`xs*`**: If you already have a List or Sequence and want to pass it into a varargs function, you must "spread" it using the `*` symbol so the compiler knows to treat the List as individual arguments.

## Anonymous Functions (Lambdas)

Often, you want to write a tiny function on the fly without giving it a name via `def`.

```scala
val double = (x: Int) => x * 2

// Type can be inferred from context
val nums = List(1, 2, 3)
nums.map(x => x * 2)       // List(2, 4, 6)
nums.map(_ * 2)            // shorthand with placeholder
```

### Lambdas Breakdown

- **`val double =`**: We are assigning a function directly to a variable.
- **`(x: Int) => x * 2`**: This is the anonymous function itself. It reads: "Take an integer `x`, and transform it (`=>`) into `x * 2`."
- **`nums.map(x => x * 2)`**: The `map` method climbs through the list and applies our anonymous function to every element.
- **`nums.map(_ * 2)`**: This is extreme shorthand. The underscore `_` acts as a blank placeholder for "whatever the incoming element is". It means exactly the same thing as `x => x * 2`.

> [!tip] Underscore placeholder
> `_` can replace a parameter that's used exactly once:
> `_ * 2` is short for `x => x * 2`
> `_ + _` is short for `(a, b) => a + b`

## Higher-Order Functions

A higher-order function is just a function that accepts *another function* as a parameter, or returns a function as its result.

```scala
def applyTwice(f: Int => Int, x: Int): Int = f(f(x))

applyTwice(_ * 2, 3)   // 12

// Returning a function
def multiplier(factor: Int): Int => Int =
  (x: Int) => x * factor

val triple = multiplier(3)
triple(10)  // 30
```

### Higher-Order Breakdown

- **`f: Int => Int`**: This defines the type of the parameter `f`. It says: "`f` must be a function that takes an `Int` and returns an `Int`."
- **`f(f(x))`**: We take the starting number `x`, pass it into function `f`, and then pass that result into function `f` again.
- **`: Int => Int =`**: In the `multiplier` example, the expected return type of the function is itself a function! It returns `(x: Int) => x * factor`.

## Currying

Currying is the process of breaking down a function that takes multiple parameters into a chain of functions that each take one parameter.

```scala
// Curried function
def add(a: Int)(b: Int): Int = a + b

add(1)(2)     // 3

val addFive = add(5)   // partially applied (returns a function)
addFive(3)             // 8
```

### Currying Breakdown

- **`(a: Int)(b: Int)`**: Notice the multiple parameter lists. This function takes `a`, and then returns *another function* that waits for `b`.
- **`val addFive = add(5)`**: Because of the multiple parameter lists, we can provide just `a` and pause. `addFive` is now a fully functional piece of code that remembers `a=5` and is waiting for `b`. This is called "partial application".

## Partial Functions

A `PartialFunction` is a function that is only designed to handle *some* specific inputs, not all possible inputs.

```scala
val divide: PartialFunction[Int, Int] =
  case x if x != 0 => 100 / x

divide.isDefinedAt(0)   // false
divide.isDefinedAt(5)   // true
divide(5)               // 20
```

### Partial Function Breakdown

- **`PartialFunction[Int, Int]`**: A special trait in Scala. The first type is the input, the second is the output.
- **`case x ...`**: Notice there are no parameters defined at the top. Instead, a partial function is written purely as pattern-matching `case` statements. Because there is no `case 0`, passing `0` into this function would crash it.
- **`isDefinedAt(0)`**: A built-in safety method that lets you ask the function: "Can you handle the input `0`?" without actually running it.

## By-Name Parameters

Sometimes you want to pass an expression into a function, but you don't want the expression to run *until* the function specifically asks for it.

```scala
def logIf(condition: Boolean, message: => String): Unit =
  if condition then println(message)

logIf(debug, s"Result: ${expensiveComputation()}")
```

### By-Name Breakdown

- **`message: => String`**: Notice the funny `=> String` type. There is no variable name on the left of the arrow. This marks it as a "By-Name" parameter. It tells Scala: "Do not evaluate the string until the exact moment the `message` variable is called inside the function."
- If `condition` is false, `message` is never called. Therefore, `expensiveComputation()` is completely skipped, saving processing power!

## Extension Methods (Scala 3)

Extension methods allow you to "add" new methods to classes that you didn't write (like `String` or `Int`).

```scala
extension (s: String)
  def shout: String = s.toUpperCase + "!"

"hello".shout         // "HELLO!"
```

### Extension Breakdown

- **`extension (s: String)`**: Tells the compiler: "I am about to add extra methods to the `String` type, and inside those methods, refer to the string as `s`."
- **`"hello".shout`**: Even though `shout` is not a built-in method in Java's `String` class, Scala makes it accessible as if it were natively there.

## Function Composition (andThen / compose)

```scala
val double = (x: Int) => x * 2
val addOne = (x: Int) => x + 1

val doubleThenAdd = double.andThen(addOne)
doubleThenAdd(5)   // 11  (5*2=10, 10+1=11)
```

### Composition Breakdown

- **`.andThen(addOne)`**: Chains two functions together visually from left to right. It takes the output of `double`, and feeds it directly into the input of `addOne`.

See [[Function Composition]] for full coverage.

## See Also

- [[Control Flow]]
- [[Collection Operations]]
- [[Function Composition]]
- [[Classes and Objects]]

**Tags:** #scala #functions #basics
