package orders

import slick.jdbc.PostgresProfile.api.*

case class Order(id: Long, userName: String, itemName: String, quantity: Int)

case class OrderForm(userName: String, itemName: String, quantity: Int)

class OrdersTable(tag: Tag) extends Table[Order](tag, "orders"):
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def userName = column[String]("user_name")
  def itemName = column[String]("item_name")
  def quantity = column[Int]("quantity")
  def * = (id, userName, itemName, quantity).mapTo[Order]
