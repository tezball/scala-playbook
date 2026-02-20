package reviews

import javax.inject.*
import play.api.data.Form
import play.api.data.Forms.*
import play.api.mvc.*

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ReviewController @Inject()(
  val controllerComponents: ControllerComponents,
  reviewRepository: ReviewRepository
)(using ec: ExecutionContext) extends BaseController:

  private val reviewForm = Form(
    mapping(
      "productName" -> nonEmptyText,
      "author" -> nonEmptyText,
      "rating" -> number(min = 1, max = 5),
      "comment" -> nonEmptyText
    )(ReviewForm.apply)(rf => Some(rf.productName, rf.author, rf.rating, rf.comment))
  )

  def showForm() = Action.async { implicit request: Request[AnyContent] =>
    val sortBy = request.getQueryString("sort").getOrElse("rating")
    reviewRepository.list().map { reviews =>
      val sorted = sortBy match
        case "date"   => reviews.sorted(using ReviewOrderings.byDate)
        case "author" => reviews.sorted(using ReviewOrderings.byAuthor)
        case _        => reviews.sorted(using ReviewOrderings.byRating)
      val showOutput = display(reviews)(using summon[Show[Review]])
      Ok(views.html.reviewBoard(reviewForm, sorted, showOutput, sortBy))
    }
  }

  def addReview() = Action.async { implicit request: Request[AnyContent] =>
    reviewForm.bindFromRequest().fold(
      formWithErrors =>
        reviewRepository.list().map { reviews =>
          val sorted = reviews.sorted(using ReviewOrderings.byRating)
          val showOutput = display(reviews)(using summon[Show[Review]])
          BadRequest(views.html.reviewBoard(formWithErrors, sorted, showOutput, "rating"))
        },
      data =>
        reviewRepository.create(data.productName, data.author, data.rating, data.comment).map { _ =>
          Redirect(routes.ReviewController.showForm()).flashing("success" -> "Review added!")
        }
    )
  }
