# Concurrency

Scala's primary concurrency model uses `Future` and `Promise` to handle code that takes a long time to run (like downloading a file or querying a database) without freezing your entire application.

## Future

A `Future` represents a value that doesn't exist yet, but will eventually. It runs asynchronously in the background.

```scala
import scala.concurrent.{Future, Await}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.*
import scala.util.{Success, Failure}

// Creating a Future
val f: Future[Int] = Future {
  Thread.sleep(1000)  
  42
}

// Non-blocking callback
f.onComplete {
  case Success(value) => println(s"Got: $value")
  case Failure(ex)    => println(s"Failed: $ex")
}
```

### Future Breakdown

- **`Future { ... }`**: The moment you wrap code in this block, Scala instantly kicks it off on a separate background thread. The code inside acts like normal, but doesn't freeze the main app.
- **`f.onComplete`**: This is a "callback". You are handing the Future a set of instructions: "Whenever you finish, whether it takes 1 second or an hour, run this pattern match on your result."
- **`Success(...) / Failure(...)`**: A Future can either succeed and return its value (42), or crash and return its Exception. You safely handle both here without `try/catch`.

## Composing Futures

What if you need to fetch a User, and *then* fetch their Orders? You have to chain Futures together.

```scala
def fetchUser(id: Int): Future[String] = Future(s"User-$id")
def fetchOrders(user: String): Future[List[String]] =
  Future(List(s"$user-order1", s"$user-order2"))

// Sequential composition with flatMap
val result: Future[List[String]] =
  fetchUser(1).flatMap(user => fetchOrders(user))

// For-comprehension (same as above, much cleaner)
val result2: Future[List[String]] = for
  user   <- fetchUser(1)
  orders <- fetchOrders(user)
yield orders
```

### Composition Breakdown

- **`.flatMap`**: Because `fetchUser` returns a Future, and `fetchOrders` *also* returns a Future, you can't just `map` them. You use `flatMap` to fuse the two background tasks together seamlessly into a single timeline.
- **`for ... yield`**: This is exactly identical to the `flatMap` above, but it is vastly easier to read! It reads like standard synchronous code: "Wait for User, then wait for Orders, then yield the final Orders."

## Error Handling in Futures

```scala
val f = Future { "abc".toInt }  // This will fail with an Exception

// recover (like catch)
val safe = f.recover {
  case _: NumberFormatException => 0
}
```

### Recovery Breakdown

- **`.recover`**: If the Future crashes on a background thread, the app doesn't crash! Instead, `.recover` acts like a safety net. If it detects a specific error, it instantly repairs the Future by providing a default backup value (0). If the Future succeeded normally, `.recover` does nothing.

## Combining Multiple Futures

```scala
val futures: List[Future[Int]] = List(
  Future(1), Future(2), Future(3)
)

// Future.sequence
val combined: Future[List[Int]] = Future.sequence(futures)
```

### Combining Breakdown

- **`Future.sequence`**: A magical utility. It takes a List of 3 independent Futures running in parallel, and flips them inside out. It returns a single giant `Future` that only completes once *all 3* background tasks finish, yielding a single `List[Int]`.

## Promise

A `Promise` is a Future that you can manually control.

```scala
import scala.concurrent.Promise

val promise = Promise[Int]()
val future = promise.future

promise.success(42)
// future is now completely resolved with 42!
```

### Promise Breakdown

- **`Promise[Int]()`**: You are creating an empty box.
- **`promise.future`**: You hand the "read-only" side of the box to whoever needs the data. They can attach `.onComplete` to it and wait.
- **`promise.success(42)`**: You manually stuff the value 42 into the writable side of the box. The associated Future instantly wakes up and triggers its completion.

## ExecutionContext

All futures need an `ExecutionContext` — the thread pool that runs them.

```scala
// Global (fine for simple apps, demos)
import scala.concurrent.ExecutionContext.Implicits.global
```

### ExecutionContext Breakdown

- **`Implicits.global`**: By importing this, you are silently passing Scala's default, global thread pool into every single Future you build in this file. The `global` pool uses as many threads as your computer has CPU cores.

## See Also

- [[Error Handling]]
- [[Functions]]
- [[Standard Library Utilities]]

**Tags:** #scala #concurrency #futures #async
