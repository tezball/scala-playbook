# Strings

Scala strings are just `java.lang.String` under the hood, but Scala sprinkles them with magical super-powers via its `StringOps` library.

## Basics

```scala
val s = "Hello, Scala!"

s.length              // 13
s.charAt(0)           // 'H'
s(0)                  // 'H' (apply method)
s.substring(7)        // "Scala!"
s.toUpperCase         // "HELLO, SCALA!"
s.trim                // removes leading/trailing whitespace
```

### Basics Breakdown

- **`s(0)`**: You can fetch the first character of a string exactly as if the string was an Array. It is actually shorthand for calling `.charAt(0)`.
- **`.substring(7)`**: Chops the first 7 characters off, returning the rest of the word.
- **`.trim`**: If a user accidentally types "   Alice   " into a form, `.trim` instantly cleans it to a beautiful "Alice".

## String Interpolation

This is how you inject variables directly into the middle of text smoothly.

```scala
val name = "World"
val n = 42

// s-interpolator
s"Hello, $name"                 // "Hello, World"
s"Answer: ${n + 1}"             // "Answer: 43"

// raw-interpolator
raw"Newline is \n"              // "Newline is \n" 
```

### Interpolation Breakdown

- **`s"..."`**: The "s" prefix is crucial! Without it, Scala will literally print out the symbols "$name". By prefixing it with 's', Scala knows it should substitute variables dynamically.
- **`${n + 1}`**: If you want to run live math, or call a specific method on an object inside a string, wrap it in curly braces so Scala knows where the statement ends.
- **`raw"..."`**: Ignores all escape characters entirely. The `\n` won't actually create a new line on the screen; it will literally print a slash and an n.

## Multiline Strings

```scala
val text = """
  |This is a
  |multiline string
  |with a margin.
  """.stripMargin
```

### Multiline Breakdown

- **`"""..."""`**: Triple quotes let you safely hit the "Enter" key and type large blocks of text across many lines without breaking the code.
- **`.stripMargin`**: By putting a `|` at the start of all your text lines, `.stripMargin` keeps your code visually indented and pretty in your IDE, but instantly chops all the ugly empty space off the front before actually printing the string.

## Common Operations

```scala
val s = "hello world"

s.contains("world")        // true
s.startsWith("hello")      // true
s.replace("world", "Scala") // "hello Scala"
s.split(" ")               // Array("hello", "world")
```

### Operations Breakdown

- **`.split(" ")`**: Searches for every empty space in the string, slashes the string apart at those spaces, and gives you back an Array of all the freshly chopped words.

## Treating Strings as Collections

Because strings are practically just "Arrays of Characters", almost every single Collection function (like map, flatMap, and filter) works elegantly on them!

```scala
val s = "Hello"

s.map(_.toUpper)           // "HELLO"
s.filter(_.isLetter)       // "Hello"
s.exists(_.isDigit)        // false
s.reverse                  // "olleH"
s.take(3)                  // "Hel"
```

### Collections Breakdown

- **`.filter(_.isLetter)`**: Iterates over every character sequentially. If the character is a letter, keep it. If it's a number, punctuation, or symbol, securely delete it.
- **`.take(3)`**: Keeps exactly the first 3 characters and skips the rest.
- See [[Collection Operations]] for complete details!

## Conversions

```scala
// String to numbers
"42".toInt              // 42
"42".toIntOption        // Some(42)
"abc".toIntOption       // None

// Numbers to strings
42.toString             // "42"
```

### Conversions Breakdown

- **`.toIntOption`**: Far, far safer than calling `.toInt`. If someone incorrectly types "abc" into a phone number field, `.toInt` crashes the entire backend app instantly. `.toIntOption` politely and securely returns `None`.

## See Also

- [[Variables and Types]]
- [[Collection Operations]]

**Tags:** #scala #strings #basics
