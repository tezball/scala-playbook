// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:15
package analytics.javascript {

  // @LINE:15
  class ReverseAnalyticsController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:15
    def dashboard: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "analytics.AnalyticsController.dashboard",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "analytics"})
        }
      """
    )
  
  }


}
