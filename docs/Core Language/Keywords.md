# Scala Keywords

This is a cheat sheet of all reserved keywords in Scala 3 (most also apply to Scala 2).

## Variable & Value Declaration

| Keyword | Description |
| --- | --- |
| `val` | Declares an immutable value (like `final` in Java). Once set, it cannot be changed. |
| `var` | Declares a mutable variable. Can be updated. |
| `def` | Declares a method or function. Evaluated every time it is called. |
| `type` | Declares a type alias (a nickname for a complex data type). |
| `lazy` | Defers evaluation of a `val` until the first time it is accessed. |

```scala
val name = "Scala"                // immutable
var count = 0                     // mutable
def greet() = "hello"             // method
type StringList = List[String]    // type alias
lazy val expensive = computeIt()  // evaluated on first use
```

### Variables Breakdown

- **`val name`**: Creates a permanent reference pointer. `name = "Java"` would throw a compile error later.
- **`var count`**: Creates an updatable pointer. `count += 1` works fine.
- **`type StringList`**: You can now write `StringList` anywhere in your code instead of `List[String]`.
- **`lazy val`**: `computeIt()` won't actually run until the exact moment another piece of code asks for `expensive`. Good for delaying heavy processing.

## Class & Object Keywords

| Keyword | Description |
| --- | --- |
| `class` | Declares a class template. |
| `object` | Declares a singleton object (only one instance ever exists). |
| `trait` | Declares a trait (like an interface in Java, but can hold implemented methods). |
| `abstract` | Marks a class or member as abstract (not fully implemented). |
| `extends` | Inherits from a single parent class or trait. |
| `with` | Mixes in additional traits alongside `extends`. |
| `new` | Creates a new instance of a class. |
| `this` | Reference to the current instance. |
| `super` | Reference to the parent class. |
| `override` | Overrides a parent member. Explicitly required in Scala. |
| `final` | Prevents overriding or subclassing. |
| `sealed` | Restricts subclassing to the same file (used for Pattern Matching safety). |
| `enum` | Declares an enumeration of fixed types (Scala 3). |
| `case` | Used with `class` to create a lightweight data-holding class with auto-generated methods. |
| `given` | Declares a given instance (Scala 3, replaces `implicit`). |
| `using` | Declares a context parameter (Scala 3). |
| `extension` | Declares extension methods to add functions to types you don't own (Scala 3). |
| `opaque` | Declares an opaque type alias for performance (Scala 3). |

```scala
class Dog(val name: String) extends Animal with Serializable

object Dog:
  def apply(name: String) = new Dog(name)

trait Greeter:
  def greet(): String

case class Point(x: Int, y: Int)

sealed trait Shape
case class Circle(r: Double) extends Shape
case class Rect(w: Double, h: Double) extends Shape

enum Color:
  case Red, Green, Blue
```

### Classes Breakdown

- **`class Dog... extends... with`**: A class inheriting from one parent `Animal`, and mixing in the `Serializable` trait.
- **`object Dog`**: A singleton. There is only one companion object named `Dog` in the application. It acts like `static` methods in Java.
- **`trait Greeter`**: A blueprint requiring any implementers to define `greet()`.
- **`case class Point`**: Automatically generates getters, setters (if `var`), `equals()`, `hashCode()`, and a `copy()` method for the data inside. Ideal for modeling data in pure functional programming.
- **`sealed trait Shape`**: Enforces that all subclasses (`Circle` and `Rect`) must be defined in this exact same file. This completely prevents third parties from extending `Shape`.
- **`enum Color`**: A Scala 3 feature building a fixed set of choices.

## Access Modifiers

| Keyword | Description |
| --- | --- |
| `private` | Visible only within the enclosing class/object. |
| `protected` | Visible within the class and its subclasses. |
| `private[pkg]` | Visible anywhere within a specific package scope. |

```scala
class Account:
  private var balance = 0.0
  protected def update(amount: Double): Unit = balance += amount
  private[model] def internalReset(): Unit = balance = 0
```

### Access Breakdown

- **`private var`**: Only methods inside `Account` can touch this.
- **`protected def`**: If you `class SavingsAccount extends Account`, it can use `update()`. Outside code cannot.
- **`private[model]`**: Any file inside the `package model` can use this function, acting like "package-private" in Java.

## Control Flow

| Keyword | Description |
| --- | --- |
| `if` / `else` | Conditional expression. |
| `match` | Pattern matching switch. |
| `case` | A branch in a `match` expression or partial function. |
| `for` | For-comprehension (map/flatMap/filter syntax sugar). |
| `yield` | Produces a value from a for-comprehension. |
| `while` | Standard while loop. |
| `do` | Used with `while`, or as block syntax in Scala 3. |
| `return` | Explicit return (highly discouraged in functional Scala, as the last line is automatically returned). |
| `throw` | Throws an exception. |
| `try` / `catch` / `finally` | Exception handling block. |

```scala
val result = if x > 0 then "positive" else "non-positive"

x match
  case 0 => "zero"
  case n if n > 0 => "positive"
  case _ => "negative"

for
  i <- 1 to 10
  if i % 2 == 0
yield i * i
```

See [[Control Flow]] for the detailed breakdown of these snippets.

## Package & Import

| Keyword | Description |
| --- | --- |
| `package` | Declares which package/folder structure a file belongs to. |
| `import` | Brings classes, objects, or members into scope. |
| `export` | Re-exports members from another object so others can access them (Scala 3). |

```scala
package com.example.app

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import scala.util.{Try, Success, Failure}
```

### Packages Breakdown

- **`package com.example.app`**: Must be at the very top of the file. Sets the namespace.
- **`import ...`**: Brings things into scope.
- **`{ArrayBuffer, ListBuffer}`**: We can import multiple items from the same package using curly braces to save space. To import everything, use `import scala.util.*` (Scala 3) or `import scala.util._` (Scala 2).

## Special Keywords

| Keyword | Description |
| --- | --- |
| `implicit` | Marks implicit conversions/parameters/classes (Scala 2). |
| `null` | The null reference, completely discouraged in Scala. Use `Option` instead. |
| `true` / `false` | Standard boolean literals. |
| `then` | Used after an `if` condition in Scala 3 to avoid curly braces. |
| `end` | Optional visual marker to cleanly end long blocks of code (Scala 3). |
| `derives` | Auto-derives Type Class instances for `enum` or `class` (Scala 3). |
| `transparent` | Transparent trait or inline modifier for compiler magic (Scala 3). |
| `inline` | Requests the compiler to insert the code directly at the call site for performance (Scala 3). |

## Soft Keywords

These are context-sensitive and only act as keywords in certain positions, meaning you can technically name a variable `open` if you wanted to, but the compiler treats it uniquely in the right context:

| Keyword | Context |
| --- | --- |
| `as` | Import renaming (e.g., `import java.util.Date as JDate`). |
| `derives` | Used after a class or enum declaration. |
| `end` | Closes blocks (e.g., `end if`, `end for`). |
| `extension` | Used to declare extension methods. |
| `infix` | Allows a method to be called like an operator (e.g., `1 add 2`). |
| `open` | Tells the compiler a class CAN be extended (classes are locked by default in Scala 3). |
| `transparent` | Used with `inline` methods. |
| `using` | Declares context parameters. |

## See Also

- [[Variables and Types]]
- [[Control Flow]]
- [[Classes and Objects]]

**Tags:** #scala #keywords #reference
