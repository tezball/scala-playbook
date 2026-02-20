package ledger

import javax.inject.*
import play.api.data.Form
import play.api.data.Forms.*
import play.api.data.format.Formats.doubleFormat
import play.api.mvc.*

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class LedgerController @Inject()(
  val controllerComponents: ControllerComponents,
  ledgerRepository: LedgerRepository
)(using ec: ExecutionContext) extends BaseController:

  private val entryForm = Form(
    mapping(
      "accountName" -> nonEmptyText,
      "txType" -> nonEmptyText,
      "amount" -> of[Double],
      "description" -> nonEmptyText
    )(LedgerEntryForm.apply)(lf => Some(lf.accountName, lf.txType, lf.amount, lf.description))
  )

  def showLedger() = Action.async { implicit request: Request[AnyContent] =>
    val account = request.getQueryString("account")
    for
      accounts <- ledgerRepository.accounts()
      entries <- account.filter(_.nonEmpty).map(ledgerRepository.listByAccount).getOrElse(ledgerRepository.list())
    yield
      val running = LedgerCalculator.runningBalances(entries)
      val byType = LedgerCalculator.summaryByType(entries)
      val balance = LedgerCalculator.computeBalance(entries)
      val credits = LedgerCalculator.totalCredits(entries)
      val debits = LedgerCalculator.totalDebits(entries)
      Ok(views.html.ledgerView(entryForm, running, byType, balance, credits, debits, entries.size, accounts, account.getOrElse("")))
  }

  def addEntry() = Action.async { implicit request: Request[AnyContent] =>
    entryForm.bindFromRequest().fold(
      formWithErrors =>
        for
          accounts <- ledgerRepository.accounts()
          entries <- ledgerRepository.list()
        yield
          val running = LedgerCalculator.runningBalances(entries)
          val byType = LedgerCalculator.summaryByType(entries)
          val balance = LedgerCalculator.computeBalance(entries)
          val credits = LedgerCalculator.totalCredits(entries)
          val debits = LedgerCalculator.totalDebits(entries)
          BadRequest(views.html.ledgerView(formWithErrors, running, byType, balance, credits, debits, entries.size, accounts, "")),
      data =>
        ledgerRepository.create(data.accountName, data.txType, data.amount, data.description).map { _ =>
          Redirect(routes.LedgerController.showLedger()).flashing("success" -> s"Entry added: ${data.txType} $$${data.amount}")
        }
    )
  }
