// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:11
package products {

  // @LINE:11
  class ReverseProductController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:11
    def showForm(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "products")
    }
  
    // @LINE:12
    def addProduct(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "products")
    }
  
  }


}
