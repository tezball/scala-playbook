package users

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UserRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(using ec: ExecutionContext):

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig.*
  import profile.api.*

  private val users = TableQuery[UsersTable]

  def list(): Future[Seq[User]] = db.run(users.result)

  def count(): Future[Int] = db.run(users.length.result)

  def create(name: String, email: String, phone: String): Future[User] =
    val insertQuery = (users.map(u => (u.name, u.email, u.phone))
      returning users.map(_.id)
      into ((fields, id) => User(id, fields._1, fields._2, fields._3)))

    db.run(insertQuery += (name, email, phone))
