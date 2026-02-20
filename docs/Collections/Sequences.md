# Sequences

Ordered collections of elements. Sequences guarantee that items will be returned in the exact same order you put them in.

## List (Immutable Linked List)

The most commonly used sequence. It is a linked list, which means prepending to the front is magically fast, but fetching an item from the middle or end is slow.

```scala
val xs = List(1, 2, 3, 4, 5)

// Constructing
val ys = 0 :: xs              // List(0, 1, 2, 3, 4, 5)
val zs = xs :+ 6              // List(1, 2, 3, 4, 5, 6)
val combined = xs ++ ys       // concatenation

// Head and tail
xs.head          // 1
xs.tail          // List(2, 3, 4, 5)
xs.last          // 5
xs.init          // List(1, 2, 3, 4)

// Access
xs(2)            // 3 
xs.length        // 5
xs.isEmpty       // false

// Building with ::
val manual = 1 :: 2 :: 3 :: Nil   // List(1, 2, 3)
```

### List Breakdown

- **`0 :: xs`**: The `::` ("cons") operator prepends an element to the front of a list. Because Lists are linked lists, adding to the front is instantaneous (O(1)).
- **`xs :+ 6`**: The `:+` operator appends to the end of a list. In a linked list, this is slow because it has to traverse the entire list first (O(N)).
- **`++`**: Glues two entire collections together.
- **`xs.head` / `xs.tail`**: `head` grabs the very first element. `tail` grabs *everything else* except the first element.
- **`xs.init`**: Grabs everything *except* the last element.
- **`xs(2)`**: Grabs the element at index 2 (the third element). Again, this is slow on Lists.
- **`1 :: 2 :: 3 :: Nil`**: This is how Lists are built behind the scenes. `Nil` is the empty list. You prepend 3 to `Nil`, then 2 to that, then 1 to that.

> [!info] `::` vs `+:`
> `::` is specific to List. `+:` works on any sequence type. Both prepend to the front.

## Vector (Immutable Indexed Sequence)

Balanced tree structure. Vector offers good all-around performance for both fetching by index and appending to the end.

```scala
val v = Vector(1, 2, 3, 4, 5)

v(2)               // 3 
v :+ 6             // append
0 +: v             // prepend
v.updated(2, 99)   // Vector(1, 2, 99, 4, 5)
```

### Vector Breakdown

- **`v(2)`**: Grabbing an element by index in a Vector is effectively instantaneous, unlike a List.
- **`v.updated(2, 99)`**: Since Vectors are immutable, this doesn't actually mutate `v`. It gives you a brand new Vector where index 2 has been swapped with 99, intelligently sharing the rest of the memory to keep things fast.

> [!tip] When to use Vector vs List
>
> - **List**: when you mostly prepend elements, or when you are using recursive pattern matching on `head` and `tail`.
> - **Vector**: as a safe general-purpose default. Especially use it if you need random access (like `v(50)`) or need to append to the end frequently.

## Array

A standard JVM array. It is mutable, fixed-size, and has extremely fast memory access.

```scala
val arr = Array(1, 2, 3, 4, 5)

arr(0)             // 1
arr(0) = 10        // mutation
arr.length         // 5

arr.mkString(", ")     // "10, 2, 3, 4, 5"
arr.toList             // convert to List
```

### Array Breakdown

- **`arr(0) = 10`**: Arrays are one of the few built-in collections that are mutable. You can change elements in-place.
- **`arr.length`**: Once created, an array can never grow or shrink. Its length is locked forever.
- **`arr.mkString(", ")`**: Because Arrays use Java's raw `toString`, printing them directly looks like `[I@5f1860`. `.mkString` explicitly builds a pretty, human-readable string out of the elements.

## ArrayBuffer (Mutable Resizable Array)

If you need a list that can grow and shrink and change over time, use `ArrayBuffer`. It behaves almost exactly like Java's `ArrayList`.

```scala
import scala.collection.mutable.ArrayBuffer

val buf = ArrayBuffer(1, 2, 3)

buf += 4                     // append
buf += (5, 6)                // append multiple
buf.prepend(0)               // prepend
buf.insert(2, 99)            // insert at index
buf.remove(2)                // remove at index
buf.toList                   // convert to immutable List
```

### ArrayBuffer Breakdown

- **`buf += 4`**: The `+=` operator permanently modifies the `ArrayBuffer` by mutating it in place and adding 4 to the end.
- **`.insert` / `.remove`**: You can chop and change the buffer dynamically.

## LazyList (Lazy Sequence)

Elements are computed only when they are explicitly accessed. This allows for infinitely long sequences without crashing your computer!

```scala
// Finite
val lazy1 = LazyList(1, 2, 3)

// Infinite sequences
val naturals = LazyList.from(1)           
val fibs: LazyList[BigInt] =
  BigInt(0) #:: BigInt(1) #:: fibs.zip(fibs.tail).map(_ + _)

naturals.take(5).toList    // List(1, 2, 3, 4, 5)
```

### LazyList Breakdown

- **`LazyList.from(1)`**: Generates an infinite list of numbers starting at 1. Because it's "lazy", it doesn't actually calculate the numbers until you explicitly ask for them, preventing memory overflows.
- **`#::`**: This is the lazy version of the `::` operator. It prepends elements without eagerly evaluating them.
- **`.take(5).toList`**: This asks the infinitely long list to finally calculate just its first 5 elements, and then saves them securely into a normal, fixed List.

## Range

```scala
val r1 = 1 to 10         // 1, 2, ..., 10 (inclusive)
val r2 = 1 until 10      // 1, 2, ..., 9 (exclusive)
val r3 = 1 to 20 by 3    // 1, 4, 7, 10, 13, 16, 19
val r4 = 10 to 1 by -1   // 10, 9, 8, ..., 1

r1.toList
r1.sum                    // 55
```

### Range Breakdown

- **`by 3`**: Step syntax. It generates numbers incrementing by 3 instead of 1.
- **`by -1`**: If you want a range to count backwards, you must explicitly tell it to step by -1.

## Common Operations

See [[Collection Operations]] for complete details on sequence methods.

```scala
val xs = List(3, 1, 4, 1, 5, 9, 2, 6)

xs.sorted                 // List(1, 1, 2, 3, 4, 5, 6, 9)
xs.sortBy(-_)             // descending
xs.reverse                // List(6, 2, 9, 5, 1, 4, 1, 3)
xs.distinct               // List(3, 1, 4, 5, 9, 2, 6)
xs.take(3)                // List(3, 1, 4)
xs.drop(3)                // List(1, 5, 9, 2, 6)
xs.slice(2, 5)            // List(4, 1, 5)
xs.contains(5)            // true
xs.indexOf(4)             // 2
xs.zip(List("a", "b"))    // List((3, "a"), (1, "b"))
xs.grouped(3).toList      // List(List(3,1,4), List(1,5,9), List(2,6))
xs.sliding(3).toList       // List(List(3,1,4), List(1,4,1), ...)
```

## See Also

- [[Collections Overview]]
- [[Collection Operations]]
- [[Maps and Sets]]

**Tags:** #scala #collections #sequences
