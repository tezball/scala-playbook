
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

object userForm extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[Form[_root_.users.UserForm],Seq[_root_.users.User],RequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(form: Form[_root_.users.UserForm], userList: Seq[_root_.users.User])(implicit request: RequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),_display_(/*3.2*/main("User Details")/*3.22*/ {_display_(Seq[Any](format.raw/*3.24*/("""
    """),format.raw/*4.5*/("""<h1>User Details</h1>

    <div class="concept-banner">
        <h3>Concepts: Slick Table Mappings, Case Classes, Repository Pattern, Futures</h3>
        <p>User data is persisted via Slick ORM. A <code>case class User</code> maps directly to a database table through a <code>Table[User]</code> definition. All queries return <code>Future</code> and follow the repository pattern.</p>
        <pre><code>case class User(id: Long, name: String, email: String, phone: String)

class UsersTable(tag: Tag) extends Table[User](tag, "users"):
  def id    = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name  = column[String]("name")
  def email = column[String]("email")
  def phone = column[String]("phone")
  def * = (id, name, email, phone).mapTo[User]

// Repository pattern - all DB ops return Future
def list(): Future[Seq[User]] = db.run(users.result)
def create(name: String, ...): Future[User] = db.run(insertQuery += (...))</code></pre>
    </div>

    """),_display_(/*23.6*/request/*23.13*/.flash.get("success").map/*23.38*/ { message =>_display_(Seq[Any](format.raw/*23.51*/("""
        """),format.raw/*24.9*/("""<div class="success">"""),_display_(/*24.31*/message),format.raw/*24.38*/("""</div>
    """)))}),format.raw/*25.6*/("""

    """),_display_(/*27.6*/helper/*27.12*/.form(action = users.routes.UserController.saveUser())/*27.66*/ {_display_(Seq[Any](format.raw/*27.68*/("""
        """),_display_(/*28.10*/helper/*28.16*/.CSRF.formField),format.raw/*28.31*/("""

        """),format.raw/*30.9*/("""<div class="form-group">
            <label for="name">Name</label>
            <input type="text" id="name" name="name" value=""""),_display_(/*32.62*/form("name")/*32.74*/.value.getOrElse("")),format.raw/*32.94*/("""" required>
            """),_display_(/*33.14*/form/*33.18*/.error("name").map/*33.36*/ { error =>_display_(Seq[Any](format.raw/*33.47*/("""
                """),format.raw/*34.17*/("""<div class="error-msg">"""),_display_(/*34.41*/error/*34.46*/.message),format.raw/*34.54*/("""</div>
            """)))}),format.raw/*35.14*/("""
        """),format.raw/*36.9*/("""</div>

        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" value=""""),_display_(/*40.65*/form("email")/*40.78*/.value.getOrElse("")),format.raw/*40.98*/("""" required>
            """),_display_(/*41.14*/form/*41.18*/.error("email").map/*41.37*/ { error =>_display_(Seq[Any](format.raw/*41.48*/("""
                """),format.raw/*42.17*/("""<div class="error-msg">"""),_display_(/*42.41*/error/*42.46*/.message),format.raw/*42.54*/("""</div>
            """)))}),format.raw/*43.14*/("""
        """),format.raw/*44.9*/("""</div>

        <div class="form-group">
            <label for="phone">Phone</label>
            <input type="tel" id="phone" name="phone" value=""""),_display_(/*48.63*/form("phone")/*48.76*/.value.getOrElse("")),format.raw/*48.96*/("""" required>
            """),_display_(/*49.14*/form/*49.18*/.error("phone").map/*49.37*/ { error =>_display_(Seq[Any](format.raw/*49.48*/("""
                """),format.raw/*50.17*/("""<div class="error-msg">"""),_display_(/*50.41*/error/*50.46*/.message),format.raw/*50.54*/("""</div>
            """)))}),format.raw/*51.14*/("""
        """),format.raw/*52.9*/("""</div>

        <div class="btn-group">
            <button type="submit">Save User</button>
            <button type="button" class="btn-autofill" onclick="autoFillUser()">Auto Fill</button>
        </div>
    """)))}),format.raw/*58.6*/("""

    """),format.raw/*60.5*/("""<script>
    function autoFillUser() """),format.raw/*61.29*/("""{"""),format.raw/*61.30*/("""
        """),format.raw/*62.9*/("""var users = [
            """),format.raw/*63.13*/("""{"""),format.raw/*63.14*/(""" """),format.raw/*63.15*/("""name: 'Alice Johnson', email: 'alice.johnson@example.com', phone: '555-0101' """),format.raw/*63.93*/("""}"""),format.raw/*63.94*/(""",
            """),format.raw/*64.13*/("""{"""),format.raw/*64.14*/(""" """),format.raw/*64.15*/("""name: 'Bob Martinez', email: 'bob.martinez@shopmail.com', phone: '555-0202' """),format.raw/*64.92*/("""}"""),format.raw/*64.93*/(""",
            """),format.raw/*65.13*/("""{"""),format.raw/*65.14*/(""" """),format.raw/*65.15*/("""name: 'Carol Chen', email: 'carol.chen@techcorp.io', phone: '555-0303' """),format.raw/*65.87*/("""}"""),format.raw/*65.88*/(""",
            """),format.raw/*66.13*/("""{"""),format.raw/*66.14*/(""" """),format.raw/*66.15*/("""name: 'David Kim', email: 'david.kim@startup.dev', phone: '555-0404' """),format.raw/*66.85*/("""}"""),format.raw/*66.86*/(""",
            """),format.raw/*67.13*/("""{"""),format.raw/*67.14*/(""" """),format.raw/*67.15*/("""name: 'Eva Rossi', email: 'eva.rossi@designlab.co', phone: '555-0505' """),format.raw/*67.86*/("""}"""),format.raw/*67.87*/(""",
            """),format.raw/*68.13*/("""{"""),format.raw/*68.14*/(""" """),format.raw/*68.15*/("""name: 'Frank Okafor', email: 'frank.okafor@globalshop.com', phone: '555-0606' """),format.raw/*68.94*/("""}"""),format.raw/*68.95*/("""
        """),format.raw/*69.9*/("""];
        var u = users[Math.floor(Math.random() * users.length)];
        document.getElementById('name').value = u.name;
        document.getElementById('email').value = u.email;
        document.getElementById('phone').value = u.phone;
    """),format.raw/*74.5*/("""}"""),format.raw/*74.6*/("""
    """),format.raw/*75.5*/("""</script>

    <h2>Saved Users</h2>
    """),_display_(if(userList.isEmpty)/*78.26*/ {_display_(Seq[Any](format.raw/*78.28*/("""
        """),format.raw/*79.9*/("""<p>No users saved yet.</p>
    """)))}else/*80.12*/{_display_(Seq[Any](format.raw/*80.13*/("""
        """),format.raw/*81.9*/("""<table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                </tr>
            </thead>
            <tbody>
                """),_display_(/*91.18*/for(user <- userList) yield /*91.39*/ {_display_(Seq[Any](format.raw/*91.41*/("""
                    """),format.raw/*92.21*/("""<tr>
                        <td>"""),_display_(/*93.30*/user/*93.34*/.id),format.raw/*93.37*/("""</td>
                        <td>"""),_display_(/*94.30*/user/*94.34*/.name),format.raw/*94.39*/("""</td>
                        <td>"""),_display_(/*95.30*/user/*95.34*/.email),format.raw/*95.40*/("""</td>
                        <td>"""),_display_(/*96.30*/user/*96.34*/.phone),format.raw/*96.40*/("""</td>
                    </tr>
                """)))}),format.raw/*98.18*/("""
            """),format.raw/*99.13*/("""</tbody>
        </table>
    """)))}),format.raw/*101.6*/("""
""")))}),format.raw/*102.2*/("""
"""))
      }
    }
  }

  def render(form:Form[_root_.users.UserForm],userList:Seq[_root_.users.User],request:RequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(form,userList)(request)

  def f:((Form[_root_.users.UserForm],Seq[_root_.users.User]) => (RequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (form,userList) => (request) => apply(form,userList)(request)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/userForm.scala.html
                  HASH: 01388dc01454500cdcdfbd16421a379db1ef7ede
                  MATRIX: 828->1|1024->104|1051->106|1079->126|1118->128|1149->133|2139->1097|2155->1104|2189->1129|2240->1142|2276->1151|2325->1173|2353->1180|2395->1192|2428->1199|2443->1205|2506->1259|2546->1261|2583->1271|2598->1277|2634->1292|2671->1302|2827->1431|2848->1443|2889->1463|2941->1488|2954->1492|2981->1510|3030->1521|3075->1538|3126->1562|3140->1567|3169->1575|3220->1595|3256->1604|3433->1754|3455->1767|3496->1787|3548->1812|3561->1816|3589->1835|3638->1846|3683->1863|3734->1887|3748->1892|3777->1900|3828->1920|3864->1929|4039->2077|4061->2090|4102->2110|4154->2135|4167->2139|4195->2158|4244->2169|4289->2186|4340->2210|4354->2215|4383->2223|4434->2243|4470->2252|4712->2464|4745->2470|4810->2507|4839->2508|4875->2517|4929->2543|4958->2544|4987->2545|5092->2623|5121->2624|5163->2638|5192->2639|5221->2640|5325->2717|5354->2718|5396->2732|5425->2733|5454->2734|5553->2806|5582->2807|5624->2821|5653->2822|5682->2823|5779->2893|5808->2894|5850->2908|5879->2909|5908->2910|6006->2981|6035->2982|6077->2996|6106->2997|6135->2998|6241->3077|6270->3078|6306->3087|6577->3331|6605->3332|6637->3337|6725->3398|6765->3400|6801->3409|6856->3447|6895->3448|6931->3457|7223->3722|7260->3743|7300->3745|7349->3766|7410->3800|7423->3804|7447->3807|7509->3842|7522->3846|7548->3851|7610->3886|7623->3890|7650->3896|7712->3931|7725->3935|7752->3941|7832->3990|7873->4003|7935->4034|7968->4036
                  LINES: 22->1|27->2|28->3|28->3|28->3|29->4|48->23|48->23|48->23|48->23|49->24|49->24|49->24|50->25|52->27|52->27|52->27|52->27|53->28|53->28|53->28|55->30|57->32|57->32|57->32|58->33|58->33|58->33|58->33|59->34|59->34|59->34|59->34|60->35|61->36|65->40|65->40|65->40|66->41|66->41|66->41|66->41|67->42|67->42|67->42|67->42|68->43|69->44|73->48|73->48|73->48|74->49|74->49|74->49|74->49|75->50|75->50|75->50|75->50|76->51|77->52|83->58|85->60|86->61|86->61|87->62|88->63|88->63|88->63|88->63|88->63|89->64|89->64|89->64|89->64|89->64|90->65|90->65|90->65|90->65|90->65|91->66|91->66|91->66|91->66|91->66|92->67|92->67|92->67|92->67|92->67|93->68|93->68|93->68|93->68|93->68|94->69|99->74|99->74|100->75|103->78|103->78|104->79|105->80|105->80|106->81|116->91|116->91|116->91|117->92|118->93|118->93|118->93|119->94|119->94|119->94|120->95|120->95|120->95|121->96|121->96|121->96|123->98|124->99|126->101|127->102
                  -- GENERATED --
              */
          