// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:9
package cart.javascript {

  // @LINE:9
  class ReverseCartController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:9
    def showCart: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "cart.CartController.showCart",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "cart"})
        }
      """
    )
  
    // @LINE:10
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
