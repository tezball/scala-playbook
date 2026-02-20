
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

object productCatalog extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[Form[_root_.products.ProductForm],Seq[_root_.products.Product],RequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(form: Form[_root_.products.ProductForm], productList: Seq[_root_.products.Product])(implicit request: RequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),_display_(/*3.2*/main("Products - Pattern Matching")/*3.37*/ {_display_(Seq[Any](format.raw/*3.39*/("""
    """),format.raw/*4.5*/("""<h1>Product Catalog</h1>

    <div class="concept-banner">
        <h3>Concepts: Pattern Matching, Sealed Traits, Enums, Case Classes</h3>
        <p>Product pricing is computed using pattern matching on sealed trait discount types and enum categories. The compiler enforces exhaustive matching &mdash; every case must be handled.</p>
        <pre><code>sealed trait Discount
case class Percentage(percent: Double) extends Discount
case class FixedAmount(amount: Double) extends Discount
case class BuyOneGetOne(freeItemName: String) extends Discount
case object NoDiscount extends Discount

def applyDiscount(price: Double, discount: Discount): Double = discount match
  case Percentage(pct)  =&gt; price * (1 - pct / 100)
  case FixedAmount(amt) =&gt; (price - amt).max(0)
  case BuyOneGetOne(_)  =&gt; price
  case NoDiscount       =&gt; price

def taxRate(category: Category): Double = category match
  case Category.Food                          =&gt; 0.0
  case Category.Books                         =&gt; 0.05
  case Category.Clothing                      =&gt; 0.08
  case Category.Electronics | Category.Sports =&gt; 0.10</code></pre>
    </div>

    """),_display_(/*28.6*/request/*28.13*/.flash.get("success").map/*28.38*/ { message =>_display_(Seq[Any](format.raw/*28.51*/("""
        """),format.raw/*29.9*/("""<div class="success">"""),_display_(/*29.31*/message),format.raw/*29.38*/("""</div>
    """)))}),format.raw/*30.6*/("""

    """),format.raw/*32.5*/("""<h2>Add Product</h2>
    """),_display_(/*33.6*/helper/*33.12*/.form(action = _root_.products.routes.ProductController.addProduct())/*33.81*/ {_display_(Seq[Any](format.raw/*33.83*/("""
        """),_display_(/*34.10*/helper/*34.16*/.CSRF.formField),format.raw/*34.31*/("""

        """),format.raw/*36.9*/("""<div class="form-group">
            <label for="name">Product Name</label>
            <input type="text" id="name" name="name" value=""""),_display_(/*38.62*/form("name")/*38.74*/.value.getOrElse("")),format.raw/*38.94*/("""" required>
        </div>

        <div class="form-group">
            <label for="category">Category</label>
            <select id="category" name="category">
                <option value="Electronics" """),_display_(if(form("category").value.contains("Electronics"))/*44.96*/{_display_(Seq[Any](format.raw/*44.97*/("""selected""")))} else {null} ),format.raw/*44.106*/(""">Electronics (10% tax)</option>
                <option value="Books" """),_display_(if(form("category").value.contains("Books"))/*45.84*/{_display_(Seq[Any](format.raw/*45.85*/("""selected""")))} else {null} ),format.raw/*45.94*/(""">Books (5% tax)</option>
                <option value="Clothing" """),_display_(if(form("category").value.contains("Clothing"))/*46.90*/{_display_(Seq[Any](format.raw/*46.91*/("""selected""")))} else {null} ),format.raw/*46.100*/(""">Clothing (8% tax)</option>
                <option value="Food" """),_display_(if(form("category").value.contains("Food"))/*47.82*/{_display_(Seq[Any](format.raw/*47.83*/("""selected""")))} else {null} ),format.raw/*47.92*/(""">Food (0% tax)</option>
                <option value="Sports" """),_display_(if(form("category").value.contains("Sports"))/*48.86*/{_display_(Seq[Any](format.raw/*48.87*/("""selected""")))} else {null} ),format.raw/*48.96*/(""">Sports (10% tax)</option>
            </select>
        </div>

        <div class="form-group">
            <label for="basePrice">Base Price ($)</label>
            <input type="number" id="basePrice" name="basePrice" step="0.01" min="0" value=""""),_display_(/*54.94*/form("basePrice")/*54.111*/.value.getOrElse("0.00")),format.raw/*54.135*/("""" required>
        </div>

        <div class="form-group">
            <label for="discountType">Discount Type</label>
            <select id="discountType" name="discountType">
                <option value="none" """),_display_(if(form("discountType").value.contains("none"))/*60.86*/{_display_(Seq[Any](format.raw/*60.87*/("""selected""")))} else {null} ),format.raw/*60.96*/(""">No Discount</option>
                <option value="percentage" """),_display_(if(form("discountType").value.contains("percentage"))/*61.98*/{_display_(Seq[Any](format.raw/*61.99*/("""selected""")))} else {null} ),format.raw/*61.108*/(""">Percentage Off</option>
                <option value="fixed" """),_display_(if(form("discountType").value.contains("fixed"))/*62.88*/{_display_(Seq[Any](format.raw/*62.89*/("""selected""")))} else {null} ),format.raw/*62.98*/(""">Fixed Amount Off</option>
                <option value="bogo" """),_display_(if(form("discountType").value.contains("bogo"))/*63.86*/{_display_(Seq[Any](format.raw/*63.87*/("""selected""")))} else {null} ),format.raw/*63.96*/(""">Buy One Get One</option>
            </select>
        </div>

        <div class="form-group">
            <label for="discountValue">Discount Value (% or $ amount, 0 for BOGO/None)</label>
            <input type="number" id="discountValue" name="discountValue" step="0.01" min="0" value=""""),_display_(/*69.102*/form("discountValue")/*69.123*/.value.getOrElse("0")),format.raw/*69.144*/("""" required>
        </div>

        <button type="submit">Add Product</button>
    """)))}),format.raw/*73.6*/("""

    """),format.raw/*75.5*/("""<h2>Products</h2>
    """),_display_(if(productList.isEmpty)/*76.29*/ {_display_(Seq[Any](format.raw/*76.31*/("""
        """),format.raw/*77.9*/("""<p>No products yet. Add one above!</p>
    """)))}else/*78.12*/{_display_(Seq[Any](format.raw/*78.13*/("""
        """),format.raw/*79.9*/("""<table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Category</th>
                    <th>Base Price</th>
                    <th>Discount</th>
                    <th>Final Price</th>
                </tr>
            </thead>
            <tbody>
                """),_display_(/*91.18*/for(product <- productList) yield /*91.45*/ {_display_(Seq[Any](format.raw/*91.47*/("""
                    """),format.raw/*92.21*/("""<tr>
                        <td>"""),_display_(/*93.30*/product/*93.37*/.id),format.raw/*93.40*/("""</td>
                        <td>"""),_display_(/*94.30*/product/*94.37*/.name),format.raw/*94.42*/("""</td>
                        <td>"""),_display_(/*95.30*/product/*95.37*/.category),format.raw/*95.46*/("""</td>
                        <td>$"""),_display_(/*96.31*/{f"${product.basePrice}%.2f"}),format.raw/*96.60*/("""</td>
                        <td>"""),_display_(/*97.30*/{product.discountType match {
                            case "percentage" => s"${product.discountValue}% off"
                            case "fixed" => s"$$${product.discountValue} off"
                            case "bogo" => "Buy One Get One"
                            case _ => "None"
                        }}),format.raw/*102.27*/("""</td>
                        <td><strong>$"""),_display_(/*103.39*/{f"${product.finalPrice}%.2f"}),format.raw/*103.69*/("""</strong></td>
                    </tr>
                """)))}),format.raw/*105.18*/("""
            """),format.raw/*106.13*/("""</tbody>
        </table>
    """)))}),format.raw/*108.6*/("""
""")))}),format.raw/*109.2*/("""
"""))
      }
    }
  }

  def render(form:Form[_root_.products.ProductForm],productList:Seq[_root_.products.Product],request:RequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(form,productList)(request)

  def f:((Form[_root_.products.ProductForm],Seq[_root_.products.Product]) => (RequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (form,productList) => (request) => apply(form,productList)(request)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/productCatalog.scala.html
                  HASH: 53034774e39bb730ff8b2da93523de577341ba8c
                  MATRIX: 846->1|1057->119|1084->121|1127->156|1166->158|1197->163|2385->1325|2401->1332|2435->1357|2486->1370|2522->1379|2571->1401|2599->1408|2641->1420|2674->1426|2726->1452|2741->1458|2819->1527|2859->1529|2896->1539|2911->1545|2947->1560|2984->1570|3148->1707|3169->1719|3210->1739|3495->1997|3534->1998|3588->2007|3730->2122|3769->2123|3822->2132|3963->2246|4002->2247|4056->2256|4192->2365|4231->2366|4284->2375|4420->2484|4459->2485|4512->2494|4788->2743|4815->2760|4861->2784|5153->3049|5192->3050|5245->3059|5391->3178|5430->3179|5484->3188|5623->3300|5662->3301|5715->3310|5854->3422|5893->3423|5946->3432|6267->3725|6298->3746|6341->3767|6455->3851|6488->3857|6561->3903|6601->3905|6637->3914|6704->3964|6743->3965|6779->3974|7158->4326|7201->4353|7241->4355|7290->4376|7351->4410|7367->4417|7391->4420|7453->4455|7469->4462|7495->4467|7557->4502|7573->4509|7603->4518|7666->4554|7716->4583|7778->4618|8122->4940|8194->4984|8246->5014|8336->5072|8378->5085|8440->5116|8473->5118
                  LINES: 22->1|27->2|28->3|28->3|28->3|29->4|53->28|53->28|53->28|53->28|54->29|54->29|54->29|55->30|57->32|58->33|58->33|58->33|58->33|59->34|59->34|59->34|61->36|63->38|63->38|63->38|69->44|69->44|69->44|70->45|70->45|70->45|71->46|71->46|71->46|72->47|72->47|72->47|73->48|73->48|73->48|79->54|79->54|79->54|85->60|85->60|85->60|86->61|86->61|86->61|87->62|87->62|87->62|88->63|88->63|88->63|94->69|94->69|94->69|98->73|100->75|101->76|101->76|102->77|103->78|103->78|104->79|116->91|116->91|116->91|117->92|118->93|118->93|118->93|119->94|119->94|119->94|120->95|120->95|120->95|121->96|121->96|122->97|127->102|128->103|128->103|130->105|131->106|133->108|134->109
                  -- GENERATED --
              */
          