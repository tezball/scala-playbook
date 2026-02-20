
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

        <div class="btn-group">
            <button type="submit">Add Product</button>
            <button type="button" class="btn-autofill" onclick="autoFillProduct()">Auto Fill</button>
        </div>
    """)))}),format.raw/*76.6*/("""

    """),format.raw/*78.5*/("""<script>
    function autoFillProduct() """),format.raw/*79.32*/("""{"""),format.raw/*79.33*/("""
        """),format.raw/*80.9*/("""var products = [
            """),format.raw/*81.13*/("""{"""),format.raw/*81.14*/(""" """),format.raw/*81.15*/("""name: 'MacBook Pro 16"', cat: 'Electronics', price: 2499.00, dtype: 'percentage', dval: 10 """),format.raw/*81.106*/("""}"""),format.raw/*81.107*/(""",
            """),format.raw/*82.13*/("""{"""),format.raw/*82.14*/(""" """),format.raw/*82.15*/("""name: 'The Pragmatic Programmer', cat: 'Books', price: 49.99, dtype: 'none', dval: 0 """),format.raw/*82.100*/("""}"""),format.raw/*82.101*/(""",
            """),format.raw/*83.13*/("""{"""),format.raw/*83.14*/(""" """),format.raw/*83.15*/("""name: 'Merino Wool Sweater', cat: 'Clothing', price: 89.00, dtype: 'fixed', dval: 15 """),format.raw/*83.100*/("""}"""),format.raw/*83.101*/(""",
            """),format.raw/*84.13*/("""{"""),format.raw/*84.14*/(""" """),format.raw/*84.15*/("""name: 'Organic Avocados (6pk)', cat: 'Food', price: 8.99, dtype: 'bogo', dval: 0 """),format.raw/*84.96*/("""}"""),format.raw/*84.97*/(""",
            """),format.raw/*85.13*/("""{"""),format.raw/*85.14*/(""" """),format.raw/*85.15*/("""name: 'Carbon Fiber Tennis Racket', cat: 'Sports', price: 199.99, dtype: 'percentage', dval: 20 """),format.raw/*85.111*/("""}"""),format.raw/*85.112*/(""",
            """),format.raw/*86.13*/("""{"""),format.raw/*86.14*/(""" """),format.raw/*86.15*/("""name: 'Wireless Noise-Cancelling Headphones', cat: 'Electronics', price: 349.00, dtype: 'fixed', dval: 50 """),format.raw/*86.121*/("""}"""),format.raw/*86.122*/(""",
            """),format.raw/*87.13*/("""{"""),format.raw/*87.14*/(""" """),format.raw/*87.15*/("""name: 'Running Shorts', cat: 'Clothing', price: 34.99, dtype: 'none', dval: 0 """),format.raw/*87.93*/("""}"""),format.raw/*87.94*/(""",
            """),format.raw/*88.13*/("""{"""),format.raw/*88.14*/(""" """),format.raw/*88.15*/("""name: 'Scala 3 In Depth', cat: 'Books', price: 44.95, dtype: 'percentage', dval: 15 """),format.raw/*88.99*/("""}"""),format.raw/*88.100*/("""
        """),format.raw/*89.9*/("""];
        var p = products[Math.floor(Math.random() * products.length)];
        document.getElementById('name').value = p.name;
        document.getElementById('category').value = p.cat;
        document.getElementById('basePrice').value = p.price;
        document.getElementById('discountType').value = p.dtype;
        document.getElementById('discountValue').value = p.dval;
    """),format.raw/*96.5*/("""}"""),format.raw/*96.6*/("""
    """),format.raw/*97.5*/("""</script>

    <h2>Products</h2>
    """),_display_(if(productList.isEmpty)/*100.29*/ {_display_(Seq[Any](format.raw/*100.31*/("""
        """),format.raw/*101.9*/("""<p>No products yet. Add one above!</p>
    """)))}else/*102.12*/{_display_(Seq[Any](format.raw/*102.13*/("""
        """),format.raw/*103.9*/("""<table>
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
                """),_display_(/*115.18*/for(product <- productList) yield /*115.45*/ {_display_(Seq[Any](format.raw/*115.47*/("""
                    """),format.raw/*116.21*/("""<tr>
                        <td>"""),_display_(/*117.30*/product/*117.37*/.id),format.raw/*117.40*/("""</td>
                        <td>"""),_display_(/*118.30*/product/*118.37*/.name),format.raw/*118.42*/("""</td>
                        <td>"""),_display_(/*119.30*/product/*119.37*/.category),format.raw/*119.46*/("""</td>
                        <td>$"""),_display_(/*120.31*/{f"${product.basePrice}%.2f"}),format.raw/*120.60*/("""</td>
                        <td>"""),_display_(/*121.30*/{product.discountType match {
                            case "percentage" => s"${product.discountValue}% off"
                            case "fixed" => s"$$${product.discountValue} off"
                            case "bogo" => "Buy One Get One"
                            case _ => "None"
                        }}),format.raw/*126.27*/("""</td>
                        <td><strong>$"""),_display_(/*127.39*/{f"${product.finalPrice}%.2f"}),format.raw/*127.69*/("""</strong></td>
                    </tr>
                """)))}),format.raw/*129.18*/("""
            """),format.raw/*130.13*/("""</tbody>
        </table>
    """)))}),format.raw/*132.6*/("""
