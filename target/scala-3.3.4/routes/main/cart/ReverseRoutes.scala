// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:13
package cart {

  // @LINE:13
  class ReverseCartController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:13
    def showCart(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "cart")
    }
  
    // @LINE:14
    def addItem(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "cart")
    }
  
  }


}
