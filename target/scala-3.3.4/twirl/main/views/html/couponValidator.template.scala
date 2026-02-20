
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
                <button type="button" class="btn-autofill btn-sm" onclick="autoFillCoupon()">Auto Fill</button>
            </div>
        </div>
    """)))}),format.raw/*59.6*/("""

    """),format.raw/*61.5*/("""<script>
    function autoFillCoupon() """),format.raw/*62.31*/("""{"""),format.raw/*62.32*/("""
        """),format.raw/*63.9*/("""var scenarios = [
            """),format.raw/*64.13*/("""{"""),format.raw/*64.14*/(""" """),format.raw/*64.15*/("""code: 'SAVE10', total: 75.00 """),format.raw/*64.44*/("""}"""),format.raw/*64.45*/(""",
            """),format.raw/*65.13*/("""{"""),format.raw/*65.14*/(""" """),format.raw/*65.15*/("""code: 'EXPIRED22', total: 50.00 """),format.raw/*65.47*/("""}"""),format.raw/*65.48*/(""",
            """),format.raw/*66.13*/("""{"""),format.raw/*66.14*/(""" """),format.raw/*66.15*/("""code: 'USED_UP', total: 100.00 """),format.raw/*66.46*/("""}"""),format.raw/*66.47*/(""",
            """),format.raw/*67.13*/("""{"""),format.raw/*67.14*/(""" """),format.raw/*67.15*/("""code: 'BIGSPEND', total: 120.00 """),format.raw/*67.47*/("""}"""),format.raw/*67.48*/(""",
            """),format.raw/*68.13*/("""{"""),format.raw/*68.14*/(""" """),format.raw/*68.15*/("""code: 'WELCOME', total: 49.99 """),format.raw/*68.45*/("""}"""),format.raw/*68.46*/(""",
            """),format.raw/*69.13*/("""{"""),format.raw/*69.14*/(""" """),format.raw/*69.15*/("""code: 'NOTREAL', total: 80.00 """),format.raw/*69.45*/("""}"""),format.raw/*69.46*/(""",
            """),format.raw/*70.13*/("""{"""),format.raw/*70.14*/(""" """),format.raw/*70.15*/("""code: '', total: 60.00 """),format.raw/*70.38*/("""}"""),format.raw/*70.39*/("""
        """),format.raw/*71.9*/("""];
        var s = scenarios[Math.floor(Math.random() * scenarios.length)];
        document.getElementById('code').value = s.code;
        document.getElementById('orderTotal').value = s.total;
    """),format.raw/*75.5*/("""}"""),format.raw/*75.6*/("""
    """),format.raw/*76.5*/("""</script>

    """),_display_(/*78.6*/result/*78.12*/.map/*78.16*/ { res =>_display_(Seq[Any](format.raw/*78.25*/("""
        """),format.raw/*79.9*/("""<h2>Validation Pipeline Results</h2>

        """),_display_(/*81.10*/for(step <- res.steps) yield /*81.32*/ {_display_(Seq[Any](format.raw/*81.34*/("""
            """),format.raw/*82.13*/("""<div class="step """),_display_(if(step.passed)/*82.46*/{_display_(Seq[Any](format.raw/*82.47*/("""success-step""")))}else/*82.64*/{_display_(Seq[Any](format.raw/*82.65*/("""error-step""")))}),format.raw/*82.76*/("""">
                <strong>Step: """),_display_(/*83.32*/step/*83.36*/.name),format.raw/*83.41*/("""</strong> (<code>"""),_display_(/*83.59*/step/*83.63*/.concept),format.raw/*83.71*/("""</code>)
                <br>
                """),_display_(if(step.passed)/*85.33*/ {_display_(Seq[Any](format.raw/*85.35*/("""
                    """),format.raw/*86.21*/("""&#x2705; """),_display_(/*86.31*/step/*86.35*/.detail),format.raw/*86.42*/("""
                """)))}else/*87.24*/{_display_(Seq[Any](format.raw/*87.25*/("""
                    """),format.raw/*88.21*/("""&#x274C; """),_display_(/*88.31*/step/*88.35*/.detail),format.raw/*88.42*/("""
                """)))}),format.raw/*89.18*/("""
            """),format.raw/*90.13*/("""</div>
        """)))}),format.raw/*91.10*/("""

        """),format.raw/*93.9*/("""<div class="card" style="margin-top:16px">
            """),_display_(/*94.14*/res/*94.17*/.discount/*94.26*/ match/*94.32*/ {/*95.17*/case Some(amount) =>/*95.37*/ {_display_(Seq[Any](format.raw/*95.39*/("""
                    """),format.raw/*96.21*/("""<h3 style="color:#4CAF50">Coupon Valid!</h3>
                    <p>Discount amount: <strong>$"""),_display_(/*97.51*/{f"${amount}%.2f"}),format.raw/*97.69*/("""</strong></p>
                """)))}/*99.17*/case None =>/*99.29*/ {_display_(Seq[Any](format.raw/*99.31*/("""
                    """),format.raw/*100.21*/("""<h3 style="color:#f44336">Coupon Invalid</h3>
                    <p>The coupon could not be applied. See the pipeline steps above for details.</p>
                """)))}}),format.raw/*103.14*/("""
        """),format.raw/*104.9*/("""</div>
    """)))}),format.raw/*105.6*/("""
""")))}),format.raw/*106.2*/("""
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
                  HASH: 85facdd4fa18c4e9e5b7c17e642f767e1d214693
                  MATRIX: 883->1|1138->163|1165->165|1205->197|1244->199|1275->204|1839->741|1867->742|1895->743|1991->811|2020->812|2049->814|2732->1470|2773->1495|2813->1497|2858->1514|2921->1550|2936->1556|2962->1561|3027->1599|3072->1623|3131->1655|3146->1661|3177->1671|3235->1702|3250->1708|3285->1722|3344->1754|3396->1785|3468->1826|3504->1835|3589->1894|3604->1900|3684->1971|3724->1973|3761->1983|3776->1989|3812->2004|3848->2013|4057->2195|4078->2207|4119->2227|4404->2484|4432->2502|4480->2528|4822->2840|4855->2846|4922->2885|4951->2886|4987->2895|5045->2925|5074->2926|5103->2927|5160->2956|5189->2957|5231->2971|5260->2972|5289->2973|5349->3005|5378->3006|5420->3020|5449->3021|5478->3022|5537->3053|5566->3054|5608->3068|5637->3069|5666->3070|5726->3102|5755->3103|5797->3117|5826->3118|5855->3119|5913->3149|5942->3150|5984->3164|6013->3165|6042->3166|6100->3196|6129->3197|6171->3211|6200->3212|6229->3213|6280->3236|6309->3237|6345->3246|6571->3445|6599->3446|6631->3451|6673->3467|6688->3473|6701->3477|6748->3486|6784->3495|6858->3542|6896->3564|6936->3566|6977->3579|7037->3612|7076->3613|7112->3630|7151->3631|7193->3642|7254->3676|7267->3680|7293->3685|7338->3703|7351->3707|7380->3715|7469->3777|7509->3779|7558->3800|7595->3810|7608->3814|7636->3821|7677->3845|7716->3846|7765->3867|7802->3877|7815->3881|7843->3888|7892->3906|7933->3919|7980->3935|8017->3945|8100->4001|8112->4004|8130->4013|8145->4019|8156->4038|8185->4058|8225->4060|8274->4081|8396->4176|8435->4194|8485->4242|8506->4254|8546->4256|8596->4277|8794->4456|8831->4465|8874->4477|8907->4479
                  LINES: 22->1|27->2|28->3|28->3|28->3|29->4|36->11|36->11|36->11|36->11|36->11|38->13|55->30|55->30|55->30|56->31|57->32|57->32|57->32|58->33|58->33|59->34|59->34|59->34|60->35|60->35|60->35|61->36|61->36|63->38|64->39|68->43|68->43|68->43|68->43|69->44|69->44|69->44|70->45|73->48|73->48|73->48|77->52|77->52|77->52|84->59|86->61|87->62|87->62|88->63|89->64|89->64|89->64|89->64|89->64|90->65|90->65|90->65|90->65|90->65|91->66|91->66|91->66|91->66|91->66|92->67|92->67|92->67|92->67|92->67|93->68|93->68|93->68|93->68|93->68|94->69|94->69|94->69|94->69|94->69|95->70|95->70|95->70|95->70|95->70|96->71|100->75|100->75|101->76|103->78|103->78|103->78|103->78|104->79|106->81|106->81|106->81|107->82|107->82|107->82|107->82|107->82|107->82|108->83|108->83|108->83|108->83|108->83|108->83|110->85|110->85|111->86|111->86|111->86|111->86|112->87|112->87|113->88|113->88|113->88|113->88|114->89|115->90|116->91|118->93|119->94|119->94|119->94|119->94|119->95|119->95|119->95|120->96|121->97|121->97|122->99|122->99|122->99|123->100|125->103|126->104|127->105|128->106
                  -- GENERATED --
              */
          