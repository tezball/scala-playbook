
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
                <button type="button" class="btn-autofill btn-sm" onclick="autoFillCart()">Auto Fill</button>
            </div>
        </div>
    """)))}),format.raw/*61.6*/("""

    """),format.raw/*63.5*/("""<script>
    function autoFillCart() """),format.raw/*64.29*/("""{"""),format.raw/*64.30*/("""
        """),format.raw/*65.9*/("""var items = [
            """),format.raw/*66.13*/("""{"""),format.raw/*66.14*/(""" """),format.raw/*66.15*/("""name: 'Bluetooth Speaker', cat: 'Electronics', price: 59.99, qty: 1 """),format.raw/*66.83*/("""}"""),format.raw/*66.84*/(""",
            """),format.raw/*67.13*/("""{"""),format.raw/*67.14*/(""" """),format.raw/*67.15*/("""name: 'HDMI Cable 6ft', cat: 'Electronics', price: 14.99, qty: 2 """),format.raw/*67.80*/("""}"""),format.raw/*67.81*/(""",
            """),format.raw/*68.13*/("""{"""),format.raw/*68.14*/(""" """),format.raw/*68.15*/("""name: 'Design Patterns', cat: 'Books', price: 42.00, qty: 1 """),format.raw/*68.75*/("""}"""),format.raw/*68.76*/(""",
            """),format.raw/*69.13*/("""{"""),format.raw/*69.14*/(""" """),format.raw/*69.15*/("""name: 'Winter Jacket', cat: 'Clothing', price: 129.99, qty: 1 """),format.raw/*69.77*/("""}"""),format.raw/*69.78*/(""",
            """),format.raw/*70.13*/("""{"""),format.raw/*70.14*/(""" """),format.raw/*70.15*/("""name: 'Almond Butter', cat: 'Food', price: 11.49, qty: 3 """),format.raw/*70.72*/("""}"""),format.raw/*70.73*/(""",
            """),format.raw/*71.13*/("""{"""),format.raw/*71.14*/(""" """),format.raw/*71.15*/("""name: 'Resistance Bands Set', cat: 'Sports', price: 24.99, qty: 2 """),format.raw/*71.81*/("""}"""),format.raw/*71.82*/(""",
            """),format.raw/*72.13*/("""{"""),format.raw/*72.14*/(""" """),format.raw/*72.15*/("""name: 'Mechanical Keyboard', cat: 'Electronics', price: 89.00, qty: 1 """),format.raw/*72.85*/("""}"""),format.raw/*72.86*/(""",
            """),format.raw/*73.13*/("""{"""),format.raw/*73.14*/(""" """),format.raw/*73.15*/("""name: 'Trail Mix 1lb', cat: 'Food', price: 7.99, qty: 4 """),format.raw/*73.71*/("""}"""),format.raw/*73.72*/("""
        """),format.raw/*74.9*/("""];
        var i = items[Math.floor(Math.random() * items.length)];
        document.getElementById('name').value = i.name;
        document.getElementById('category').value = i.cat;
        document.getElementById('unitPrice').value = i.price;
        document.getElementById('quantity').value = i.qty;
    """),format.raw/*80.5*/("""}"""),format.raw/*80.6*/("""
    """),format.raw/*81.5*/("""</script>

    <h2>Cart Items (<code>map</code> &mdash; line totals)</h2>
    <table>
        <thead>
            <tr><th>Name</th><th>Category</th><th>Unit Price</th><th>Qty</th><th>Line Total</th></tr>
        </thead>
        <tbody>
            """),_display_(/*89.14*/for((item, lineTotal) <- _root_.cart.CartCalculator.lineTotals(items)) yield /*89.84*/ {_display_(Seq[Any](format.raw/*89.86*/("""
                """),format.raw/*90.17*/("""<tr>
                    <td>"""),_display_(/*91.26*/item/*91.30*/.name),format.raw/*91.35*/("""</td>
                    <td>"""),_display_(/*92.26*/item/*92.30*/.category),format.raw/*92.39*/("""</td>
                    <td>$"""),_display_(/*93.27*/{f"${item.unitPrice}%.2f"}),format.raw/*93.53*/("""</td>
                    <td>"""),_display_(/*94.26*/item/*94.30*/.quantity),format.raw/*94.39*/("""</td>
                    <td><strong>$"""),_display_(/*95.35*/{f"${lineTotal}%.2f"}),format.raw/*95.56*/("""</strong></td>
                </tr>
            """)))}),format.raw/*97.14*/("""
        """),format.raw/*98.9*/("""</tbody>
    </table>

    <h2>Collection Operations Results</h2>

    <div class="cards">
        <div class="card">
            <h3><code>foldLeft</code> &mdash; Grand Total</h3>
            <p style="font-size:1.5em"><strong>$"""),_display_(/*106.50*/{f"${_root_.cart.CartCalculator.grandTotal(items)}%.2f"}),format.raw/*106.106*/("""</strong></p>
        </div>
        <div class="card">
            <h3><code>distinctBy</code> &mdash; Unique Categories</h3>
            <p>"""),_display_(/*110.17*/{_root_.cart.CartCalculator.uniqueCategories(items).mkString(", ")}),format.raw/*110.84*/("""</p>
        </div>
    </div>

    <h3><code>groupBy</code> &mdash; Subtotals by Category</h3>
    <table>
        <thead><tr><th>Category</th><th>Subtotal</th></tr></thead>
        <tbody>
            """),_display_(/*118.14*/for((cat, subtotal) <- _root_.cart.CartCalculator.subtotalsByCategory(items).toSeq.sortBy(_._1)) yield /*118.110*/ {_display_(Seq[Any](format.raw/*118.112*/("""
                """),format.raw/*119.17*/("""<tr><td>"""),_display_(/*119.26*/cat),format.raw/*119.29*/("""</td><td><strong>$"""),_display_(/*119.48*/{f"${subtotal}%.2f"}),format.raw/*119.68*/("""</strong></td></tr>
            """)))}),format.raw/*120.14*/("""
        """),format.raw/*121.9*/("""</tbody>
    </table>

    <h3><code>filter</code> &mdash; Expensive Items (unit price &gt; $20)</h3>
    """),_display_(/*125.6*/defining(_root_.cart.CartCalculator.expensiveItems(items))/*125.64*/ { expensive =>_display_(Seq[Any](format.raw/*125.79*/("""
        """),_display_(if(expensive.isEmpty)/*126.31*/ {_display_(Seq[Any](format.raw/*126.33*/("""
            """),format.raw/*127.13*/("""<p>No items over $20.</p>
        """)))}else/*128.16*/{_display_(Seq[Any](format.raw/*128.17*/("""
            """),format.raw/*129.13*/("""<table>
                <thead><tr><th>Name</th><th>Unit Price</th></tr></thead>
                <tbody>
                    """),_display_(/*132.22*/for(item <- expensive) yield /*132.44*/ {_display_(Seq[Any](format.raw/*132.46*/("""
                        """),format.raw/*133.25*/("""<tr><td>"""),_display_(/*133.34*/item/*133.38*/.name),format.raw/*133.43*/("""</td><td>$"""),_display_(/*133.54*/{f"${item.unitPrice}%.2f"}),format.raw/*133.80*/("""</td></tr>
                    """)))}),format.raw/*134.22*/("""
                """),format.raw/*135.17*/("""</tbody>
            </table>
        """)))}),format.raw/*137.10*/("""
    """)))}),format.raw/*138.6*/("""

    """),format.raw/*140.5*/("""<h3><code>sortBy</code> &mdash; Sorted by Price (descending)</h3>
    <table>
        <thead><tr><th>Name</th><th>Unit Price</th><th>Category</th></tr></thead>
        <tbody>
            """),_display_(/*144.14*/for(item <- _root_.cart.CartCalculator.sortedByPrice(items)) yield /*144.74*/ {_display_(Seq[Any](format.raw/*144.76*/("""
                """),format.raw/*145.17*/("""<tr><td>"""),_display_(/*145.26*/item/*145.30*/.name),format.raw/*145.35*/("""</td><td>$"""),_display_(/*145.46*/{f"${item.unitPrice}%.2f"}),format.raw/*145.72*/("""</td><td>"""),_display_(/*145.82*/item/*145.86*/.category),format.raw/*145.95*/("""</td></tr>
            """)))}),format.raw/*146.14*/("""
        """),format.raw/*147.9*/("""</tbody>
    </table>

    <h3><code>collect</code> &mdash; Free Shipping Eligible (line total &gt; $50)</h3>
    """),_display_(/*151.6*/defining(_root_.cart.CartCalculator.freeShippingEligible(items))/*151.70*/ { eligible =>_display_(Seq[Any](format.raw/*151.84*/("""
        """),_display_(if(eligible.isEmpty)/*152.30*/ {_display_(Seq[Any](format.raw/*152.32*/("""
            """),format.raw/*153.13*/("""<p>No items qualify for free shipping.</p>
        """)))}else/*154.16*/{_display_(Seq[Any](format.raw/*154.17*/("""
            """),format.raw/*155.13*/("""<ul>
                """),_display_(/*156.18*/for(name <- eligible) yield /*156.39*/ {_display_(Seq[Any](format.raw/*156.41*/("""
                    """),format.raw/*157.21*/("""<li>"""),_display_(/*157.26*/name),format.raw/*157.30*/("""</li>
                """)))}),format.raw/*158.18*/("""
            """),format.raw/*159.13*/("""</ul>
        """)))}),format.raw/*160.10*/("""
    """)))}),format.raw/*161.6*/("""
