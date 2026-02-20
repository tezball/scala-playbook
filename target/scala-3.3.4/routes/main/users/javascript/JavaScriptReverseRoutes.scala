// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:5
package users.javascript {

  // @LINE:5
  class ReverseUserController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:5
    def showForm: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "users.UserController.showForm",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "users"})
        }
      """
    )
  
    // @LINE:6
    def saveUser: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "users.UserController.saveUser",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "users"})
        }
      """
    )
  
  }


}
