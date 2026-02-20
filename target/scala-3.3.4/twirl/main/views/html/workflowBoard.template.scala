
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
            </div>
        </div>
    """)))}),format.raw/*55.6*/("""

    """),format.raw/*57.5*/("""<h2>Orders</h2>
    """),_display_(if(orders.isEmpty)/*58.24*/ {_display_(Seq[Any](format.raw/*58.26*/("""
        """),format.raw/*59.9*/("""<p>No workflow orders yet. Create one above!</p>
    """)))}else/*60.12*/{_display_(Seq[Any](format.raw/*60.13*/("""
        """),_display_(/*61.10*/for((order, status, actions, detail) <- orders) yield /*61.57*/ {_display_(Seq[Any](format.raw/*61.59*/("""
            """),format.raw/*62.13*/("""<div class="card">
                <div style="display:flex; justify-content:space-between; align-items:start">
                    <div>
                        <h3>#"""),_display_(/*65.31*/order/*65.36*/.id),format.raw/*65.39*/(""" """),format.raw/*65.40*/("""- """),_display_(/*65.43*/order/*65.48*/.description),format.raw/*65.60*/("""</h3>
                        <p>
                            <strong>Status:</strong>
                            <span style="background:"""),_display_(/*68.54*/{_root_.workflow.WorkflowEngine.statusName(status) match {
                                case "Pending" => "#FFF3E0"
                                case "Approved" => "#E8F5E9"
                                case "Shipped" => "#E3F2FD"
                                case "Delivered" => "#E8F5E9"
                                case "Cancelled" => "#FFEBEE"
                                case _ => "#f5f5f5"
                            }}),format.raw/*75.31*/("""; padding:2px 8px; border-radius:3px">
                                """),_display_(/*76.34*/{_root_.workflow.WorkflowEngine.statusName(status)}),format.raw/*76.85*/("""
                            """),format.raw/*77.29*/("""</span>
                            """),_display_(if(detail.nonEmpty)/*78.49*/ {_display_(Seq[Any](format.raw/*78.51*/("""
                                """),format.raw/*79.33*/("""<br><span style="color:#999; font-size:0.85em">"""),_display_(/*79.81*/detail),format.raw/*79.87*/("""</span>
                            """)))} else {null} ),format.raw/*80.30*/("""
                        """),format.raw/*81.25*/("""</p>
                    </div>
                    <div>
                        """),_display_(if(actions.nonEmpty)/*84.46*/ {_display_(Seq[Any](format.raw/*84.48*/("""
                            """),_display_(/*85.30*/for(action <- actions) yield /*85.52*/ {_display_(Seq[Any](format.raw/*85.54*/("""
                                """),_display_(/*86.34*/helper/*86.40*/.form(action = _root_.workflow.routes.WorkflowController.transition(), Symbol("style") -> "display:inline-block; margin:2px")/*86.165*/ {_display_(Seq[Any](format.raw/*86.167*/("""
                                    """),_display_(/*87.38*/helper/*87.44*/.CSRF.formField),format.raw/*87.59*/("""
                                    """),format.raw/*88.37*/("""<input type="hidden" name="orderId" value=""""),_display_(/*88.81*/order/*88.86*/.id),format.raw/*88.89*/("""">
                                    <input type="hidden" name="action" value=""""),_display_(/*89.80*/action),format.raw/*89.86*/("""">
                                    <input type="hidden" name="data" value=""""),_display_(/*90.78*/{action match {
                                        case "approve" => "by=admin"
                                        case "ship" => "tracking=TRK-" + order.id + ",carrier=FedEx"
                                        case "deliver" => "signature=John Doe"
                                        case "cancel" => "reason=Customer request"
                                        case _ => ""
                                    }}),format.raw/*96.39*/("""">
                                    <button type="submit" class="btn-sm """),_display_(/*97.74*/{if(action == "cancel") "btn-red" else "btn-blue"}),format.raw/*97.124*/("""">"""),_display_(/*97.127*/action),format.raw/*97.133*/("""</button>
                                """)))}),format.raw/*98.34*/("""
                            """)))}),format.raw/*99.30*/("""
                        """)))}else/*100.32*/{_display_(Seq[Any](format.raw/*100.33*/("""
                            """),format.raw/*101.29*/("""<span style="color:#999">Terminal state</span>
                        """)))}),format.raw/*102.26*/("""
                    """),format.raw/*103.21*/("""</div>
                </div>
            </div>
        """)))}),format.raw/*106.10*/("""
    """)))}),format.raw/*107.6*/("""
""")))}),format.raw/*108.2*/("""
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
                  HASH: 5fe0e253cc3409012b0ac934e7963c26bd6d1189
                  MATRIX: 925->1|1219->202|1246->204|1294->244|1333->246|1364->251|2953->1814|2969->1821|3003->1846|3054->1859|3090->1868|3139->1890|3167->1897|3209->1909|3241->1915|3255->1920|3268->1924|3319->1937|3355->1946|3484->2047|3513->2054|3555->2066|3587->2072|3603->2079|3635->2102|3686->2115|3722->2124|3851->2225|3880->2232|3922->2244|3955->2250|4012->2281|4027->2287|4107->2358|4147->2360|4184->2370|4199->2376|4235->2391|4271->2400|4698->2797|4731->2803|4797->2842|4837->2844|4873->2853|4950->2913|4989->2914|5026->2924|5089->2971|5129->2973|5170->2986|5365->3154|5379->3159|5403->3162|5432->3163|5462->3166|5476->3171|5509->3183|5676->3323|6143->3769|6242->3841|6314->3892|6371->3921|6454->3977|6494->3979|6555->4012|6630->4060|6657->4066|6738->4103|6791->4128|6921->4231|6961->4233|7018->4263|7056->4285|7096->4287|7157->4321|7172->4327|7307->4452|7348->4454|7413->4492|7428->4498|7464->4513|7529->4550|7600->4594|7614->4599|7638->4602|7747->4684|7774->4690|7881->4770|8341->5209|8444->5285|8516->5335|8547->5338|8575->5344|8649->5387|8710->5417|8760->5449|8800->5450|8858->5479|8962->5551|9012->5572|9102->5630|9139->5636|9172->5638
                  LINES: 22->1|27->2|28->3|28->3|28->3|29->4|58->33|58->33|58->33|58->33|59->34|59->34|59->34|60->35|61->36|61->36|61->36|61->36|62->37|62->37|62->37|63->38|64->39|64->39|64->39|64->39|65->40|65->40|65->40|66->41|68->43|69->44|69->44|69->44|69->44|70->45|70->45|70->45|71->46|80->55|82->57|83->58|83->58|84->59|85->60|85->60|86->61|86->61|86->61|87->62|90->65|90->65|90->65|90->65|90->65|90->65|90->65|93->68|100->75|101->76|101->76|102->77|103->78|103->78|104->79|104->79|104->79|105->80|106->81|109->84|109->84|110->85|110->85|110->85|111->86|111->86|111->86|111->86|112->87|112->87|112->87|113->88|113->88|113->88|113->88|114->89|114->89|115->90|121->96|122->97|122->97|122->97|122->97|123->98|124->99|125->100|125->100|126->101|127->102|128->103|131->106|132->107|133->108
                  -- GENERATED --
              */
          