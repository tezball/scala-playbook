package coupons

import java.time.LocalDate
import scala.util.Try

case class ValidationStep(name: String, concept: String, passed: Boolean, detail: String)

case class ValidationResult(steps: Seq[ValidationStep], discount: Option[Double])

object CouponValidator:

  // Step 1: Try - parse the input (could throw)
  def parseCode(raw: String): Try[String] =
    Try {
      require(raw.nonEmpty, "Code cannot be empty")
      require(raw.trim.matches("^[A-Za-z0-9_]+$"), "Code must be alphanumeric")
      raw.trim.toUpperCase
    }

  // Step 2: Option - look up result (may not exist)
  // (handled by repository returning Future[Option[Coupon]])

  // Step 3: Either - validate business rules
  def validateCoupon(coupon: Coupon, orderTotal: Double): Either[String, Coupon] =
    if coupon.expiresAt.isBefore(LocalDate.now()) then Left(s"Coupon expired on ${coupon.expiresAt}")
    else if coupon.usesRemaining <= 0 then Left("Coupon has no uses remaining")
    else if coupon.minimumOrder > orderTotal then Left(f"Minimum order $$${coupon.minimumOrder}%.2f not met (your total: $$${orderTotal}%.2f)")
    else Right(coupon)

  // Full validation pipeline - returns steps for visualization
  def validate(rawInput: String, couponOpt: Option[Coupon], orderTotal: Double): ValidationResult =
    val steps = scala.collection.mutable.ListBuffer[ValidationStep]()

    // Step 1: Try - parse
    val parseResult = parseCode(rawInput)
    parseResult match
      case scala.util.Success(code) =>
        steps += ValidationStep("Parse Input", "Try", passed = true, s"Success: '$code'")
      case scala.util.Failure(ex) =>
        steps += ValidationStep("Parse Input", "Try", passed = false, s"Failure: ${ex.getMessage}")
        return ValidationResult(steps.toSeq, None)

    // Step 2: Option - lookup
    couponOpt match
      case Some(coupon) =>
        steps += ValidationStep("Lookup Coupon", "Option", passed = true, s"Some(${coupon.code} - ${coupon.discountPercent}% off)")
      case None =>
        steps += ValidationStep("Lookup Coupon", "Option", passed = false, "None - Coupon not found")
        return ValidationResult(steps.toSeq, None)

    // Step 3: Either - validate business rules
    val coupon = couponOpt.get
    validateCoupon(coupon, orderTotal) match
      case Right(valid) =>
        steps += ValidationStep("Validate Rules", "Either", passed = true, s"Right(${valid.code}) - All checks passed")
        val discount = orderTotal * (valid.discountPercent / 100)
        ValidationResult(steps.toSeq, Some(discount))
      case Left(error) =>
        steps += ValidationStep("Validate Rules", "Either", passed = false, s"Left($error)")
        ValidationResult(steps.toSeq, None)
