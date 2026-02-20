// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:13
package notifications.javascript {

  // @LINE:13
  class ReverseNotificationController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:13
    def showForm: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "notifications.NotificationController.showForm",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "notifications"})
        }
      """
    )
  
    // @LINE:14
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
