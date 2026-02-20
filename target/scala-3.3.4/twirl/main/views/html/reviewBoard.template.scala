
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

object reviewBoard extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Form[_root_.reviews.ReviewForm],Seq[_root_.reviews.Review],Seq[String],String,RequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(form: Form[_root_.reviews.ReviewForm], reviewList: Seq[_root_.reviews.Review], showOutput: Seq[String], currentSort: String)(implicit request: RequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),_display_(/*3.2*/main("Reviews - Traits + Type Classes")/*3.41*/ {_display_(Seq[Any](format.raw/*3.43*/("""
    """),format.raw/*4.5*/("""<h1>Product Reviews</h1>

    <div class="concept-banner">
        <h3>Advanced Concepts: Traits, Mixins, Type Classes (Ordering, Show), Givens</h3>
        <p>Reviews mix in <code>Timestamped</code>, <code>Rateable</code>, and <code>Authorable</code> traits. Sorting uses <code>given Ordering[Review]</code> instances, and rendering uses a custom <code>Show</code> type class.</p>
        <pre><code>// Composable traits
trait Timestamped:
  def createdAt: Instant
trait Rateable:
  def rating: Int
  def stars: String = "\u2605" * rating + "\u2606" * (5 - rating)
trait Authorable:
  def author: String

// Case class mixing in multiple traits
case class Review(...) extends Timestamped, Rateable, Authorable

// Custom Ordering instances (given = type class instances)
given byRating: Ordering[Review] = Ordering.by(-_.rating)
given byDate: Ordering[Review] = Ordering.by(_.createdAt)

// Custom type class: Show
trait Show[A]:
  def show(a: A): String
given Show[Review] with
  def show(r: Review) = s"$"""),format.raw/*29.28*/("""{"""),format.raw/*29.29*/("""r.stars"""),format.raw/*29.36*/("""}"""),format.raw/*29.37*/(""" """),format.raw/*29.38*/("""by $"""),format.raw/*29.42*/("""{"""),format.raw/*29.43*/("""r.author"""),format.raw/*29.51*/("""}"""),format.raw/*29.52*/(""": $"""),format.raw/*29.55*/("""{"""),format.raw/*29.56*/("""r.comment"""),format.raw/*29.65*/("""}"""),format.raw/*29.66*/(""""

def display[A](items: Seq[A])(using s: Show[A]): Seq[String] =
  items.map(s.show)</code></pre>
    </div>

    """),_display_(/*35.6*/request/*35.13*/.flash.get("success").map/*35.38*/ { message =>_display_(Seq[Any](format.raw/*35.51*/("""
        """),format.raw/*36.9*/("""<div class="success">"""),_display_(/*36.31*/message),format.raw/*36.38*/("""</div>
    """)))}),format.raw/*37.6*/("""

    """),format.raw/*39.5*/("""<h2>Write a Review</h2>
    """),_display_(/*40.6*/helper/*40.12*/.form(action = _root_.reviews.routes.ReviewController.addReview())/*40.78*/ {_display_(Seq[Any](format.raw/*40.80*/("""
        """),_display_(/*41.10*/helper/*41.16*/.CSRF.formField),format.raw/*41.31*/("""

        """),format.raw/*43.9*/("""<div class="form-group">
            <label for="productName">Product Name</label>
            <input type="text" id="productName" name="productName" value=""""),_display_(/*45.76*/form("productName")/*45.95*/.value.getOrElse("")),format.raw/*45.115*/("""" required>
        </div>

        <div class="form-group">
            <label for="author">Your Name</label>
            <input type="text" id="author" name="author" value=""""),_display_(/*50.66*/form("author")/*50.80*/.value.getOrElse("")),format.raw/*50.100*/("""" required>
        </div>

        <div class="form-group">
            <label for="rating">Rating (1-5)</label>
            <select id="rating" name="rating">
                """),_display_(/*56.18*/for(i <- 5 to 1 by -1) yield /*56.40*/ {_display_(Seq[Any](format.raw/*56.42*/("""
                    """),format.raw/*57.21*/("""<option value=""""),_display_(/*57.37*/i),format.raw/*57.38*/("""" """),_display_(if(form("rating").value.contains(i.toString))/*57.86*/{_display_(Seq[Any](format.raw/*57.87*/("""selected""")))} else {null} ),format.raw/*57.96*/(""">"""),_display_(/*57.98*/i),format.raw/*57.99*/(""" """),format.raw/*57.100*/("""- """),_display_(/*57.103*/{"\u2605" * i + "\u2606" * (5 - i)}),format.raw/*57.138*/("""</option>
                """)))}),format.raw/*58.18*/("""
            """),format.raw/*59.13*/("""</select>
        </div>

        <div class="form-group">
            <label for="comment">Comment</label>
            <textarea id="comment" name="comment" rows="2" required>"""),_display_(/*64.70*/form("comment")/*64.85*/.value.getOrElse("")),format.raw/*64.105*/("""</textarea>
        </div>

        <div class="btn-group">
            <button type="submit" class="btn-orange">Submit Review</button>
            <button type="button" class="btn-autofill" onclick="autoFillReview()">Auto Fill</button>
        </div>
    """)))}),format.raw/*71.6*/("""

    """),format.raw/*73.5*/("""<script>
    function autoFillReview() """),format.raw/*74.31*/("""{"""),format.raw/*74.32*/("""
        """),format.raw/*75.9*/("""var reviews = [
            """),format.raw/*76.13*/("""{"""),format.raw/*76.14*/(""" """),format.raw/*76.15*/("""product: 'MacBook Pro 16"', author: 'Alice Johnson', rating: 5, comment: 'Incredible performance for software development. The M3 chip handles sbt compilation like a breeze.' """),format.raw/*76.190*/("""}"""),format.raw/*76.191*/(""",
            """),format.raw/*77.13*/("""{"""),format.raw/*77.14*/(""" """),format.raw/*77.15*/("""product: 'Wireless Mouse', author: 'Bob Martinez', rating: 3, comment: 'Decent mouse but the scroll wheel started squeaking after a month. Battery life is good though.' """),format.raw/*77.184*/("""}"""),format.raw/*77.185*/(""",
            """),format.raw/*78.13*/("""{"""),format.raw/*78.14*/(""" """),format.raw/*78.15*/("""product: 'Standing Desk', author: 'Carol Chen', rating: 4, comment: 'Sturdy build quality and smooth height adjustment. Cable management could be better.' """),format.raw/*78.170*/("""}"""),format.raw/*78.171*/(""",
            """),format.raw/*79.13*/("""{"""),format.raw/*79.14*/(""" """),format.raw/*79.15*/("""product: 'Noise-Cancelling Headphones', author: 'David Kim', rating: 5, comment: 'Best headphones I have ever owned. The noise cancellation is perfect for open offices.' """),format.raw/*79.185*/("""}"""),format.raw/*79.186*/(""",
            """),format.raw/*80.13*/("""{"""),format.raw/*80.14*/(""" """),format.raw/*80.15*/("""product: 'Ergonomic Chair', author: 'Eva Rossi', rating: 2, comment: 'Lumbar support is uncomfortable and the armrests wobble. Not worth the premium price.' """),format.raw/*80.172*/("""}"""),format.raw/*80.173*/(""",
            """),format.raw/*81.13*/("""{"""),format.raw/*81.14*/(""" """),format.raw/*81.15*/("""product: 'Mechanical Keyboard', author: 'Frank Okafor', rating: 4, comment: 'Great tactile feedback with Cherry MX Browns. A bit loud for shared workspaces.' """),format.raw/*81.173*/("""}"""),format.raw/*81.174*/(""",
            """),format.raw/*82.13*/("""{"""),format.raw/*82.14*/(""" """),format.raw/*82.15*/("""product: 'USB-C Hub', author: 'Grace Liu', rating: 1, comment: 'Stopped working after two weeks. The HDMI port never detected my external monitor reliably.' """),format.raw/*82.172*/("""}"""),format.raw/*82.173*/(""",
            """),format.raw/*83.13*/("""{"""),format.raw/*83.14*/(""" """),format.raw/*83.15*/("""product: '27" 4K Monitor', author: 'Henry Park', rating: 5, comment: 'Crystal clear text rendering makes code so much easier to read. Colors are accurate out of the box.' """),format.raw/*83.186*/("""}"""),format.raw/*83.187*/("""
        """),format.raw/*84.9*/("""];
        var r = reviews[Math.floor(Math.random() * reviews.length)];
        document.getElementById('productName').value = r.product;
        document.getElementById('author').value = r.author;
        document.getElementById('rating').value = r.rating;
        document.getElementById('comment').value = r.comment;
    """),format.raw/*90.5*/("""}"""),format.raw/*90.6*/("""
    """),format.raw/*91.5*/("""</script>

    <h2>Reviews (sorted by <code>given Ordering</code>)</h2>
    <p>
        Sort by:
        <a href="?sort=rating" """),_display_(if(currentSort == "rating")/*96.60*/{_display_(Seq[Any](format.raw/*96.61*/(""" """),format.raw/*96.62*/("""style="font-weight:bold" """)))} else {null} ),format.raw/*96.88*/(""">Rating</a> |
        <a href="?sort=date" """),_display_(if(currentSort == "date")/*97.56*/{_display_(Seq[Any](format.raw/*97.57*/(""" """),format.raw/*97.58*/("""style="font-weight:bold" """)))} else {null} ),format.raw/*97.84*/(""">Date</a> |
        <a href="?sort=author" """),_display_(if(currentSort == "author")/*98.60*/{_display_(Seq[Any](format.raw/*98.61*/(""" """),format.raw/*98.62*/("""style="font-weight:bold" """)))} else {null} ),format.raw/*98.88*/(""">Author</a>
    </p>

    """),_display_(if(reviewList.isEmpty)/*101.28*/ {_display_(Seq[Any](format.raw/*101.30*/("""
        """),format.raw/*102.9*/("""<p>No reviews yet. Be the first!</p>
    """)))}else/*103.12*/{_display_(Seq[Any](format.raw/*103.13*/("""
        """),format.raw/*104.9*/("""<table>
            <thead><tr><th>Stars</th><th>Product</th><th>Author</th><th>Comment</th><th>Date</th></tr></thead>
            <tbody>
                """),_display_(/*107.18*/for(review <- reviewList) yield /*107.43*/ {_display_(Seq[Any](format.raw/*107.45*/("""
                    """),format.raw/*108.21*/("""<tr>
                        <td>"""),_display_(/*109.30*/review/*109.36*/.stars),format.raw/*109.42*/("""</td>
                        <td>"""),_display_(/*110.30*/review/*110.36*/.productName),format.raw/*110.48*/("""</td>
                        <td>"""),_display_(/*111.30*/review/*111.36*/.author),format.raw/*111.43*/("""</td>
                        <td>"""),_display_(/*112.30*/review/*112.36*/.comment),format.raw/*112.44*/("""</td>
                        <td>"""),_display_(/*113.30*/review/*113.36*/.createdAt),format.raw/*113.46*/("""</td>
                    </tr>
                """)))}),format.raw/*115.18*/("""
            """),format.raw/*116.13*/("""</tbody>
        </table>
    """)))}),format.raw/*118.6*/("""

    """),format.raw/*120.5*/("""<h2><code>Show[Review]</code> Type Class Output</h2>
    """),_display_(if(showOutput.isEmpty)/*121.28*/ {_display_(Seq[Any](format.raw/*121.30*/("""
        """),format.raw/*122.9*/("""<p>No reviews to display.</p>
    """)))}else/*123.12*/{_display_(Seq[Any](format.raw/*123.13*/("""
        """),format.raw/*124.9*/("""<ul>
            """),_display_(/*125.14*/for(line <- showOutput) yield /*125.37*/ {_display_(Seq[Any](format.raw/*125.39*/("""
                """),format.raw/*126.17*/("""<li><code>"""),_display_(/*126.28*/line),format.raw/*126.32*/("""</code></li>
            """)))}),format.raw/*127.14*/("""
        """),format.raw/*128.9*/("""</ul>
    """)))}),format.raw/*129.6*/("""
""")))}),format.raw/*130.2*/("""
"""))
      }
    }
  }

  def render(form:Form[_root_.reviews.ReviewForm],reviewList:Seq[_root_.reviews.Review],showOutput:Seq[String],currentSort:String,request:RequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(form,reviewList,showOutput,currentSort)(request)

  def f:((Form[_root_.reviews.ReviewForm],Seq[_root_.reviews.Review],Seq[String],String) => (RequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (form,reviewList,showOutput,currentSort) => (request) => apply(form,reviewList,showOutput,currentSort)(request)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/reviewBoard.scala.html
                  HASH: 23a37258194581b9517b8ddc96efe06c9a332a4e
                  MATRIX: 858->1|1110->160|1137->162|1184->201|1223->203|1254->208|2289->1215|2318->1216|2353->1223|2382->1224|2411->1225|2443->1229|2472->1230|2508->1238|2537->1239|2568->1242|2597->1243|2634->1252|2663->1253|2805->1369|2821->1376|2855->1401|2906->1414|2942->1423|2991->1445|3019->1452|3061->1464|3094->1470|3149->1499|3164->1505|3239->1571|3279->1573|3316->1583|3331->1589|3367->1604|3404->1614|3589->1772|3617->1791|3659->1811|3862->1987|3885->2001|3927->2021|4132->2199|4170->2221|4210->2223|4259->2244|4302->2260|4324->2261|4399->2309|4438->2310|4491->2319|4520->2321|4542->2322|4572->2323|4603->2326|4660->2361|4718->2388|4759->2401|4963->2578|4987->2593|5029->2613|5316->2870|5349->2876|5416->2915|5445->2916|5481->2925|5537->2953|5566->2954|5595->2955|5799->3130|5829->3131|5871->3145|5900->3146|5929->3147|6127->3316|6157->3317|6199->3331|6228->3332|6257->3333|6441->3488|6471->3489|6513->3503|6542->3504|6571->3505|6770->3675|6800->3676|6842->3690|6871->3691|6900->3692|7086->3849|7116->3850|7158->3864|7187->3865|7216->3866|7403->4024|7433->4025|7475->4039|7504->4040|7533->4041|7719->4198|7749->4199|7791->4213|7820->4214|7849->4215|8049->4386|8079->4387|8115->4396|8466->4720|8494->4721|8526->4726|8709->4882|8748->4883|8777->4884|8847->4910|8943->4979|8982->4980|9011->4981|9081->5007|9179->5078|9218->5079|9247->5080|9317->5106|9394->5155|9435->5157|9472->5166|9538->5214|9578->5215|9615->5224|9799->5380|9841->5405|9882->5407|9932->5428|9994->5462|10010->5468|10038->5474|10101->5509|10117->5515|10151->5527|10214->5562|10230->5568|10259->5575|10322->5610|10338->5616|10368->5624|10431->5659|10447->5665|10479->5675|10560->5724|10602->5737|10664->5768|10698->5774|10806->5854|10847->5856|10884->5865|10943->5906|10983->5907|11020->5916|11066->5934|11106->5957|11147->5959|11193->5976|11232->5987|11258->5991|11316->6017|11353->6026|11395->6037|11428->6039
                  LINES: 22->1|27->2|28->3|28->3|28->3|29->4|54->29|54->29|54->29|54->29|54->29|54->29|54->29|54->29|54->29|54->29|54->29|54->29|54->29|60->35|60->35|60->35|60->35|61->36|61->36|61->36|62->37|64->39|65->40|65->40|65->40|65->40|66->41|66->41|66->41|68->43|70->45|70->45|70->45|75->50|75->50|75->50|81->56|81->56|81->56|82->57|82->57|82->57|82->57|82->57|82->57|82->57|82->57|82->57|82->57|82->57|83->58|84->59|89->64|89->64|89->64|96->71|98->73|99->74|99->74|100->75|101->76|101->76|101->76|101->76|101->76|102->77|102->77|102->77|102->77|102->77|103->78|103->78|103->78|103->78|103->78|104->79|104->79|104->79|104->79|104->79|105->80|105->80|105->80|105->80|105->80|106->81|106->81|106->81|106->81|106->81|107->82|107->82|107->82|107->82|107->82|108->83|108->83|108->83|108->83|108->83|109->84|115->90|115->90|116->91|121->96|121->96|121->96|121->96|122->97|122->97|122->97|122->97|123->98|123->98|123->98|123->98|126->101|126->101|127->102|128->103|128->103|129->104|132->107|132->107|132->107|133->108|134->109|134->109|134->109|135->110|135->110|135->110|136->111|136->111|136->111|137->112|137->112|137->112|138->113|138->113|138->113|140->115|141->116|143->118|145->120|146->121|146->121|147->122|148->123|148->123|149->124|150->125|150->125|150->125|151->126|151->126|151->126|152->127|153->128|154->129|155->130
                  -- GENERATED --
              */
          