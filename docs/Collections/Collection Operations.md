# Collection Operations

The real power of Scala collections comes from the rich set of transformation methods available on all collection types. Most of these functions accept "anonymous functions" (lambdas) to do their work.

## Transforming

```scala
val xs = List(1, 2, 3, 4, 5)

// map — transform each element
xs.map(_ * 2)                // List(2, 4, 6, 8, 10)
xs.map(x => s"num: $x")     // List("num: 1", "num: 2", ...)

// flatMap — transform and flatten
xs.flatMap(x => List(x, x * 10))   // List(1, 10, 2, 20, 3, 30, 4, 40, 5, 50)
List("hello world", "hi").flatMap(_.split(" "))  // List(hello, world, hi)

// collect — partial function (map + filter in one)
xs.collect { case x if x % 2 == 0 => x * 10 }   // List(20, 40)
```

### Transformers Breakdown

- **`.map(x => ...)`**: Visits every element in the list, applies your math or logic to it, and gives you back a brand new list with the transformed results.
- **`.map(_ * 2)`**: The `_` is shorthand. It means "take whatever element we are currently looking at and multiply it by 2".
- **`.flatMap`**: If your `.map` function accidentally returns lists *inside* of lists (`List(List(1), List(2))`), `flatMap` automatically smashes them flat into a single list (`List(1, 2)`). It is extremely common.
- **`.collect { case ... }`**: A combination of filtering and mapping. It runs a Pattern Match against every element. If the element doesn't match the case, it deletes it. If it does match, it transforms it.

## Filtering

```scala
val xs = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

xs.filter(_ % 2 == 0)       // List(2, 4, 6, 8, 10)
xs.filterNot(_ % 2 == 0)    // List(1, 3, 5, 7, 9)
xs.partition(_ % 2 == 0)    // (List(2, 4, 6...), List(1, 3, 5...))
xs.takeWhile(_ < 5)         // List(1, 2, 3, 4)
xs.dropWhile(_ < 5)         // List(5, 6, 7, 8, 9, 10)
xs.exists(_ > 8)             // true
xs.forall(_ > 0)             // true
xs.find(_ > 3)               // Some(4)
```

### Filtering Breakdown

- **`.filter(condition)`**: Keeps elements where the condition is true. Deletes the rest.
- **`.filterNot`**: The exact opposite of filter.
- **`.partition`**: Gives you back a Tuple of two lists: one list of the elements that passed the filter, and one list of elements that failed the filter.
- **`.takeWhile`**: Reads the list left to right, keeping elements until it hits one that fails the condition. Then it stops immediately and throws the rest of the list away.
- **`.exists` / `.forall`**: Returns a simple `true` or `false` if *any* element matches, or if *every* element matches.
- **`.find`**: Searches for the first element that matches the condition and returns it safely wrapped in an `Option`. (See [[Error Handling]]).

## Folding and Reducing

Folding is how you collapse a list of many items into a single, cohesive value (like finding the sum of all numbers, or gluing a list of words into a sentence).

```scala
val xs = List(1, 2, 3, 4, 5)

// foldLeft — accumulate from left with initial value
xs.foldLeft(0)(_ + _)            // 15  (sum)
xs.foldLeft("")(_ + _.toString)  // "12345"

// reduce — like fold but uses first element as initial value
xs.reduce(_ + _)                 // 15
```

### Folding Breakdown

- **`.foldLeft(0)(_ + _)`**: The first parentheses `(0)` provides a "starting value". The second parentheses `(_ + _)` provides the math to combine items.
  1. It starts with your `0`.
  2. It takes the first element `1` and adds it to your starting value `0 + 1 = 1`.
  3. It takes the next element `2` and adds it to the running total `1 + 2 = 3`.
  4. It repeats this left-to-right until the list is empty and returns the final total.
- **`.reduce`**: Exactly the same as foldLeft, but instead of forcing you to provide a starting value like `0`, it just grabs the very first element of your list and uses that as the starting point.

## Grouping

```scala
val words = List("apple", "avocado", "banana", "blueberry", "cherry")

words.groupBy(_.head)
// Map(a -> List(apple, avocado), b -> List(banana, blueberry), c -> List(cherry))
```

### Grouping Breakdown

- **`.groupBy(_.head)`**: Asks you for a rule to group by. Here, we say "Group by the first letter (`head`)". It returns a `Map` where the keys are the letters, and the values are lists of words that start with that letter.

## Sorting

```scala
val xs = List(3, 1, 4, 1, 5, 9)

xs.sorted                     // List(1, 1, 3, 4, 5, 9)
xs.sortBy(-_)                 // List(9, 5, 4, 3, 1, 1) — descending

case class Person(name: String, age: Int)
val people = List(Person("Alice", 30), Person("Bob", 25))
people.sortBy(_.age)          // sorted by age ascending
```

### Sorting Breakdown

- **`.sorted`**: Sorts elements in their natural, default order (numbers ascending, strings alphabetical).
- **`.sortBy(_.age)`**: Extracts a specific piece of data from your objects (like `age`), and then uses that data to determine the sorted order.
- **`-_`**: A neat trick. Putting a minus sign in front of a number sorts it in descending order.

## Zipping and Unzipping

```scala
val names = List("Alice", "Bob", "Carol")
val ages = List(30, 25, 35)

names.zip(ages)               // List(("Alice", 30), ("Bob", 25), ("Carol", 35))
names.zipWithIndex            // List(("Alice", 0), ("Bob", 1), ("Carol", 2))
```

### Zipping Breakdown

- **`.zip`**: Takes two separate lists and merges them together like a zipper, creating a single list of Tuples. The first elements become a pair, the second elements become a pair, etc.
- **`.zipWithIndex`**: Automatically attaches an index number (0, 1, 2...) to every single element in your list.

## Chaining Operations

Because all these functions return new collections without mutating the old one, you can chain them together sequentially to do very complex data processing in clean, readable steps.

```scala
val data = List("hello world", "good morning", "hi there", "hello")

val result = data
  .flatMap(_.split(" "))                   // 1. split sentences into indvidual words
  .map(_.toLowerCase)                      // 2. force all words to lowercase
  .filter(_.length > 3)                    // 3. delete small words under 3 characters
  .groupBy(identity)                       // 4. group identical words together into a Map
  .map((word, list) => word -> list.size)  // 5. convert to a count of the words
  .toList                                  // 6. convert Map back to a List of Tuples
  .sortBy(-_._2)                           // 7. sort by the Tuple's count (descending)
```

## See Also

- [[Collections Overview]]
- [[Sequences]]
- [[Maps and Sets]]

**Tags:** #scala #collections #operations #functional
