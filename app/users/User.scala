package users

import slick.jdbc.PostgresProfile.api.*

case class User(id: Long, name: String, email: String, phone: String)

case class UserForm(name: String, email: String, phone: String)

class UsersTable(tag: Tag) extends Table[User](tag, "users"):
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def email = column[String]("email")
  def phone = column[String]("phone")
  def * = (id, name, email, phone).mapTo[User]
