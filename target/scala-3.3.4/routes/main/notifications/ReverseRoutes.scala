// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:13
package notifications {

  // @LINE:13
  class ReverseNotificationController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:13
    def showForm(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "notifications")
    }
  
    // @LINE:14
    def sendNotification(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "notifications")
    }
  
  }


}
