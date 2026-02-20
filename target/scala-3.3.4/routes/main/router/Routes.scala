// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:2
  HomeController_10: controllers.HomeController,
  // @LINE:5
  UserController_9: users.UserController,
  // @LINE:7
  OrderController_6: orders.OrderController,
  // @LINE:11
  ProductController_1: products.ProductController,
  // @LINE:13
  CartController_8: cart.CartController,
  // @LINE:15
  CouponController_3: coupons.CouponController,
  // @LINE:17
  NotificationController_4: notifications.NotificationController,
  // @LINE:19
  AnalyticsController_0: analytics.AnalyticsController,
  // @LINE:22
  ReviewController_5: reviews.ReviewController,
  // @LINE:24
  WorkflowController_2: workflow.WorkflowController,
  // @LINE:27
  LedgerController_7: ledger.LedgerController,
  val prefix: String
) extends GeneratedRouter {

  @javax.inject.Inject()
  def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:2
    HomeController_10: controllers.HomeController,
    // @LINE:5
    UserController_9: users.UserController,
    // @LINE:7
    OrderController_6: orders.OrderController,
    // @LINE:11
    ProductController_1: products.ProductController,
    // @LINE:13
    CartController_8: cart.CartController,
    // @LINE:15
    CouponController_3: coupons.CouponController,
    // @LINE:17
    NotificationController_4: notifications.NotificationController,
    // @LINE:19
    AnalyticsController_0: analytics.AnalyticsController,
    // @LINE:22
    ReviewController_5: reviews.ReviewController,
    // @LINE:24
    WorkflowController_2: workflow.WorkflowController,
    // @LINE:27
    LedgerController_7: ledger.LedgerController
  ) = this(errorHandler, HomeController_10, UserController_9, OrderController_6, ProductController_1, CartController_8, CouponController_3, NotificationController_4, AnalyticsController_0, ReviewController_5, WorkflowController_2, LedgerController_7, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_10, UserController_9, OrderController_6, ProductController_1, CartController_8, CouponController_3, NotificationController_4, AnalyticsController_0, ReviewController_5, WorkflowController_2, LedgerController_7, prefix)
  }

  private val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.HomeController.index()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """users""", """users.UserController.showForm()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """users""", """users.UserController.saveUser()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """orders""", """orders.OrderController.showForm()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """orders""", """orders.OrderController.placeOrder()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """products""", """products.ProductController.showForm()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """products""", """products.ProductController.addProduct()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cart""", """cart.CartController.showCart()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cart""", """cart.CartController.addItem()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """coupons""", """coupons.CouponController.showForm()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """coupons""", """coupons.CouponController.validateCoupon()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """notifications""", """notifications.NotificationController.showForm()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """notifications""", """notifications.NotificationController.sendNotification()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """analytics""", """analytics.AnalyticsController.dashboard()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reviews""", """reviews.ReviewController.showForm()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reviews""", """reviews.ReviewController.addReview()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """workflow""", """workflow.WorkflowController.showBoard()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """workflow""", """workflow.WorkflowController.createOrder()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """workflow/transition""", """workflow.WorkflowController.transition()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """ledger""", """ledger.LedgerController.showLedger()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """ledger""", """ledger.LedgerController.addEntry()"""),
    Nil
  ).foldLeft(Seq.empty[(String, String, String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String, String, String)]
    case l => s ++ l.asInstanceOf[List[(String, String, String)]]
  }}


  // @LINE:2
  private lazy val controllers_HomeController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private lazy val controllers_HomeController_index0_invoker = createInvoker(
    HomeController_10.index(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "index",
      Nil,
      "GET",
      this.prefix + """""",
      """ Home""",
      Seq()
    )
  )

  // @LINE:5
  private lazy val users_UserController_showForm1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("users")))
  )
  private lazy val users_UserController_showForm1_invoker = createInvoker(
    UserController_9.showForm(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "users.UserController",
      "showForm",
      Nil,
      "GET",
      this.prefix + """users""",
      """ Existing Modules""",
      Seq()
    )
  )

  // @LINE:6
  private lazy val users_UserController_saveUser2_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("users")))
  )
  private lazy val users_UserController_saveUser2_invoker = createInvoker(
    UserController_9.saveUser(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "users.UserController",
      "saveUser",
      Nil,
      "POST",
      this.prefix + """users""",
      """""",
      Seq()
    )
  )

  // @LINE:7
  private lazy val orders_OrderController_showForm3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("orders")))
  )
  private lazy val orders_OrderController_showForm3_invoker = createInvoker(
    OrderController_6.showForm(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "orders.OrderController",
      "showForm",
      Nil,
      "GET",
      this.prefix + """orders""",
      """""",
      Seq()
    )
  )

  // @LINE:8
  private lazy val orders_OrderController_placeOrder4_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("orders")))
  )
  private lazy val orders_OrderController_placeOrder4_invoker = createInvoker(
    OrderController_6.placeOrder(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "orders.OrderController",
      "placeOrder",
      Nil,
      "POST",
      this.prefix + """orders""",
      """""",
      Seq()
    )
  )

  // @LINE:11
  private lazy val products_ProductController_showForm5_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("products")))
  )
  private lazy val products_ProductController_showForm5_invoker = createInvoker(
    ProductController_1.showForm(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "products.ProductController",
      "showForm",
      Nil,
      "GET",
      this.prefix + """products""",
      """ Core Concepts""",
      Seq()
    )
  )

  // @LINE:12
  private lazy val products_ProductController_addProduct6_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("products")))
  )
  private lazy val products_ProductController_addProduct6_invoker = createInvoker(
    ProductController_1.addProduct(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "products.ProductController",
      "addProduct",
      Nil,
      "POST",
      this.prefix + """products""",
      """""",
      Seq()
    )
  )

  // @LINE:13
  private lazy val cart_CartController_showCart7_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cart")))
  )
  private lazy val cart_CartController_showCart7_invoker = createInvoker(
    CartController_8.showCart(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "cart.CartController",
      "showCart",
      Nil,
      "GET",
      this.prefix + """cart""",
      """""",
      Seq()
    )
  )

  // @LINE:14
  private lazy val cart_CartController_addItem8_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cart")))
  )
  private lazy val cart_CartController_addItem8_invoker = createInvoker(
    CartController_8.addItem(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "cart.CartController",
      "addItem",
      Nil,
      "POST",
      this.prefix + """cart""",
      """""",
      Seq()
    )
  )

  // @LINE:15
  private lazy val coupons_CouponController_showForm9_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("coupons")))
  )
  private lazy val coupons_CouponController_showForm9_invoker = createInvoker(
    CouponController_3.showForm(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "coupons.CouponController",
      "showForm",
      Nil,
      "GET",
      this.prefix + """coupons""",
      """""",
      Seq()
    )
  )

  // @LINE:16
  private lazy val coupons_CouponController_validateCoupon10_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("coupons")))
  )
  private lazy val coupons_CouponController_validateCoupon10_invoker = createInvoker(
    CouponController_3.validateCoupon(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "coupons.CouponController",
      "validateCoupon",
      Nil,
      "POST",
      this.prefix + """coupons""",
      """""",
      Seq()
    )
  )

  // @LINE:17
  private lazy val notifications_NotificationController_showForm11_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("notifications")))
  )
  private lazy val notifications_NotificationController_showForm11_invoker = createInvoker(
    NotificationController_4.showForm(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "notifications.NotificationController",
      "showForm",
      Nil,
      "GET",
      this.prefix + """notifications""",
      """""",
      Seq()
    )
  )

  // @LINE:18
  private lazy val notifications_NotificationController_sendNotification12_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("notifications")))
  )
  private lazy val notifications_NotificationController_sendNotification12_invoker = createInvoker(
    NotificationController_4.sendNotification(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "notifications.NotificationController",
      "sendNotification",
      Nil,
      "POST",
      this.prefix + """notifications""",
      """""",
      Seq()
    )
  )

  // @LINE:19
  private lazy val analytics_AnalyticsController_dashboard13_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("analytics")))
  )
  private lazy val analytics_AnalyticsController_dashboard13_invoker = createInvoker(
    AnalyticsController_0.dashboard(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "analytics.AnalyticsController",
      "dashboard",
      Nil,
      "GET",
      this.prefix + """analytics""",
      """""",
      Seq()
    )
  )

  // @LINE:22
  private lazy val reviews_ReviewController_showForm14_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reviews")))
  )
  private lazy val reviews_ReviewController_showForm14_invoker = createInvoker(
    ReviewController_5.showForm(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "reviews.ReviewController",
      "showForm",
      Nil,
      "GET",
      this.prefix + """reviews""",
      """ Advanced Concepts""",
      Seq()
    )
  )

  // @LINE:23
  private lazy val reviews_ReviewController_addReview15_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reviews")))
  )
  private lazy val reviews_ReviewController_addReview15_invoker = createInvoker(
    ReviewController_5.addReview(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "reviews.ReviewController",
      "addReview",
      Nil,
      "POST",
      this.prefix + """reviews""",
      """""",
      Seq()
    )
  )

  // @LINE:24
  private lazy val workflow_WorkflowController_showBoard16_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("workflow")))
  )
  private lazy val workflow_WorkflowController_showBoard16_invoker = createInvoker(
    WorkflowController_2.showBoard(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "workflow.WorkflowController",
      "showBoard",
      Nil,
      "GET",
      this.prefix + """workflow""",
      """""",
      Seq()
    )
  )

  // @LINE:25
  private lazy val workflow_WorkflowController_createOrder17_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("workflow")))
  )
  private lazy val workflow_WorkflowController_createOrder17_invoker = createInvoker(
    WorkflowController_2.createOrder(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "workflow.WorkflowController",
      "createOrder",
      Nil,
      "POST",
      this.prefix + """workflow""",
      """""",
      Seq()
    )
  )

  // @LINE:26
  private lazy val workflow_WorkflowController_transition18_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("workflow/transition")))
  )
  private lazy val workflow_WorkflowController_transition18_invoker = createInvoker(
    WorkflowController_2.transition(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "workflow.WorkflowController",
      "transition",
      Nil,
      "POST",
      this.prefix + """workflow/transition""",
      """""",
      Seq()
    )
  )

  // @LINE:27
  private lazy val ledger_LedgerController_showLedger19_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("ledger")))
  )
  private lazy val ledger_LedgerController_showLedger19_invoker = createInvoker(
    LedgerController_7.showLedger(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "ledger.LedgerController",
      "showLedger",
      Nil,
      "GET",
      this.prefix + """ledger""",
      """""",
      Seq()
    )
  )

  // @LINE:28
  private lazy val ledger_LedgerController_addEntry20_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("ledger")))
  )
  private lazy val ledger_LedgerController_addEntry20_invoker = createInvoker(
    LedgerController_7.addEntry(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "ledger.LedgerController",
      "addEntry",
      Nil,
      "POST",
      this.prefix + """ledger""",
      """""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:2
    case controllers_HomeController_index0_route(params@_) =>
      call { 
        controllers_HomeController_index0_invoker.call(HomeController_10.index())
      }
  
    // @LINE:5
    case users_UserController_showForm1_route(params@_) =>
      call { 
        users_UserController_showForm1_invoker.call(UserController_9.showForm())
      }
  
    // @LINE:6
    case users_UserController_saveUser2_route(params@_) =>
      call { 
        users_UserController_saveUser2_invoker.call(UserController_9.saveUser())
      }
  
    // @LINE:7
    case orders_OrderController_showForm3_route(params@_) =>
      call { 
        orders_OrderController_showForm3_invoker.call(OrderController_6.showForm())
      }
  
    // @LINE:8
    case orders_OrderController_placeOrder4_route(params@_) =>
      call { 
        orders_OrderController_placeOrder4_invoker.call(OrderController_6.placeOrder())
      }
  
    // @LINE:11
    case products_ProductController_showForm5_route(params@_) =>
      call { 
        products_ProductController_showForm5_invoker.call(ProductController_1.showForm())
      }
  
    // @LINE:12
    case products_ProductController_addProduct6_route(params@_) =>
      call { 
        products_ProductController_addProduct6_invoker.call(ProductController_1.addProduct())
      }
  
    // @LINE:13
    case cart_CartController_showCart7_route(params@_) =>
      call { 
        cart_CartController_showCart7_invoker.call(CartController_8.showCart())
      }
  
    // @LINE:14
    case cart_CartController_addItem8_route(params@_) =>
      call { 
        cart_CartController_addItem8_invoker.call(CartController_8.addItem())
      }
  
    // @LINE:15
    case coupons_CouponController_showForm9_route(params@_) =>
      call { 
        coupons_CouponController_showForm9_invoker.call(CouponController_3.showForm())
      }
  
    // @LINE:16
    case coupons_CouponController_validateCoupon10_route(params@_) =>
      call { 
        coupons_CouponController_validateCoupon10_invoker.call(CouponController_3.validateCoupon())
      }
  
    // @LINE:17
    case notifications_NotificationController_showForm11_route(params@_) =>
      call { 
        notifications_NotificationController_showForm11_invoker.call(NotificationController_4.showForm())
      }
  
    // @LINE:18
    case notifications_NotificationController_sendNotification12_route(params@_) =>
      call { 
        notifications_NotificationController_sendNotification12_invoker.call(NotificationController_4.sendNotification())
      }
  
    // @LINE:19
    case analytics_AnalyticsController_dashboard13_route(params@_) =>
      call { 
        analytics_AnalyticsController_dashboard13_invoker.call(AnalyticsController_0.dashboard())
      }
  
    // @LINE:22
    case reviews_ReviewController_showForm14_route(params@_) =>
      call { 
        reviews_ReviewController_showForm14_invoker.call(ReviewController_5.showForm())
      }
  
    // @LINE:23
    case reviews_ReviewController_addReview15_route(params@_) =>
      call { 
        reviews_ReviewController_addReview15_invoker.call(ReviewController_5.addReview())
      }
  
    // @LINE:24
    case workflow_WorkflowController_showBoard16_route(params@_) =>
      call { 
        workflow_WorkflowController_showBoard16_invoker.call(WorkflowController_2.showBoard())
      }
  
    // @LINE:25
    case workflow_WorkflowController_createOrder17_route(params@_) =>
      call { 
        workflow_WorkflowController_createOrder17_invoker.call(WorkflowController_2.createOrder())
      }
  
    // @LINE:26
    case workflow_WorkflowController_transition18_route(params@_) =>
      call { 
        workflow_WorkflowController_transition18_invoker.call(WorkflowController_2.transition())
      }
  
    // @LINE:27
    case ledger_LedgerController_showLedger19_route(params@_) =>
      call { 
        ledger_LedgerController_showLedger19_invoker.call(LedgerController_7.showLedger())
      }
  
    // @LINE:28
    case ledger_LedgerController_addEntry20_route(params@_) =>
      call { 
        ledger_LedgerController_addEntry20_invoker.call(LedgerController_7.addEntry())
      }
  }
}
