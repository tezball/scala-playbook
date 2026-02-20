
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
        <h3>Advanced Concepts: Kafka Producer/Consumer, Event Serialization, JSON Formats, Async Messaging</h3>
        <p>When an order is placed, an <code>OrderPlacedEvent</code> is published to Kafka. A background consumer thread reads events and displays them below. This demonstrates event-driven architecture with typed JSON serialization using Play's <code>Format</code> type class.</p>
        <pre><code>// Event case class with JSON Format (type class instance via given)
case class OrderPlacedEvent(eventType: String, timestamp: String, order: OrderPayload)
object OrderPlacedEvent:
  given Format[OrderPlacedEvent] = Json.format[OrderPlacedEvent]

// Publish to Kafka after DB write
kafkaProducer.publish(topic, order.id.toString, Json.toJson(event).toString())

// Consumer polls in a background thread
val records = consumer.poll(Duration.ofMillis(500))
for record &lt;- records.asScala do
  Json.parse(record.value()).validate[OrderPlacedEvent]</code></pre>
    </div>

    """),_display_(/*23.6*/request/*23.13*/.flash.get("success").map/*23.38*/ { message =>_display_(Seq[Any](format.raw/*23.51*/("""
        """),format.raw/*24.9*/("""<div class="success">"""),_display_(/*24.31*/message),format.raw/*24.38*/("""</div>
    """)))}),format.raw/*25.6*/("""

    """),_display_(/*27.6*/helper/*27.12*/.form(action = orders.routes.OrderController.placeOrder())/*27.70*/ {_display_(Seq[Any](format.raw/*27.72*/("""
        """),_display_(/*28.10*/helper/*28.16*/.CSRF.formField),format.raw/*28.31*/("""

        """),format.raw/*30.9*/("""<div class="form-group">
            <label for="userName">Your Name</label>
            <input type="text" id="userName" name="userName" value=""""),_display_(/*32.70*/form("userName")/*32.86*/.value.getOrElse("")),format.raw/*32.106*/("""" required>
            """),_display_(/*33.14*/form/*33.18*/.error("userName").map/*33.40*/ { error =>_display_(Seq[Any](format.raw/*33.51*/("""
                """),format.raw/*34.17*/("""<div class="error-msg">"""),_display_(/*34.41*/error/*34.46*/.message),format.raw/*34.54*/("""</div>
            """)))}),format.raw/*35.14*/("""
        """),format.raw/*36.9*/("""</div>

        <div class="form-group">
            <label for="itemName">Item</label>
            <input type="text" id="itemName" name="itemName" value=""""),_display_(/*40.70*/form("itemName")/*40.86*/.value.getOrElse("")),format.raw/*40.106*/("""" required>
            """),_display_(/*41.14*/form/*41.18*/.error("itemName").map/*41.40*/ { error =>_display_(Seq[Any](format.raw/*41.51*/("""
                """),format.raw/*42.17*/("""<div class="error-msg">"""),_display_(/*42.41*/error/*42.46*/.message),format.raw/*42.54*/("""</div>
            """)))}),format.raw/*43.14*/("""
        """),format.raw/*44.9*/("""</div>

        <div class="form-group">
            <label for="quantity">Quantity</label>
            <input type="number" id="quantity" name="quantity" value=""""),_display_(/*48.72*/form("quantity")/*48.88*/.value.getOrElse("1")),format.raw/*48.109*/("""" min="1" required>
            """),_display_(/*49.14*/form/*49.18*/.error("quantity").map/*49.40*/ { error =>_display_(Seq[Any](format.raw/*49.51*/("""
                """),format.raw/*50.17*/("""<div class="error-msg">"""),_display_(/*50.41*/error/*50.46*/.message),format.raw/*50.54*/("""</div>
            """)))}),format.raw/*51.14*/("""
        """),format.raw/*52.9*/("""</div>

        <button type="submit" class="btn-blue">Place Order</button>
    """)))}),format.raw/*55.6*/("""

    """),format.raw/*57.5*/("""<h2>Orders</h2>
    """),_display_(if(orderList.isEmpty)/*58.27*/ {_display_(Seq[Any](format.raw/*58.29*/("""
        """),format.raw/*59.9*/("""<p>No orders placed yet.</p>
    """)))}else/*60.12*/{_display_(Seq[Any](format.raw/*60.13*/("""
        """),format.raw/*61.9*/("""<table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Item</th>
                    <th>Qty</th>
                </tr>
            </thead>
            <tbody>
                """),_display_(/*71.18*/for(order <- orderList) yield /*71.41*/ {_display_(Seq[Any](format.raw/*71.43*/("""
                    """),format.raw/*72.21*/("""<tr>
                        <td>"""),_display_(/*73.30*/order/*73.35*/.id),format.raw/*73.38*/("""</td>
                        <td>"""),_display_(/*74.30*/order/*74.35*/.userName),format.raw/*74.44*/("""</td>
                        <td>"""),_display_(/*75.30*/order/*75.35*/.itemName),format.raw/*75.44*/("""</td>
                        <td>"""),_display_(/*76.30*/order/*76.35*/.quantity),format.raw/*76.44*/("""</td>
                    </tr>
                """)))}),format.raw/*78.18*/("""
            """),format.raw/*79.13*/("""</tbody>
        </table>
    """)))}),format.raw/*81.6*/("""

    """),format.raw/*83.5*/("""<h2>Checkout Emails (Kafka Events)</h2>
    """),_display_(if(kafkaEvents.isEmpty)/*84.29*/ {_display_(Seq[Any](format.raw/*84.31*/("""
        """),format.raw/*85.9*/("""<p>No checkout emails queued yet.</p>
    """)))}else/*86.12*/{_display_(Seq[Any](format.raw/*86.13*/("""
        """),format.raw/*87.9*/("""<table>
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
                """),_display_(/*99.18*/for(event <- kafkaEvents) yield /*99.43*/ {_display_(Seq[Any](format.raw/*99.45*/("""
                    """),format.raw/*100.21*/("""<tr>
                        <td>"""),_display_(/*101.30*/event/*101.35*/.eventType),format.raw/*101.45*/("""</td>
                        <td>"""),_display_(/*102.30*/event/*102.35*/.timestamp),format.raw/*102.45*/("""</td>
                        <td>"""),_display_(/*103.30*/event/*103.35*/.order.id),format.raw/*103.44*/("""</td>
                        <td>"""),_display_(/*104.30*/event/*104.35*/.order.userName),format.raw/*104.50*/("""</td>
                        <td>"""),_display_(/*105.30*/event/*105.35*/.order.itemName),format.raw/*105.50*/("""</td>
                        <td>"""),_display_(/*106.30*/event/*106.35*/.order.quantity),format.raw/*106.50*/("""</td>
                    </tr>
                """)))}),format.raw/*108.18*/("""
            """),format.raw/*109.13*/("""</tbody>
        </table>
    """)))}),format.raw/*111.6*/("""
