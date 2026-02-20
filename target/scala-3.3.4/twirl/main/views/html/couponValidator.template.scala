
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

object couponValidator extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Form[_root_.coupons.CouponForm],Seq[_root_.coupons.Coupon],Option[_root_.coupons.ValidationResult],RequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(form: Form[_root_.coupons.CouponForm], couponList: Seq[_root_.coupons.Coupon], result: Option[_root_.coupons.ValidationResult])(implicit request: RequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),_display_(/*3.2*/main("Coupons - Error Handling")/*3.34*/ {_display_(Seq[Any](format.raw/*3.36*/("""
    """),format.raw/*4.5*/("""<h1>Coupon Validator</h1>

    <div class="concept-banner">
        <h3>Concepts: Error Handling &mdash; Option, Either, Try, for-comprehensions</h3>
        <p>Coupon validation follows a pipeline: <strong>Try</strong> (parse input) &rarr; <strong>Option</strong> (DB lookup) &rarr; <strong>Either</strong> (business rule validation). Each step can fail differently, and Scala's types make the failure modes explicit.</p>
        <pre><code>// Step 1: Try - parse the input (could throw)
def parseCode(raw: String): Try[String] =
  Try """),format.raw/*11.7*/("""{"""),format.raw/*11.8*/(""" """),format.raw/*11.9*/("""require(raw.nonEmpty, "Code cannot be empty"); raw.trim.toUpperCase """),format.raw/*11.77*/("""}"""),format.raw/*11.78*/("""

"""),format.raw/*13.1*/("""// Step 2: Option - look up in database (may not exist)
def findCoupon(code: String): Future[Option[Coupon]]

// Step 3: Either - validate business rules
def validateCoupon(coupon: Coupon): Either[String, Coupon] =
  if coupon.isExpired then Left("Coupon expired")
  else if coupon.usesRemaining &lt;= 0 then Left("No uses remaining")
  else Right(coupon)</code></pre>
    </div>

    <h2>Known Coupon Codes</h2>
    <p>Try these codes to see different validation outcomes:</p>
    <table>
        <thead>
            <tr><th>Code</th><th>Discount</th><th>Expires</th><th>Uses Left</th><th>Min Order</th></tr>
        </thead>
        <tbody>
            """),_display_(/*30.14*/for(coupon <- couponList) yield /*30.39*/ {_display_(Seq[Any](format.raw/*30.41*/("""
                """),format.raw/*31.17*/("""<tr>
                    <td><code>"""),_display_(/*32.32*/coupon/*32.38*/.code),format.raw/*32.43*/("""</code></td>
                    <td>"""),_display_(/*33.26*/{coupon.discountPercent}),format.raw/*33.50*/("""%</td>
                    <td>"""),_display_(/*34.26*/coupon/*34.32*/.expiresAt),format.raw/*34.42*/("""</td>
                    <td>"""),_display_(/*35.26*/coupon/*35.32*/.usesRemaining),format.raw/*35.46*/("""</td>
                    <td>$"""),_display_(/*36.27*/{f"${coupon.minimumOrder}%.2f"}),format.raw/*36.58*/("""</td>
                </tr>
            """)))}),format.raw/*38.14*/("""
        """),format.raw/*39.9*/("""</tbody>
    </table>

    <h2>Validate a Coupon</h2>
    """),_display_(/*43.6*/helper/*43.12*/.form(action = _root_.coupons.routes.CouponController.validateCoupon())/*43.83*/ {_display_(Seq[Any](format.raw/*43.85*/("""
        """),_display_(/*44.10*/helper/*44.16*/.CSRF.formField),format.raw/*44.31*/("""
        """),format.raw/*45.9*/("""<div class="inline-form">
            <div class="form-group">
                <label for="code">Coupon Code</label>
                <input type="text" id="code" name="code" value=""""),_display_(/*48.66*/form("code")/*48.78*/.value.getOrElse("")),format.raw/*48.98*/("""" placeholder="e.g. SAVE10" required>
            </div>
            <div class="form-group">
                <label for="orderTotal">Order Total ($)</label>
                <input type="number" id="orderTotal" name="orderTotal" step="0.01" min="0" value=""""),_display_(/*52.100*/form("orderTotal")/*52.118*/.value.getOrElse("100.00")),format.raw/*52.144*/("""" required style="width:120px">
            </div>
            <div class="form-group">
                <button type="submit" class="btn-blue">Validate</button>
            </div>
        </div>
    """)))}),format.raw/*58.6*/("""

    """),_display_(/*60.6*/result/*60.12*/.map/*60.16*/ { res =>_display_(Seq[Any](format.raw/*60.25*/("""
        """),format.raw/*61.9*/("""<h2>Validation Pipeline Results</h2>

        """),_display_(/*63.10*/for(step <- res.steps) yield /*63.32*/ {_display_(Seq[Any](format.raw/*63.34*/("""
            """),format.raw/*64.13*/("""<div class="step """),_display_(if(step.passed)/*64.46*/{_display_(Seq[Any](format.raw/*64.47*/("""success-step""")))}else/*64.64*/{_display_(Seq[Any](format.raw/*64.65*/("""error-step""")))}),format.raw/*64.76*/("""">
                <strong>Step: """),_display_(/*65.32*/step/*65.36*/.name),format.raw/*65.41*/("""</strong> (<code>"""),_display_(/*65.59*/step/*65.63*/.concept),format.raw/*65.71*/("""</code>)
                <br>
                """),_display_(if(step.passed)/*67.33*/ {_display_(Seq[Any](format.raw/*67.35*/("""
                    """),format.raw/*68.21*/("""&#x2705; """),_display_(/*68.31*/step/*68.35*/.detail),format.raw/*68.42*/("""
                """)))}else/*69.24*/{_display_(Seq[Any](format.raw/*69.25*/("""
                    """),format.raw/*70.21*/("""&#x274C; """),_display_(/*70.31*/step/*70.35*/.detail),format.raw/*70.42*/("""
                """)))}),format.raw/*71.18*/("""
            """),format.raw/*72.13*/("""</div>
        """)))}),format.raw/*73.10*/("""

        """),format.raw/*75.9*/("""<div class="card" style="margin-top:16px">
            """),_display_(/*76.14*/res/*76.17*/.discount/*76.26*/ match/*76.32*/ {/*77.17*/case Some(amount) =>/*77.37*/ {_display_(Seq[Any](format.raw/*77.39*/("""
                    """),format.raw/*78.21*/("""<h3 style="color:#4CAF50">Coupon Valid!</h3>
                    <p>Discount amount: <strong>$"""),_display_(/*79.51*/{f"${amount}%.2f"}),format.raw/*79.69*/("""</strong></p>
                """)))}/*81.17*/case None =>/*81.29*/ {_display_(Seq[Any](format.raw/*81.31*/("""
                    """),format.raw/*82.21*/("""<h3 style="color:#f44336">Coupon Invalid</h3>
                    <p>The coupon could not be applied. See the pipeline steps above for details.</p>
                """)))}}),format.raw/*85.14*/("""
        """),format.raw/*86.9*/("""</div>
    """)))}),format.raw/*87.6*/("""
""")))}),format.raw/*88.2*/("""
"""))
      }
    }
  }

  def render(form:Form[_root_.coupons.CouponForm],couponList:Seq[_root_.coupons.Coupon],result:Option[_root_.coupons.ValidationResult],request:RequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(form,couponList,result)(request)

  def f:((Form[_root_.coupons.CouponForm],Seq[_root_.coupons.Coupon],Option[_root_.coupons.ValidationResult]) => (RequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (form,couponList,result) => (request) => apply(form,couponList,result)(request)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/couponValidator.scala.html
                  HASH: c692304b714f1ebacc9f1ec206fe78ad98203de7
                  MATRIX: 883->1|1138->163|1165->165|1205->197|1244->199|1275->204|1839->741|1867->742|1895->743|1991->811|2020->812|2049->814|2732->1470|2773->1495|2813->1497|2858->1514|2921->1550|2936->1556|2962->1561|3027->1599|3072->1623|3131->1655|3146->1661|3177->1671|3235->1702|3250->1708|3285->1722|3344->1754|3396->1785|3468->1826|3504->1835|3589->1894|3604->1900|3684->1971|3724->1973|3761->1983|3776->1989|3812->2004|3848->2013|4057->2195|4078->2207|4119->2227|4404->2484|4432->2502|4480->2528|4710->2728|4743->2735|4758->2741|4771->2745|4818->2754|4854->2763|4928->2810|4966->2832|5006->2834|5047->2847|5107->2880|5146->2881|5182->2898|5221->2899|5263->2910|5324->2944|5337->2948|5363->2953|5408->2971|5421->2975|5450->2983|5539->3045|5579->3047|5628->3068|5665->3078|5678->3082|5706->3089|5747->3113|5786->3114|5835->3135|5872->3145|5885->3149|5913->3156|5962->3174|6003->3187|6050->3203|6087->3213|6170->3269|6182->3272|6200->3281|6215->3287|6226->3306|6255->3326|6295->3328|6344->3349|6466->3444|6505->3462|6555->3510|6576->3522|6616->3524|6665->3545|6862->3724|6898->3733|6940->3745|6972->3747
                  LINES: 22->1|27->2|28->3|28->3|28->3|29->4|36->11|36->11|36->11|36->11|36->11|38->13|55->30|55->30|55->30|56->31|57->32|57->32|57->32|58->33|58->33|59->34|59->34|59->34|60->35|60->35|60->35|61->36|61->36|63->38|64->39|68->43|68->43|68->43|68->43|69->44|69->44|69->44|70->45|73->48|73->48|73->48|77->52|77->52|77->52|83->58|85->60|85->60|85->60|85->60|86->61|88->63|88->63|88->63|89->64|89->64|89->64|89->64|89->64|89->64|90->65|90->65|90->65|90->65|90->65|90->65|92->67|92->67|93->68|93->68|93->68|93->68|94->69|94->69|95->70|95->70|95->70|95->70|96->71|97->72|98->73|100->75|101->76|101->76|101->76|101->76|101->77|101->77|101->77|102->78|103->79|103->79|104->81|104->81|104->81|105->82|107->85|108->86|109->87|110->88
                  -- GENERATED --
              */
          