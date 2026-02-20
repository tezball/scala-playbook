package cart

import javax.inject.*
import play.api.data.Form
import play.api.data.Forms.*
import play.api.data.format.Formats.doubleFormat
import play.api.mvc.*

@Singleton
class CartController @Inject()(
  val controllerComponents: ControllerComponents
) extends BaseController:

  private val defaultItems = Seq(
    CartItem("Wireless Mouse", "Electronics", 29.99, 2),
    CartItem("USB-C Cable", "Electronics", 12.99, 3),
    CartItem("Scala Programming", "Books", 45.00, 1),
    CartItem("Clean Code", "Books", 35.50, 1),
    CartItem("Cotton T-Shirt", "Clothing", 18.99, 4),
    CartItem("Running Shoes", "Clothing", 89.99, 1),
    CartItem("Organic Coffee", "Food", 15.99, 2),
    CartItem("Protein Bars (12pk)", "Food", 24.99, 3),
    CartItem("Yoga Mat", "Sports", 34.99, 1),
    CartItem("Water Bottle", "Sports", 9.99, 5)
  )

  private val cartItemForm = Form(
    mapping(
      "name" -> nonEmptyText,
      "category" -> nonEmptyText,
      "unitPrice" -> of[Double],
      "quantity" -> number(min = 1)
    )(CartItemForm.apply)(cf => Some(cf.name, cf.category, cf.unitPrice, cf.quantity))
  )

  private def getSessionCart(implicit request: Request[AnyContent]): Seq[CartItem] =
    request.session.get("cart").map { cartData =>
      cartData.split("\\|\\|").filter(_.nonEmpty).map { entry =>
        val parts = entry.split(";;")
        CartItem(parts(0), parts(1), parts(2).toDouble, parts(3).toInt)
      }.toSeq
    }.getOrElse(defaultItems)

  private def serializeCart(items: Seq[CartItem]): String =
    items.map(i => s"${i.name};;${i.category};;${i.unitPrice};;${i.quantity}").mkString("||")

  def showCart() = Action { implicit request: Request[AnyContent] =>
    val items = getSessionCart
    Ok(views.html.cartDemo(cartItemForm, items))
  }

  def addItem() = Action { implicit request: Request[AnyContent] =>
    cartItemForm.bindFromRequest().fold(
      formWithErrors =>
        val items = getSessionCart
        BadRequest(views.html.cartDemo(formWithErrors, items)),
      data =>
        val items = getSessionCart
        val newItem = CartItem(data.name, data.category, data.unitPrice, data.quantity)
        val updatedItems = items :+ newItem
        Redirect(routes.CartController.showCart())
          .withSession("cart" -> serializeCart(updatedItems))
          .flashing("success" -> s"Added ${data.name} to cart!")
    )
  }
