
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

object cartDemo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[Form[_root_.cart.CartItemForm],Seq[_root_.cart.CartItem],RequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(form: Form[_root_.cart.CartItemForm], items: Seq[_root_.cart.CartItem])(implicit request: RequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),_display_(/*3.2*/main("Cart - Collection Operations")/*3.38*/ {_display_(Seq[Any](format.raw/*3.40*/("""
    """),format.raw/*4.5*/("""<h1>Shopping Cart</h1>

    <div class="concept-banner">
        <h3>Concepts: Collection Operations &mdash; map, filter, foldLeft, groupBy, sortBy, collect</h3>
        <p>All cart calculations are performed using Scala collection methods. Each result below shows which method produced it.</p>
        <pre><code>// foldLeft - grand total
items.foldLeft(0.0)((total, item) =&gt; total + item.unitPrice * item.quantity)

// groupBy + map - subtotals by category
items.groupBy(_.category).map((cat, items) =&gt;
  cat -&gt; items.map(i =&gt; i.unitPrice * i.quantity).sum)

// filter - expensive items
items.filter(_.unitPrice &gt; 20.0)

// sortBy - sort by price descending
items.sortBy(-_.unitPrice)

// collect - free shipping eligible (line total &gt; $50)
items.collect """),format.raw/*23.15*/("""{"""),format.raw/*23.16*/(""" case i if i.unitPrice * i.quantity &gt; 50 =&gt; i.name """),format.raw/*23.73*/("""}"""),format.raw/*23.74*/("""</code></pre>
    </div>

    """),_display_(/*26.6*/request/*26.13*/.flash.get("success").map/*26.38*/ { message =>_display_(Seq[Any](format.raw/*26.51*/("""
        """),format.raw/*27.9*/("""<div class="success">"""),_display_(/*27.31*/message),format.raw/*27.38*/("""</div>
    """)))}),format.raw/*28.6*/("""

    """),format.raw/*30.5*/("""<h2>Add Item to Cart</h2>
    """),_display_(/*31.6*/helper/*31.12*/.form(action = _root_.cart.routes.CartController.addItem())/*31.71*/ {_display_(Seq[Any](format.raw/*31.73*/("""
        """),_display_(/*32.10*/helper/*32.16*/.CSRF.formField),format.raw/*32.31*/("""
        """),format.raw/*33.9*/("""<div class="inline-form">
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" id="name" name="name" value=""""),_display_(/*36.66*/form("name")/*36.78*/.value.getOrElse("")),format.raw/*36.98*/("""" required>
            </div>
            <div class="form-group">
                <label for="category">Category</label>
                <select id="category" name="category">
                    <option value="Electronics">Electronics</option>
                    <option value="Books">Books</option>
                    <option value="Clothing">Clothing</option>
                    <option value="Food">Food</option>
                    <option value="Sports">Sports</option>
                </select>
            </div>
            <div class="form-group">
                <label for="unitPrice">Price ($)</label>
                <input type="number" id="unitPrice" name="unitPrice" step="0.01" min="0" value=""""),_display_(/*50.98*/form("unitPrice")/*50.115*/.value.getOrElse("0.00")),format.raw/*50.139*/("""" required style="width:100px">
            </div>
            <div class="form-group">
                <label for="quantity">Qty</label>
                <input type="number" id="quantity" name="quantity" min="1" value=""""),_display_(/*54.84*/form("quantity")/*54.100*/.value.getOrElse("1")),format.raw/*54.121*/("""" required style="width:70px">
            </div>
            <div class="form-group">
                <button type="submit">Add</button>
            </div>
        </div>
    """)))}),format.raw/*60.6*/("""

    """),format.raw/*62.5*/("""<h2>Cart Items (<code>map</code> &mdash; line totals)</h2>
    <table>
        <thead>
            <tr><th>Name</th><th>Category</th><th>Unit Price</th><th>Qty</th><th>Line Total</th></tr>
        </thead>
        <tbody>
            """),_display_(/*68.14*/for((item, lineTotal) <- _root_.cart.CartCalculator.lineTotals(items)) yield /*68.84*/ {_display_(Seq[Any](format.raw/*68.86*/("""
                """),format.raw/*69.17*/("""<tr>
                    <td>"""),_display_(/*70.26*/item/*70.30*/.name),format.raw/*70.35*/("""</td>
                    <td>"""),_display_(/*71.26*/item/*71.30*/.category),format.raw/*71.39*/("""</td>
                    <td>$"""),_display_(/*72.27*/{f"${item.unitPrice}%.2f"}),format.raw/*72.53*/("""</td>
                    <td>"""),_display_(/*73.26*/item/*73.30*/.quantity),format.raw/*73.39*/("""</td>
                    <td><strong>$"""),_display_(/*74.35*/{f"${lineTotal}%.2f"}),format.raw/*74.56*/("""</strong></td>
                </tr>
            """)))}),format.raw/*76.14*/("""
        """),format.raw/*77.9*/("""</tbody>
    </table>

    <h2>Collection Operations Results</h2>

    <div class="cards">
        <div class="card">
            <h3><code>foldLeft</code> &mdash; Grand Total</h3>
            <p style="font-size:1.5em"><strong>$"""),_display_(/*85.50*/{f"${_root_.cart.CartCalculator.grandTotal(items)}%.2f"}),format.raw/*85.106*/("""</strong></p>
        </div>
        <div class="card">
            <h3><code>distinctBy</code> &mdash; Unique Categories</h3>
            <p>"""),_display_(/*89.17*/{_root_.cart.CartCalculator.uniqueCategories(items).mkString(", ")}),format.raw/*89.84*/("""</p>
        </div>
    </div>

    <h3><code>groupBy</code> &mdash; Subtotals by Category</h3>
    <table>
        <thead><tr><th>Category</th><th>Subtotal</th></tr></thead>
        <tbody>
            """),_display_(/*97.14*/for((cat, subtotal) <- _root_.cart.CartCalculator.subtotalsByCategory(items).toSeq.sortBy(_._1)) yield /*97.110*/ {_display_(Seq[Any](format.raw/*97.112*/("""
                """),format.raw/*98.17*/("""<tr><td>"""),_display_(/*98.26*/cat),format.raw/*98.29*/("""</td><td><strong>$"""),_display_(/*98.48*/{f"${subtotal}%.2f"}),format.raw/*98.68*/("""</strong></td></tr>
            """)))}),format.raw/*99.14*/("""
        """),format.raw/*100.9*/("""</tbody>
    </table>

    <h3><code>filter</code> &mdash; Expensive Items (unit price &gt; $20)</h3>
    """),_display_(/*104.6*/defining(_root_.cart.CartCalculator.expensiveItems(items))/*104.64*/ { expensive =>_display_(Seq[Any](format.raw/*104.79*/("""
        """),_display_(if(expensive.isEmpty)/*105.31*/ {_display_(Seq[Any](format.raw/*105.33*/("""
            """),format.raw/*106.13*/("""<p>No items over $20.</p>
        """)))}else/*107.16*/{_display_(Seq[Any](format.raw/*107.17*/("""
            """),format.raw/*108.13*/("""<table>
                <thead><tr><th>Name</th><th>Unit Price</th></tr></thead>
                <tbody>
                    """),_display_(/*111.22*/for(item <- expensive) yield /*111.44*/ {_display_(Seq[Any](format.raw/*111.46*/("""
                        """),format.raw/*112.25*/("""<tr><td>"""),_display_(/*112.34*/item/*112.38*/.name),format.raw/*112.43*/("""</td><td>$"""),_display_(/*112.54*/{f"${item.unitPrice}%.2f"}),format.raw/*112.80*/("""</td></tr>
                    """)))}),format.raw/*113.22*/("""
                """),format.raw/*114.17*/("""</tbody>
            </table>
        """)))}),format.raw/*116.10*/("""
    """)))}),format.raw/*117.6*/("""

    """),format.raw/*119.5*/("""<h3><code>sortBy</code> &mdash; Sorted by Price (descending)</h3>
    <table>
        <thead><tr><th>Name</th><th>Unit Price</th><th>Category</th></tr></thead>
        <tbody>
            """),_display_(/*123.14*/for(item <- _root_.cart.CartCalculator.sortedByPrice(items)) yield /*123.74*/ {_display_(Seq[Any](format.raw/*123.76*/("""
                """),format.raw/*124.17*/("""<tr><td>"""),_display_(/*124.26*/item/*124.30*/.name),format.raw/*124.35*/("""</td><td>$"""),_display_(/*124.46*/{f"${item.unitPrice}%.2f"}),format.raw/*124.72*/("""</td><td>"""),_display_(/*124.82*/item/*124.86*/.category),format.raw/*124.95*/("""</td></tr>
            """)))}),format.raw/*125.14*/("""
        """),format.raw/*126.9*/("""</tbody>
    </table>

    <h3><code>collect</code> &mdash; Free Shipping Eligible (line total &gt; $50)</h3>
    """),_display_(/*130.6*/defining(_root_.cart.CartCalculator.freeShippingEligible(items))/*130.70*/ { eligible =>_display_(Seq[Any](format.raw/*130.84*/("""
        """),_display_(if(eligible.isEmpty)/*131.30*/ {_display_(Seq[Any](format.raw/*131.32*/("""
            """),format.raw/*132.13*/("""<p>No items qualify for free shipping.</p>
        """)))}else/*133.16*/{_display_(Seq[Any](format.raw/*133.17*/("""
            """),format.raw/*134.13*/("""<ul>
                """),_display_(/*135.18*/for(name <- eligible) yield /*135.39*/ {_display_(Seq[Any](format.raw/*135.41*/("""
                    """),format.raw/*136.21*/("""<li>"""),_display_(/*136.26*/name),format.raw/*136.30*/("""</li>
                """)))}),format.raw/*137.18*/("""
            """),format.raw/*138.13*/("""</ul>
        """)))}),format.raw/*139.10*/("""
    """)))}),format.raw/*140.6*/("""
""")))}),format.raw/*141.2*/("""
"""))
      }
    }
  }

  def render(form:Form[_root_.cart.CartItemForm],items:Seq[_root_.cart.CartItem],request:RequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(form,items)(request)

  def f:((Form[_root_.cart.CartItemForm],Seq[_root_.cart.CartItem]) => (RequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (form,items) => (request) => apply(form,items)(request)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/cartDemo.scala.html
                  HASH: b437940f6ab25e68f295202b98279218314b6cd6
                  MATRIX: 834->1|1033->107|1060->109|1104->145|1143->147|1174->152|1977->927|2006->928|2091->985|2120->986|2177->1017|2193->1024|2227->1049|2278->1062|2314->1071|2363->1093|2391->1100|2433->1112|2466->1118|2523->1149|2538->1155|2606->1214|2646->1216|2683->1226|2698->1232|2734->1247|2770->1256|2972->1431|2993->1443|3034->1463|3778->2180|3805->2197|3851->2221|4099->2442|4125->2458|4168->2479|4375->2656|4408->2662|4670->2897|4756->2967|4796->2969|4841->2986|4898->3016|4911->3020|4937->3025|4995->3056|5008->3060|5038->3069|5097->3101|5144->3127|5202->3158|5215->3162|5245->3171|5312->3211|5354->3232|5435->3282|5471->3291|5728->3521|5806->3577|5976->3720|6064->3787|6295->3991|6408->4087|6449->4089|6494->4106|6530->4115|6554->4118|6600->4137|6641->4157|6705->4190|6742->4199|6876->4306|6944->4364|6998->4379|7057->4410|7098->4412|7140->4425|7199->4466|7239->4467|7281->4480|7435->4606|7474->4628|7515->4630|7569->4655|7606->4664|7620->4668|7647->4673|7686->4684|7734->4710|7798->4742|7844->4759|7915->4798|7952->4804|7986->4810|8203->4999|8280->5059|8321->5061|8367->5078|8404->5087|8418->5091|8445->5096|8484->5107|8532->5133|8570->5143|8584->5147|8615->5156|8671->5180|8708->5189|8850->5304|8924->5368|8977->5382|9035->5412|9076->5414|9118->5427|9194->5485|9234->5486|9276->5499|9326->5521|9364->5542|9405->5544|9455->5565|9488->5570|9514->5574|9569->5597|9611->5610|9658->5625|9695->5631|9728->5633
                  LINES: 22->1|27->2|28->3|28->3|28->3|29->4|48->23|48->23|48->23|48->23|51->26|51->26|51->26|51->26|52->27|52->27|52->27|53->28|55->30|56->31|56->31|56->31|56->31|57->32|57->32|57->32|58->33|61->36|61->36|61->36|75->50|75->50|75->50|79->54|79->54|79->54|85->60|87->62|93->68|93->68|93->68|94->69|95->70|95->70|95->70|96->71|96->71|96->71|97->72|97->72|98->73|98->73|98->73|99->74|99->74|101->76|102->77|110->85|110->85|114->89|114->89|122->97|122->97|122->97|123->98|123->98|123->98|123->98|123->98|124->99|125->100|129->104|129->104|129->104|130->105|130->105|131->106|132->107|132->107|133->108|136->111|136->111|136->111|137->112|137->112|137->112|137->112|137->112|137->112|138->113|139->114|141->116|142->117|144->119|148->123|148->123|148->123|149->124|149->124|149->124|149->124|149->124|149->124|149->124|149->124|149->124|150->125|151->126|155->130|155->130|155->130|156->131|156->131|157->132|158->133|158->133|159->134|160->135|160->135|160->135|161->136|161->136|161->136|162->137|163->138|164->139|165->140|166->141
                  -- GENERATED --
              */
          