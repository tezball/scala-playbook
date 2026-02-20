# Maps and Sets

## Map (Immutable)

A Map is a collection of Key-Value pairs. It is like a dictionary where you look up a word (the key) to find its definition (the value). Keys must be completely unique.

```scala
val ages = Map("Alice" -> 30, "Bob" -> 25, "Carol" -> 35)

// Access
ages("Alice")                // 30
ages.get("Alice")            // Some(30)
ages.get("Unknown")          // None
ages.getOrElse("Unknown", 0) // 0
ages.contains("Bob")         // true

// Adding / removing
val updated = ages + ("Dave" -> 40)
val removed = ages - "Bob"
```

### Map Breakdown

- **`Map("Alice" -> 30)`**: Creates an immutable Map with string keys and integer values. The `->` arrow is used to link a key to its value.
- **`ages("Alice")`**: Looking up a key directly returns its value. However, if you look up a key that doesn't exist, **your program will crash**.
- **`.get("Alice")`**: The safest way to look up a key. It returns an `Option`. If the key exists, it returns `Some(30)`. If it doesn't, it safely returns `None` instead of crashing.
- **`.getOrElse(..., 0)`**: Extremely useful. "Try to find Unknown. If you can't, just give me the default value `0`."
- **`.contains("Bob")`**: Checks if the key exists, returning `true` or `false`.
- **`+` / `-`**: Because the Map is immutable, the `+` operator doesn't actually add Dave to the original Map. It creates a brand new Map with Dave included.

## Mutable Map

If you need a Map that updates values in-place (like a Java `HashMap`), you must explicitly import the mutable version.

```scala
import scala.collection.mutable

val scores = mutable.Map("Alice" -> 100, "Bob" -> 85)

scores("Alice") = 105          // update
scores += ("Carol" -> 90)      // add
scores -= "Bob"                // remove
scores.getOrElseUpdate("Dave", 0)  // get or insert default
```

### Mutable Map Breakdown

- **`scores("Alice") = 105`**: Directly overrides Alice's score in the existing memory space.
- **`+=` / `-=`**: Permanently adds or removes elements from the Map.
- **`.getOrElseUpdate`**: A brilliant shortcut. It checks if "Dave" exists. Since he doesn't, it inserts him with a score of 0, and then returns 0.

## Set (Immutable)

A Set is a collection of entirely unique elements. Unlike Lists, there is no guaranteed order to the items inside a Set.

```scala
val fruits = Set("apple", "banana", "cherry")

// Operations
fruits.contains("apple")     // true
fruits("apple")              // true (shorthand)
fruits + "date"              // Set(apple, banana, cherry, date)
fruits - "banana"            // Set(apple, cherry)

// Set operations
val a = Set(1, 2, 3, 4, 5)
val b = Set(3, 4, 5, 6, 7)

a & b        // intersect: Set(3, 4, 5)
a | b        // union: Set(1, 2, 3, 4, 5, 6, 7)
a diff b     // diff: Set(1, 2)
```

### Set Breakdown

- **`.contains("apple")`**: Checking if an item exists inside a Set is blisteringly fast, no matter how huge the Set is.
- **`fruits("apple")`**: A shorthand way to write `.contains()`.
- **`a & b`**: The "intersection". Returns a new Set containing only the numbers that exist in *both* Sets.
- **`a | b`**: The "union". Merges the two Sets together into one giant Set, automatically deleting any duplicates.
- **`a diff b`**: The "difference". Takes Set A, and removes any numbers from it that also appear in Set B.

## See Also

- [[Collections Overview]]
- [[Sequences]]
- [[Collection Operations]]

**Tags:** #scala #collections #maps #sets
