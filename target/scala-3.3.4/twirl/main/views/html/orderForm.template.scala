
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

        <div class="btn-group">
            <button type="submit" class="btn-blue">Place Order</button>
            <button type="button" class="btn-autofill" onclick="autoFillOrder()">Auto Fill</button>
        </div>
    """)))}),format.raw/*58.6*/("""

    """),format.raw/*60.5*/("""<script>
    function autoFillOrder() """),format.raw/*61.30*/("""{"""),format.raw/*61.31*/("""
        """),format.raw/*62.9*/("""var orders = [
            """),format.raw/*63.13*/("""{"""),format.raw/*63.14*/(""" """),format.raw/*63.15*/("""user: 'Alice Johnson', item: 'MacBook Pro 16"', qty: 1 """),format.raw/*63.70*/("""}"""),format.raw/*63.71*/(""",
            """),format.raw/*64.13*/("""{"""),format.raw/*64.14*/(""" """),format.raw/*64.15*/("""user: 'Bob Martinez', item: 'Wireless Mouse', qty: 3 """),format.raw/*64.68*/("""}"""),format.raw/*64.69*/(""",
            """),format.raw/*65.13*/("""{"""),format.raw/*65.14*/(""" """),format.raw/*65.15*/("""user: 'Carol Chen', item: 'Standing Desk', qty: 1 """),format.raw/*65.65*/("""}"""),format.raw/*65.66*/(""",
            """),format.raw/*66.13*/("""{"""),format.raw/*66.14*/(""" """),format.raw/*66.15*/("""user: 'David Kim', item: 'Mechanical Keyboard', qty: 2 """),format.raw/*66.70*/("""}"""),format.raw/*66.71*/(""",
            """),format.raw/*67.13*/("""{"""),format.raw/*67.14*/(""" """),format.raw/*67.15*/("""user: 'Eva Rossi', item: 'Noise-Cancelling Headphones', qty: 1 """),format.raw/*67.78*/("""}"""),format.raw/*67.79*/(""",
            """),format.raw/*68.13*/("""{"""),format.raw/*68.14*/(""" """),format.raw/*68.15*/("""user: 'Frank Okafor', item: 'USB-C Hub', qty: 4 """),format.raw/*68.63*/("""}"""),format.raw/*68.64*/(""",
            """),format.raw/*69.13*/("""{"""),format.raw/*69.14*/(""" """),format.raw/*69.15*/("""user: 'Grace Liu', item: '27" 4K Monitor', qty: 2 """),format.raw/*69.65*/("""}"""),format.raw/*69.66*/(""",
            """),format.raw/*70.13*/("""{"""),format.raw/*70.14*/(""" """),format.raw/*70.15*/("""user: 'Henry Park', item: 'Ergonomic Chair', qty: 1 """),format.raw/*70.67*/("""}"""),format.raw/*70.68*/("""
        """),format.raw/*71.9*/("""];
        var o = orders[Math.floor(Math.random() * orders.length)];
        document.getElementById('userName').value = o.user;
        document.getElementById('itemName').value = o.item;
        document.getElementById('quantity').value = o.qty;
    """),format.raw/*76.5*/("""}"""),format.raw/*76.6*/("""
    """),format.raw/*77.5*/("""</script>

    <h2>Orders</h2>
    """),_display_(if(orderList.isEmpty)/*80.27*/ {_display_(Seq[Any](format.raw/*80.29*/("""
        """),format.raw/*81.9*/("""<p>No orders placed yet.</p>
    """)))}else/*82.12*/{_display_(Seq[Any](format.raw/*82.13*/("""
        """),format.raw/*83.9*/("""<table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Item</th>
                    <th>Qty</th>
                </tr>
            </thead>
            <tbody>
                """),_display_(/*93.18*/for(order <- orderList) yield /*93.41*/ {_display_(Seq[Any](format.raw/*93.43*/("""
                    """),format.raw/*94.21*/("""<tr>
                        <td>"""),_display_(/*95.30*/order/*95.35*/.id),format.raw/*95.38*/("""</td>
                        <td>"""),_display_(/*96.30*/order/*96.35*/.userName),format.raw/*96.44*/("""</td>
                        <td>"""),_display_(/*97.30*/order/*97.35*/.itemName),format.raw/*97.44*/("""</td>
                        <td>"""),_display_(/*98.30*/order/*98.35*/.quantity),format.raw/*98.44*/("""</td>
                    </tr>
                """)))}),format.raw/*100.18*/("""
            """),format.raw/*101.13*/("""</tbody>
        </table>
    """)))}),format.raw/*103.6*/("""

    """),format.raw/*105.5*/("""<h2>Checkout Emails (Kafka Events)</h2>
    """),_display_(if(kafkaEvents.isEmpty)/*106.29*/ {_display_(Seq[Any](format.raw/*106.31*/("""
        """),format.raw/*107.9*/("""<p>No checkout emails queued yet.</p>
    """)))}else/*108.12*/{_display_(Seq[Any](format.raw/*108.13*/("""
        """),format.raw/*109.9*/("""<table>
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
                """),_display_(/*121.18*/for(event <- kafkaEvents) yield /*121.43*/ {_display_(Seq[Any](format.raw/*121.45*/("""
                    """),format.raw/*122.21*/("""<tr>
                        <td>"""),_display_(/*123.30*/event/*123.35*/.eventType),format.raw/*123.45*/("""</td>
                        <td>"""),_display_(/*124.30*/event/*124.35*/.timestamp),format.raw/*124.45*/("""</td>
                        <td>"""),_display_(/*125.30*/event/*125.35*/.order.id),format.raw/*125.44*/("""</td>
                        <td>"""),_display_(/*126.30*/event/*126.35*/.order.userName),format.raw/*126.50*/("""</td>
                        <td>"""),_display_(/*127.30*/event/*127.35*/.order.itemName),format.raw/*127.50*/("""</td>
                        <td>"""),_display_(/*128.30*/event/*128.35*/.order.quantity),format.raw/*128.50*/("""</td>
                    </tr>
                """)))}),format.raw/*130.18*/("""
            """),format.raw/*131.13*/("""</tbody>
        </table>
    """)))}),format.raw/*133.6*/("""
