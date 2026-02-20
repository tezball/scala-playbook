package orders

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class OrderRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(using ec: ExecutionContext):

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig.*
  import profile.api.*

  private val orders = TableQuery[OrdersTable]

  def list(): Future[Seq[Order]] = db.run(orders.result)

  def count(): Future[Int] = db.run(orders.length.result)

  def topItems(n: Int): Future[Seq[(String, Int)]] =
    db.run(
      orders.groupBy(_.itemName)
        .map((item, group) => (item, group.map(_.quantity).sum.getOrElse(0)))
        .sortBy(_._2.desc)
        .take(n)
        .result
    )

  def recent(n: Int): Future[Seq[Order]] =
    db.run(orders.sortBy(_.id.desc).take(n).result)

  def create(userName: String, itemName: String, quantity: Int): Future[Order] =
    val insertQuery = (orders.map(o => (o.userName, o.itemName, o.quantity))
      returning orders.map(_.id)
      into ((fields, id) => Order(id, fields._1, fields._2, fields._3)))

    db.run(insertQuery += (userName, itemName, quantity))
