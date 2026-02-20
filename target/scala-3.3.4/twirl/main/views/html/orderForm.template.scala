
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

object orderForm extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Form[_root_.orders.OrderForm],Seq[_root_.orders.Order],Seq[_root_.orders.OrderPlacedEvent],RequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(form: Form[_root_.orders.OrderForm], orderList: Seq[_root_.orders.Order], kafkaEvents: Seq[_root_.orders.OrderPlacedEvent])(implicit request: RequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),_display_(/*3.2*/main("Place Order")/*3.21*/ {_display_(Seq[Any](format.raw/*3.23*/("""
    """),format.raw/*4.5*/("""<h1>Place Order</h1>

    <div class="concept-banner">
        <h3>Concepts: Play Forms, Slick ORM, Kafka Producer/Consumer</h3>
        <p>This page demonstrates order placement with database persistence and Kafka event publishing for checkout emails.</p>
    </div>

    """),_display_(/*11.6*/request/*11.13*/.flash.get("success").map/*11.38*/ { message =>_display_(Seq[Any](format.raw/*11.51*/("""
        """),format.raw/*12.9*/("""<div class="success">"""),_display_(/*12.31*/message),format.raw/*12.38*/("""</div>
    """)))}),format.raw/*13.6*/("""

    """),_display_(/*15.6*/helper/*15.12*/.form(action = orders.routes.OrderController.placeOrder())/*15.70*/ {_display_(Seq[Any](format.raw/*15.72*/("""
        """),_display_(/*16.10*/helper/*16.16*/.CSRF.formField),format.raw/*16.31*/("""

        """),format.raw/*18.9*/("""<div class="form-group">
            <label for="userName">Your Name</label>
            <input type="text" id="userName" name="userName" value=""""),_display_(/*20.70*/form("userName")/*20.86*/.value.getOrElse("")),format.raw/*20.106*/("""" required>
            """),_display_(/*21.14*/form/*21.18*/.error("userName").map/*21.40*/ { error =>_display_(Seq[Any](format.raw/*21.51*/("""
                """),format.raw/*22.17*/("""<div class="error-msg">"""),_display_(/*22.41*/error/*22.46*/.message),format.raw/*22.54*/("""</div>
            """)))}),format.raw/*23.14*/("""
        """),format.raw/*24.9*/("""</div>

        <div class="form-group">
            <label for="itemName">Item</label>
            <input type="text" id="itemName" name="itemName" value=""""),_display_(/*28.70*/form("itemName")/*28.86*/.value.getOrElse("")),format.raw/*28.106*/("""" required>
            """),_display_(/*29.14*/form/*29.18*/.error("itemName").map/*29.40*/ { error =>_display_(Seq[Any](format.raw/*29.51*/("""
                """),format.raw/*30.17*/("""<div class="error-msg">"""),_display_(/*30.41*/error/*30.46*/.message),format.raw/*30.54*/("""</div>
            """)))}),format.raw/*31.14*/("""
        """),format.raw/*32.9*/("""</div>

        <div class="form-group">
            <label for="quantity">Quantity</label>
            <input type="number" id="quantity" name="quantity" value=""""),_display_(/*36.72*/form("quantity")/*36.88*/.value.getOrElse("1")),format.raw/*36.109*/("""" min="1" required>
            """),_display_(/*37.14*/form/*37.18*/.error("quantity").map/*37.40*/ { error =>_display_(Seq[Any](format.raw/*37.51*/("""
                """),format.raw/*38.17*/("""<div class="error-msg">"""),_display_(/*38.41*/error/*38.46*/.message),format.raw/*38.54*/("""</div>
            """)))}),format.raw/*39.14*/("""
        """),format.raw/*40.9*/("""</div>

        <button type="submit" class="btn-blue">Place Order</button>
    """)))}),format.raw/*43.6*/("""

    """),format.raw/*45.5*/("""<h2>Orders</h2>
    """),_display_(if(orderList.isEmpty)/*46.27*/ {_display_(Seq[Any](format.raw/*46.29*/("""
        """),format.raw/*47.9*/("""<p>No orders placed yet.</p>
    """)))}else/*48.12*/{_display_(Seq[Any](format.raw/*48.13*/("""
        """),format.raw/*49.9*/("""<table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Item</th>
                    <th>Qty</th>
                </tr>
            </thead>
            <tbody>
                """),_display_(/*59.18*/for(order <- orderList) yield /*59.41*/ {_display_(Seq[Any](format.raw/*59.43*/("""
                    """),format.raw/*60.21*/("""<tr>
                        <td>"""),_display_(/*61.30*/order/*61.35*/.id),format.raw/*61.38*/("""</td>
                        <td>"""),_display_(/*62.30*/order/*62.35*/.userName),format.raw/*62.44*/("""</td>
                        <td>"""),_display_(/*63.30*/order/*63.35*/.itemName),format.raw/*63.44*/("""</td>
                        <td>"""),_display_(/*64.30*/order/*64.35*/.quantity),format.raw/*64.44*/("""</td>
                    </tr>
                """)))}),format.raw/*66.18*/("""
            """),format.raw/*67.13*/("""</tbody>
        </table>
    """)))}),format.raw/*69.6*/("""

    """),format.raw/*71.5*/("""<h2>Checkout Emails (Kafka Events)</h2>
    """),_display_(if(kafkaEvents.isEmpty)/*72.29*/ {_display_(Seq[Any](format.raw/*72.31*/("""
        """),format.raw/*73.9*/("""<p>No checkout emails queued yet.</p>
    """)))}else/*74.12*/{_display_(Seq[Any](format.raw/*74.13*/("""
        """),format.raw/*75.9*/("""<table>
            <thead>
                <tr>
                    <th>Event Type</th>
                    <th>Timestamp</th>
                    <th>Order ID</th>
                    <th>Name</th>
                    <th>Item</th>
                    <th>Qty</th>
                </tr>
            </thead>
            <tbody>
                """),_display_(/*87.18*/for(event <- kafkaEvents) yield /*87.43*/ {_display_(Seq[Any](format.raw/*87.45*/("""
                    """),format.raw/*88.21*/("""<tr>
                        <td>"""),_display_(/*89.30*/event/*89.35*/.eventType),format.raw/*89.45*/("""</td>
                        <td>"""),_display_(/*90.30*/event/*90.35*/.timestamp),format.raw/*90.45*/("""</td>
                        <td>"""),_display_(/*91.30*/event/*91.35*/.order.id),format.raw/*91.44*/("""</td>
                        <td>"""),_display_(/*92.30*/event/*92.35*/.order.userName),format.raw/*92.50*/("""</td>
                        <td>"""),_display_(/*93.30*/event/*93.35*/.order.itemName),format.raw/*93.50*/("""</td>
                        <td>"""),_display_(/*94.30*/event/*94.35*/.order.quantity),format.raw/*94.50*/("""</td>
                    </tr>
                """)))}),format.raw/*96.18*/("""
            """),format.raw/*97.13*/("""</tbody>
        </table>
    """)))}),format.raw/*99.6*/("""
""")))}),format.raw/*100.2*/("""
"""))
      }
    }
  }

  def render(form:Form[_root_.orders.OrderForm],orderList:Seq[_root_.orders.Order],kafkaEvents:Seq[_root_.orders.OrderPlacedEvent],request:RequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(form,orderList,kafkaEvents)(request)

  def f:((Form[_root_.orders.OrderForm],Seq[_root_.orders.Order],Seq[_root_.orders.OrderPlacedEvent]) => (RequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (form,orderList,kafkaEvents) => (request) => apply(form,orderList,kafkaEvents)(request)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/orderForm.scala.html
                  HASH: 03637bae0f6820b01b077100fd609300d53f0a43
                  MATRIX: 869->1|1120->159|1147->161|1174->180|1213->182|1244->187|1544->461|1560->468|1594->493|1645->506|1681->515|1730->537|1758->544|1800->556|1833->563|1848->569|1915->627|1955->629|1992->639|2007->645|2043->660|2080->670|2253->816|2278->832|2320->852|2372->877|2385->881|2416->903|2465->914|2510->931|2561->955|2575->960|2604->968|2655->988|2691->997|2875->1154|2900->1170|2942->1190|2994->1215|3007->1219|3038->1241|3087->1252|3132->1269|3183->1293|3197->1298|3226->1306|3277->1326|3313->1335|3503->1498|3528->1514|3571->1535|3631->1568|3644->1572|3675->1594|3724->1605|3769->1622|3820->1646|3834->1651|3863->1659|3914->1679|3950->1688|4061->1769|4094->1775|4163->1817|4203->1819|4239->1828|4296->1868|4335->1869|4371->1878|4660->2140|4699->2163|4739->2165|4788->2186|4849->2220|4863->2225|4887->2228|4949->2263|4963->2268|4993->2277|5055->2312|5069->2317|5099->2326|5161->2361|5175->2366|5205->2375|5285->2424|5326->2437|5387->2468|5420->2474|5515->2542|5555->2544|5591->2553|5657->2602|5696->2603|5732->2612|6106->2959|6147->2984|6187->2986|6236->3007|6297->3041|6311->3046|6342->3056|6404->3091|6418->3096|6449->3106|6511->3141|6525->3146|6555->3155|6617->3190|6631->3195|6667->3210|6729->3245|6743->3250|6779->3265|6841->3300|6855->3305|6891->3320|6971->3369|7012->3382|7073->3413|7106->3415
                  LINES: 22->1|27->2|28->3|28->3|28->3|29->4|36->11|36->11|36->11|36->11|37->12|37->12|37->12|38->13|40->15|40->15|40->15|40->15|41->16|41->16|41->16|43->18|45->20|45->20|45->20|46->21|46->21|46->21|46->21|47->22|47->22|47->22|47->22|48->23|49->24|53->28|53->28|53->28|54->29|54->29|54->29|54->29|55->30|55->30|55->30|55->30|56->31|57->32|61->36|61->36|61->36|62->37|62->37|62->37|62->37|63->38|63->38|63->38|63->38|64->39|65->40|68->43|70->45|71->46|71->46|72->47|73->48|73->48|74->49|84->59|84->59|84->59|85->60|86->61|86->61|86->61|87->62|87->62|87->62|88->63|88->63|88->63|89->64|89->64|89->64|91->66|92->67|94->69|96->71|97->72|97->72|98->73|99->74|99->74|100->75|112->87|112->87|112->87|113->88|114->89|114->89|114->89|115->90|115->90|115->90|116->91|116->91|116->91|117->92|117->92|117->92|118->93|118->93|118->93|119->94|119->94|119->94|121->96|122->97|124->99|125->100
                  -- GENERATED --
              */
          