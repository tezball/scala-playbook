
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

object analyticsDashboard extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[_root_.analytics.DashboardData,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(data: _root_.analytics.DashboardData):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),_display_(/*3.2*/main("Analytics - Concurrency")/*3.33*/ {_display_(Seq[Any](format.raw/*3.35*/("""
    """),format.raw/*4.5*/("""<h1>Analytics Dashboard</h1>

    <div class="concept-banner">
        <h3>Concepts: Concurrency &mdash; Future, parallel composition, for-comprehension, recover</h3>
        <p>All dashboard queries run in parallel using <code>Future</code>. Starting each future before the for-comprehension means they execute concurrently, not sequentially.</p>
        <pre><code>// Start all futures before combining (runs in parallel)
val userCountF: Future[Int] = userRepository.count()
val orderCountF: Future[Int] = orderRepository.count()
val topItemsF: Future[Seq[(String, Int)]] = orderRepository.topItems(5)
val recentOrdersF: Future[Seq[Order]] = orderRepository.recent(10)

// Compose with for-comprehension
val dashboard = for
  userCount    &lt;- userCountF
  orderCount   &lt;- orderCountF
  topItems     &lt;- topItemsF
  recentOrders &lt;- recentOrdersF
yield DashboardData(userCount, orderCount, topItems, recentOrders)

// recover - graceful fallback
dashboard.recover """),format.raw/*24.19*/("""{"""),format.raw/*24.20*/(""" case _: Exception =&gt; DashboardData.empty """),format.raw/*24.65*/("""}"""),format.raw/*24.66*/("""</code></pre>
    </div>

    <div class="cards">
        <div class="card">
            <h3>Total Users</h3>
            <p style="font-size:2em"><strong>"""),_display_(/*30.47*/data/*30.51*/.userCount),format.raw/*30.61*/("""</strong></p>
            <p><code>userRepository.count()</code></p>
        </div>
        <div class="card">
            <h3>Total Orders</h3>
            <p style="font-size:2em"><strong>"""),_display_(/*35.47*/data/*35.51*/.orderCount),format.raw/*35.62*/("""</strong></p>
            <p><code>orderRepository.count()</code></p>
        </div>
    </div>

    <h2>Top 5 Most Ordered Items (<code>topItems</code>)</h2>
    """),_display_(if(data.topItems.isEmpty)/*41.31*/ {_display_(Seq[Any](format.raw/*41.33*/("""
        """),format.raw/*42.9*/("""<p>No orders yet. <a href=""""),_display_(/*42.37*/_root_/*42.43*/.orders.routes.OrderController.showForm()),format.raw/*42.84*/("""">Place some orders</a> first!</p>
    """)))}else/*43.12*/{_display_(Seq[Any](format.raw/*43.13*/("""
        """),format.raw/*44.9*/("""<table>
            <thead><tr><th>Item</th><th>Total Quantity</th></tr></thead>
            <tbody>
                """),_display_(/*47.18*/for((item, qty) <- data.topItems) yield /*47.51*/ {_display_(Seq[Any](format.raw/*47.53*/("""
                    """),format.raw/*48.21*/("""<tr><td>"""),_display_(/*48.30*/item),format.raw/*48.34*/("""</td><td>"""),_display_(/*48.44*/qty),format.raw/*48.47*/("""</td></tr>
                """)))}),format.raw/*49.18*/("""
            """),format.raw/*50.13*/("""</tbody>
        </table>
    """)))}),format.raw/*52.6*/("""

    """),format.raw/*54.5*/("""<h2>Recent 10 Orders (<code>recent</code>)</h2>
    """),_display_(if(data.recentOrders.isEmpty)/*55.35*/ {_display_(Seq[Any](format.raw/*55.37*/("""
        """),format.raw/*56.9*/("""<p>No orders yet. <a href=""""),_display_(/*56.37*/_root_/*56.43*/.orders.routes.OrderController.showForm()),format.raw/*56.84*/("""">Place some orders</a> first!</p>
    """)))}else/*57.12*/{_display_(Seq[Any](format.raw/*57.13*/("""
        """),format.raw/*58.9*/("""<table>
            <thead><tr><th>ID</th><th>User</th><th>Item</th><th>Qty</th></tr></thead>
            <tbody>
                """),_display_(/*61.18*/for(order <- data.recentOrders) yield /*61.49*/ {_display_(Seq[Any](format.raw/*61.51*/("""
                    """),format.raw/*62.21*/("""<tr>
                        <td>"""),_display_(/*63.30*/order/*63.35*/.id),format.raw/*63.38*/("""</td>
                        <td>"""),_display_(/*64.30*/order/*64.35*/.userName),format.raw/*64.44*/("""</td>
                        <td>"""),_display_(/*65.30*/order/*65.35*/.itemName),format.raw/*65.44*/("""</td>
                        <td>"""),_display_(/*66.30*/order/*66.35*/.quantity),format.raw/*66.44*/("""</td>
                    </tr>
                """)))}),format.raw/*68.18*/("""
            """),format.raw/*69.13*/("""</tbody>
        </table>
    """)))}),format.raw/*71.6*/("""
""")))}),format.raw/*72.2*/("""
"""))
      }
    }
  }

  def render(data:_root_.analytics.DashboardData): play.twirl.api.HtmlFormat.Appendable = apply(data)

  def f:((_root_.analytics.DashboardData) => play.twirl.api.HtmlFormat.Appendable) = (data) => apply(data)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/analyticsDashboard.scala.html
                  HASH: b9c170a03c38e3399ac0dbb86ebfb71d95db0a91
                  MATRIX: 804->1|936->40|963->42|1002->73|1041->75|1072->80|2074->1054|2103->1055|2176->1100|2205->1101|2388->1257|2401->1261|2432->1271|2650->1462|2663->1466|2695->1477|2911->1666|2951->1668|2987->1677|3042->1705|3057->1711|3119->1752|3182->1798|3221->1799|3257->1808|3402->1926|3451->1959|3491->1961|3540->1982|3576->1991|3601->1995|3638->2005|3662->2008|3721->2036|3762->2049|3823->2080|3856->2086|3965->2168|4005->2170|4041->2179|4096->2207|4111->2213|4173->2254|4236->2300|4275->2301|4311->2310|4469->2441|4516->2472|4556->2474|4605->2495|4666->2529|4680->2534|4704->2537|4766->2572|4780->2577|4810->2586|4872->2621|4886->2626|4916->2635|4978->2670|4992->2675|5022->2684|5102->2733|5143->2746|5204->2777|5236->2779
                  LINES: 22->1|27->2|28->3|28->3|28->3|29->4|49->24|49->24|49->24|49->24|55->30|55->30|55->30|60->35|60->35|60->35|66->41|66->41|67->42|67->42|67->42|67->42|68->43|68->43|69->44|72->47|72->47|72->47|73->48|73->48|73->48|73->48|73->48|74->49|75->50|77->52|79->54|80->55|80->55|81->56|81->56|81->56|81->56|82->57|82->57|83->58|86->61|86->61|86->61|87->62|88->63|88->63|88->63|89->64|89->64|89->64|90->65|90->65|90->65|91->66|91->66|91->66|93->68|94->69|96->71|97->72
                  -- GENERATED --
              */
          