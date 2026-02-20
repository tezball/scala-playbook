
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

        <button type="submit">Save User</button>
    """)))}),format.raw/*55.6*/("""

    """),format.raw/*57.5*/("""<h2>Saved Users</h2>
    """),_display_(if(userList.isEmpty)/*58.26*/ {_display_(Seq[Any](format.raw/*58.28*/("""
        """),format.raw/*59.9*/("""<p>No users saved yet.</p>
    """)))}else/*60.12*/{_display_(Seq[Any](format.raw/*60.13*/("""
        """),format.raw/*61.9*/("""<table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                </tr>
            </thead>
            <tbody>
                """),_display_(/*71.18*/for(user <- userList) yield /*71.39*/ {_display_(Seq[Any](format.raw/*71.41*/("""
                    """),format.raw/*72.21*/("""<tr>
                        <td>"""),_display_(/*73.30*/user/*73.34*/.id),format.raw/*73.37*/("""</td>
                        <td>"""),_display_(/*74.30*/user/*74.34*/.name),format.raw/*74.39*/("""</td>
                        <td>"""),_display_(/*75.30*/user/*75.34*/.email),format.raw/*75.40*/("""</td>
                        <td>"""),_display_(/*76.30*/user/*76.34*/.phone),format.raw/*76.40*/("""</td>
                    </tr>
                """)))}),format.raw/*78.18*/("""
            """),format.raw/*79.13*/("""</tbody>
        </table>
    """)))}),format.raw/*81.6*/("""
""")))}),format.raw/*82.2*/("""
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
                  HASH: b029ff10243533df05517b4d121e420c44e844fb
                  MATRIX: 828->1|1024->104|1051->106|1079->126|1118->128|1149->133|2139->1097|2155->1104|2189->1129|2240->1142|2276->1151|2325->1173|2353->1180|2395->1192|2428->1199|2443->1205|2506->1259|2546->1261|2583->1271|2598->1277|2634->1292|2671->1302|2827->1431|2848->1443|2889->1463|2941->1488|2954->1492|2981->1510|3030->1521|3075->1538|3126->1562|3140->1567|3169->1575|3220->1595|3256->1604|3433->1754|3455->1767|3496->1787|3548->1812|3561->1816|3589->1835|3638->1846|3683->1863|3734->1887|3748->1892|3777->1900|3828->1920|3864->1929|4039->2077|4061->2090|4102->2110|4154->2135|4167->2139|4195->2158|4244->2169|4289->2186|4340->2210|4354->2215|4383->2223|4434->2243|4470->2252|4562->2314|4595->2320|4668->2366|4708->2368|4744->2377|4799->2415|4838->2416|4874->2425|5166->2690|5203->2711|5243->2713|5292->2734|5353->2768|5366->2772|5390->2775|5452->2810|5465->2814|5491->2819|5553->2854|5566->2858|5593->2864|5655->2899|5668->2903|5695->2909|5775->2958|5816->2971|5877->3002|5909->3004
                  LINES: 22->1|27->2|28->3|28->3|28->3|29->4|48->23|48->23|48->23|48->23|49->24|49->24|49->24|50->25|52->27|52->27|52->27|52->27|53->28|53->28|53->28|55->30|57->32|57->32|57->32|58->33|58->33|58->33|58->33|59->34|59->34|59->34|59->34|60->35|61->36|65->40|65->40|65->40|66->41|66->41|66->41|66->41|67->42|67->42|67->42|67->42|68->43|69->44|73->48|73->48|73->48|74->49|74->49|74->49|74->49|75->50|75->50|75->50|75->50|76->51|77->52|80->55|82->57|83->58|83->58|84->59|85->60|85->60|86->61|96->71|96->71|96->71|97->72|98->73|98->73|98->73|99->74|99->74|99->74|100->75|100->75|100->75|101->76|101->76|101->76|103->78|104->79|106->81|107->82
                  -- GENERATED --
              */
          