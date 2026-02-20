// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:24
package workflow {

  // @LINE:24
  class ReverseWorkflowController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:24
    def showBoard(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "workflow")
    }
  
    // @LINE:25
    def createOrder(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "workflow")
    }
  
    // @LINE:26
    def transition(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "workflow/transition")
    }
  
  }


}
