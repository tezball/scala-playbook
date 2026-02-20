
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

    <h2>Core Concepts</h2>
    <div class="cards">
        <div class="card">
            <h3><a href=""""),_display_(/*10.27*/_root_/*10.33*/.users.routes.UserController.showForm()),format.raw/*10.72*/("""">Users</a></h3>
            <p>User registration with database persistence via Slick ORM.</p>
            <p><strong>Concepts:</strong> Slick Table Mappings, Case Classes, Repository Pattern, Futures</p>
        </div>
        <div class="card">
            <h3><a href=""""),_display_(/*15.27*/_root_/*15.33*/.products.routes.ProductController.showForm()),format.raw/*15.78*/("""">Products</a></h3>
            <p>Product catalog with categories and pricing rules.</p>
            <p><strong>Concepts:</strong> Pattern Matching, Sealed Traits, Enums, Case Classes</p>
        </div>
        <div class="card">
            <h3><a href=""""),_display_(/*20.27*/_root_/*20.33*/.cart.routes.CartController.showCart()),format.raw/*20.71*/("""">Cart</a></h3>
            <p>Shopping cart with totals, discounts, and grouping.</p>
            <p><strong>Concepts:</strong> map, filter, foldLeft, groupBy, sortBy, collect</p>
        </div>
        <div class="card">
            <h3><a href=""""),_display_(/*25.27*/_root_/*25.33*/.coupons.routes.CouponController.showForm()),format.raw/*25.76*/("""">Coupons</a></h3>
            <p>Coupon code validation pipeline.</p>
            <p><strong>Concepts:</strong> Option, Either, Try, for-comprehensions</p>
        </div>
        <div class="card">
            <h3><a href=""""),_display_(/*30.27*/_root_/*30.33*/.notifications.routes.NotificationController.showForm()),format.raw/*30.88*/("""">Notifications</a></h3>
            <p>Message processing pipeline.</p>
            <p><strong>Concepts:</strong> andThen, compose, pipe, tap, PartialFunction</p>
        </div>
        <div class="card">
            <h3><a href=""""),_display_(/*35.27*/_root_/*35.33*/.analytics.routes.AnalyticsController.dashboard()),format.raw/*35.82*/("""">Analytics</a></h3>
            <p>Dashboard aggregating data from users and orders.</p>
            <p><strong>Concepts:</strong> Future, parallel composition, Future.sequence, recover</p>
        </div>
    </div>

    <h2>Advanced Concepts</h2>
    <div class="cards">
        <div class="card">
            <h3><a href=""""),_display_(/*44.27*/_root_/*44.33*/.orders.routes.OrderController.showForm()),format.raw/*44.74*/("""">Orders</a></h3>
            <p>Order placement with Kafka event-driven checkout messaging.</p>
            <p><strong>Concepts:</strong> Kafka Producer/Consumer, Event Serialization, JSON Formats, Async Messaging</p>
        </div>
        <div class="card">
            <h3><a href=""""),_display_(/*49.27*/_root_/*49.33*/.reviews.routes.ReviewController.showForm()),format.raw/*49.76*/("""">Reviews</a></h3>
            <p>Product reviews with sorting and rendering.</p>
            <p><strong>Concepts:</strong> Traits, Mixins, Type Classes, Ordering, Givens</p>
        </div>
        <div class="card">
            <h3><a href=""""),_display_(/*54.27*/_root_/*54.33*/.workflow.routes.WorkflowController.showBoard()),format.raw/*54.80*/("""">Workflow</a></h3>
            <p>Order status state machine.</p>
            <p><strong>Concepts:</strong> ADTs, State Machines, Exhaustive Matching, copy</p>
        </div>
        <div class="card">
            <h3><a href=""""),_display_(/*59.27*/_root_/*59.33*/.ledger.routes.LedgerController.showLedger()),format.raw/*59.77*/("""">Ledger</a></h3>
            <p>Financial transaction ledger.</p>
            <p><strong>Concepts:</strong> Event Sourcing, foldLeft, scanLeft, Immutability</p>
        </div>
    </div>
""")))}),format.raw/*64.2*/("""
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
                  HASH: 5a783436491bd567c7ae36a17b4c490e59477d5c
                  MATRIX: 760->1|856->4|883->6|903->18|942->20|973->25|1293->318|1308->324|1368->363|1668->636|1683->642|1749->687|2033->944|2048->950|2107->988|2383->1237|2398->1243|2462->1286|2714->1511|2729->1517|2805->1572|3064->1804|3079->1810|3149->1859|3502->2185|3517->2191|3579->2232|3893->2519|3908->2525|3972->2568|4242->2811|4257->2817|4325->2864|4581->3093|4596->3099|4661->3143|4880->3332
                  LINES: 22->1|27->2|28->3|28->3|28->3|29->4|35->10|35->10|35->10|40->15|40->15|40->15|45->20|45->20|45->20|50->25|50->25|50->25|55->30|55->30|55->30|60->35|60->35|60->35|69->44|69->44|69->44|74->49|74->49|74->49|79->54|79->54|79->54|84->59|84->59|84->59|89->64
                  -- GENERATED --
              */
          