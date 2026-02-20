// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:18
package orders.javascript {

  // @LINE:18
  class ReverseOrderController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:18
    def showForm: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "orders.OrderController.showForm",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "orders"})
        }
      """
    )
  
    // @LINE:19
    def placeOrder: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "orders.OrderController.placeOrder",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "orders"})
        }
      """
    )
  
  }


}
