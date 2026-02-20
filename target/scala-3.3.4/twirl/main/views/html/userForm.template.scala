
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

object userForm extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Form[_root_.users.UserForm],Seq[_root_.users.User],Seq[_root_.users.UserCreatedEvent],RequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(form: Form[_root_.users.UserForm], userList: Seq[_root_.users.User], kafkaEvents: Seq[_root_.users.UserCreatedEvent])(implicit request: RequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),_display_(/*3.2*/main("User Details")/*3.22*/ {_display_(Seq[Any](format.raw/*3.24*/("""
    """),format.raw/*4.5*/("""<h1>User Details</h1>

    <div class="concept-banner">
        <h3>Concepts: Play Forms, Slick ORM, Kafka Producer/Consumer</h3>
        <p>This page demonstrates the Play Framework form binding, Slick database operations, and Kafka event-driven messaging.</p>
    </div>

    """),_display_(/*11.6*/request/*11.13*/.flash.get("success").map/*11.38*/ { message =>_display_(Seq[Any](format.raw/*11.51*/("""
        """),format.raw/*12.9*/("""<div class="success">"""),_display_(/*12.31*/message),format.raw/*12.38*/("""</div>
    """)))}),format.raw/*13.6*/("""

    """),_display_(/*15.6*/helper/*15.12*/.form(action = users.routes.UserController.saveUser())/*15.66*/ {_display_(Seq[Any](format.raw/*15.68*/("""
        """),_display_(/*16.10*/helper/*16.16*/.CSRF.formField),format.raw/*16.31*/("""

        """),format.raw/*18.9*/("""<div class="form-group">
            <label for="name">Name</label>
            <input type="text" id="name" name="name" value=""""),_display_(/*20.62*/form("name")/*20.74*/.value.getOrElse("")),format.raw/*20.94*/("""" required>
            """),_display_(/*21.14*/form/*21.18*/.error("name").map/*21.36*/ { error =>_display_(Seq[Any](format.raw/*21.47*/("""
                """),format.raw/*22.17*/("""<div class="error-msg">"""),_display_(/*22.41*/error/*22.46*/.message),format.raw/*22.54*/("""</div>
            """)))}),format.raw/*23.14*/("""
        """),format.raw/*24.9*/("""</div>

        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" value=""""),_display_(/*28.65*/form("email")/*28.78*/.value.getOrElse("")),format.raw/*28.98*/("""" required>
            """),_display_(/*29.14*/form/*29.18*/.error("email").map/*29.37*/ { error =>_display_(Seq[Any](format.raw/*29.48*/("""
                """),format.raw/*30.17*/("""<div class="error-msg">"""),_display_(/*30.41*/error/*30.46*/.message),format.raw/*30.54*/("""</div>
            """)))}),format.raw/*31.14*/("""
        """),format.raw/*32.9*/("""</div>

        <div class="form-group">
            <label for="phone">Phone</label>
            <input type="tel" id="phone" name="phone" value=""""),_display_(/*36.63*/form("phone")/*36.76*/.value.getOrElse("")),format.raw/*36.96*/("""" required>
            """),_display_(/*37.14*/form/*37.18*/.error("phone").map/*37.37*/ { error =>_display_(Seq[Any](format.raw/*37.48*/("""
                """),format.raw/*38.17*/("""<div class="error-msg">"""),_display_(/*38.41*/error/*38.46*/.message),format.raw/*38.54*/("""</div>
            """)))}),format.raw/*39.14*/("""
        """),format.raw/*40.9*/("""</div>

        <button type="submit">Save User</button>
    """)))}),format.raw/*43.6*/("""

    """),format.raw/*45.5*/("""<h2>Saved Users</h2>
    """),_display_(if(userList.isEmpty)/*46.26*/ {_display_(Seq[Any](format.raw/*46.28*/("""
        """),format.raw/*47.9*/("""<p>No users saved yet.</p>
    """)))}else/*48.12*/{_display_(Seq[Any](format.raw/*48.13*/("""
        """),format.raw/*49.9*/("""<table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                </tr>
            </thead>
            <tbody>
                """),_display_(/*59.18*/for(user <- userList) yield /*59.39*/ {_display_(Seq[Any](format.raw/*59.41*/("""
                    """),format.raw/*60.21*/("""<tr>
                        <td>"""),_display_(/*61.30*/user/*61.34*/.id),format.raw/*61.37*/("""</td>
                        <td>"""),_display_(/*62.30*/user/*62.34*/.name),format.raw/*62.39*/("""</td>
                        <td>"""),_display_(/*63.30*/user/*63.34*/.email),format.raw/*63.40*/("""</td>
                        <td>"""),_display_(/*64.30*/user/*64.34*/.phone),format.raw/*64.40*/("""</td>
                    </tr>
                """)))}),format.raw/*66.18*/("""
            """),format.raw/*67.13*/("""</tbody>
        </table>
    """)))}),format.raw/*69.6*/("""

    """),format.raw/*71.5*/("""<h2>Kafka Events</h2>
    """),_display_(if(kafkaEvents.isEmpty)/*72.29*/ {_display_(Seq[Any](format.raw/*72.31*/("""
        """),format.raw/*73.9*/("""<p>No events consumed yet.</p>
    """)))}else/*74.12*/{_display_(Seq[Any](format.raw/*74.13*/("""
        """),format.raw/*75.9*/("""<table>
            <thead>
                <tr>
                    <th>Event Type</th>
                    <th>Timestamp</th>
                    <th>User ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                </tr>
            </thead>
            <tbody>
                """),_display_(/*87.18*/for(event <- kafkaEvents) yield /*87.43*/ {_display_(Seq[Any](format.raw/*87.45*/("""
                    """),format.raw/*88.21*/("""<tr>
                        <td>"""),_display_(/*89.30*/event/*89.35*/.eventType),format.raw/*89.45*/("""</td>
                        <td>"""),_display_(/*90.30*/event/*90.35*/.timestamp),format.raw/*90.45*/("""</td>
                        <td>"""),_display_(/*91.30*/event/*91.35*/.user.id),format.raw/*91.43*/("""</td>
                        <td>"""),_display_(/*92.30*/event/*92.35*/.user.name),format.raw/*92.45*/("""</td>
                        <td>"""),_display_(/*93.30*/event/*93.35*/.user.email),format.raw/*93.46*/("""</td>
                        <td>"""),_display_(/*94.30*/event/*94.35*/.user.phone),format.raw/*94.46*/("""</td>
                    </tr>
                """)))}),format.raw/*96.18*/("""
            """),format.raw/*97.13*/("""</tbody>
        </table>
    """)))}),format.raw/*99.6*/("""
""")))}),format.raw/*100.2*/("""
"""))
      }
    }
  }

  def render(form:Form[_root_.users.UserForm],userList:Seq[_root_.users.User],kafkaEvents:Seq[_root_.users.UserCreatedEvent],request:RequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(form,userList,kafkaEvents)(request)

  def f:((Form[_root_.users.UserForm],Seq[_root_.users.User],Seq[_root_.users.UserCreatedEvent]) => (RequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (form,userList,kafkaEvents) => (request) => apply(form,userList,kafkaEvents)(request)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/userForm.scala.html
                  HASH: 9d1101385c7fd175d6e24c4c3576ad9036087d5d
                  MATRIX: 863->1|1108->153|1135->155|1163->175|1202->177|1233->182|1538->461|1554->468|1588->493|1639->506|1675->515|1724->537|1752->544|1794->556|1827->563|1842->569|1905->623|1945->625|1982->635|1997->641|2033->656|2070->666|2226->795|2247->807|2288->827|2340->852|2353->856|2380->874|2429->885|2474->902|2525->926|2539->931|2568->939|2619->959|2655->968|2832->1118|2854->1131|2895->1151|2947->1176|2960->1180|2988->1199|3037->1210|3082->1227|3133->1251|3147->1256|3176->1264|3227->1284|3263->1293|3438->1441|3460->1454|3501->1474|3553->1499|3566->1503|3594->1522|3643->1533|3688->1550|3739->1574|3753->1579|3782->1587|3833->1607|3869->1616|3961->1678|3994->1684|4067->1730|4107->1732|4143->1741|4198->1779|4237->1780|4273->1789|4565->2054|4602->2075|4642->2077|4691->2098|4752->2132|4765->2136|4789->2139|4851->2174|4864->2178|4890->2183|4952->2218|4965->2222|4992->2228|5054->2263|5067->2267|5094->2273|5174->2322|5215->2335|5276->2366|5309->2372|5386->2422|5426->2424|5462->2433|5521->2475|5560->2476|5596->2485|5972->2834|6013->2859|6053->2861|6102->2882|6163->2916|6177->2921|6208->2931|6270->2966|6284->2971|6315->2981|6377->3016|6391->3021|6420->3029|6482->3064|6496->3069|6527->3079|6589->3114|6603->3119|6635->3130|6697->3165|6711->3170|6743->3181|6823->3230|6864->3243|6925->3274|6958->3276
                  LINES: 22->1|27->2|28->3|28->3|28->3|29->4|36->11|36->11|36->11|36->11|37->12|37->12|37->12|38->13|40->15|40->15|40->15|40->15|41->16|41->16|41->16|43->18|45->20|45->20|45->20|46->21|46->21|46->21|46->21|47->22|47->22|47->22|47->22|48->23|49->24|53->28|53->28|53->28|54->29|54->29|54->29|54->29|55->30|55->30|55->30|55->30|56->31|57->32|61->36|61->36|61->36|62->37|62->37|62->37|62->37|63->38|63->38|63->38|63->38|64->39|65->40|68->43|70->45|71->46|71->46|72->47|73->48|73->48|74->49|84->59|84->59|84->59|85->60|86->61|86->61|86->61|87->62|87->62|87->62|88->63|88->63|88->63|89->64|89->64|89->64|91->66|92->67|94->69|96->71|97->72|97->72|98->73|99->74|99->74|100->75|112->87|112->87|112->87|113->88|114->89|114->89|114->89|115->90|115->90|115->90|116->91|116->91|116->91|117->92|117->92|117->92|118->93|118->93|118->93|119->94|119->94|119->94|121->96|122->97|124->99|125->100
                  -- GENERATED --
              */
          