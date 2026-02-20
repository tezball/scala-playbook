// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:7
package products.javascript {

  // @LINE:7
  class ReverseProductController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:7
    def showForm: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "products.ProductController.showForm",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "products"})
        }
      """
    )
  
    // @LINE:8
    def addProduct: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "products.ProductController.addProduct",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "products"})
        }
      """
    )
  
  }


}
