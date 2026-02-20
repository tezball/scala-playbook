
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

object main extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[String,Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(title: String)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),format.raw/*3.1*/("""<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>"""),_display_(/*8.13*/title),format.raw/*8.18*/(""" """),format.raw/*8.19*/("""- Scala Playbook</title>
    <style>
        * """),format.raw/*10.11*/("""{"""),format.raw/*10.12*/(""" """),format.raw/*10.13*/("""box-sizing: border-box; """),format.raw/*10.37*/("""}"""),format.raw/*10.38*/("""
        """),format.raw/*11.9*/("""body """),format.raw/*11.14*/("""{"""),format.raw/*11.15*/(""" """),format.raw/*11.16*/("""font-family: sans-serif; margin: 0; padding: 0; color: #333; """),format.raw/*11.77*/("""}"""),format.raw/*11.78*/("""
        """),format.raw/*12.9*/(""".top-nav """),format.raw/*12.18*/("""{"""),format.raw/*12.19*/(""" """),format.raw/*12.20*/("""background: #263238; padding: 0; margin: 0; display: flex; flex-wrap: wrap; align-items: center; """),format.raw/*12.117*/("""}"""),format.raw/*12.118*/("""
        """),format.raw/*13.9*/(""".top-nav .brand """),format.raw/*13.25*/("""{"""),format.raw/*13.26*/(""" """),format.raw/*13.27*/("""color: #fff; font-weight: bold; font-size: 1.1em; padding: 12px 20px; text-decoration: none; """),format.raw/*13.120*/("""}"""),format.raw/*13.121*/("""
        """),format.raw/*14.9*/(""".top-nav .brand:hover """),format.raw/*14.31*/("""{"""),format.raw/*14.32*/(""" """),format.raw/*14.33*/("""background: #37474F; """),format.raw/*14.54*/("""}"""),format.raw/*14.55*/("""
        """),format.raw/*15.9*/(""".nav-section """),format.raw/*15.22*/("""{"""),format.raw/*15.23*/(""" """),format.raw/*15.24*/("""display: flex; align-items: center; """),format.raw/*15.60*/("""}"""),format.raw/*15.61*/("""
        """),format.raw/*16.9*/(""".nav-section .nav-label """),format.raw/*16.33*/("""{"""),format.raw/*16.34*/(""" """),format.raw/*16.35*/("""color: #78909C; font-size: 0.75em; text-transform: uppercase; padding: 0 8px 0 16px; letter-spacing: 0.5px; """),format.raw/*16.143*/("""}"""),format.raw/*16.144*/("""
        """),format.raw/*17.9*/(""".nav-section a """),format.raw/*17.24*/("""{"""),format.raw/*17.25*/(""" """),format.raw/*17.26*/("""color: #B0BEC5; text-decoration: none; padding: 12px 12px; font-size: 0.9em; transition: background 0.15s; """),format.raw/*17.133*/("""}"""),format.raw/*17.134*/("""
        """),format.raw/*18.9*/(""".nav-section a:hover """),format.raw/*18.30*/("""{"""),format.raw/*18.31*/(""" """),format.raw/*18.32*/("""background: #37474F; color: #fff; """),format.raw/*18.66*/("""}"""),format.raw/*18.67*/("""
        """),format.raw/*19.9*/(""".nav-divider """),format.raw/*19.22*/("""{"""),format.raw/*19.23*/(""" """),format.raw/*19.24*/("""width: 1px; height: 24px; background: #455A64; margin: 0 4px; """),format.raw/*19.86*/("""}"""),format.raw/*19.87*/("""
        """),format.raw/*20.9*/(""".page-content """),format.raw/*20.23*/("""{"""),format.raw/*20.24*/(""" """),format.raw/*20.25*/("""max-width: 900px; margin: 0 auto; padding: 24px 20px; """),format.raw/*20.79*/("""}"""),format.raw/*20.80*/("""
        """),format.raw/*21.9*/("""h1, h2 """),format.raw/*21.16*/("""{"""),format.raw/*21.17*/(""" """),format.raw/*21.18*/("""color: #333; """),format.raw/*21.31*/("""}"""),format.raw/*21.32*/("""
        """),format.raw/*22.9*/(""".form-group """),format.raw/*22.21*/("""{"""),format.raw/*22.22*/(""" """),format.raw/*22.23*/("""margin-bottom: 12px; """),format.raw/*22.44*/("""}"""),format.raw/*22.45*/("""
        """),format.raw/*23.9*/("""label """),format.raw/*23.15*/("""{"""),format.raw/*23.16*/(""" """),format.raw/*23.17*/("""display: block; font-weight: bold; margin-bottom: 4px; """),format.raw/*23.72*/("""}"""),format.raw/*23.73*/("""
        """),format.raw/*24.9*/("""input[type="text"], input[type="email"], input[type="tel"], input[type="number"], select, textarea """),format.raw/*24.108*/("""{"""),format.raw/*24.109*/("""
            """),format.raw/*25.13*/("""width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; font-size: 0.95em;
        """),format.raw/*26.9*/("""}"""),format.raw/*26.10*/("""
        """),format.raw/*27.9*/("""textarea """),format.raw/*27.18*/("""{"""),format.raw/*27.19*/(""" """),format.raw/*27.20*/("""resize: vertical; """),format.raw/*27.38*/("""}"""),format.raw/*27.39*/("""
        """),format.raw/*28.9*/("""button, .btn """),format.raw/*28.22*/("""{"""),format.raw/*28.23*/(""" """),format.raw/*28.24*/("""padding: 10px 20px; background: #4CAF50; color: white; border: none; border-radius: 4px; cursor: pointer; font-size: 0.95em; """),format.raw/*28.149*/("""}"""),format.raw/*28.150*/("""
        """),format.raw/*29.9*/("""button:hover, .btn:hover """),format.raw/*29.34*/("""{"""),format.raw/*29.35*/(""" """),format.raw/*29.36*/("""opacity: 0.9; """),format.raw/*29.50*/("""}"""),format.raw/*29.51*/("""
        """),format.raw/*30.9*/(""".btn-blue """),format.raw/*30.19*/("""{"""),format.raw/*30.20*/(""" """),format.raw/*30.21*/("""background: #2196F3; """),format.raw/*30.42*/("""}"""),format.raw/*30.43*/("""
        """),format.raw/*31.9*/(""".btn-orange """),format.raw/*31.21*/("""{"""),format.raw/*31.22*/(""" """),format.raw/*31.23*/("""background: #FF9800; """),format.raw/*31.44*/("""}"""),format.raw/*31.45*/("""
        """),format.raw/*32.9*/(""".btn-red """),format.raw/*32.18*/("""{"""),format.raw/*32.19*/(""" """),format.raw/*32.20*/("""background: #f44336; """),format.raw/*32.41*/("""}"""),format.raw/*32.42*/("""
        """),format.raw/*33.9*/(""".btn-sm """),format.raw/*33.17*/("""{"""),format.raw/*33.18*/(""" """),format.raw/*33.19*/("""padding: 5px 12px; font-size: 0.85em; """),format.raw/*33.57*/("""}"""),format.raw/*33.58*/("""
        """),format.raw/*34.9*/(""".success """),format.raw/*34.18*/("""{"""),format.raw/*34.19*/(""" """),format.raw/*34.20*/("""background: #dff0d8; color: #3c763d; padding: 10px; border-radius: 4px; margin-bottom: 16px; """),format.raw/*34.113*/("""}"""),format.raw/*34.114*/("""
        """),format.raw/*35.9*/(""".error-msg """),format.raw/*35.20*/("""{"""),format.raw/*35.21*/(""" """),format.raw/*35.22*/("""color: #a94442; font-size: 0.9em; """),format.raw/*35.56*/("""}"""),format.raw/*35.57*/("""
        """),format.raw/*36.9*/("""table """),format.raw/*36.15*/("""{"""),format.raw/*36.16*/(""" """),format.raw/*36.17*/("""width: 100%; border-collapse: collapse; margin-top: 16px; """),format.raw/*36.75*/("""}"""),format.raw/*36.76*/("""
        """),format.raw/*37.9*/("""th, td """),format.raw/*37.16*/("""{"""),format.raw/*37.17*/(""" """),format.raw/*37.18*/("""text-align: left; padding: 8px; border-bottom: 1px solid #ddd; """),format.raw/*37.81*/("""}"""),format.raw/*37.82*/("""
        """),format.raw/*38.9*/("""th """),format.raw/*38.12*/("""{"""),format.raw/*38.13*/(""" """),format.raw/*38.14*/("""background: #f5f5f5; """),format.raw/*38.35*/("""}"""),format.raw/*38.36*/("""
        """),format.raw/*39.9*/(""".concept-banner """),format.raw/*39.25*/("""{"""),format.raw/*39.26*/(""" """),format.raw/*39.27*/("""background: #E3F2FD; border-left: 4px solid #2196F3; padding: 16px; margin-bottom: 24px; border-radius: 0 4px 4px 0; """),format.raw/*39.144*/("""}"""),format.raw/*39.145*/("""
        """),format.raw/*40.9*/(""".concept-banner h3 """),format.raw/*40.28*/("""{"""),format.raw/*40.29*/(""" """),format.raw/*40.30*/("""margin: 0 0 8px 0; color: #1565C0; """),format.raw/*40.65*/("""}"""),format.raw/*40.66*/("""
        """),format.raw/*41.9*/(""".concept-banner pre """),format.raw/*41.29*/("""{"""),format.raw/*41.30*/(""" """),format.raw/*41.31*/("""background: #263238; color: #EEFFFF; padding: 12px; border-radius: 4px; overflow-x: auto; font-size: 0.85em; margin: 8px 0 0 0; """),format.raw/*41.159*/("""}"""),format.raw/*41.160*/("""
        """),format.raw/*42.9*/(""".concept-banner code """),format.raw/*42.30*/("""{"""),format.raw/*42.31*/(""" """),format.raw/*42.32*/("""font-family: 'Courier New', monospace; """),format.raw/*42.71*/("""}"""),format.raw/*42.72*/("""
        """),format.raw/*43.9*/(""".card """),format.raw/*43.15*/("""{"""),format.raw/*43.16*/(""" """),format.raw/*43.17*/("""background: #fff; border: 1px solid #ddd; border-radius: 8px; padding: 16px; margin-bottom: 12px; """),format.raw/*43.115*/("""}"""),format.raw/*43.116*/("""
        """),format.raw/*44.9*/(""".card h3 """),format.raw/*44.18*/("""{"""),format.raw/*44.19*/(""" """),format.raw/*44.20*/("""margin-top: 0; """),format.raw/*44.35*/("""}"""),format.raw/*44.36*/("""
        """),format.raw/*45.9*/(""".cards """),format.raw/*45.16*/("""{"""),format.raw/*45.17*/(""" """),format.raw/*45.18*/("""display: grid; grid-template-columns: repeat(auto-fit, minmax(200px, 1fr)); gap: 12px; """),format.raw/*45.105*/("""}"""),format.raw/*45.106*/("""
        """),format.raw/*46.9*/(""".step """),format.raw/*46.15*/("""{"""),format.raw/*46.16*/(""" """),format.raw/*46.17*/("""border-left: 3px solid #2196F3; padding: 8px 12px; margin-bottom: 8px; background: #FAFAFA; """),format.raw/*46.109*/("""}"""),format.raw/*46.110*/("""
        """),format.raw/*47.9*/(""".step.success-step """),format.raw/*47.28*/("""{"""),format.raw/*47.29*/(""" """),format.raw/*47.30*/("""border-left-color: #4CAF50; """),format.raw/*47.58*/("""}"""),format.raw/*47.59*/("""
        """),format.raw/*48.9*/(""".step.error-step """),format.raw/*48.26*/("""{"""),format.raw/*48.27*/(""" """),format.raw/*48.28*/("""border-left-color: #f44336; """),format.raw/*48.56*/("""}"""),format.raw/*48.57*/("""
        """),format.raw/*49.9*/(""".inline-form """),format.raw/*49.22*/("""{"""),format.raw/*49.23*/(""" """),format.raw/*49.24*/("""display: flex; gap: 8px; align-items: end; flex-wrap: wrap; """),format.raw/*49.84*/("""}"""),format.raw/*49.85*/("""
        """),format.raw/*50.9*/(""".inline-form .form-group """),format.raw/*50.34*/("""{"""),format.raw/*50.35*/(""" """),format.raw/*50.36*/("""margin-bottom: 0; """),format.raw/*50.54*/("""}"""),format.raw/*50.55*/("""
    """),format.raw/*51.5*/("""</style>
</head>
<body>
    <nav class="top-nav">
        <a class="brand" href=""""),_display_(/*55.33*/_root_/*55.39*/.controllers.routes.HomeController.index()),format.raw/*55.81*/("""">Scala Playbook</a>
        <div class="nav-divider"></div>
        <div class="nav-section">
            <span class="nav-label">Existing</span>
            <a href=""""),_display_(/*59.23*/_root_/*59.29*/.users.routes.UserController.showForm()),format.raw/*59.68*/("""">Users</a>
            <a href=""""),_display_(/*60.23*/_root_/*60.29*/.orders.routes.OrderController.showForm()),format.raw/*60.70*/("""">Orders</a>
        </div>
        <div class="nav-divider"></div>
        <div class="nav-section">
            <span class="nav-label">Core</span>
            <a href=""""),_display_(/*65.23*/_root_/*65.29*/.products.routes.ProductController.showForm()),format.raw/*65.74*/("""">Products</a>
            <a href=""""),_display_(/*66.23*/_root_/*66.29*/.cart.routes.CartController.showCart()),format.raw/*66.67*/("""">Cart</a>
            <a href=""""),_display_(/*67.23*/_root_/*67.29*/.coupons.routes.CouponController.showForm()),format.raw/*67.72*/("""">Coupons</a>
            <a href=""""),_display_(/*68.23*/_root_/*68.29*/.notifications.routes.NotificationController.showForm()),format.raw/*68.84*/("""">Notifications</a>
            <a href=""""),_display_(/*69.23*/_root_/*69.29*/.analytics.routes.AnalyticsController.dashboard()),format.raw/*69.78*/("""">Analytics</a>
        </div>
        <div class="nav-divider"></div>
        <div class="nav-section">
            <span class="nav-label">Advanced</span>
            <a href=""""),_display_(/*74.23*/_root_/*74.29*/.reviews.routes.ReviewController.showForm()),format.raw/*74.72*/("""">Reviews</a>
            <a href=""""),_display_(/*75.23*/_root_/*75.29*/.workflow.routes.WorkflowController.showBoard()),format.raw/*75.76*/("""">Workflow</a>
            <a href=""""),_display_(/*76.23*/_root_/*76.29*/.ledger.routes.LedgerController.showLedger()),format.raw/*76.73*/("""">Ledger</a>
        </div>
    </nav>
    <div class="page-content">
        """),_display_(/*80.10*/content),format.raw/*80.17*/("""
    """),format.raw/*81.5*/("""</div>
</body>
</html>
"""))
      }
    }
  }

  def render(title:String,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(title)(content)

  def f:((String) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (title) => (content) => apply(title)(content)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/main.scala.html
                  HASH: fd883255c1b672c89518b59a531e1a5cf1433f0a
                  MATRIX: 771->1|895->32|922->33|1100->185|1125->190|1153->191|1228->238|1257->239|1286->240|1338->264|1367->265|1403->274|1436->279|1465->280|1494->281|1583->342|1612->343|1648->352|1685->361|1714->362|1743->363|1869->460|1899->461|1935->470|1979->486|2008->487|2037->488|2159->581|2189->582|2225->591|2275->613|2304->614|2333->615|2382->636|2411->637|2447->646|2488->659|2517->660|2546->661|2610->697|2639->698|2675->707|2727->731|2756->732|2785->733|2922->841|2952->842|2988->851|3031->866|3060->867|3089->868|3225->975|3255->976|3291->985|3340->1006|3369->1007|3398->1008|3460->1042|3489->1043|3525->1052|3566->1065|3595->1066|3624->1067|3714->1129|3743->1130|3779->1139|3821->1153|3850->1154|3879->1155|3961->1209|3990->1210|4026->1219|4061->1226|4090->1227|4119->1228|4160->1241|4189->1242|4225->1251|4265->1263|4294->1264|4323->1265|4372->1286|4401->1287|4437->1296|4471->1302|4500->1303|4529->1304|4612->1359|4641->1360|4677->1369|4805->1468|4835->1469|4876->1482|5025->1604|5054->1605|5090->1614|5127->1623|5156->1624|5185->1625|5231->1643|5260->1644|5296->1653|5337->1666|5366->1667|5395->1668|5549->1793|5579->1794|5615->1803|5668->1828|5697->1829|5726->1830|5768->1844|5797->1845|5833->1854|5871->1864|5900->1865|5929->1866|5978->1887|6007->1888|6043->1897|6083->1909|6112->1910|6141->1911|6190->1932|6219->1933|6255->1942|6292->1951|6321->1952|6350->1953|6399->1974|6428->1975|6464->1984|6500->1992|6529->1993|6558->1994|6624->2032|6653->2033|6689->2042|6726->2051|6755->2052|6784->2053|6906->2146|6936->2147|6972->2156|7011->2167|7040->2168|7069->2169|7131->2203|7160->2204|7196->2213|7230->2219|7259->2220|7288->2221|7374->2279|7403->2280|7439->2289|7474->2296|7503->2297|7532->2298|7623->2361|7652->2362|7688->2371|7719->2374|7748->2375|7777->2376|7826->2397|7855->2398|7891->2407|7935->2423|7964->2424|7993->2425|8139->2542|8169->2543|8205->2552|8252->2571|8281->2572|8310->2573|8373->2608|8402->2609|8438->2618|8486->2638|8515->2639|8544->2640|8701->2768|8731->2769|8767->2778|8816->2799|8845->2800|8874->2801|8941->2840|8970->2841|9006->2850|9040->2856|9069->2857|9098->2858|9225->2956|9255->2957|9291->2966|9328->2975|9357->2976|9386->2977|9429->2992|9458->2993|9494->3002|9529->3009|9558->3010|9587->3011|9703->3098|9733->3099|9769->3108|9803->3114|9832->3115|9861->3116|9982->3208|10012->3209|10048->3218|10095->3237|10124->3238|10153->3239|10209->3267|10238->3268|10274->3277|10319->3294|10348->3295|10377->3296|10433->3324|10462->3325|10498->3334|10539->3347|10568->3348|10597->3349|10685->3409|10714->3410|10750->3419|10803->3444|10832->3445|10861->3446|10907->3464|10936->3465|10968->3470|11077->3552|11092->3558|11155->3600|11351->3769|11366->3775|11426->3814|11487->3848|11502->3854|11564->3895|11763->4067|11778->4073|11844->4118|11908->4155|11923->4161|11982->4199|12042->4232|12057->4238|12121->4281|12184->4317|12199->4323|12275->4378|12344->4420|12359->4426|12429->4475|12635->4654|12650->4660|12714->4703|12777->4739|12792->4745|12860->4792|12924->4829|12939->4835|13004->4879|13110->4958|13138->4965|13170->4970
                  LINES: 22->1|27->2|28->3|33->8|33->8|33->8|35->10|35->10|35->10|35->10|35->10|36->11|36->11|36->11|36->11|36->11|36->11|37->12|37->12|37->12|37->12|37->12|37->12|38->13|38->13|38->13|38->13|38->13|38->13|39->14|39->14|39->14|39->14|39->14|39->14|40->15|40->15|40->15|40->15|40->15|40->15|41->16|41->16|41->16|41->16|41->16|41->16|42->17|42->17|42->17|42->17|42->17|42->17|43->18|43->18|43->18|43->18|43->18|43->18|44->19|44->19|44->19|44->19|44->19|44->19|45->20|45->20|45->20|45->20|45->20|45->20|46->21|46->21|46->21|46->21|46->21|46->21|47->22|47->22|47->22|47->22|47->22|47->22|48->23|48->23|48->23|48->23|48->23|48->23|49->24|49->24|49->24|50->25|51->26|51->26|52->27|52->27|52->27|52->27|52->27|52->27|53->28|53->28|53->28|53->28|53->28|53->28|54->29|54->29|54->29|54->29|54->29|54->29|55->30|55->30|55->30|55->30|55->30|55->30|56->31|56->31|56->31|56->31|56->31|56->31|57->32|57->32|57->32|57->32|57->32|57->32|58->33|58->33|58->33|58->33|58->33|58->33|59->34|59->34|59->34|59->34|59->34|59->34|60->35|60->35|60->35|60->35|60->35|60->35|61->36|61->36|61->36|61->36|61->36|61->36|62->37|62->37|62->37|62->37|62->37|62->37|63->38|63->38|63->38|63->38|63->38|63->38|64->39|64->39|64->39|64->39|64->39|64->39|65->40|65->40|65->40|65->40|65->40|65->40|66->41|66->41|66->41|66->41|66->41|66->41|67->42|67->42|67->42|67->42|67->42|67->42|68->43|68->43|68->43|68->43|68->43|68->43|69->44|69->44|69->44|69->44|69->44|69->44|70->45|70->45|70->45|70->45|70->45|70->45|71->46|71->46|71->46|71->46|71->46|71->46|72->47|72->47|72->47|72->47|72->47|72->47|73->48|73->48|73->48|73->48|73->48|73->48|74->49|74->49|74->49|74->49|74->49|74->49|75->50|75->50|75->50|75->50|75->50|75->50|76->51|80->55|80->55|80->55|84->59|84->59|84->59|85->60|85->60|85->60|90->65|90->65|90->65|91->66|91->66|91->66|92->67|92->67|92->67|93->68|93->68|93->68|94->69|94->69|94->69|99->74|99->74|99->74|100->75|100->75|100->75|101->76|101->76|101->76|105->80|105->80|106->81
                  -- GENERATED --
              */
          