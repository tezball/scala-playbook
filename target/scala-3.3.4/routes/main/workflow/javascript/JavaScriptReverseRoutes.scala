// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:24
package workflow.javascript {

  // @LINE:24
  class ReverseWorkflowController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:24
    def showBoard: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "workflow.WorkflowController.showBoard",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "workflow"})
        }
      """
    )
  
    // @LINE:25
    def createOrder: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "workflow.WorkflowController.createOrder",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "workflow"})
        }
      """
    )
  
    // @LINE:26
    def transition: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "workflow.WorkflowController.transition",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "workflow/transition"})
        }
      """
    )
  
  }


}
