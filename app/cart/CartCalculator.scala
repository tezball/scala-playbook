package cart

object CartCalculator:

  // map - compute line totals
  def lineTotals(items: Seq[CartItem]): Seq[(CartItem, Double)] =
    items.map(i => (i, i.unitPrice * i.quantity))

  // filter - find expensive items (unit price > 20)
  def expensiveItems(items: Seq[CartItem]): Seq[CartItem] =
    items.filter(_.unitPrice > 20.0)

  // foldLeft - compute grand total
  def grandTotal(items: Seq[CartItem]): Double =
    items.foldLeft(0.0)((total, item) => total + item.unitPrice * item.quantity)

  // groupBy + map - subtotals by category
  def subtotalsByCategory(items: Seq[CartItem]): Map[String, Double] =
    items.groupBy(_.category).map((cat, catItems) =>
      cat -> catItems.map(i => i.unitPrice * i.quantity).sum
    )

  // sortBy - sort by price descending
  def sortedByPrice(items: Seq[CartItem]): Seq[CartItem] =
    items.sortBy(-_.unitPrice)

  // collect - only items that qualify for free shipping (line total > 50)
  def freeShippingEligible(items: Seq[CartItem]): Seq[String] =
    items.collect { case i if i.unitPrice * i.quantity > 50 => i.name }

  // distinctBy - unique categories
  def uniqueCategories(items: Seq[CartItem]): Seq[String] =
    items.distinctBy(_.category).map(_.category)
