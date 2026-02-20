package ledger

import slick.jdbc.PostgresProfile.api.*
import java.time.Instant

// Sealed trait for transaction types
sealed trait TransactionType(val label: String)
case object Credit extends TransactionType("Credit")
case object Debit extends TransactionType("Debit")
case object Refund extends TransactionType("Refund")
case object Fee extends TransactionType("Fee")

object TransactionType:
  def fromString(s: String): TransactionType = s match
    case "Credit" => Credit
    case "Debit"  => Debit
    case "Refund" => Refund
    case "Fee"    => Fee
    case _        => Credit

case class LedgerEntry(id: Long, accountName: String, txType: String, amount: Double, description: String, createdAt: Instant)

case class LedgerEntryForm(accountName: String, txType: String, amount: Double, description: String)

class LedgerEntriesTable(tag: Tag) extends Table[LedgerEntry](tag, "ledger_entries"):
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def accountName = column[String]("account_name")
  def txType = column[String]("tx_type")
  def amount = column[Double]("amount")
  def description = column[String]("description")
  def createdAt = column[Instant]("created_at")
  def * = (id, accountName, txType, amount, description, createdAt).mapTo[LedgerEntry]

  given instantColumnType: BaseColumnType[Instant] =
    MappedColumnType.base[Instant, Long](_.toEpochMilli, Instant.ofEpochMilli)
