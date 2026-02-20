package products

import javax.inject.*
import play.api.data.Form
import play.api.data.Forms.*
import play.api.data.format.Formats.doubleFormat
import play.api.mvc.*

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ProductController @Inject()(
  val controllerComponents: ControllerComponents,
  productRepository: ProductRepository
)(using ec: ExecutionContext) extends BaseController:

  private val productForm = Form(
    mapping(
      "name" -> nonEmptyText,
      "category" -> nonEmptyText,
      "basePrice" -> of[Double],
      "discountType" -> text,
      "discountValue" -> of[Double]
    )(ProductForm.apply)(pf => Some(pf.name, pf.category, pf.basePrice, pf.discountType, pf.discountValue))
  )

  def showForm() = Action.async { implicit request: Request[AnyContent] =>
    productRepository.list().map { products =>
      Ok(views.html.productCatalog(productForm, products))
    }
  }

  def addProduct() = Action.async { implicit request: Request[AnyContent] =>
    productForm.bindFromRequest().fold(
      formWithErrors =>
        productRepository.list().map { products =>
          BadRequest(views.html.productCatalog(formWithErrors, products))
        },
      data =>
        val category = PricingEngine.parseCategory(data.category)
        val discount = PricingEngine.parseDiscount(data.discountType, data.discountValue)
        val finalPrice = PricingEngine.computeFinalPrice(data.basePrice, category, discount)
        productRepository.create(data.name, data.category, data.basePrice, data.discountType, data.discountValue, finalPrice).map { _ =>
          Redirect(routes.ProductController.showForm()).flashing("success" -> s"Product added! Final price: $$${finalPrice}")
        }
    )
  }
