package coupons

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class CouponRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(using ec: ExecutionContext):

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig.*
  import profile.api.*

  private val coupons = TableQuery[CouponsTable]

  given localDateColumnType: BaseColumnType[java.time.LocalDate] =
    MappedColumnType.base[java.time.LocalDate, String](_.toString, java.time.LocalDate.parse)

  def list(): Future[Seq[Coupon]] = db.run(coupons.result)

  def findByCode(code: String): Future[Option[Coupon]] =
    db.run(coupons.filter(_.code === code).result.headOption)
