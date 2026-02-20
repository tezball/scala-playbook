// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:17
package notifications {

  // @LINE:17
  class ReverseNotificationController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:17
    def showForm(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "notifications")
    }
  
    // @LINE:18
    def sendNotification(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "notifications")
    }
  
  }


}