""")))}),format.raw/*133.2*/("""
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
                  HASH: 6f45e6e4cfb4795e993a4ad50d261edf3802681e
                  MATRIX: 846->1|1057->119|1084->121|1127->156|1166->158|1197->163|2385->1325|2401->1332|2435->1357|2486->1370|2522->1379|2571->1401|2599->1408|2641->1420|2674->1426|2726->1452|2741->1458|2819->1527|2859->1529|2896->1539|2911->1545|2947->1560|2984->1570|3148->1707|3169->1719|3210->1739|3495->1997|3534->1998|3588->2007|3730->2122|3769->2123|3822->2132|3963->2246|4002->2247|4056->2256|4192->2365|4231->2366|4284->2375|4420->2484|4459->2485|4512->2494|4788->2743|4815->2760|4861->2784|5153->3049|5192->3050|5245->3059|5391->3178|5430->3179|5484->3188|5623->3300|5662->3301|5715->3310|5854->3422|5893->3423|5946->3432|6267->3725|6298->3746|6341->3767|6608->4004|6641->4010|6709->4050|6738->4051|6774->4060|6831->4089|6860->4090|6889->4091|7009->4182|7039->4183|7081->4197|7110->4198|7139->4199|7253->4284|7283->4285|7325->4299|7354->4300|7383->4301|7497->4386|7527->4387|7569->4401|7598->4402|7627->4403|7736->4484|7765->4485|7807->4499|7836->4500|7865->4501|7990->4597|8020->4598|8062->4612|8091->4613|8120->4614|8255->4720|8285->4721|8327->4735|8356->4736|8385->4737|8491->4815|8520->4816|8562->4830|8591->4831|8620->4832|8732->4916|8762->4917|8798->4926|9210->5311|9238->5312|9270->5317|9359->5378|9400->5380|9437->5389|9505->5439|9545->5440|9582->5449|9962->5801|10006->5828|10047->5830|10097->5851|10159->5885|10176->5892|10201->5895|10264->5930|10281->5937|10308->5942|10371->5977|10388->5984|10419->5993|10483->6029|10534->6058|10597->6093|10941->6415|11013->6459|11065->6489|11155->6547|11197->6560|11259->6591|11292->6593
                  LINES: 22->1|27->2|28->3|28->3|28->3|29->4|53->28|53->28|53->28|53->28|54->29|54->29|54->29|55->30|57->32|58->33|58->33|58->33|58->33|59->34|59->34|59->34|61->36|63->38|63->38|63->38|69->44|69->44|69->44|70->45|70->45|70->45|71->46|71->46|71->46|72->47|72->47|72->47|73->48|73->48|73->48|79->54|79->54|79->54|85->60|85->60|85->60|86->61|86->61|86->61|87->62|87->62|87->62|88->63|88->63|88->63|94->69|94->69|94->69|101->76|103->78|104->79|104->79|105->80|106->81|106->81|106->81|106->81|106->81|107->82|107->82|107->82|107->82|107->82|108->83|108->83|108->83|108->83|108->83|109->84|109->84|109->84|109->84|109->84|110->85|110->85|110->85|110->85|110->85|111->86|111->86|111->86|111->86|111->86|112->87|112->87|112->87|112->87|112->87|113->88|113->88|113->88|113->88|113->88|114->89|121->96|121->96|122->97|125->100|125->100|126->101|127->102|127->102|128->103|140->115|140->115|140->115|141->116|142->117|142->117|142->117|143->118|143->118|143->118|144->119|144->119|144->119|145->120|145->120|146->121|151->126|152->127|152->127|154->129|155->130|157->132|158->133
                  -- GENERATED --
              */
          