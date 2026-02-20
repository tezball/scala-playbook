package products

// Enum for product categories
enum Category:
  case Electronics, Books, Clothing, Food, Sports

// Sealed trait for discount types - each is a different "shape"
sealed trait Discount
case class Percentage(percent: Double) extends Discount
case class FixedAmount(amount: Double) extends Discount
case class BuyOneGetOne(freeItemName: String) extends Discount
case object NoDiscount extends Discount

object PricingEngine:

  // Pattern matching to calculate final price
  def applyDiscount(price: Double, discount: Discount): Double = discount match
    case Percentage(pct)  => price * (1 - pct / 100)
    case FixedAmount(amt) => (price - amt).max(0)
    case BuyOneGetOne(_)  => price // price unchanged, but you get a free item
    case NoDiscount       => price

  // Pattern matching on category for tax rates
  def taxRate(category: Category): Double = category match
    case Category.Food                          => 0.0
    case Category.Books                         => 0.05
    case Category.Clothing                      => 0.08
    case Category.Electronics | Category.Sports => 0.10

  def parseCategory(s: String): Category = s match
    case "Electronics" => Category.Electronics
    case "Books"       => Category.Books
    case "Clothing"    => Category.Clothing
    case "Food"        => Category.Food
    case "Sports"      => Category.Sports
    case _             => Category.Electronics

  def parseDiscount(discountType: String, discountValue: Double): Discount = discountType match
    case "percentage" => Percentage(discountValue)
    case "fixed"      => FixedAmount(discountValue)
    case "bogo"       => BuyOneGetOne("same item")
    case _            => NoDiscount

  def computeFinalPrice(basePrice: Double, category: Category, discount: Discount): Double =
    val afterDiscount = applyDiscount(basePrice, discount)
    val tax = taxRate(category)
    val finalPrice = afterDiscount * (1 + tax)
    BigDecimal(finalPrice).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
