
package views.html

import _root_.play.twirl.api.TwirlFeatureImports.*
import _root_.play.twirl.api.TwirlHelperImports.*
import scala.language.adhocExtensions
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

object ledgerView extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template10[Form[_root_.ledger.LedgerEntryForm],Seq[(_root_.ledger.LedgerEntry, Double)],Map[String, Double],Double,Double,Double,Int,Seq[String],String,RequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(form: Form[_root_.ledger.LedgerEntryForm], running: Seq[(_root_.ledger.LedgerEntry, Double)], byType: Map[String, Double], balance: Double, credits: Double, debits: Double, txCount: Int, accounts: Seq[String], currentAccount: String)(implicit request: RequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),_display_(/*3.2*/main("Ledger - Event Sourcing")/*3.33*/ {_display_(Seq[Any](format.raw/*3.35*/("""
    """),format.raw/*4.5*/("""<h1>Financial Ledger</h1>

    <div class="concept-banner">
        <h3>Advanced Concepts: Event Sourcing, foldLeft, scanLeft, Immutability, groupMapReduce</h3>
        <p>Entries are append-only (immutable history). The current balance is derived by folding over the entire transaction history. Running balances use <code>scanLeft</code>. No entry is ever edited or deleted.</p>
        <pre><code>// Event sourcing: current state derived from folding over history
def computeBalance(entries: Seq[LedgerEntry]): Double =
  entries.foldLeft(0.0) """),format.raw/*11.25*/("""{"""),format.raw/*11.26*/(""" """),format.raw/*11.27*/("""(balance, entry) =&gt;
    entry.txType match
      case Credit | Refund =&gt; balance + entry.amount
      case Debit | Fee     =&gt; balance - entry.amount
  """),format.raw/*15.3*/("""}"""),format.raw/*15.4*/("""

"""),format.raw/*17.1*/("""// Running balance at each point (scanLeft)
def runningBalances(entries: Seq[LedgerEntry]): Seq[(LedgerEntry, Double)] =
  entries.scanLeft((null, 0.0)) """),format.raw/*19.33*/("""{"""),format.raw/*19.34*/(""" case ((_, bal), entry) =&gt;
    entry.txType match
      case Credit | Refund =&gt; (entry, bal + entry.amount)
      case Debit | Fee     =&gt; (entry, bal - entry.amount)
  """),format.raw/*23.3*/("""}"""),format.raw/*23.4*/(""".tail

// Grouping transactions
def summaryByType(entries: Seq[LedgerEntry]): Map[String, Double] =
  entries.groupMapReduce(_.txType)(_.amount)(_ + _)</code></pre>
    </div>

    """),_display_(/*30.6*/request/*30.13*/.flash.get("success").map/*30.38*/ { message =>_display_(Seq[Any](format.raw/*30.51*/("""
        """),format.raw/*31.9*/("""<div class="success">"""),_display_(/*31.31*/message),format.raw/*31.38*/("""</div>
    """)))}),format.raw/*32.6*/("""

    """),format.raw/*34.5*/("""<h2>Add Transaction (append-only)</h2>
    """),_display_(/*35.6*/helper/*35.12*/.form(action = _root_.ledger.routes.LedgerController.addEntry())/*35.76*/ {_display_(Seq[Any](format.raw/*35.78*/("""
        """),_display_(/*36.10*/helper/*36.16*/.CSRF.formField),format.raw/*36.31*/("""
        """),format.raw/*37.9*/("""<div class="inline-form">
            <div class="form-group">
                <label for="accountName">Account</label>
                <input type="text" id="accountName" name="accountName" value=""""),_display_(/*40.80*/form("accountName")/*40.99*/.value.getOrElse("Main Account")),format.raw/*40.131*/("""" required>
            </div>
            <div class="form-group">
                <label for="txType">Type</label>
                <select id="txType" name="txType">
                    <option value="Credit" """),_display_(if(form("txType").value.contains("Credit"))/*45.88*/{_display_(Seq[Any](format.raw/*45.89*/("""selected""")))} else {null} ),format.raw/*45.98*/(""">Credit</option>
                    <option value="Debit" """),_display_(if(form("txType").value.contains("Debit"))/*46.86*/{_display_(Seq[Any](format.raw/*46.87*/("""selected""")))} else {null} ),format.raw/*46.96*/(""">Debit</option>
                    <option value="Refund" """),_display_(if(form("txType").value.contains("Refund"))/*47.88*/{_display_(Seq[Any](format.raw/*47.89*/("""selected""")))} else {null} ),format.raw/*47.98*/(""">Refund</option>
                    <option value="Fee" """),_display_(if(form("txType").value.contains("Fee"))/*48.82*/{_display_(Seq[Any](format.raw/*48.83*/("""selected""")))} else {null} ),format.raw/*48.92*/(""">Fee</option>
                </select>
            </div>
            <div class="form-group">
                <label for="amount">Amount ($)</label>
                <input type="number" id="amount" name="amount" step="0.01" min="0.01" value=""""),_display_(/*53.95*/form("amount")/*53.109*/.value.getOrElse("")),format.raw/*53.129*/("""" required style="width:120px">
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <input type="text" id="description" name="description" value=""""),_display_(/*57.80*/form("description")/*57.99*/.value.getOrElse("")),format.raw/*57.119*/("""" required>
            </div>
            <div class="form-group">
                <button type="submit" class="btn-blue">Add Entry</button>
                <button type="button" class="btn-autofill btn-sm" onclick="autoFillLedger()">Auto Fill</button>
            </div>
        </div>
    """)))}),format.raw/*64.6*/("""

    """),format.raw/*66.5*/("""<script>
    function autoFillLedger() """),format.raw/*67.31*/("""{"""),format.raw/*67.32*/("""
        """),format.raw/*68.9*/("""var entries = [
            """),format.raw/*69.13*/("""{"""),format.raw/*69.14*/(""" """),format.raw/*69.15*/("""account: 'Main Account', type: 'Credit', amount: 5000.00, desc: 'Monthly salary deposit - January 2025' """),format.raw/*69.119*/("""}"""),format.raw/*69.120*/(""",
            """),format.raw/*70.13*/("""{"""),format.raw/*70.14*/(""" """),format.raw/*70.15*/("""account: 'Main Account', type: 'Debit', amount: 1250.00, desc: 'Rent payment - apartment lease' """),format.raw/*70.111*/("""}"""),format.raw/*70.112*/(""",
            """),format.raw/*71.13*/("""{"""),format.raw/*71.14*/(""" """),format.raw/*71.15*/("""account: 'Main Account', type: 'Debit', amount: 89.99, desc: 'Electric utility bill - ConEd' """),format.raw/*71.108*/("""}"""),format.raw/*71.109*/(""",
            """),format.raw/*72.13*/("""{"""),format.raw/*72.14*/(""" """),format.raw/*72.15*/("""account: 'Main Account', type: 'Fee', amount: 12.50, desc: 'Monthly account maintenance fee' """),format.raw/*72.108*/("""}"""),format.raw/*72.109*/(""",
            """),format.raw/*73.13*/("""{"""),format.raw/*73.14*/(""" """),format.raw/*73.15*/("""account: 'Savings Account', type: 'Credit', amount: 1000.00, desc: 'Transfer from checking - emergency fund' """),format.raw/*73.124*/("""}"""),format.raw/*73.125*/(""",
            """),format.raw/*74.13*/("""{"""),format.raw/*74.14*/(""" """),format.raw/*74.15*/("""account: 'Main Account', type: 'Refund', amount: 149.99, desc: 'Amazon return - defective headphones' """),format.raw/*74.117*/("""}"""),format.raw/*74.118*/(""",
            """),format.raw/*75.13*/("""{"""),format.raw/*75.14*/(""" """),format.raw/*75.15*/("""account: 'Business Account', type: 'Credit', amount: 3200.00, desc: 'Client invoice payment - Project Alpha' """),format.raw/*75.124*/("""}"""),format.raw/*75.125*/(""",
            """),format.raw/*76.13*/("""{"""),format.raw/*76.14*/(""" """),format.raw/*76.15*/("""account: 'Business Account', type: 'Debit', amount: 450.00, desc: 'Software license renewal - JetBrains' """),format.raw/*76.120*/("""}"""),format.raw/*76.121*/(""",
            """),format.raw/*77.13*/("""{"""),format.raw/*77.14*/(""" """),format.raw/*77.15*/("""account: 'Business Account', type: 'Fee', amount: 29.99, desc: 'Payment processing fee - Stripe' """),format.raw/*77.112*/("""}"""),format.raw/*77.113*/(""",
            """),format.raw/*78.13*/("""{"""),format.raw/*78.14*/(""" """),format.raw/*78.15*/("""account: 'Savings Account', type: 'Credit', amount: 250.00, desc: 'Interest payment - Q4 2024' """),format.raw/*78.110*/("""}"""),format.raw/*78.111*/("""
        """),format.raw/*79.9*/("""];
        var e = entries[Math.floor(Math.random() * entries.length)];
        document.getElementById('accountName').value = e.account;
        document.getElementById('txType').value = e.type;
        document.getElementById('amount').value = e.amount;
        document.getElementById('description').value = e.desc;
    """),format.raw/*85.5*/("""}"""),format.raw/*85.6*/("""
    """),format.raw/*86.5*/("""</script>

    """),_display_(if(accounts.nonEmpty)/*88.27*/ {_display_(Seq[Any](format.raw/*88.29*/("""
        """),format.raw/*89.9*/("""<h2>Filter by Account</h2>
        <p>
            <a href=""""),_display_(/*91.23*/_root_/*91.29*/.ledger.routes.LedgerController.showLedger()),format.raw/*91.73*/("""" """),_display_(if(currentAccount.isEmpty)/*91.102*/{_display_(Seq[Any](format.raw/*91.103*/(""" """),format.raw/*91.104*/("""style="font-weight:bold" """)))} else {null} ),format.raw/*91.130*/(""">All</a>
            """),_display_(/*92.14*/for(acc <- accounts) yield /*92.34*/ {_display_(Seq[Any](format.raw/*92.36*/("""
                """),format.raw/*93.17*/("""| <a href="?account="""),_display_(/*93.38*/acc),format.raw/*93.41*/("""" """),_display_(if(currentAccount == acc)/*93.69*/{_display_(Seq[Any](format.raw/*93.70*/(""" """),format.raw/*93.71*/("""style="font-weight:bold" """)))} else {null} ),format.raw/*93.97*/(""">"""),_display_(/*93.99*/acc),format.raw/*93.102*/("""</a>
            """)))}),format.raw/*94.14*/("""
        """),format.raw/*95.9*/("""</p>
    """)))} else {null} ),format.raw/*96.6*/("""

    """),format.raw/*98.5*/("""<div class="cards">
        <div class="card">
            <h3>Current Balance (<code>foldLeft</code>)</h3>
            <p style="font-size:1.8em; color:"""),_display_(/*101.47*/{if(balance >= 0) "#4CAF50" else "#f44336"}),format.raw/*101.90*/(""""><strong>$"""),_display_(/*101.102*/{f"${balance}%.2f"}),format.raw/*101.121*/("""</strong></p>
        </div>
        <div class="card">
            <h3>Total Credits</h3>
            <p style="font-size:1.3em; color:#4CAF50"><strong>$"""),_display_(/*105.65*/{f"${credits}%.2f"}),format.raw/*105.84*/("""</strong></p>
        </div>
        <div class="card">
            <h3>Total Debits</h3>
            <p style="font-size:1.3em; color:#f44336"><strong>$"""),_display_(/*109.65*/{f"${debits}%.2f"}),format.raw/*109.83*/("""</strong></p>
        </div>
        <div class="card">
            <h3>Transaction Count</h3>
            <p style="font-size:1.3em"><strong>"""),_display_(/*113.49*/txCount),format.raw/*113.56*/("""</strong></p>
        </div>
    </div>

    """),_display_(if(byType.nonEmpty)/*117.25*/ {_display_(Seq[Any](format.raw/*117.27*/("""
        """),format.raw/*118.9*/("""<h2>By Type (<code>groupMapReduce</code>)</h2>
        <table>
            <thead><tr><th>Type</th><th>Total</th></tr></thead>
            <tbody>
                """),_display_(/*122.18*/for((txType, total) <- byType.toSeq.sortBy(_._1)) yield /*122.67*/ {_display_(Seq[Any](format.raw/*122.69*/("""
                    """),format.raw/*123.21*/("""<tr><td>"""),_display_(/*123.30*/txType),format.raw/*123.36*/("""</td><td>$"""),_display_(/*123.47*/{f"${total}%.2f"}),format.raw/*123.64*/("""</td></tr>
                """)))}),format.raw/*124.18*/("""
            """),format.raw/*125.13*/("""</tbody>
        </table>
    """)))} else {null} ),format.raw/*127.6*/("""

    """),format.raw/*129.5*/("""<h2>Transaction History with Running Balance (<code>scanLeft</code>)</h2>
    """),_display_(if(running.isEmpty)/*130.25*/ {_display_(Seq[Any](format.raw/*130.27*/("""
        """),format.raw/*131.9*/("""<p>No transactions yet. Add one above!</p>
    """)))}else/*132.12*/{_display_(Seq[Any](format.raw/*132.13*/("""
        """),format.raw/*133.9*/("""<table>
            <thead><tr><th>ID</th><th>Account</th><th>Type</th><th>Amount</th><th>Description</th><th>Running Balance</th></tr></thead>
            <tbody>
                """),_display_(/*136.18*/for((entry, bal) <- running) yield /*136.46*/ {_display_(Seq[Any](format.raw/*136.48*/("""
                    """),format.raw/*137.21*/("""<tr>
                        <td>"""),_display_(/*138.30*/entry/*138.35*/.id),format.raw/*138.38*/("""</td>
                        <td>"""),_display_(/*139.30*/entry/*139.35*/.accountName),format.raw/*139.47*/("""</td>
                        <td>"""),_display_(/*140.30*/entry/*140.35*/.txType),format.raw/*140.42*/("""</td>
                        <td style="color:"""),_display_(/*141.43*/{if(entry.txType == "Credit" || entry.txType == "Refund") "#4CAF50" else "#f44336"}),format.raw/*141.126*/("""">
                            """),_display_(/*142.30*/{if(entry.txType == "Credit" || entry.txType == "Refund") "+" else "-"}),format.raw/*142.101*/("""$"""),_display_(/*142.103*/{f"${entry.amount}%.2f"}),format.raw/*142.127*/("""
                        """),format.raw/*143.25*/("""</td>
                        <td>"""),_display_(/*144.30*/entry/*144.35*/.description),format.raw/*144.47*/("""</td>
                        <td style="font-weight:bold; color:"""),_display_(/*145.61*/{if(bal >= 0) "#4CAF50" else "#f44336"}),format.raw/*145.100*/("""">$"""),_display_(/*145.104*/{f"${bal}%.2f"}),format.raw/*145.119*/("""</td>
                    </tr>
                """)))}),format.raw/*147.18*/("""
            """),format.raw/*148.13*/("""</tbody>
        </table>
    """)))}),format.raw/*150.6*/("""
""")))}),format.raw/*151.2*/("""
"""))
      }
    }
  }

  def render(form:Form[_root_.ledger.LedgerEntryForm],running:Seq[(_root_.ledger.LedgerEntry, Double)],byType:Map[String, Double],balance:Double,credits:Double,debits:Double,txCount:Int,accounts:Seq[String],currentAccount:String,request:RequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(form,running,byType,balance,credits,debits,txCount,accounts,currentAccount)(request)

  def f:((Form[_root_.ledger.LedgerEntryForm],Seq[(_root_.ledger.LedgerEntry, Double)],Map[String, Double],Double,Double,Double,Int,Seq[String],String) => (RequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (form,running,byType,balance,credits,debits,txCount,accounts,currentAccount) => (request) => apply(form,running,byType,balance,credits,debits,txCount,accounts,currentAccount)(request)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/ledgerView.scala.html
                  HASH: ae24b5322abc11d103dc9e2cc57db6f6016f4a38
                  MATRIX: 921->1|1282->269|1309->271|1348->302|1387->304|1418->309|1992->855|2021->856|2050->857|2237->1017|2265->1018|2294->1020|2475->1173|2504->1174|2708->1351|2736->1352|2944->1534|2960->1541|2994->1566|3045->1579|3081->1588|3130->1610|3158->1617|3200->1629|3233->1635|3303->1679|3318->1685|3391->1749|3431->1751|3468->1761|3483->1767|3519->1782|3555->1791|3781->1990|3809->2009|3863->2041|4145->2296|4184->2297|4237->2306|4366->2408|4405->2409|4458->2418|4588->2521|4627->2522|4680->2531|4805->2629|4844->2630|4897->2639|5169->2884|5193->2898|5235->2918|5490->3146|5518->3165|5560->3185|5883->3478|5916->3484|5983->3523|6012->3524|6048->3533|6104->3561|6133->3562|6162->3563|6295->3667|6325->3668|6367->3682|6396->3683|6425->3684|6550->3780|6580->3781|6622->3795|6651->3796|6680->3797|6802->3890|6832->3891|6874->3905|6903->3906|6932->3907|7054->4000|7084->4001|7126->4015|7155->4016|7184->4017|7322->4126|7352->4127|7394->4141|7423->4142|7452->4143|7583->4245|7613->4246|7655->4260|7684->4261|7713->4262|7851->4371|7881->4372|7923->4386|7952->4387|7981->4388|8115->4493|8145->4494|8187->4508|8216->4509|8245->4510|8371->4607|8401->4608|8443->4622|8472->4623|8501->4624|8625->4719|8655->4720|8691->4729|9041->5052|9069->5053|9101->5058|9165->5095|9205->5097|9241->5106|9329->5167|9344->5173|9409->5217|9466->5246|9506->5247|9536->5248|9607->5274|9656->5296|9692->5316|9732->5318|9777->5335|9825->5356|9849->5359|9904->5387|9943->5388|9972->5389|10042->5415|10071->5417|10096->5420|10145->5438|10181->5447|10234->5457|10267->5463|10449->5617|10514->5660|10555->5672|10597->5691|10780->5846|10821->5865|11003->6019|11043->6037|11214->6180|11243->6187|11336->6252|11377->6254|11414->6263|11606->6427|11672->6476|11713->6478|11763->6499|11800->6508|11828->6514|11867->6525|11906->6542|11966->6570|12008->6583|12083->6614|12117->6620|12243->6718|12284->6720|12321->6729|12393->6783|12433->6784|12470->6793|12679->6974|12724->7002|12765->7004|12815->7025|12877->7059|12892->7064|12917->7067|12980->7102|12995->7107|13029->7119|13092->7154|13107->7159|13136->7166|13212->7214|13318->7297|13378->7329|13472->7400|13503->7402|13550->7426|13604->7451|13667->7486|13682->7491|13716->7503|13810->7569|13872->7608|13905->7612|13943->7627|14024->7676|14066->7689|14128->7720|14161->7722
                  LINES: 22->1|27->2|28->3|28->3|28->3|29->4|36->11|36->11|36->11|40->15|40->15|42->17|44->19|44->19|48->23|48->23|55->30|55->30|55->30|55->30|56->31|56->31|56->31|57->32|59->34|60->35|60->35|60->35|60->35|61->36|61->36|61->36|62->37|65->40|65->40|65->40|70->45|70->45|70->45|71->46|71->46|71->46|72->47|72->47|72->47|73->48|73->48|73->48|78->53|78->53|78->53|82->57|82->57|82->57|89->64|91->66|92->67|92->67|93->68|94->69|94->69|94->69|94->69|94->69|95->70|95->70|95->70|95->70|95->70|96->71|96->71|96->71|96->71|96->71|97->72|97->72|97->72|97->72|97->72|98->73|98->73|98->73|98->73|98->73|99->74|99->74|99->74|99->74|99->74|100->75|100->75|100->75|100->75|100->75|101->76|101->76|101->76|101->76|101->76|102->77|102->77|102->77|102->77|102->77|103->78|103->78|103->78|103->78|103->78|104->79|110->85|110->85|111->86|113->88|113->88|114->89|116->91|116->91|116->91|116->91|116->91|116->91|116->91|117->92|117->92|117->92|118->93|118->93|118->93|118->93|118->93|118->93|118->93|118->93|118->93|119->94|120->95|121->96|123->98|126->101|126->101|126->101|126->101|130->105|130->105|134->109|134->109|138->113|138->113|142->117|142->117|143->118|147->122|147->122|147->122|148->123|148->123|148->123|148->123|148->123|149->124|150->125|152->127|154->129|155->130|155->130|156->131|157->132|157->132|158->133|161->136|161->136|161->136|162->137|163->138|163->138|163->138|164->139|164->139|164->139|165->140|165->140|165->140|166->141|166->141|167->142|167->142|167->142|167->142|168->143|169->144|169->144|169->144|170->145|170->145|170->145|170->145|172->147|173->148|175->150|176->151
                  -- GENERATED --
              */
          