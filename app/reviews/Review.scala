package reviews

import slick.jdbc.PostgresProfile.api.*
import java.time.Instant

// Case class mixing in multiple traits
case class Review(id: Long, productName: String, author: String, rating: Int, comment: String, createdAt: Instant)
  extends Timestamped, Rateable, Authorable

case class ReviewForm(productName: String, author: String, rating: Int, comment: String)

class ReviewsTable(tag: Tag) extends Table[Review](tag, "reviews"):
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def productName = column[String]("product_name")
  def author = column[String]("author")
  def rating = column[Int]("rating")
  def comment = column[String]("comment")
  def createdAt = column[Instant]("created_at")
  def * = (id, productName, author, rating, comment, createdAt).mapTo[Review]

  given instantColumnType: BaseColumnType[Instant] =
    MappedColumnType.base[Instant, Long](_.toEpochMilli, Instant.ofEpochMilli)

// Given instance for Show[Review]
given Show[Review] with
  def show(r: Review) = s"${r.stars} by ${r.author}: ${r.comment}"
