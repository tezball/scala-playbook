# Iterators and Views

Sometimes you have to process massive amounts of data. Converting a million items into a `List`, processing them, and returning another million-item `List` will quickly cause your computer to run out of memory. Scala provides **Iterators** and **Views** to process data *lazily*, one piece at a time.

## Iterator

An Iterator is a one-time-use pointer. It sits at the beginning of some data and moves forward one step at a time. Elements are "consumed" and thrown away after you look at them.

```scala
val it = Iterator(1, 2, 3, 4, 5)

it.next()       // 1
it.next()       // 2
it.hasNext      // true

// Transformations are lazy — nothing computed until consumption
val result = Iterator(1, 2, 3, 4, 5)
  .map(_ * 2)
  .filter(_ > 4)
  .take(2)

result.toList   // List(6, 8) 
```

### Iterator Breakdown

- **`.next()`**: Asks the iterator to give you the current element, and then permanently moves the pointer forward.
- **`.hasNext`**: Checks if there is anything left to read. If you call `.next()` and there is nothing left, the program will crash. Always check `.hasNext`!
- **`result.toList`**: The `.map` and `.filter` functions above *did absolutely nothing* when they were declared. Iterators are completely lazy. Only when you finally call `.toList` does the Iterator wake up, pull the first number, multiply it, check it, and save it to the list.

> [!warning] Iterators are single-use
> After traversing an Iterator, it is permanently exhausted. Calling `.toList` a second time will return an empty list because the pointer is already at the end!

## View

A `View` is a lazy collection just like an Iterator, but with one massive advantage: **Views are reusable!**

```scala
val v = List(1, 2, 3, 4, 5).view

// Transformations are lazy
val result = v
  .map(_ * 2)
  .filter(_ > 4)

// Nothing computed yet. Force evaluation:
result.toList        // List(6, 8, 10)
result.toList        // List(6, 8, 10) 
```

### View Breakdown

- **`.view`**: You can convert any standard Scala collection (like a List or Array) into a View by just calling `.view`.
- **`result.toList`**: Notice that we can call `.toList` twice here! Unlike an Iterator, a View remembers the original collection it came from, so it can start over from the beginning as many times as you want.

### Why Use Views?

If you chain multiple operations together on a normal `List`, Scala creates a brand new temporary List in memory after *every single step*. Views prevent this waste.

```scala
// BAD: Creates 3 temporary Lists in memory
val bad = (1 to 1000000)
  .map(_ * 2)          
  .filter(_ % 3 == 0)  
  .take(10)            
  .toList

// GOOD: Creates NO temporary Lists!
val good = (1 to 1000000).view
  .map(_ * 2)
  .filter(_ % 3 == 0)
  .take(10)
  .toList              
```

## LazyList (Persistent Lazy Sequence)

A `LazyList` is the third lazy option. Unlike Iterators, it is reusable. Unlike Views, it actually saves (caches) the elements it calculates so it never has to do the math twice.

```scala
val fibs: LazyList[BigInt] =
  BigInt(0) #:: BigInt(1) #:: fibs.zip(fibs.tail).map(_ + _)

fibs(10)              // 55 (computed and cached)
fibs(10)              // 55 (instant — already cached)
fibs.take(8).toList   // List(0, 1, 1, 2, 3, 5, 8, 13)
```

### LazyList Breakdown

- **`fibs(10)`**: The first time you ask for the 10th Fibonacci number, the computer does the math to figure it out. The *second* time you ask for it, it answers instantly because it saved the answer in its cache.

| Feature | LazyList | View | Iterator |
| --- | --- | --- | --- |
| Lazy | Yes | Yes | Yes |
| Reusable | Yes | Yes | No |
| Caches results | **Yes** | No | No |
| Can be infinite | Yes | Depends on source | Yes |

## See Also

- [[Collections Overview]]
- [[Collection Operations]]
- [[Sequences]]

**Tags:** #scala #collections #iterators #views #lazy
