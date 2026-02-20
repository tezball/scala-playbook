package ledger

object LedgerCalculator:

  // Event sourcing: current state derived from folding over history
  def computeBalance(entries: Seq[LedgerEntry]): Double =
    entries.foldLeft(0.0) { (balance, entry) =>
      TransactionType.fromString(entry.txType) match
        case Credit | Refund => balance + entry.amount
        case Debit | Fee     => balance - entry.amount
    }

  // Running balance at each point (scanLeft)
  def runningBalances(entries: Seq[LedgerEntry]): Seq[(LedgerEntry, Double)] =
    entries.scanLeft((null.asInstanceOf[LedgerEntry], 0.0)) { case ((_, balance), entry) =>
      TransactionType.fromString(entry.txType) match
        case Credit | Refund => (entry, balance + entry.amount)
        case Debit | Fee     => (entry, balance - entry.amount)
    }.tail

  // Grouping transactions by type
  def summaryByType(entries: Seq[LedgerEntry]): Map[String, Double] =
    entries.groupMapReduce(_.txType)(_.amount)(_ + _)

  // Total credits
  def totalCredits(entries: Seq[LedgerEntry]): Double =
    entries.filter(e => e.txType == "Credit" || e.txType == "Refund").map(_.amount).sum

  // Total debits
  def totalDebits(entries: Seq[LedgerEntry]): Double =
    entries.filter(e => e.txType == "Debit" || e.txType == "Fee").map(_.amount).sum
