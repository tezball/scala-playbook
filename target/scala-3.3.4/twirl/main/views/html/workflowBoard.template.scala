
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

object workflowBoard extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Form[_root_.workflow.WorkflowOrderForm],Seq[(_root_.workflow.WorkflowOrder, _root_.workflow.OrderStatus, List[String], String)],Option[String],RequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(form: Form[_root_.workflow.WorkflowOrderForm], orders: Seq[(_root_.workflow.WorkflowOrder, _root_.workflow.OrderStatus, List[String], String)], error: Option[String])(implicit request: RequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),_display_(/*3.2*/main("Workflow - ADTs + State Machines")/*3.42*/ {_display_(Seq[Any](format.raw/*3.44*/("""
    """),format.raw/*4.5*/("""<h1>Order Workflow</h1>

    <div class="concept-banner">
        <h3>Advanced Concepts: ADTs (Algebraic Data Types), State Machines, Exhaustive Matching, copy</h3>
        <p>Each order has a typed state from a sealed trait ADT. The compiler enforces that every state is handled in pattern matches. Transitions are validated &mdash; you can only move to legal next states.</p>
        <pre><code>// ADT for order states - each state carries different data
sealed trait OrderStatus
case object Pending extends OrderStatus
case class Approved(approvedBy: String, approvedAt: Instant) extends OrderStatus
case class Shipped(trackingNumber: String, carrier: String) extends OrderStatus
case class Delivered(deliveredAt: Instant, signature: String) extends OrderStatus
case class Cancelled(reason: String, cancelledAt: Instant) extends OrderStatus

// State machine - exhaustive matching
def nextActions(status: OrderStatus): List[String] = status match
  case Pending        =&gt; List("approve", "cancel")
  case Approved(_, _) =&gt; List("ship", "cancel")
  case Shipped(_, _)  =&gt; List("deliver", "cancel")
  case Delivered(_, _) =&gt; List()  // terminal
  case Cancelled(_, _) =&gt; List()  // terminal</code></pre>
    </div>

    <div style="background:#f5f5f5; padding:12px; border-radius:4px; margin-bottom:20px; text-align:center">
        <strong>State Flow:</strong>
        Pending &rarr; Approved &rarr; Shipped &rarr; Delivered
        <br>
        <span style="color:#999">(any non-terminal state can also &rarr; Cancelled)</span>
    </div>

    """),_display_(/*33.6*/request/*33.13*/.flash.get("success").map/*33.38*/ { message =>_display_(Seq[Any](format.raw/*33.51*/("""
        """),format.raw/*34.9*/("""<div class="success">"""),_display_(/*34.31*/message),format.raw/*34.38*/("""</div>
    """)))}),format.raw/*35.6*/("""
    """),_display_(/*36.6*/error/*36.11*/.map/*36.15*/ { message =>_display_(Seq[Any](format.raw/*36.28*/("""
        """),format.raw/*37.9*/("""<div style="background:#f2dede; color:#a94442; padding:10px; border-radius:4px; margin-bottom:16px">"""),_display_(/*37.110*/message),format.raw/*37.117*/("""</div>
    """)))}),format.raw/*38.6*/("""
    """),_display_(/*39.6*/request/*39.13*/.flash.get("error").map/*39.36*/ { message =>_display_(Seq[Any](format.raw/*39.49*/("""
        """),format.raw/*40.9*/("""<div style="background:#f2dede; color:#a94442; padding:10px; border-radius:4px; margin-bottom:16px">"""),_display_(/*40.110*/message),format.raw/*40.117*/("""</div>
    """)))}),format.raw/*41.6*/("""

    """),format.raw/*43.5*/("""<h2>Create New Order</h2>
    """),_display_(/*44.6*/helper/*44.12*/.form(action = _root_.workflow.routes.WorkflowController.createOrder())/*44.83*/ {_display_(Seq[Any](format.raw/*44.85*/("""
        """),_display_(/*45.10*/helper/*45.16*/.CSRF.formField),format.raw/*45.31*/("""
        """),format.raw/*46.9*/("""<div class="inline-form">
            <div class="form-group">
                <label for="description">Item Description</label>
                <input type="text" id="description" name="description" placeholder="e.g. Laptop Stand" required>
            </div>
            <div class="form-group">
                <button type="submit">Create Order</button>
                <button type="button" class="btn-autofill btn-sm" onclick="autoFillWorkflow()">Auto Fill</button>
            </div>
        </div>
    """)))}),format.raw/*56.6*/("""

    """),format.raw/*58.5*/("""<script>
    function autoFillWorkflow() """),format.raw/*59.33*/("""{"""),format.raw/*59.34*/("""
        """),format.raw/*60.9*/("""var items = [
            'Laptop Stand - Adjustable Aluminum',
            'Webcam HD 1080p with Ring Light',
            'Desk Organizer - Bamboo 5-Slot',
            'Monitor Arm - Dual Mount',
            'Cable Management Kit (20 pieces)',
            'Whiteboard 36x24 - Magnetic',
            'Footrest - Ergonomic Adjustable',
            'Desk Lamp - LED with USB Charging'
        ];
        document.getElementById('description').value = items[Math.floor(Math.random() * items.length)];
    """),format.raw/*71.5*/("""}"""),format.raw/*71.6*/("""
    """),format.raw/*72.5*/("""</script>

    <h2>Orders</h2>
    """),_display_(if(orders.isEmpty)/*75.24*/ {_display_(Seq[Any](format.raw/*75.26*/("""
        """),format.raw/*76.9*/("""<p>No workflow orders yet. Create one above!</p>
    """)))}else/*77.12*/{_display_(Seq[Any](format.raw/*77.13*/("""
        """),_display_(/*78.10*/for((order, status, actions, detail) <- orders) yield /*78.57*/ {_display_(Seq[Any](format.raw/*78.59*/("""
            """),format.raw/*79.13*/("""<div class="card">
                <div style="display:flex; justify-content:space-between; align-items:start">
                    <div>
                        <h3>#"""),_display_(/*82.31*/order/*82.36*/.id),format.raw/*82.39*/(""" """),format.raw/*82.40*/("""- """),_display_(/*82.43*/order/*82.48*/.description),format.raw/*82.60*/("""</h3>
                        <p>
                            <strong>Status:</strong>
                            <span style="background:"""),_display_(/*85.54*/{_root_.workflow.WorkflowEngine.statusName(status) match {
                                case "Pending" => "#FFF3E0"
                                case "Approved" => "#E8F5E9"
                                case "Shipped" => "#E3F2FD"
                                case "Delivered" => "#E8F5E9"
                                case "Cancelled" => "#FFEBEE"
                                case _ => "#f5f5f5"
                            }}),format.raw/*92.31*/("""; padding:2px 8px; border-radius:3px">
                                """),_display_(/*93.34*/{_root_.workflow.WorkflowEngine.statusName(status)}),format.raw/*93.85*/("""
                            """),format.raw/*94.29*/("""</span>
                            """),_display_(if(detail.nonEmpty)/*95.49*/ {_display_(Seq[Any](format.raw/*95.51*/("""
                                """),format.raw/*96.33*/("""<br><span style="color:#999; font-size:0.85em">"""),_display_(/*96.81*/detail),format.raw/*96.87*/("""</span>
                            """)))} else {null} ),format.raw/*97.30*/("""
                        """),format.raw/*98.25*/("""</p>
                    </div>
                    <div>
                        """),_display_(if(actions.nonEmpty)/*101.46*/ {_display_(Seq[Any](format.raw/*101.48*/("""
                            """),_display_(/*102.30*/for(action <- actions) yield /*102.52*/ {_display_(Seq[Any](format.raw/*102.54*/("""
                                """),_display_(/*103.34*/helper/*103.40*/.form(action = _root_.workflow.routes.WorkflowController.transition(), Symbol("style") -> "display:inline-block; margin:2px")/*103.165*/ {_display_(Seq[Any](format.raw/*103.167*/("""
                                    """),_display_(/*104.38*/helper/*104.44*/.CSRF.formField),format.raw/*104.59*/("""
                                    """),format.raw/*105.37*/("""<input type="hidden" name="orderId" value=""""),_display_(/*105.81*/order/*105.86*/.id),format.raw/*105.89*/("""">
                                    <input type="hidden" name="action" value=""""),_display_(/*106.80*/action),format.raw/*106.86*/("""">
                                    <input type="hidden" name="data" value=""""),_display_(/*107.78*/{action match {
                                        case "approve" => "by=admin"
                                        case "ship" => "tracking=TRK-" + order.id + ",carrier=FedEx"
                                        case "deliver" => "signature=John Doe"
                                        case "cancel" => "reason=Customer request"
                                        case _ => ""
                                    }}),format.raw/*113.39*/("""">
                                    <button type="submit" class="btn-sm """),_display_(/*114.74*/{if(action == "cancel") "btn-red" else "btn-blue"}),format.raw/*114.124*/("""">"""),_display_(/*114.127*/action),format.raw/*114.133*/("""</button>
                                """)))}),format.raw/*115.34*/("""
                            """)))}),format.raw/*116.30*/("""
                        """)))}else/*117.32*/{_display_(Seq[Any](format.raw/*117.33*/("""
                            """),format.raw/*118.29*/("""<span style="color:#999">Terminal state</span>
                        """)))}),format.raw/*119.26*/("""
                    """),format.raw/*120.21*/("""</div>
                </div>
            </div>
        """)))}),format.raw/*123.10*/("""
    """)))}),format.raw/*124.6*/("""
""")))}),format.raw/*125.2*/("""
"""))
      }
    }
  }

  def render(form:Form[_root_.workflow.WorkflowOrderForm],orders:Seq[(_root_.workflow.WorkflowOrder, _root_.workflow.OrderStatus, List[String], String)],error:Option[String],request:RequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(form,orders,error)(request)

  def f:((Form[_root_.workflow.WorkflowOrderForm],Seq[(_root_.workflow.WorkflowOrder, _root_.workflow.OrderStatus, List[String], String)],Option[String]) => (RequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (form,orders,error) => (request) => apply(form,orders,error)(request)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/workflowBoard.scala.html
                  HASH: a792910bee5690e7e6965d6eefc6dd8e7d2d8a74
                  MATRIX: 925->1|1219->202|1246->204|1294->244|1333->246|1364->251|2953->1814|2969->1821|3003->1846|3054->1859|3090->1868|3139->1890|3167->1897|3209->1909|3241->1915|3255->1920|3268->1924|3319->1937|3355->1946|3484->2047|3513->2054|3555->2066|3587->2072|3603->2079|3635->2102|3686->2115|3722->2124|3851->2225|3880->2232|3922->2244|3955->2250|4012->2281|4027->2287|4107->2358|4147->2360|4184->2370|4199->2376|4235->2391|4271->2400|4812->2911|4845->2917|4914->2958|4943->2959|4979->2968|5508->3470|5536->3471|5568->3476|5649->3530|5689->3532|5725->3541|5802->3601|5841->3602|5878->3612|5941->3659|5981->3661|6022->3674|6217->3842|6231->3847|6255->3850|6284->3851|6314->3854|6328->3859|6361->3871|6528->4011|6995->4457|7094->4529|7166->4580|7223->4609|7306->4665|7346->4667|7407->4700|7482->4748|7509->4754|7590->4791|7643->4816|7774->4919|7815->4921|7873->4951|7912->4973|7953->4975|8015->5009|8031->5015|8167->5140|8209->5142|8275->5180|8291->5186|8328->5201|8394->5238|8466->5282|8481->5287|8506->5290|8616->5372|8644->5378|8752->5458|9213->5897|9317->5973|9390->6023|9422->6026|9451->6032|9526->6075|9588->6105|9638->6137|9678->6138|9736->6167|9840->6239|9890->6260|9980->6318|10017->6324|10050->6326
                  LINES: 22->1|27->2|28->3|28->3|28->3|29->4|58->33|58->33|58->33|58->33|59->34|59->34|59->34|60->35|61->36|61->36|61->36|61->36|62->37|62->37|62->37|63->38|64->39|64->39|64->39|64->39|65->40|65->40|65->40|66->41|68->43|69->44|69->44|69->44|69->44|70->45|70->45|70->45|71->46|81->56|83->58|84->59|84->59|85->60|96->71|96->71|97->72|100->75|100->75|101->76|102->77|102->77|103->78|103->78|103->78|104->79|107->82|107->82|107->82|107->82|107->82|107->82|107->82|110->85|117->92|118->93|118->93|119->94|120->95|120->95|121->96|121->96|121->96|122->97|123->98|126->101|126->101|127->102|127->102|127->102|128->103|128->103|128->103|128->103|129->104|129->104|129->104|130->105|130->105|130->105|130->105|131->106|131->106|132->107|138->113|139->114|139->114|139->114|139->114|140->115|141->116|142->117|142->117|143->118|144->119|145->120|148->123|149->124|150->125
                  -- GENERATED --
              */
          