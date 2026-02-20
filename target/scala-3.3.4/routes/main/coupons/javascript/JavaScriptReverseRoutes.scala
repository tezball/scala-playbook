// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:11
package coupons.javascript {

  // @LINE:11
  class ReverseCouponController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:11
    def showForm: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "coupons.CouponController.showForm",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "coupons"})
        }
      """
    )
  
    // @LINE:12
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
