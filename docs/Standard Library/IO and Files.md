# IO and Files

Scala provides `scala.io.Source` for reading basic text files, and relies on standard `java.io` or `java.nio` for writing them.

## Reading Files

```scala
import scala.io.Source
import scala.util.Using

// Process line by line
Using(Source.fromFile("data.txt")) { source =>
  for line <- source.getLines() do
    println(line)
}
```

### Reading Breakdown

- **`Source.fromFile("data.txt")`**: Opens a live connection from your app to the file on the hard drive.
- **`Using(...) { source => ... }`**: A critical safety mechanism. Files must be closed when you are done reading them, or your operating system will run out of file handles and crash. `Using` guarantees that the file will be closed automatically the exact millisecond the block finishes, even if exceptions are thrown while reading!
- **`.getLines()`**: Reads the file lazily one line at a time to prevent RAM overload.

## Writing Files

Scala relies totally on Java's robust IO libraries for writing.

```scala
import java.io.{PrintWriter, File}
import scala.util.Using

Using(PrintWriter(File("output.txt"))) { pw =>
  pw.println("Safe writing")
  pw.println("With resource management")
}
```

### Writing Breakdown

- **`PrintWriter(File(...))`**: Standard Java. Opens an output stream to the file.
- **`pw.println`**: Writes raw text into the file followed by a newline.
- Just like reading, we wrap this in `Using` so that the file is safely closed and saved to the hard drive when finished.

## java.nio.file (Modern Approach)

For fast, tiny file operations, modern Java (and thus Scala) provides the `Files` utility.

```scala
import java.nio.file.{Files, Paths}
import java.nio.charset.StandardCharsets

// Write an entire string at once
Files.writeString(
  Paths.get("output.txt"),
  "Hello from nio!",
  StandardCharsets.UTF_8
)

// Read an entire file at once
val content = Files.readString(Paths.get("output.txt"))
```

### NIO Breakdown

- **`Paths.get`**: Points to a location on your hard drive.
- **`Files.writeString` / `readString`**: Incredibly convenient one-liner functions that open the file, read/write ALL the text instantly, and close the file. You don't even need `Using`! Only use this for small text files that easily fit in your computer's RAM.

## Reading from stdin (Console Input)

```scala
import scala.io.StdIn

val name = StdIn.readLine("Enter your name: ")
val age = StdIn.readLine("Enter your age: ").toInt
println(s"Hello $name, age $age")
```

### StdIn Breakdown

- **`StdIn.readLine`**: Pauses your entire program and waits for the user to type something into the terminal and press Enter.

## See Also

- [[Error Handling]]
- [[Strings]]

**Tags:** #scala #io #files