""")))}),format.raw/*112.2*/("""
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
                  HASH: 7bdcb2df7383f3f97159dc8c65fed3c00b94e57c
                  MATRIX: 869->1|1120->159|1147->161|1174->180|1213->182|1244->187|2315->1232|2331->1239|2365->1264|2416->1277|2452->1286|2501->1308|2529->1315|2571->1327|2604->1334|2619->1340|2686->1398|2726->1400|2763->1410|2778->1416|2814->1431|2851->1441|3024->1587|3049->1603|3091->1623|3143->1648|3156->1652|3187->1674|3236->1685|3281->1702|3332->1726|3346->1731|3375->1739|3426->1759|3462->1768|3646->1925|3671->1941|3713->1961|3765->1986|3778->1990|3809->2012|3858->2023|3903->2040|3954->2064|3968->2069|3997->2077|4048->2097|4084->2106|4274->2269|4299->2285|4342->2306|4402->2339|4415->2343|4446->2365|4495->2376|4540->2393|4591->2417|4605->2422|4634->2430|4685->2450|4721->2459|4832->2540|4865->2546|4934->2588|4974->2590|5010->2599|5067->2639|5106->2640|5142->2649|5431->2911|5470->2934|5510->2936|5559->2957|5620->2991|5634->2996|5658->2999|5720->3034|5734->3039|5764->3048|5826->3083|5840->3088|5870->3097|5932->3132|5946->3137|5976->3146|6056->3195|6097->3208|6158->3239|6191->3245|6286->3313|6326->3315|6362->3324|6428->3373|6467->3374|6503->3383|6877->3730|6918->3755|6958->3757|7008->3778|7070->3812|7085->3817|7117->3827|7180->3862|7195->3867|7227->3877|7290->3912|7305->3917|7336->3926|7399->3961|7414->3966|7451->3981|7514->4016|7529->4021|7566->4036|7629->4071|7644->4076|7681->4091|7762->4140|7804->4153|7866->4184|7899->4186
                  LINES: 22->1|27->2|28->3|28->3|28->3|29->4|48->23|48->23|48->23|48->23|49->24|49->24|49->24|50->25|52->27|52->27|52->27|52->27|53->28|53->28|53->28|55->30|57->32|57->32|57->32|58->33|58->33|58->33|58->33|59->34|59->34|59->34|59->34|60->35|61->36|65->40|65->40|65->40|66->41|66->41|66->41|66->41|67->42|67->42|67->42|67->42|68->43|69->44|73->48|73->48|73->48|74->49|74->49|74->49|74->49|75->50|75->50|75->50|75->50|76->51|77->52|80->55|82->57|83->58|83->58|84->59|85->60|85->60|86->61|96->71|96->71|96->71|97->72|98->73|98->73|98->73|99->74|99->74|99->74|100->75|100->75|100->75|101->76|101->76|101->76|103->78|104->79|106->81|108->83|109->84|109->84|110->85|111->86|111->86|112->87|124->99|124->99|124->99|125->100|126->101|126->101|126->101|127->102|127->102|127->102|128->103|128->103|128->103|129->104|129->104|129->104|130->105|130->105|130->105|131->106|131->106|131->106|133->108|134->109|136->111|137->112
                  -- GENERATED --
              */
          