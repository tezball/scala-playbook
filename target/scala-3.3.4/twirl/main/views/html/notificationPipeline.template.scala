
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

object notificationPipeline extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[Form[_root_.notifications.NotificationForm],Option[(Seq[_root_.notifications.PipelineStep], String)],RequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(form: Form[_root_.notifications.NotificationForm], result: Option[(Seq[_root_.notifications.PipelineStep], String)])(implicit request: RequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),_display_(/*3.2*/main("Notifications - Function Composition")/*3.46*/ {_display_(Seq[Any](format.raw/*3.48*/("""
    """),format.raw/*4.5*/("""<h1>Notification Pipeline</h1>

    <div class="concept-banner">
        <h3>Concepts: Function Composition &mdash; andThen, compose, pipe, tap, PartialFunction, Currying</h3>
        <p>A notification passes through a pipeline of composed functions. Each stage transforms the notification, and routing uses <code>PartialFunction.orElse</code> to dispatch by channel.</p>
        <pre><code>// Individual pipeline stages as functions
val sanitize: Notification =&gt; Notification = n =&gt;
  n.copy(message = n.message.replaceAll("&lt;[^&gt;]*&gt;", "").trim)
val addTimestamp: Notification =&gt; Notification = ...
val truncate: Notification =&gt; Notification = ...

// andThen - compose left to right
val pipeline = sanitize.andThen(addTimestamp).andThen(truncate)

// PartialFunction for routing by channel
val emailHandler: PartialFunction[Notification, String] =
  case n if n.channel == "email" =&gt; s"Sending email to $"""),format.raw/*20.60*/("""{"""),format.raw/*20.61*/("""n.recipient"""),format.raw/*20.72*/("""}"""),format.raw/*20.73*/(""""
val route = emailHandler.orElse(smsHandler).orElse(pushHandler)

// Curried function for reusable formatters
def formatForChannel(maxLen: Int)(prefix: String)(n: Notification): Notification = ...
val formatForSms = formatForChannel(160)("[SMS]")</code></pre>
    </div>

    <h2>Send a Notification</h2>
    <p>Try including HTML tags like <code>&lt;b&gt;bold&lt;/b&gt;</code> or <code>&lt;script&gt;alert('hi')&lt;/script&gt;</code> to see sanitization. Try a long message (100+ chars) to see truncation.</p>

    """),_display_(/*31.6*/helper/*31.12*/.form(action = _root_.notifications.routes.NotificationController.sendNotification())/*31.97*/ {_display_(Seq[Any](format.raw/*31.99*/("""
        """),_display_(/*32.10*/helper/*32.16*/.CSRF.formField),format.raw/*32.31*/("""

        """),format.raw/*34.9*/("""<div class="form-group">
            <label for="channel">Channel</label>
            <select id="channel" name="channel">
                <option value="email" """),_display_(if(form("channel").value.contains("email"))/*37.83*/{_display_(Seq[Any](format.raw/*37.84*/("""selected""")))} else {null} ),format.raw/*37.93*/(""">Email</option>
                <option value="sms" """),_display_(if(form("channel").value.contains("sms"))/*38.79*/{_display_(Seq[Any](format.raw/*38.80*/("""selected""")))} else {null} ),format.raw/*38.89*/(""">SMS</option>
                <option value="push" """),_display_(if(form("channel").value.contains("push"))/*39.81*/{_display_(Seq[Any](format.raw/*39.82*/("""selected""")))} else {null} ),format.raw/*39.91*/(""">Push</option>
            </select>
        </div>

        <div class="form-group">
            <label for="recipient">Recipient</label>
            <input type="text" id="recipient" name="recipient" value=""""),_display_(/*45.72*/form("recipient")/*45.89*/.value.getOrElse("")),format.raw/*45.109*/("""" placeholder="user@example.com" required>
        </div>

        <div class="form-group">
            <label for="message">Message (try HTML tags and long text!)</label>
            <textarea id="message" name="message" rows="3" required>"""),_display_(/*50.70*/form("message")/*50.85*/.value.getOrElse("")),format.raw/*50.105*/("""</textarea>
        </div>

        <div class="btn-group">
            <button type="submit" class="btn-blue">Process Notification</button>
            <button type="button" class="btn-autofill" onclick="autoFillNotification()">Auto Fill</button>
        </div>
    """)))}),format.raw/*57.6*/("""

    """),format.raw/*59.5*/("""<script>
    function autoFillNotification() """),format.raw/*60.37*/("""{"""),format.raw/*60.38*/("""
        """),format.raw/*61.9*/("""var notifications = [
            """),format.raw/*62.13*/("""{"""),format.raw/*62.14*/(""" """),format.raw/*62.15*/("""ch: 'email', to: 'alice.johnson@example.com', msg: 'Your order <b>#1042</b> has shipped! <a href="#">Track it here</a>. Expected delivery: 3-5 business days.' """),format.raw/*62.175*/("""}"""),format.raw/*62.176*/(""",
            """),format.raw/*63.13*/("""{"""),format.raw/*63.14*/(""" """),format.raw/*63.15*/("""ch: 'sms', to: '+1-555-0101', msg: 'Hi Alice, your prescription is ready for pickup at Walgreens on Main St. Reply STOP to unsubscribe.' """),format.raw/*63.152*/("""}"""),format.raw/*63.153*/(""",
            """),format.raw/*64.13*/("""{"""),format.raw/*64.14*/(""" """),format.raw/*64.15*/("""ch: 'push', to: 'bob.martinez', msg: 'Flash sale! <strong>50% off</strong> all electronics for the next 2 hours. <script>alert("deal")</script> Shop now!' """),format.raw/*64.170*/("""}"""),format.raw/*64.171*/(""",
            """),format.raw/*65.13*/("""{"""),format.raw/*65.14*/(""" """),format.raw/*65.15*/("""ch: 'email', to: 'carol.chen@techcorp.io', msg: 'Your return for Order #987 has been processed. A refund of $49.99 will appear on your statement within 5-7 business days. Thank you for shopping with us and we hope to see you again soon!' """),format.raw/*65.254*/("""}"""),format.raw/*65.255*/(""",
            """),format.raw/*66.13*/("""{"""),format.raw/*66.14*/(""" """),format.raw/*66.15*/("""ch: 'sms', to: '+44-20-7946-0958', msg: 'Delivery update: Your package is <em>out for delivery</em> and will arrive by 5pm today.' """),format.raw/*66.146*/("""}"""),format.raw/*66.147*/(""",
            """),format.raw/*67.13*/("""{"""),format.raw/*67.14*/(""" """),format.raw/*67.15*/("""ch: 'push', to: 'david.kim', msg: 'Price drop alert! The <b>Wireless Headphones</b> in your wishlist just dropped from $349 to $279.' """),format.raw/*67.149*/("""}"""),format.raw/*67.150*/("""
        """),format.raw/*68.9*/("""];
        var n = notifications[Math.floor(Math.random() * notifications.length)];
        document.getElementById('channel').value = n.ch;
        document.getElementById('recipient').value = n.to;
        document.getElementById('message').value = n.msg;
    """),format.raw/*73.5*/("""}"""),format.raw/*73.6*/("""
    """),format.raw/*74.5*/("""</script>

    """),_display_(/*76.6*/result/*76.12*/.map/*76.16*/ { case (steps, routed) =>_display_(Seq[Any](format.raw/*76.42*/("""
        """),format.raw/*77.9*/("""<h2>Pipeline Steps</h2>

        """),_display_(/*79.10*/for(step <- steps) yield /*79.28*/ {_display_(Seq[Any](format.raw/*79.30*/("""
            """),format.raw/*80.13*/("""<div class="step">
                <strong>"""),_display_(/*81.26*/step/*81.30*/.name),format.raw/*81.35*/("""</strong>
                <table style="margin-top:4px">
                    <tr>
                        <td style="width:60px;color:#999">Before:</td>
                        <td><code>"""),_display_(/*85.36*/step/*85.40*/.before.message),format.raw/*85.55*/("""</code></td>
                    </tr>
                    <tr>
                        <td style="color:#999">After:</td>
                        <td><code>"""),_display_(/*89.36*/step/*89.40*/.after.message),format.raw/*89.54*/("""</code></td>
                    </tr>
                    """),_display_(if(step.after.metadata != step.before.metadata)/*91.69*/ {_display_(Seq[Any](format.raw/*91.71*/("""
                        """),format.raw/*92.25*/("""<tr>
                            <td style="color:#999">Meta:</td>
                            <td><code>"""),_display_(/*94.40*/step/*94.44*/.after.metadata.mkString(", ")),format.raw/*94.74*/("""</code></td>
                        </tr>
                    """)))} else {null} ),format.raw/*96.22*/("""
                """),format.raw/*97.17*/("""</table>
            </div>
        """)))}),format.raw/*99.10*/("""

        """),format.raw/*101.9*/("""<div class="card" style="margin-top:16px">
            <h3>Final Output (<code>PartialFunction.orElse</code> routing)</h3>
            <p>"""),_display_(/*103.17*/routed),format.raw/*103.23*/("""</p>
        </div>
    """)))}),format.raw/*105.6*/("""
""")))}),format.raw/*106.2*/("""
"""))
      }
    }
  }

  def render(form:Form[_root_.notifications.NotificationForm],result:Option[(Seq[_root_.notifications.PipelineStep], String)],request:RequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(form,result)(request)

  def f:((Form[_root_.notifications.NotificationForm],Option[(Seq[_root_.notifications.PipelineStep], String)]) => (RequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (form,result) => (request) => apply(form,result)(request)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/notificationPipeline.scala.html
                  HASH: f946fbe66796d88ebd8343ea944596e968c049f8
                  MATRIX: 890->1|1134->152|1161->154|1213->198|1252->200|1283->205|2239->1133|2268->1134|2307->1145|2336->1146|2880->1664|2895->1670|2989->1755|3029->1757|3066->1767|3081->1773|3117->1788|3154->1798|3386->2003|3425->2004|3478->2013|3599->2107|3638->2108|3691->2117|3812->2211|3851->2212|3904->2221|4141->2431|4167->2448|4209->2468|4477->2710|4501->2725|4543->2745|4841->3013|4874->3019|4947->3064|4976->3065|5012->3074|5074->3108|5103->3109|5132->3110|5320->3270|5350->3271|5392->3285|5421->3286|5450->3287|5616->3424|5646->3425|5688->3439|5717->3440|5746->3441|5930->3596|5960->3597|6002->3611|6031->3612|6060->3613|6327->3852|6357->3853|6399->3867|6428->3868|6457->3869|6617->4000|6647->4001|6689->4015|6718->4016|6747->4017|6910->4151|6940->4152|6976->4161|7265->4423|7293->4424|7325->4429|7367->4445|7382->4451|7395->4455|7459->4481|7495->4490|7556->4524|7590->4542|7630->4544|7671->4557|7742->4601|7755->4605|7781->4610|7996->4798|8009->4802|8045->4817|8230->4975|8243->4979|8278->4993|8412->5100|8452->5102|8505->5127|8638->5233|8651->5237|8702->5267|8810->5331|8855->5348|8923->5385|8961->5395|9128->5534|9156->5540|9212->5565|9245->5567
                  LINES: 22->1|27->2|28->3|28->3|28->3|29->4|45->20|45->20|45->20|45->20|56->31|56->31|56->31|56->31|57->32|57->32|57->32|59->34|62->37|62->37|62->37|63->38|63->38|63->38|64->39|64->39|64->39|70->45|70->45|70->45|75->50|75->50|75->50|82->57|84->59|85->60|85->60|86->61|87->62|87->62|87->62|87->62|87->62|88->63|88->63|88->63|88->63|88->63|89->64|89->64|89->64|89->64|89->64|90->65|90->65|90->65|90->65|90->65|91->66|91->66|91->66|91->66|91->66|92->67|92->67|92->67|92->67|92->67|93->68|98->73|98->73|99->74|101->76|101->76|101->76|101->76|102->77|104->79|104->79|104->79|105->80|106->81|106->81|106->81|110->85|110->85|110->85|114->89|114->89|114->89|116->91|116->91|117->92|119->94|119->94|119->94|121->96|122->97|124->99|126->101|128->103|128->103|130->105|131->106
                  -- GENERATED --
              */
          