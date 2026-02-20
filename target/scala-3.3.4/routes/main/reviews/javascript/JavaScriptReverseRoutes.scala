// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:20
package reviews.javascript {

  // @LINE:20
  class ReverseReviewController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:20
    def showForm: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "reviews.ReviewController.showForm",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "reviews"})
        }
      """
    )
  
    // @LINE:21
    def addReview: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "reviews.ReviewController.addReview",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "reviews"})
        }
      """
    )
  
  }


}
