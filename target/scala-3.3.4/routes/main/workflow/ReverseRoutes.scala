// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:22
package workflow {

  // @LINE:22
  class ReverseWorkflowController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:22
    def showBoard(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "workflow")
    }
  
    // @LINE:23
    def createOrder(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "workflow")
    }
  
    // @LINE:24
    def transition(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "workflow/transition")
    }
  
  }


}
