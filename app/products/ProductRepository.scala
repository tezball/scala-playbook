package products

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ProductRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(using ec: ExecutionContext):

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig.*
  import profile.api.*

  private val products = TableQuery[ProductsTable]

  def list(): Future[Seq[Product]] = db.run(products.result)

  def create(name: String, category: String, basePrice: Double, discountType: String, discountValue: Double, finalPrice: Double): Future[Product] =
    val insertQuery = (products.map(p => (p.name, p.category, p.basePrice, p.discountType, p.discountValue, p.finalPrice))
      returning products.map(_.id)
      into ((fields, id) => Product(id, fields._1, fields._2, fields._3, fields._4, fields._5, fields._6)))

    db.run(insertQuery += (name, category, basePrice, discountType, discountValue, finalPrice))
