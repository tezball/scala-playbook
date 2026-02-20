# Traits

Traits are Scala's mechanism for code reuse and multiple inheritance. They are similar to Java `interfaces`, but far more powerful because you can add actual code (methods and fields) inside them, and any class can inherit from many of them simultaneously.

## Basic Traits

```scala
trait Greeter:
  def greet(name: String): String

trait Loud:
  def shout(msg: String): String = msg.toUpperCase + "!"

class FriendlyGreeter extends Greeter, Loud:
  def greet(name: String) = s"Hello, $name"

val g = FriendlyGreeter()
g.greet("Alice")     // "Hello, Alice"
g.shout("hey")       // "HEY!"
```

### Basic Traits Breakdown

- **`trait Greeter`**: An empty blueprint. It establishes that anyone who inherits it *must* write logic for `greet(name)`.
- **`trait Loud ... def shout`**: A trait that already has fully functional, ready-to-use code (`shout()`).
- **`class FriendlyGreeter extends Greeter, Loud`**: A single class inheriting from *both* traits at once!
- Notice the `greet` method inside the class. We are fulfilling the promise we made to `Greeter` by providing the actual implementation. Since `Loud` was already fully implemented, we get it for free without writing anything else.

## Traits with State

Traits can hold variables (state), not just functions.

```scala
trait Logger:
  var logCount = 0

  def log(msg: String): Unit =
    logCount += 1
    println(s"[$logCount] $msg")
```

### State Breakdown

- **`var logCount = 0`**: Any class that inherits the `Logger` trait will quietly get its very own `logCount` variable injected into it.

## Mixin Composition

The act of combining multiple traits onto a single class is called "mixing in".

```scala
trait Swimmer:
  def swim(): String = "swimming"

trait Flyer:
  def fly(): String = "flying"

trait Runner:
  def run(): String = "running"

class Triathlete extends Runner, Swimmer, Flyer

val t = Triathlete()
t.run()   // "running"
t.swim()  // "swimming"
```

### Mixin Breakdown

- **`class Triathlete extends Runner, Swimmer, Flyer`**: We glued three distinct pieces of behavior together completely effortlessly into an otherwise empty class. This allows you to compose complex logic out of tiny, single-purpose traits.

## Stacking Traits (Stackable Modifications)

Because you can mix in multiple traits, what happens if two traits have the same method? Scala evaluates them from right to left! You can stack behaviors like Russian nesting dolls.

```scala
trait Base:
  def operate(s: String): String

trait Trim extends Base:
  abstract override def operate(s: String): String =
    super.operate(s.trim)

trait Lower extends Base:
  abstract override def operate(s: String): String =
    super.operate(s.toLowerCase)

class Identity extends Base:
  def operate(s: String) = s

val processor = new Identity with Trim with Lower
processor.operate("  HELLO  ")   // "hello"
```

### Stacking Breakdown

- **`abstract override def operate`**: This odd pairing tells the compiler: "I am overriding a method, but I know it isn't fully implemented yet. Let me call `super.operate` to pass the responsibility up the chain."
- **`extends Base ... with Trim with Lower`**: Because Scala reads right-to-left:
  1. It hits `Lower` first, converting `"  HELLO  "` to `"  hello  "`.
  2. It hits `Trim` next, trimming away whitespace to `"hello"`.
  3. It hits `Identity` last, which does nothing but return the final string.

## Sealed Traits

Sealed traits ensure security and safety by preventing third parties from creating rogue implementations.

```scala
sealed trait Result
case class Ok(value: String) extends Result
case class Err(message: String) extends Result

def handle(r: Result): String = r match
  case Ok(v)  => s"Success: $v"
  case Err(m) => s"Error: $m"
```

### Sealed Breakdown

- **`sealed trait Result`**: Adding the `sealed` keyword forcefully locks the trait. The only things that are legally allowed to extend `Result` must physically exist in this exact same `.scala` file.
- **`def handle... match`**: Because it is sealed, the compiler can guarantee that `Ok` and `Err` are the only two possible results in the entire universe. If someone forgets to write a case for `Err`, the compiler detects the missing piece and warns you.

## Self Types

Sometimes a trait relies on other functionality to work properly. You can "require" that a trait only be mixed into a class that has specific features.

```scala
trait Database:
  def query(sql: String): List[String]

trait UserRepo:
  self: Database =>   

  def findUser(id: Int) = query(s"SELECT * FROM users WHERE id = $id")
```

### Self Types Breakdown

- **`self: Database =>`**: This is a self-type annotation. It tells the compiler: "The `UserRepo` trait is literally useless unless you glue it to a `Database`. Do not let anyone create a `UserRepo` unless they also attach a `Database` to it."
- **`query(...)`**: Notice `UserRepo` is calling `query()` even though it legally doesn't have a `query` method. It is allowed to do this because the self-type *guarantees* that `query()` will exist wherever this trait ends up.

## Traits vs Abstract Classes

| Feature | Trait | Abstract Class |
| --- | --- | --- |
| Multiple inheritance | Yes | No |
| Constructor parameters | No (Scala 2), Yes (Scala 3) | Yes |
| Java interop | Compiles to interface | Compiles to class |
| State | Can have | Can have |
| Use when | Composing behavior | Establishing a base hierarchy |

> [!tip] Default to traits
> Use traits unless you specifically need constructor parameters (in old Scala 2 applications) or need perfectly clean interop with a Java framework that demands a base class.

## See Also

- [[Classes and Objects]]
- [[Pattern Matching]]
- [[Keywords]]

**Tags:** #scala #traits #oop