""")))}),format.raw/*162.2*/("""
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
                  HASH: 883f224d3896aaa56e862f7c4b014ee37229c43d
                  MATRIX: 834->1|1033->107|1060->109|1104->145|1143->147|1174->152|1977->927|2006->928|2091->985|2120->986|2177->1017|2193->1024|2227->1049|2278->1062|2314->1071|2363->1093|2391->1100|2433->1112|2466->1118|2523->1149|2538->1155|2606->1214|2646->1216|2683->1226|2698->1232|2734->1247|2770->1256|2972->1431|2993->1443|3034->1463|3778->2180|3805->2197|3851->2221|4099->2442|4125->2458|4168->2479|4485->2766|4518->2772|4583->2809|4612->2810|4648->2819|4702->2845|4731->2846|4760->2847|4856->2915|4885->2916|4927->2930|4956->2931|4985->2932|5078->2997|5107->2998|5149->3012|5178->3013|5207->3014|5295->3074|5324->3075|5366->3089|5395->3090|5424->3091|5514->3153|5543->3154|5585->3168|5614->3169|5643->3170|5728->3227|5757->3228|5799->3242|5828->3243|5857->3244|5951->3310|5980->3311|6022->3325|6051->3326|6080->3327|6178->3397|6207->3398|6249->3412|6278->3413|6307->3414|6391->3470|6420->3471|6456->3480|6791->3788|6819->3789|6851->3794|7128->4044|7214->4114|7254->4116|7299->4133|7356->4163|7369->4167|7395->4172|7453->4203|7466->4207|7496->4216|7555->4248|7602->4274|7660->4305|7673->4309|7703->4318|7770->4358|7812->4379|7893->4429|7929->4438|8187->4668|8266->4724|8437->4867|8526->4934|8758->5138|8872->5234|8914->5236|8960->5253|8997->5262|9022->5265|9069->5284|9111->5304|9176->5337|9213->5346|9347->5453|9415->5511|9469->5526|9528->5557|9569->5559|9611->5572|9670->5613|9710->5614|9752->5627|9906->5753|9945->5775|9986->5777|10040->5802|10077->5811|10091->5815|10118->5820|10157->5831|10205->5857|10269->5889|10315->5906|10386->5945|10423->5951|10457->5957|10674->6146|10751->6206|10792->6208|10838->6225|10875->6234|10889->6238|10916->6243|10955->6254|11003->6280|11041->6290|11055->6294|11086->6303|11142->6327|11179->6336|11321->6451|11395->6515|11448->6529|11506->6559|11547->6561|11589->6574|11665->6632|11705->6633|11747->6646|11797->6668|11835->6689|11876->6691|11926->6712|11959->6717|11985->6721|12040->6744|12082->6757|12129->6772|12166->6778|12199->6780
                  LINES: 22->1|27->2|28->3|28->3|28->3|29->4|48->23|48->23|48->23|48->23|51->26|51->26|51->26|51->26|52->27|52->27|52->27|53->28|55->30|56->31|56->31|56->31|56->31|57->32|57->32|57->32|58->33|61->36|61->36|61->36|75->50|75->50|75->50|79->54|79->54|79->54|86->61|88->63|89->64|89->64|90->65|91->66|91->66|91->66|91->66|91->66|92->67|92->67|92->67|92->67|92->67|93->68|93->68|93->68|93->68|93->68|94->69|94->69|94->69|94->69|94->69|95->70|95->70|95->70|95->70|95->70|96->71|96->71|96->71|96->71|96->71|97->72|97->72|97->72|97->72|97->72|98->73|98->73|98->73|98->73|98->73|99->74|105->80|105->80|106->81|114->89|114->89|114->89|115->90|116->91|116->91|116->91|117->92|117->92|117->92|118->93|118->93|119->94|119->94|119->94|120->95|120->95|122->97|123->98|131->106|131->106|135->110|135->110|143->118|143->118|143->118|144->119|144->119|144->119|144->119|144->119|145->120|146->121|150->125|150->125|150->125|151->126|151->126|152->127|153->128|153->128|154->129|157->132|157->132|157->132|158->133|158->133|158->133|158->133|158->133|158->133|159->134|160->135|162->137|163->138|165->140|169->144|169->144|169->144|170->145|170->145|170->145|170->145|170->145|170->145|170->145|170->145|170->145|171->146|172->147|176->151|176->151|176->151|177->152|177->152|178->153|179->154|179->154|180->155|181->156|181->156|181->156|182->157|182->157|182->157|183->158|184->159|185->160|186->161|187->162
                  -- GENERATED --
              */
          