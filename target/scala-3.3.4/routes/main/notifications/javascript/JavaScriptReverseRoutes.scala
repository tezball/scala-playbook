// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:17
package notifications.javascript {

  // @LINE:17
  class ReverseNotificationController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:17
    def showForm: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "notifications.NotificationController.showForm",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "notifications"})
        }
      """
    )
  
    // @LINE:18
    def sendNotification: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "notifications.NotificationController.sendNotification",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "notifications"})
        }
      """
    )
  
  }


}
