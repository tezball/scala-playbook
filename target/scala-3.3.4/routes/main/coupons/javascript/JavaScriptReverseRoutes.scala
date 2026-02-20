// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:15
package coupons.javascript {

  // @LINE:15
  class ReverseCouponController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:15
    def showForm: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "coupons.CouponController.showForm",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "coupons"})
        }
      """
    )
  
    // @LINE:16
    def validateCoupon: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "coupons.CouponController.validateCoupon",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "coupons"})
        }
      """
    )
  
  }


}
