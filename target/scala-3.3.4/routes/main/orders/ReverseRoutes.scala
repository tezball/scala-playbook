// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:18
package orders {

  // @LINE:18
  class ReverseOrderController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:18
    def showForm(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "orders")
    }
  
    // @LINE:19
    def placeOrder(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "orders")
    }
  
  }


}
