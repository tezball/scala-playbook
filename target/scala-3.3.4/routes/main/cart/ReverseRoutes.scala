// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:9
package cart {

  // @LINE:9
  class ReverseCartController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:9
    def showCart(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "cart")
    }
  
    // @LINE:10
    def addItem(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "cart")
    }
  
  }


}
