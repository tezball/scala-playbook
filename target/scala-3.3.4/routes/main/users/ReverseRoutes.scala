// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:5
package users {

  // @LINE:5
  class ReverseUserController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:5
    def showForm(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "users")
    }
  
    // @LINE:6
    def saveUser(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "users")
    }
  
  }


}
