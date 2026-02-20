// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:22
package reviews.javascript {

  // @LINE:22
  class ReverseReviewController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:22
    def showForm: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "reviews.ReviewController.showForm",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "reviews"})
        }
      """
    )
  
    // @LINE:23
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
