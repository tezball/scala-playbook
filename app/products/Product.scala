package products

import slick.jdbc.PostgresProfile.api.*

case class Product(id: Long, name: String, category: String, basePrice: Double, discountType: String, discountValue: Double, finalPrice: Double)

case class ProductForm(name: String, category: String, basePrice: Double, discountType: String, discountValue: Double)

class ProductsTable(tag: Tag) extends Table[Product](tag, "products"):
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def category = column[String]("category")
  def basePrice = column[Double]("base_price")
  def discountType = column[String]("discount_type")
  def discountValue = column[Double]("discount_value")
  def finalPrice = column[Double]("final_price")
  def * = (id, name, category, basePrice, discountType, discountValue, finalPrice).mapTo[Product]
