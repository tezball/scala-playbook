package workflow

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import java.time.Instant
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class WorkflowRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(using ec: ExecutionContext):

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig.*
  import profile.api.*

  private val orders = TableQuery[WorkflowOrdersTable]

  given instantColumnType: BaseColumnType[Instant] =
    MappedColumnType.base[Instant, Long](_.toEpochMilli, Instant.ofEpochMilli)

  def list(): Future[Seq[WorkflowOrder]] = db.run(orders.result)

  def findById(id: Long): Future[Option[WorkflowOrder]] =
    db.run(orders.filter(_.id === id).result.headOption)

  def create(description: String): Future[WorkflowOrder] =
    val now = Instant.now()
    val insertQuery = (orders.map(o => (o.description, o.status, o.statusData, o.createdAt, o.updatedAt))
      returning orders.map(_.id)
      into ((fields, id) => WorkflowOrder(id, fields._1, fields._2, fields._3, fields._4, fields._5)))

    db.run(insertQuery += (description, "Pending", "", now, now))

  def updateStatus(id: Long, status: String, statusData: String): Future[Int] =
    db.run(
      orders.filter(_.id === id)
        .map(o => (o.status, o.statusData, o.updatedAt))
        .update((status, statusData, Instant.now()))
    )
