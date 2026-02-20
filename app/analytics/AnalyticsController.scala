package analytics

import javax.inject.*
import play.api.mvc.*

import scala.concurrent.ExecutionContext

@Singleton
class AnalyticsController @Inject()(
  val controllerComponents: ControllerComponents,
  analyticsService: AnalyticsService
)(using ec: ExecutionContext) extends BaseController:

  def dashboard() = Action.async { implicit request: Request[AnyContent] =>
    analyticsService.dashboard().map { data =>
      Ok(views.html.analyticsDashboard(data))
    }
  }
