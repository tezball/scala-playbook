package reviews

import java.time.Instant

// Composable traits
trait Timestamped:
  def createdAt: Instant

trait Rateable:
  def rating: Int
  def stars: String = "\u2605" * rating + "\u2606" * (5 - rating)

trait Authorable:
  def author: String

// Custom type class: Show
trait Show[A]:
  def show(a: A): String

object Show:
  def apply[A](using s: Show[A]): Show[A] = s

// Using the type class
def display[A](items: Seq[A])(using s: Show[A]): Seq[String] =
  items.map(s.show)

// Custom Ordering instances (type class in action)
object ReviewOrderings:
  given byRating: Ordering[Review] = Ordering.by(r => (-r.rating, r.author))
  given byDate: Ordering[Review] = Ordering.by[Review, Instant](_.createdAt).reverse
  given byAuthor: Ordering[Review] = Ordering.by(_.author)
