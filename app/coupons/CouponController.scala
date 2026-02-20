package coupons

import javax.inject.*
import play.api.data.Form
import play.api.data.Forms.*
import play.api.data.format.Formats.doubleFormat
import play.api.mvc.*

import scala.concurrent.{ExecutionContext, Future}

case class CouponForm(code: String, orderTotal: Double)

@Singleton
class CouponController @Inject()(
  val controllerComponents: ControllerComponents,
  couponRepository: CouponRepository
)(using ec: ExecutionContext) extends BaseController:

  private val couponForm = Form(
    mapping(
      "code" -> text,
      "orderTotal" -> of[Double]
    )(CouponForm.apply)(cf => Some(cf.code, cf.orderTotal))
  )

  def showForm() = Action.async { implicit request: Request[AnyContent] =>
    couponRepository.list().map { coupons =>
      Ok(views.html.couponValidator(couponForm, coupons, None))
    }
  }

  def validateCoupon() = Action.async { implicit request: Request[AnyContent] =>
    couponForm.bindFromRequest().fold(
      formWithErrors =>
        couponRepository.list().map { coupons =>
          BadRequest(views.html.couponValidator(formWithErrors, coupons, None))
        },
      data =>
        for
          coupons <- couponRepository.list()
          couponOpt <- couponRepository.findByCode(data.code.trim.toUpperCase)
        yield
          val result = CouponValidator.validate(data.code, couponOpt, data.orderTotal)
          Ok(views.html.couponValidator(couponForm.fill(data), coupons, Some(result)))
    )
  }
