// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:13
package cart.javascript {

  // @LINE:13
  class ReverseCartController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:13
    def showCart: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "cart.CartController.showCart",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "cart"})
        }
      """
    )
  
    // @LINE:14
    def addItem: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "cart.CartController.addItem",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "cart"})
        }
      """
    )
  
  }


}
