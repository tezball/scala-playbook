// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:11
package coupons {

  // @LINE:11
  class ReverseCouponController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:11
    def showForm(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "coupons")
    }
  
    // @LINE:12
    def validateCoupon(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "coupons")
    }
  
  }


}