""")))}),format.raw/*134.2*/("""
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
                  HASH: f3c85aa978baf1b2711ac4ebd35fb3a21833846f
                  MATRIX: 869->1|1120->159|1147->161|1174->180|1213->182|1244->187|2315->1232|2331->1239|2365->1264|2416->1277|2452->1286|2501->1308|2529->1315|2571->1327|2604->1334|2619->1340|2686->1398|2726->1400|2763->1410|2778->1416|2814->1431|2851->1441|3024->1587|3049->1603|3091->1623|3143->1648|3156->1652|3187->1674|3236->1685|3281->1702|3332->1726|3346->1731|3375->1739|3426->1759|3462->1768|3646->1925|3671->1941|3713->1961|3765->1986|3778->1990|3809->2012|3858->2023|3903->2040|3954->2064|3968->2069|3997->2077|4048->2097|4084->2106|4274->2269|4299->2285|4342->2306|4402->2339|4415->2343|4446->2365|4495->2376|4540->2393|4591->2417|4605->2422|4634->2430|4685->2450|4721->2459|4983->2691|5016->2697|5082->2735|5111->2736|5147->2745|5202->2772|5231->2773|5260->2774|5343->2829|5372->2830|5414->2844|5443->2845|5472->2846|5553->2899|5582->2900|5624->2914|5653->2915|5682->2916|5760->2966|5789->2967|5831->2981|5860->2982|5889->2983|5972->3038|6001->3039|6043->3053|6072->3054|6101->3055|6192->3118|6221->3119|6263->3133|6292->3134|6321->3135|6397->3183|6426->3184|6468->3198|6497->3199|6526->3200|6604->3250|6633->3251|6675->3265|6704->3266|6733->3267|6813->3319|6842->3320|6878->3329|7158->3582|7186->3583|7218->3588|7302->3645|7342->3647|7378->3656|7435->3696|7474->3697|7510->3706|7799->3968|7838->3991|7878->3993|7927->4014|7988->4048|8002->4053|8026->4056|8088->4091|8102->4096|8132->4105|8194->4140|8208->4145|8238->4154|8300->4189|8314->4194|8344->4203|8425->4252|8467->4265|8529->4296|8563->4302|8659->4370|8700->4372|8737->4381|8804->4430|8844->4431|8881->4440|9256->4787|9298->4812|9339->4814|9389->4835|9451->4869|9466->4874|9498->4884|9561->4919|9576->4924|9608->4934|9671->4969|9686->4974|9717->4983|9780->5018|9795->5023|9832->5038|9895->5073|9910->5078|9947->5093|10010->5128|10025->5133|10062->5148|10143->5197|10185->5210|10247->5241|10280->5243
                  LINES: 22->1|27->2|28->3|28->3|28->3|29->4|48->23|48->23|48->23|48->23|49->24|49->24|49->24|50->25|52->27|52->27|52->27|52->27|53->28|53->28|53->28|55->30|57->32|57->32|57->32|58->33|58->33|58->33|58->33|59->34|59->34|59->34|59->34|60->35|61->36|65->40|65->40|65->40|66->41|66->41|66->41|66->41|67->42|67->42|67->42|67->42|68->43|69->44|73->48|73->48|73->48|74->49|74->49|74->49|74->49|75->50|75->50|75->50|75->50|76->51|77->52|83->58|85->60|86->61|86->61|87->62|88->63|88->63|88->63|88->63|88->63|89->64|89->64|89->64|89->64|89->64|90->65|90->65|90->65|90->65|90->65|91->66|91->66|91->66|91->66|91->66|92->67|92->67|92->67|92->67|92->67|93->68|93->68|93->68|93->68|93->68|94->69|94->69|94->69|94->69|94->69|95->70|95->70|95->70|95->70|95->70|96->71|101->76|101->76|102->77|105->80|105->80|106->81|107->82|107->82|108->83|118->93|118->93|118->93|119->94|120->95|120->95|120->95|121->96|121->96|121->96|122->97|122->97|122->97|123->98|123->98|123->98|125->100|126->101|128->103|130->105|131->106|131->106|132->107|133->108|133->108|134->109|146->121|146->121|146->121|147->122|148->123|148->123|148->123|149->124|149->124|149->124|150->125|150->125|150->125|151->126|151->126|151->126|152->127|152->127|152->127|153->128|153->128|153->128|155->130|156->131|158->133|159->134
                  -- GENERATED --
              */
          