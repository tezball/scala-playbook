package ledger

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import java.time.Instant
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class LedgerRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(using ec: ExecutionContext):

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig.*
  import profile.api.*

  private val entries = TableQuery[LedgerEntriesTable]

  given instantColumnType: BaseColumnType[Instant] =
    MappedColumnType.base[Instant, Long](_.toEpochMilli, Instant.ofEpochMilli)

  def list(): Future[Seq[LedgerEntry]] = db.run(entries.sortBy(_.id).result)

  def listByAccount(accountName: String): Future[Seq[LedgerEntry]] =
    db.run(entries.filter(_.accountName === accountName).sortBy(_.id).result)

  def accounts(): Future[Seq[String]] =
    db.run(entries.map(_.accountName).distinct.result)

  def create(accountName: String, txType: String, amount: Double, description: String): Future[LedgerEntry] =
    val now = Instant.now()
    val insertQuery = (entries.map(e => (e.accountName, e.txType, e.amount, e.description, e.createdAt))
      returning entries.map(_.id)
      into ((fields, id) => LedgerEntry(id, fields._1, fields._2, fields._3, fields._4, fields._5)))

    db.run(insertQuery += (accountName, txType, amount, description, now))
