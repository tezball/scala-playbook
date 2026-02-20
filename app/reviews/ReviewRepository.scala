package reviews

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import java.time.Instant
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ReviewRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(using ec: ExecutionContext):

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig.*
  import profile.api.*

  private val reviews = TableQuery[ReviewsTable]

  given instantColumnType: BaseColumnType[Instant] =
    MappedColumnType.base[Instant, Long](_.toEpochMilli, Instant.ofEpochMilli)

  def list(): Future[Seq[Review]] = db.run(reviews.result)

  def create(productName: String, author: String, rating: Int, comment: String): Future[Review] =
    val now = Instant.now()
    val insertQuery = (reviews.map(r => (r.productName, r.author, r.rating, r.comment, r.createdAt))
      returning reviews.map(_.id)
      into ((fields, id) => Review(id, fields._1, fields._2, fields._3, fields._4, fields._5)))

    db.run(insertQuery += (productName, author, rating, comment, now))
