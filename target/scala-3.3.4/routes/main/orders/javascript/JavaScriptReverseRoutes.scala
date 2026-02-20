// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:7
package orders.javascript {

  // @LINE:7
  class ReverseOrderController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:7
    def showForm: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "orders.OrderController.showForm",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "orders"})
        }
      """
    )
  
    // @LINE:8
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
