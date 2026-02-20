// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:15
package analytics {

  // @LINE:15
  class ReverseAnalyticsController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:15
    def dashboard(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "analytics")
    }
  
  }


}
