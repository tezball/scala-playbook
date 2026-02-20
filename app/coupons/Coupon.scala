package coupons

import slick.jdbc.PostgresProfile.api.*
import java.time.LocalDate

case class Coupon(id: Long, code: String, discountPercent: Double, expiresAt: LocalDate, usesRemaining: Int, minimumOrder: Double)

class CouponsTable(tag: Tag) extends Table[Coupon](tag, "coupons"):
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def code = column[String]("code")
  def discountPercent = column[Double]("discount_percent")
  def expiresAt = column[LocalDate]("expires_at")
  def usesRemaining = column[Int]("uses_remaining")
  def minimumOrder = column[Double]("minimum_order")
  def * = (id, code, discountPercent, expiresAt, usesRemaining, minimumOrder).mapTo[Coupon]

  given localDateColumnType: BaseColumnType[LocalDate] =
    MappedColumnType.base[LocalDate, String](_.toString, LocalDate.parse)
