// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:19
package analytics {

  // @LINE:19
  class ReverseAnalyticsController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:19
    def dashboard(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "analytics")
    }
  
  }


}
