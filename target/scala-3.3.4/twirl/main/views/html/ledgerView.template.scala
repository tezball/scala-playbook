
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
            </div>
        </div>
    """)))}),format.raw/*63.6*/("""

    """),_display_(if(accounts.nonEmpty)/*65.27*/ {_display_(Seq[Any](format.raw/*65.29*/("""
        """),format.raw/*66.9*/("""<h2>Filter by Account</h2>
        <p>
            <a href=""""),_display_(/*68.23*/_root_/*68.29*/.ledger.routes.LedgerController.showLedger()),format.raw/*68.73*/("""" """),_display_(if(currentAccount.isEmpty)/*68.102*/{_display_(Seq[Any](format.raw/*68.103*/(""" """),format.raw/*68.104*/("""style="font-weight:bold" """)))} else {null} ),format.raw/*68.130*/(""">All</a>
            """),_display_(/*69.14*/for(acc <- accounts) yield /*69.34*/ {_display_(Seq[Any](format.raw/*69.36*/("""
                """),format.raw/*70.17*/("""| <a href="?account="""),_display_(/*70.38*/acc),format.raw/*70.41*/("""" """),_display_(if(currentAccount == acc)/*70.69*/{_display_(Seq[Any](format.raw/*70.70*/(""" """),format.raw/*70.71*/("""style="font-weight:bold" """)))} else {null} ),format.raw/*70.97*/(""">"""),_display_(/*70.99*/acc),format.raw/*70.102*/("""</a>
            """)))}),format.raw/*71.14*/("""
        """),format.raw/*72.9*/("""</p>
    """)))} else {null} ),format.raw/*73.6*/("""

    """),format.raw/*75.5*/("""<div class="cards">
        <div class="card">
            <h3>Current Balance (<code>foldLeft</code>)</h3>
            <p style="font-size:1.8em; color:"""),_display_(/*78.47*/{if(balance >= 0) "#4CAF50" else "#f44336"}),format.raw/*78.90*/(""""><strong>$"""),_display_(/*78.102*/{f"${balance}%.2f"}),format.raw/*78.121*/("""</strong></p>
        </div>
        <div class="card">
            <h3>Total Credits</h3>
            <p style="font-size:1.3em; color:#4CAF50"><strong>$"""),_display_(/*82.65*/{f"${credits}%.2f"}),format.raw/*82.84*/("""</strong></p>
        </div>
        <div class="card">
            <h3>Total Debits</h3>
            <p style="font-size:1.3em; color:#f44336"><strong>$"""),_display_(/*86.65*/{f"${debits}%.2f"}),format.raw/*86.83*/("""</strong></p>
        </div>
        <div class="card">
            <h3>Transaction Count</h3>
            <p style="font-size:1.3em"><strong>"""),_display_(/*90.49*/txCount),format.raw/*90.56*/("""</strong></p>
        </div>
    </div>

    """),_display_(if(byType.nonEmpty)/*94.25*/ {_display_(Seq[Any](format.raw/*94.27*/("""
        """),format.raw/*95.9*/("""<h2>By Type (<code>groupMapReduce</code>)</h2>
        <table>
            <thead><tr><th>Type</th><th>Total</th></tr></thead>
            <tbody>
                """),_display_(/*99.18*/for((txType, total) <- byType.toSeq.sortBy(_._1)) yield /*99.67*/ {_display_(Seq[Any](format.raw/*99.69*/("""
                    """),format.raw/*100.21*/("""<tr><td>"""),_display_(/*100.30*/txType),format.raw/*100.36*/("""</td><td>$"""),_display_(/*100.47*/{f"${total}%.2f"}),format.raw/*100.64*/("""</td></tr>
                """)))}),format.raw/*101.18*/("""
            """),format.raw/*102.13*/("""</tbody>
        </table>
    """)))} else {null} ),format.raw/*104.6*/("""

    """),format.raw/*106.5*/("""<h2>Transaction History with Running Balance (<code>scanLeft</code>)</h2>
    """),_display_(if(running.isEmpty)/*107.25*/ {_display_(Seq[Any](format.raw/*107.27*/("""
        """),format.raw/*108.9*/("""<p>No transactions yet. Add one above!</p>
    """)))}else/*109.12*/{_display_(Seq[Any](format.raw/*109.13*/("""
        """),format.raw/*110.9*/("""<table>
            <thead><tr><th>ID</th><th>Account</th><th>Type</th><th>Amount</th><th>Description</th><th>Running Balance</th></tr></thead>
            <tbody>
                """),_display_(/*113.18*/for((entry, bal) <- running) yield /*113.46*/ {_display_(Seq[Any](format.raw/*113.48*/("""
                    """),format.raw/*114.21*/("""<tr>
                        <td>"""),_display_(/*115.30*/entry/*115.35*/.id),format.raw/*115.38*/("""</td>
                        <td>"""),_display_(/*116.30*/entry/*116.35*/.accountName),format.raw/*116.47*/("""</td>
                        <td>"""),_display_(/*117.30*/entry/*117.35*/.txType),format.raw/*117.42*/("""</td>
                        <td style="color:"""),_display_(/*118.43*/{if(entry.txType == "Credit" || entry.txType == "Refund") "#4CAF50" else "#f44336"}),format.raw/*118.126*/("""">
                            """),_display_(/*119.30*/{if(entry.txType == "Credit" || entry.txType == "Refund") "+" else "-"}),format.raw/*119.101*/("""$"""),_display_(/*119.103*/{f"${entry.amount}%.2f"}),format.raw/*119.127*/("""
                        """),format.raw/*120.25*/("""</td>
                        <td>"""),_display_(/*121.30*/entry/*121.35*/.description),format.raw/*121.47*/("""</td>
                        <td style="font-weight:bold; color:"""),_display_(/*122.61*/{if(bal >= 0) "#4CAF50" else "#f44336"}),format.raw/*122.100*/("""">$"""),_display_(/*122.104*/{f"${bal}%.2f"}),format.raw/*122.119*/("""</td>
                    </tr>
                """)))}),format.raw/*124.18*/("""
            """),format.raw/*125.13*/("""</tbody>
        </table>
    """)))}),format.raw/*127.6*/("""
""")))}),format.raw/*128.2*/("""
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
                  HASH: 4e59826d310a9e9c56fd26713deb39836f576300
                  MATRIX: 921->1|1282->269|1309->271|1348->302|1387->304|1418->309|1992->855|2021->856|2050->857|2237->1017|2265->1018|2294->1020|2475->1173|2504->1174|2708->1351|2736->1352|2944->1534|2960->1541|2994->1566|3045->1579|3081->1588|3130->1610|3158->1617|3200->1629|3233->1635|3303->1679|3318->1685|3391->1749|3431->1751|3468->1761|3483->1767|3519->1782|3555->1791|3781->1990|3809->2009|3863->2041|4145->2296|4184->2297|4237->2306|4366->2408|4405->2409|4458->2418|4588->2521|4627->2522|4680->2531|4805->2629|4844->2630|4897->2639|5169->2884|5193->2898|5235->2918|5490->3146|5518->3165|5560->3185|5771->3366|5826->3394|5866->3396|5902->3405|5990->3466|6005->3472|6070->3516|6127->3545|6167->3546|6197->3547|6268->3573|6317->3595|6353->3615|6393->3617|6438->3634|6486->3655|6510->3658|6565->3686|6604->3687|6633->3688|6703->3714|6732->3716|6757->3719|6806->3737|6842->3746|6895->3756|6928->3762|7109->3916|7173->3959|7213->3971|7254->3990|7436->4145|7476->4164|7657->4318|7696->4336|7866->4479|7894->4486|7986->4551|8026->4553|8062->4562|8253->4726|8318->4775|8358->4777|8408->4798|8445->4807|8473->4813|8512->4824|8551->4841|8611->4869|8653->4882|8728->4913|8762->4919|8888->5017|8929->5019|8966->5028|9038->5082|9078->5083|9115->5092|9324->5273|9369->5301|9410->5303|9460->5324|9522->5358|9537->5363|9562->5366|9625->5401|9640->5406|9674->5418|9737->5453|9752->5458|9781->5465|9857->5513|9963->5596|10023->5628|10117->5699|10148->5701|10195->5725|10249->5750|10312->5785|10327->5790|10361->5802|10455->5868|10517->5907|10550->5911|10588->5926|10669->5975|10711->5988|10773->6019|10806->6021
                  LINES: 22->1|27->2|28->3|28->3|28->3|29->4|36->11|36->11|36->11|40->15|40->15|42->17|44->19|44->19|48->23|48->23|55->30|55->30|55->30|55->30|56->31|56->31|56->31|57->32|59->34|60->35|60->35|60->35|60->35|61->36|61->36|61->36|62->37|65->40|65->40|65->40|70->45|70->45|70->45|71->46|71->46|71->46|72->47|72->47|72->47|73->48|73->48|73->48|78->53|78->53|78->53|82->57|82->57|82->57|88->63|90->65|90->65|91->66|93->68|93->68|93->68|93->68|93->68|93->68|93->68|94->69|94->69|94->69|95->70|95->70|95->70|95->70|95->70|95->70|95->70|95->70|95->70|96->71|97->72|98->73|100->75|103->78|103->78|103->78|103->78|107->82|107->82|111->86|111->86|115->90|115->90|119->94|119->94|120->95|124->99|124->99|124->99|125->100|125->100|125->100|125->100|125->100|126->101|127->102|129->104|131->106|132->107|132->107|133->108|134->109|134->109|135->110|138->113|138->113|138->113|139->114|140->115|140->115|140->115|141->116|141->116|141->116|142->117|142->117|142->117|143->118|143->118|144->119|144->119|144->119|144->119|145->120|146->121|146->121|146->121|147->122|147->122|147->122|147->122|149->124|150->125|152->127|153->128
                  -- GENERATED --
              */
          