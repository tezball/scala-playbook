
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

object index extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),_display_(/*3.2*/main("Home")/*3.14*/ {_display_(Seq[Any](format.raw/*3.16*/("""
    """),format.raw/*4.5*/("""<h1>Scala Playbook</h1>
    <p>Learn Scala concepts through real-world e-commerce scenarios. Each page demonstrates specific Scala features with interactive forms and visible results.</p>

    <h2>Existing Modules</h2>
    <div class="cards">
        <div class="card">
            <h3><a href=""""),_display_(/*10.27*/_root_/*10.33*/.users.routes.UserController.showForm()),format.raw/*10.72*/("""">Users</a></h3>
            <p>User management with PostgreSQL (Slick) and Kafka event publishing.</p>
            <p><strong>Concepts:</strong> Play Forms, Slick ORM, Kafka Producer/Consumer</p>
        </div>
        <div class="card">
            <h3><a href=""""),_display_(/*15.27*/_root_/*15.33*/.orders.routes.OrderController.showForm()),format.raw/*15.74*/("""">Orders</a></h3>
            <p>Order placement with database persistence and Kafka checkout events.</p>
            <p><strong>Concepts:</strong> Play Forms, Slick ORM, Kafka Producer/Consumer</p>
        </div>
    </div>

    <h2>Core Concepts</h2>
    <div class="cards">
        <div class="card">
            <h3><a href=""""),_display_(/*24.27*/_root_/*24.33*/.products.routes.ProductController.showForm()),format.raw/*24.78*/("""">Products</a></h3>
            <p>Product catalog with categories and pricing rules.</p>
            <p><strong>Concepts:</strong> Pattern Matching, Sealed Traits, Enums, Case Classes</p>
        </div>
        <div class="card">
            <h3><a href=""""),_display_(/*29.27*/_root_/*29.33*/.cart.routes.CartController.showCart()),format.raw/*29.71*/("""">Cart</a></h3>
            <p>Shopping cart with totals, discounts, and grouping.</p>
            <p><strong>Concepts:</strong> map, filter, foldLeft, groupBy, sortBy, collect</p>
        </div>
        <div class="card">
            <h3><a href=""""),_display_(/*34.27*/_root_/*34.33*/.coupons.routes.CouponController.showForm()),format.raw/*34.76*/("""">Coupons</a></h3>
            <p>Coupon code validation pipeline.</p>
            <p><strong>Concepts:</strong> Option, Either, Try, for-comprehensions</p>
        </div>
        <div class="card">
            <h3><a href=""""),_display_(/*39.27*/_root_/*39.33*/.notifications.routes.NotificationController.showForm()),format.raw/*39.88*/("""">Notifications</a></h3>
            <p>Message processing pipeline.</p>
            <p><strong>Concepts:</strong> andThen, compose, pipe, tap, PartialFunction</p>
        </div>
        <div class="card">
            <h3><a href=""""),_display_(/*44.27*/_root_/*44.33*/.analytics.routes.AnalyticsController.dashboard()),format.raw/*44.82*/("""">Analytics</a></h3>
            <p>Dashboard aggregating data from users and orders.</p>
            <p><strong>Concepts:</strong> Future, parallel composition, Future.sequence, recover</p>
        </div>
    </div>

    <h2>Advanced Concepts</h2>
    <div class="cards">
        <div class="card">
            <h3><a href=""""),_display_(/*53.27*/_root_/*53.33*/.reviews.routes.ReviewController.showForm()),format.raw/*53.76*/("""">Reviews</a></h3>
            <p>Product reviews with sorting and rendering.</p>
            <p><strong>Concepts:</strong> Traits, Mixins, Type Classes, Ordering, Givens</p>
        </div>
        <div class="card">
            <h3><a href=""""),_display_(/*58.27*/_root_/*58.33*/.workflow.routes.WorkflowController.showBoard()),format.raw/*58.80*/("""">Workflow</a></h3>
            <p>Order status state machine.</p>
            <p><strong>Concepts:</strong> ADTs, State Machines, Exhaustive Matching, copy</p>
        </div>
        <div class="card">
            <h3><a href=""""),_display_(/*63.27*/_root_/*63.33*/.ledger.routes.LedgerController.showLedger()),format.raw/*63.77*/("""">Ledger</a></h3>
            <p>Financial transaction ledger.</p>
            <p><strong>Concepts:</strong> Event Sourcing, foldLeft, scanLeft, Immutability</p>
        </div>
    </div>
""")))}),format.raw/*68.2*/("""
"""))
      }
    }
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/index.scala.html
                  HASH: e7f4556f75ed4fc54495e233f1b178f7da53b947
                  MATRIX: 760->1|856->4|883->6|903->18|942->20|973->25|1296->321|1311->327|1371->366|1663->631|1678->637|1740->678|2097->1008|2112->1014|2178->1059|2462->1316|2477->1322|2536->1360|2812->1609|2827->1615|2891->1658|3143->1883|3158->1889|3234->1944|3493->2176|3508->2182|3578->2231|3931->2557|3946->2563|4010->2606|4280->2849|4295->2855|4363->2902|4619->3131|4634->3137|4699->3181|4918->3370
                  LINES: 22->1|27->2|28->3|28->3|28->3|29->4|35->10|35->10|35->10|40->15|40->15|40->15|49->24|49->24|49->24|54->29|54->29|54->29|59->34|59->34|59->34|64->39|64->39|64->39|69->44|69->44|69->44|78->53|78->53|78->53|83->58|83->58|83->58|88->63|88->63|88->63|93->68
                  -- GENERATED --
              */
          