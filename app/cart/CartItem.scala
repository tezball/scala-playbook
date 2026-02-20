package cart

case class CartItem(name: String, category: String, unitPrice: Double, quantity: Int)

case class CartItemForm(name: String, category: String, unitPrice: Double, quantity: Int)
