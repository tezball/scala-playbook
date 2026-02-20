
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

        <button type="submit" class="btn-blue">Process Notification</button>
    """)))}),format.raw/*54.6*/("""

    """),_display_(/*56.6*/result/*56.12*/.map/*56.16*/ { case (steps, routed) =>_display_(Seq[Any](format.raw/*56.42*/("""
        """),format.raw/*57.9*/("""<h2>Pipeline Steps</h2>

        """),_display_(/*59.10*/for(step <- steps) yield /*59.28*/ {_display_(Seq[Any](format.raw/*59.30*/("""
            """),format.raw/*60.13*/("""<div class="step">
                <strong>"""),_display_(/*61.26*/step/*61.30*/.name),format.raw/*61.35*/("""</strong>
                <table style="margin-top:4px">
                    <tr>
                        <td style="width:60px;color:#999">Before:</td>
                        <td><code>"""),_display_(/*65.36*/step/*65.40*/.before.message),format.raw/*65.55*/("""</code></td>
                    </tr>
                    <tr>
                        <td style="color:#999">After:</td>
                        <td><code>"""),_display_(/*69.36*/step/*69.40*/.after.message),format.raw/*69.54*/("""</code></td>
                    </tr>
                    """),_display_(if(step.after.metadata != step.before.metadata)/*71.69*/ {_display_(Seq[Any](format.raw/*71.71*/("""
                        """),format.raw/*72.25*/("""<tr>
                            <td style="color:#999">Meta:</td>
                            <td><code>"""),_display_(/*74.40*/step/*74.44*/.after.metadata.mkString(", ")),format.raw/*74.74*/("""</code></td>
                        </tr>
                    """)))} else {null} ),format.raw/*76.22*/("""
                """),format.raw/*77.17*/("""</table>
            </div>
        """)))}),format.raw/*79.10*/("""

        """),format.raw/*81.9*/("""<div class="card" style="margin-top:16px">
            <h3>Final Output (<code>PartialFunction.orElse</code> routing)</h3>
            <p>"""),_display_(/*83.17*/routed),format.raw/*83.23*/("""</p>
        </div>
    """)))}),format.raw/*85.6*/("""
""")))}),format.raw/*86.2*/("""
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
                  HASH: ec1d0eb9d74aac1480183c1fb2430afeffc66227
                  MATRIX: 890->1|1134->152|1161->154|1213->198|1252->200|1283->205|2239->1133|2268->1134|2307->1145|2336->1146|2880->1664|2895->1670|2989->1755|3029->1757|3066->1767|3081->1773|3117->1788|3154->1798|3386->2003|3425->2004|3478->2013|3599->2107|3638->2108|3691->2117|3812->2211|3851->2212|3904->2221|4141->2431|4167->2448|4209->2468|4477->2710|4501->2725|4543->2745|4683->2855|4716->2862|4731->2868|4744->2872|4808->2898|4844->2907|4905->2941|4939->2959|4979->2961|5020->2974|5091->3018|5104->3022|5130->3027|5345->3215|5358->3219|5394->3234|5579->3392|5592->3396|5627->3410|5761->3517|5801->3519|5854->3544|5987->3650|6000->3654|6051->3684|6159->3748|6204->3765|6272->3802|6309->3812|6475->3951|6502->3957|6557->3982|6589->3984
                  LINES: 22->1|27->2|28->3|28->3|28->3|29->4|45->20|45->20|45->20|45->20|56->31|56->31|56->31|56->31|57->32|57->32|57->32|59->34|62->37|62->37|62->37|63->38|63->38|63->38|64->39|64->39|64->39|70->45|70->45|70->45|75->50|75->50|75->50|79->54|81->56|81->56|81->56|81->56|82->57|84->59|84->59|84->59|85->60|86->61|86->61|86->61|90->65|90->65|90->65|94->69|94->69|94->69|96->71|96->71|97->72|99->74|99->74|99->74|101->76|102->77|104->79|106->81|108->83|108->83|110->85|111->86
                  -- GENERATED --
              */
          