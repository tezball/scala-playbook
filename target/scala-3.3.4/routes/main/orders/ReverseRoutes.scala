// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:7
package orders {

  // @LINE:7
  class ReverseOrderController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:7
    def showForm(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "orders")
    }
  
    // @LINE:8
    def placeOrder(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "orders")
    }
  
  }


}
