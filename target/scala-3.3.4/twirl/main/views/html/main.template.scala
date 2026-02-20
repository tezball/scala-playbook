
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
        """),format.raw/*34.9*/(""".btn-autofill """),format.raw/*34.23*/("""{"""),format.raw/*34.24*/(""" """),format.raw/*34.25*/("""background: #607D8B; """),format.raw/*34.46*/("""}"""),format.raw/*34.47*/("""
        """),format.raw/*35.9*/(""".btn-group """),format.raw/*35.20*/("""{"""),format.raw/*35.21*/(""" """),format.raw/*35.22*/("""display: flex; gap: 8px; margin-top: 8px; """),format.raw/*35.64*/("""}"""),format.raw/*35.65*/("""
        """),format.raw/*36.9*/(""".success """),format.raw/*36.18*/("""{"""),format.raw/*36.19*/(""" """),format.raw/*36.20*/("""background: #dff0d8; color: #3c763d; padding: 10px; border-radius: 4px; margin-bottom: 16px; """),format.raw/*36.113*/("""}"""),format.raw/*36.114*/("""
        """),format.raw/*37.9*/(""".error-msg """),format.raw/*37.20*/("""{"""),format.raw/*37.21*/(""" """),format.raw/*37.22*/("""color: #a94442; font-size: 0.9em; """),format.raw/*37.56*/("""}"""),format.raw/*37.57*/("""
        """),format.raw/*38.9*/("""table """),format.raw/*38.15*/("""{"""),format.raw/*38.16*/(""" """),format.raw/*38.17*/("""width: 100%; border-collapse: collapse; margin-top: 16px; """),format.raw/*38.75*/("""}"""),format.raw/*38.76*/("""
        """),format.raw/*39.9*/("""th, td """),format.raw/*39.16*/("""{"""),format.raw/*39.17*/(""" """),format.raw/*39.18*/("""text-align: left; padding: 8px; border-bottom: 1px solid #ddd; """),format.raw/*39.81*/("""}"""),format.raw/*39.82*/("""
        """),format.raw/*40.9*/("""th """),format.raw/*40.12*/("""{"""),format.raw/*40.13*/(""" """),format.raw/*40.14*/("""background: #f5f5f5; """),format.raw/*40.35*/("""}"""),format.raw/*40.36*/("""
        """),format.raw/*41.9*/(""".concept-banner """),format.raw/*41.25*/("""{"""),format.raw/*41.26*/(""" """),format.raw/*41.27*/("""background: #E3F2FD; border-left: 4px solid #2196F3; padding: 16px; margin-bottom: 24px; border-radius: 0 4px 4px 0; """),format.raw/*41.144*/("""}"""),format.raw/*41.145*/("""
        """),format.raw/*42.9*/(""".concept-banner h3 """),format.raw/*42.28*/("""{"""),format.raw/*42.29*/(""" """),format.raw/*42.30*/("""margin: 0 0 8px 0; color: #1565C0; """),format.raw/*42.65*/("""}"""),format.raw/*42.66*/("""
        """),format.raw/*43.9*/(""".concept-banner pre """),format.raw/*43.29*/("""{"""),format.raw/*43.30*/(""" """),format.raw/*43.31*/("""background: #263238; color: #EEFFFF; padding: 12px; border-radius: 4px; overflow-x: auto; font-size: 0.85em; margin: 8px 0 0 0; """),format.raw/*43.159*/("""}"""),format.raw/*43.160*/("""
        """),format.raw/*44.9*/(""".concept-banner code """),format.raw/*44.30*/("""{"""),format.raw/*44.31*/(""" """),format.raw/*44.32*/("""font-family: 'Courier New', monospace; """),format.raw/*44.71*/("""}"""),format.raw/*44.72*/("""
        """),format.raw/*45.9*/(""".card """),format.raw/*45.15*/("""{"""),format.raw/*45.16*/(""" """),format.raw/*45.17*/("""background: #fff; border: 1px solid #ddd; border-radius: 8px; padding: 16px; margin-bottom: 12px; """),format.raw/*45.115*/("""}"""),format.raw/*45.116*/("""
        """),format.raw/*46.9*/(""".card h3 """),format.raw/*46.18*/("""{"""),format.raw/*46.19*/(""" """),format.raw/*46.20*/("""margin-top: 0; """),format.raw/*46.35*/("""}"""),format.raw/*46.36*/("""
        """),format.raw/*47.9*/(""".cards """),format.raw/*47.16*/("""{"""),format.raw/*47.17*/(""" """),format.raw/*47.18*/("""display: grid; grid-template-columns: repeat(auto-fit, minmax(200px, 1fr)); gap: 12px; """),format.raw/*47.105*/("""}"""),format.raw/*47.106*/("""
        """),format.raw/*48.9*/(""".step """),format.raw/*48.15*/("""{"""),format.raw/*48.16*/(""" """),format.raw/*48.17*/("""border-left: 3px solid #2196F3; padding: 8px 12px; margin-bottom: 8px; background: #FAFAFA; """),format.raw/*48.109*/("""}"""),format.raw/*48.110*/("""
        """),format.raw/*49.9*/(""".step.success-step """),format.raw/*49.28*/("""{"""),format.raw/*49.29*/(""" """),format.raw/*49.30*/("""border-left-color: #4CAF50; """),format.raw/*49.58*/("""}"""),format.raw/*49.59*/("""
        """),format.raw/*50.9*/(""".step.error-step """),format.raw/*50.26*/("""{"""),format.raw/*50.27*/(""" """),format.raw/*50.28*/("""border-left-color: #f44336; """),format.raw/*50.56*/("""}"""),format.raw/*50.57*/("""
        """),format.raw/*51.9*/(""".inline-form """),format.raw/*51.22*/("""{"""),format.raw/*51.23*/(""" """),format.raw/*51.24*/("""display: flex; gap: 8px; align-items: end; flex-wrap: wrap; """),format.raw/*51.84*/("""}"""),format.raw/*51.85*/("""
        """),format.raw/*52.9*/(""".inline-form .form-group """),format.raw/*52.34*/("""{"""),format.raw/*52.35*/(""" """),format.raw/*52.36*/("""margin-bottom: 0; """),format.raw/*52.54*/("""}"""),format.raw/*52.55*/("""
    """),format.raw/*53.5*/("""</style>
</head>
<body>
    <nav class="top-nav">
        <a class="brand" href=""""),_display_(/*57.33*/_root_/*57.39*/.controllers.routes.HomeController.index()),format.raw/*57.81*/("""">Scala Playbook</a>
        <div class="nav-divider"></div>
        <div class="nav-section">
            <span class="nav-label">Core</span>
            <a href=""""),_display_(/*61.23*/_root_/*61.29*/.users.routes.UserController.showForm()),format.raw/*61.68*/("""">Users</a>
            <a href=""""),_display_(/*62.23*/_root_/*62.29*/.products.routes.ProductController.showForm()),format.raw/*62.74*/("""">Products</a>
            <a href=""""),_display_(/*63.23*/_root_/*63.29*/.cart.routes.CartController.showCart()),format.raw/*63.67*/("""">Cart</a>
            <a href=""""),_display_(/*64.23*/_root_/*64.29*/.coupons.routes.CouponController.showForm()),format.raw/*64.72*/("""">Coupons</a>
            <a href=""""),_display_(/*65.23*/_root_/*65.29*/.notifications.routes.NotificationController.showForm()),format.raw/*65.84*/("""">Notifications</a>
            <a href=""""),_display_(/*66.23*/_root_/*66.29*/.analytics.routes.AnalyticsController.dashboard()),format.raw/*66.78*/("""">Analytics</a>
        </div>
        <div class="nav-divider"></div>
        <div class="nav-section">
            <span class="nav-label">Advanced</span>
            <a href=""""),_display_(/*71.23*/_root_/*71.29*/.orders.routes.OrderController.showForm()),format.raw/*71.70*/("""">Orders</a>
            <a href=""""),_display_(/*72.23*/_root_/*72.29*/.reviews.routes.ReviewController.showForm()),format.raw/*72.72*/("""">Reviews</a>
            <a href=""""),_display_(/*73.23*/_root_/*73.29*/.workflow.routes.WorkflowController.showBoard()),format.raw/*73.76*/("""">Workflow</a>
            <a href=""""),_display_(/*74.23*/_root_/*74.29*/.ledger.routes.LedgerController.showLedger()),format.raw/*74.73*/("""">Ledger</a>
        </div>
    </nav>
    <div class="page-content">
        """),_display_(/*78.10*/content),format.raw/*78.17*/("""
    """),format.raw/*79.5*/("""</div>
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
                  HASH: 44e0120c07d2e175375c3535215d8ef28dbc54ec
                  MATRIX: 771->1|895->32|922->33|1100->185|1125->190|1153->191|1228->238|1257->239|1286->240|1338->264|1367->265|1403->274|1436->279|1465->280|1494->281|1583->342|1612->343|1648->352|1685->361|1714->362|1743->363|1869->460|1899->461|1935->470|1979->486|2008->487|2037->488|2159->581|2189->582|2225->591|2275->613|2304->614|2333->615|2382->636|2411->637|2447->646|2488->659|2517->660|2546->661|2610->697|2639->698|2675->707|2727->731|2756->732|2785->733|2922->841|2952->842|2988->851|3031->866|3060->867|3089->868|3225->975|3255->976|3291->985|3340->1006|3369->1007|3398->1008|3460->1042|3489->1043|3525->1052|3566->1065|3595->1066|3624->1067|3714->1129|3743->1130|3779->1139|3821->1153|3850->1154|3879->1155|3961->1209|3990->1210|4026->1219|4061->1226|4090->1227|4119->1228|4160->1241|4189->1242|4225->1251|4265->1263|4294->1264|4323->1265|4372->1286|4401->1287|4437->1296|4471->1302|4500->1303|4529->1304|4612->1359|4641->1360|4677->1369|4805->1468|4835->1469|4876->1482|5025->1604|5054->1605|5090->1614|5127->1623|5156->1624|5185->1625|5231->1643|5260->1644|5296->1653|5337->1666|5366->1667|5395->1668|5549->1793|5579->1794|5615->1803|5668->1828|5697->1829|5726->1830|5768->1844|5797->1845|5833->1854|5871->1864|5900->1865|5929->1866|5978->1887|6007->1888|6043->1897|6083->1909|6112->1910|6141->1911|6190->1932|6219->1933|6255->1942|6292->1951|6321->1952|6350->1953|6399->1974|6428->1975|6464->1984|6500->1992|6529->1993|6558->1994|6624->2032|6653->2033|6689->2042|6731->2056|6760->2057|6789->2058|6838->2079|6867->2080|6903->2089|6942->2100|6971->2101|7000->2102|7070->2144|7099->2145|7135->2154|7172->2163|7201->2164|7230->2165|7352->2258|7382->2259|7418->2268|7457->2279|7486->2280|7515->2281|7577->2315|7606->2316|7642->2325|7676->2331|7705->2332|7734->2333|7820->2391|7849->2392|7885->2401|7920->2408|7949->2409|7978->2410|8069->2473|8098->2474|8134->2483|8165->2486|8194->2487|8223->2488|8272->2509|8301->2510|8337->2519|8381->2535|8410->2536|8439->2537|8585->2654|8615->2655|8651->2664|8698->2683|8727->2684|8756->2685|8819->2720|8848->2721|8884->2730|8932->2750|8961->2751|8990->2752|9147->2880|9177->2881|9213->2890|9262->2911|9291->2912|9320->2913|9387->2952|9416->2953|9452->2962|9486->2968|9515->2969|9544->2970|9671->3068|9701->3069|9737->3078|9774->3087|9803->3088|9832->3089|9875->3104|9904->3105|9940->3114|9975->3121|10004->3122|10033->3123|10149->3210|10179->3211|10215->3220|10249->3226|10278->3227|10307->3228|10428->3320|10458->3321|10494->3330|10541->3349|10570->3350|10599->3351|10655->3379|10684->3380|10720->3389|10765->3406|10794->3407|10823->3408|10879->3436|10908->3437|10944->3446|10985->3459|11014->3460|11043->3461|11131->3521|11160->3522|11196->3531|11249->3556|11278->3557|11307->3558|11353->3576|11382->3577|11414->3582|11523->3664|11538->3670|11601->3712|11793->3877|11808->3883|11868->3922|11929->3956|11944->3962|12010->4007|12074->4044|12089->4050|12148->4088|12208->4121|12223->4127|12287->4170|12350->4206|12365->4212|12441->4267|12510->4309|12525->4315|12595->4364|12801->4543|12816->4549|12878->4590|12940->4625|12955->4631|13019->4674|13082->4710|13097->4716|13165->4763|13229->4800|13244->4806|13309->4850|13415->4929|13443->4936|13475->4941
                  LINES: 22->1|27->2|28->3|33->8|33->8|33->8|35->10|35->10|35->10|35->10|35->10|36->11|36->11|36->11|36->11|36->11|36->11|37->12|37->12|37->12|37->12|37->12|37->12|38->13|38->13|38->13|38->13|38->13|38->13|39->14|39->14|39->14|39->14|39->14|39->14|40->15|40->15|40->15|40->15|40->15|40->15|41->16|41->16|41->16|41->16|41->16|41->16|42->17|42->17|42->17|42->17|42->17|42->17|43->18|43->18|43->18|43->18|43->18|43->18|44->19|44->19|44->19|44->19|44->19|44->19|45->20|45->20|45->20|45->20|45->20|45->20|46->21|46->21|46->21|46->21|46->21|46->21|47->22|47->22|47->22|47->22|47->22|47->22|48->23|48->23|48->23|48->23|48->23|48->23|49->24|49->24|49->24|50->25|51->26|51->26|52->27|52->27|52->27|52->27|52->27|52->27|53->28|53->28|53->28|53->28|53->28|53->28|54->29|54->29|54->29|54->29|54->29|54->29|55->30|55->30|55->30|55->30|55->30|55->30|56->31|56->31|56->31|56->31|56->31|56->31|57->32|57->32|57->32|57->32|57->32|57->32|58->33|58->33|58->33|58->33|58->33|58->33|59->34|59->34|59->34|59->34|59->34|59->34|60->35|60->35|60->35|60->35|60->35|60->35|61->36|61->36|61->36|61->36|61->36|61->36|62->37|62->37|62->37|62->37|62->37|62->37|63->38|63->38|63->38|63->38|63->38|63->38|64->39|64->39|64->39|64->39|64->39|64->39|65->40|65->40|65->40|65->40|65->40|65->40|66->41|66->41|66->41|66->41|66->41|66->41|67->42|67->42|67->42|67->42|67->42|67->42|68->43|68->43|68->43|68->43|68->43|68->43|69->44|69->44|69->44|69->44|69->44|69->44|70->45|70->45|70->45|70->45|70->45|70->45|71->46|71->46|71->46|71->46|71->46|71->46|72->47|72->47|72->47|72->47|72->47|72->47|73->48|73->48|73->48|73->48|73->48|73->48|74->49|74->49|74->49|74->49|74->49|74->49|75->50|75->50|75->50|75->50|75->50|75->50|76->51|76->51|76->51|76->51|76->51|76->51|77->52|77->52|77->52|77->52|77->52|77->52|78->53|82->57|82->57|82->57|86->61|86->61|86->61|87->62|87->62|87->62|88->63|88->63|88->63|89->64|89->64|89->64|90->65|90->65|90->65|91->66|91->66|91->66|96->71|96->71|96->71|97->72|97->72|97->72|98->73|98->73|98->73|99->74|99->74|99->74|103->78|103->78|104->79
                  -- GENERATED --
              */
          