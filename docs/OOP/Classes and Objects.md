# Classes and Objects

Scala seamlessly blends Object-Oriented Programming (OOP) with Functional Programming. Every value in Scala is an object, and every operation is a method call.

## Classes

A class is a blueprint for creating objects. You can define properties (fields) and behaviors (methods) inside it.

```scala
// Basic class
class Person(val name: String, var age: Int):
  def greet(): String = s"Hi, I'm $name, age $age"

val p = Person("Alice", 30) // Creates the object
p.name   // "Alice"
p.age    // 30
p.age = 31  // ok, age is var

// Private constructor params
class Greeter(greeting: String):
  def greet(name: String) = s"$greeting, $name!"
```

### Classes Breakdown

- **`class Person(val name..., var age...)`**: In Scala, the primary constructor is written directly into the class signature. By adding `val` and `var`, Scala automatically creates public getters (and a setter for `age`).
- **`val p = Person("Alice", 30)`**: Note there is no `new` keyword! Scala 3 allows class instantiation without `new`.
- **`class Greeter(greeting: String)`**: Because we did not write `val` or `var` in front of `greeting`, no public getters are generated. `greeting` acts as a private, unchangeable value that can only be used inside the class methods.

## Constructor Overloading

Sometimes you want to instantiate a class in multiple ways (e.g. providing only an X-coordinate instead of X and Y).

```scala
class Point(val x: Double, val y: Double):
  // Auxiliary constructor
  def this(x: Double) = this(x, 0.0)
  def this() = this(0.0, 0.0)

  override def toString = s"Point($x, $y)"
```

### Point Breakdown

- **`def this(x: Double) = this(x, 0.0)`**: This is an "auxiliary constructor". It allows someone to call `Point(5.0)`. It immediately redirects to the primary constructor by calling `this(5.0, 0.0)`.
- **`def this() = this(0.0, 0.0)`**: Allows calling `Point()` with no arguments safely.

## Case Classes

Case classes are the bread and butter of Scala. They are special classes optimized for holding immutable data instead of holding mutable state and complex behaviors.

```scala
case class User(name: String, email: String, age: Int)

// What you get for free:
val u = User("Alice", "alice@example.com", 30)  
u.name               // accessor for all fields
u.toString           // "User(Alice,alice@example.com,30)"
u == User("Alice", "alice@example.com", 30)  // structural equality
u.hashCode           // consistent with equals
u.copy(age = 31)     // creates modified copy

// Pattern matching support
u match
  case User(name, _, age) if age > 18 => s"$name is an adult"
```

### Case Classes Breakdown

- **`case class User(...)`**: Just by adding the word `case`, the compiler writes hundreds of lines of boilerplate code for you.
- **`val` by default**: You don't need to write `val name: String`; every parameter is automatically public and immutable.
- **`u == User()`**: Normally in Java, `==` checks if two objects are literally at the exact same location in computer memory. In Scala case classes, `==` automatically compares the *data inside*. Two identical users are equal.
- **`.copy()`**: Because case classes are immutable, you can never say `u.age = 31`. Instead, the auto-generated `.copy` allows you to instantly clone the whole object while changing just one piece of data.

## Objects (Singletons)

If you only ever need exactly one instance of something (like a math utility box), use `object`.

```scala
object MathUtils:
  val Pi = 3.14159
  def square(x: Double): Double = x * x

MathUtils.Pi          // 3.14159
MathUtils.square(5)   // 25.0
```

### Object Breakdown

- **`object MathUtils`**: This creates a Singleton. The `MathUtils` object is created once, the first time you ask for it, and lives forever in memory.
- **`MathUtils.Pi`**: This is functionally equivalent to `public static` fields/methods in Java.

## Companion Objects

A companion object is an `object` that has the exact same name as a `class`, and sits in the exact same file. They are "companions" and can see each other's private variables!

```scala
class Circle(val radius: Double):
  def area: Double = Circle.Pi * radius * radius

object Circle:
  private val Pi = 3.14159

  // Factory method
  def apply(radius: Double) = new Circle(radius)
  def unit = Circle(1.0)

val c = Circle(5.0)     // calls Circle.apply(5.0)
val u = Circle.unit
```

### Companion Breakdown

- **`Circle.Pi`**: The class `Circle` is able to access the truly private `Pi` variable belonging to its companion object.
- **`def apply`**: This is a magic method. Whenever you type `Circle(5.0)`, Scala secretly rewrites it as `Circle.apply(5.0)`. This allows you to build custom ways to instantly generate your classes.

## Abstract Classes

Abstract classes are blueprints that have blank, unfinished methods. You cannot create an instance of an abstract class; someone else must extend it and finish the work first.

```scala
abstract class Animal:
  def name: String           // abstract (no equals sign)
  def sound: String          // abstract
  def describe: String =     // concrete (fully implemented)
    s"$name says $sound"

class Dog extends Animal:
  def name = "Dog"
  def sound = "Woof"
```

### Animal Breakdown

- **`abstract class Animal`**: The base class.
- **`def name: String`**: Has a return type but no body. It's a promise that whatever children extend `Animal` will provide a `name`.
- **`class Dog extends Animal`**: Dog fulfills the promises by providing `name` and `sound`. Because it inherits `describe`, you can call `Dog().describe` instantly.

## Enums (Scala 3)

Enums are used when a type can only be one of a specific, restricted list of options.

```scala
// Simple enum
enum Color:
  case Red, Green, Blue

// Parameterized enum
enum Planet(val mass: Double, val radius: Double):
  case Mercury extends Planet(3.3e23, 2.4e6)
  case Earth   extends Planet(6.0e24, 6.4e6)

  def surfaceGravity: Double = 6.67e-11 * mass / (radius * radius)

Color.valueOf("Green")   // Color.Green
```

### Enums Breakdown

- **`case Red, Green...`**: These are the only three valid colors. Trying to instantiate `Color.Purple` would crash the compiler.
- **`enum Planet(...)`**: You can attach permanent data to your enums! Because Mercury will always weigh the same amount, we hardcode it directly into the definition.

## Access Modifiers

```scala
class Account:
  private var balance = 0.0           // only this class
  protected def log(msg: String) = {} // this class + subclasses
  private[model] val id = "abc"       // visible within `model` package

class Savings extends Account:
  def deposit(amount: Double) =
    log(s"depositing $amount")  // ok, protected
    // balance += amount        // ERROR: balance is private
```

### Modifiers Breakdown

- **`private`**: The strictest lockdown. Only code physically existing inside `class Account` can touch this.
- **`protected`**: Code existing inside `Account` or any child class like `Savings` can touch this.
- **`private[model]`**: "Package private." Any file sitting inside the `model` folder can freely touch this.

## See Also

- [[Traits]]
- [[Pattern Matching]]
- [[Keywords]]

**Tags:** #scala #oop #classes
