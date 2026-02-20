# Collections Overview

Scala has a rich, well-designed collections library. Everything is under `scala.collection`.

## The Three Branches

```text
                  Iterable
                 /    |    \
               Seq   Map   Set
              / | \
           List Vector ArraySeq
```

Each collection type has three variants:

- **`scala.collection`** — base traits (can be mutable or immutable)
- **`scala.collection.immutable`** — default, imported automatically
- **`scala.collection.mutable`** — must be explicitly imported

> [!tip] Immutable by default
> When you write `List`, `Map`, `Set` etc. without importing anything, you get the **immutable** versions.

## Quick Reference

### Immutable (default)

| Collection | Description | Indexed? | Prepend | Append | Lookup |
| --- | --- | --- | --- | --- | --- |
| `List` | Linked list | No | O(1) | O(n) | O(n) |
| `Vector` | Balanced tree | Yes | ~O(1) | ~O(1) | ~O(1) |
| `LazyList` | Lazy linked list | No | O(1) | - | O(n) |
| `ArraySeq` | Wrapped array | Yes | O(n) | O(n) | O(1) |
| `Map` | Hash trie map | - | - | - | ~O(1) |
| `Set` | Hash trie set | - | - | - | ~O(1) |
| `SortedMap` | Red-black tree | - | - | - | O(log n) |
| `SortedSet` | Red-black tree | - | - | - | O(log n) |

### Mutable

| Collection | Description |
| --- | --- |
| `ArrayBuffer` | Resizable array (like Java `ArrayList`) |
| `ListBuffer` | Mutable list for efficient building |
| `mutable.Map` | Hash map |
| `mutable.Set` | Hash set |
| `mutable.Queue` | FIFO queue |
| `mutable.Stack` | LIFO stack |
| `Array` | JVM array, mutable and fixed-size |

## Creating Collections

```scala
// Immutable
val list = List(1, 2, 3)
val vec = Vector(1, 2, 3)
val set = Set(1, 2, 3)
val map = Map("a" -> 1, "b" -> 2)
val range = 1 to 10          // inclusive
val range2 = 1 until 10      // exclusive

// Mutable
import scala.collection.mutable
val buf = mutable.ArrayBuffer(1, 2, 3)
val mMap = mutable.Map("a" -> 1)
val mSet = mutable.Set(1, 2, 3)

// Empty collections
val empty = List.empty[Int]
val emptyMap = Map.empty[String, Int]

// From other collections
val fromArray = List.from(Array(1, 2, 3))
vec.to(mutable.ArrayBuffer)
```

### Creation Breakdown

- **`List(1, 2, 3)`**: Creates an immutable linked list. You don't need `new`.
- **`Set(1, 2, 3)`**: Creates an immutable set (no duplicates allowed).
- **`Map("a" -> 1, ...)`**: Creates an immutable dictionary/map. The `->` syntax is just syntactic sugar for creating a Tuple `("a", 1)`.
- **`1 to 10`**: Generates a collection of numbers exactly from 1 to 10. `until` generates from 1 to 9.
- **`import scala.collection.mutable`**: If you absolutely need a sequence you can update (like Java's `ArrayList`), you must import `mutable` first to get `ArrayBuffer` or `mutable.Map`.
- **`List.empty[Int]`**: A safe way to create a blank list while explicitly telling the compiler what type of data will eventually go inside it.
- **`vec.to(mutable.ArrayBuffer)`**: You can convert any collection into any other type of collection by calling `.to(TargetType)`.

## Choosing the Right Collection

```text
Need indexed access?
├─ Yes → Vector (immutable) or Array/ArrayBuffer (mutable)
└─ No
   ├─ Prepend-heavy? → List
   ├─ Need lazy eval? → LazyList
   ├─ Key-value pairs? → Map
   ├─ Unique elements? → Set
   └─ FIFO/LIFO? → Queue or Stack (mutable)
```

## Converting Between Collections

```scala
val list = List(1, 2, 3)
list.toVector
list.toSet
list.toArray
list.to(mutable.ArrayBuffer)

val map = Map("a" -> 1, "b" -> 2)
map.toList          // List(("a", 1), ("b", 2))
map.keys.toList     // List("a", "b")
map.values.toSet    // Set(1, 2)
```

### Conversion Breakdown

- **`list.toVector`**: Converts the linked list into a Vector, which is faster for random access.
- **`map.toList`**: Converts a Map into a List of Tuples.
- **`map.keys` / `map.values`**: Extracts only the keys or only the values from the map, which can then be converted to a List or Set.

## See Also

- [[Sequences]]
- [[Maps and Sets]]
- [[Collection Operations]]

**Tags:** #scala #collections #overview
