// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:22
package workflow.javascript {

  // @LINE:22
  class ReverseWorkflowController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:22
    def showBoard: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "workflow.WorkflowController.showBoard",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "workflow"})
        }
      """
    )
  
    // @LINE:23
    def createOrder: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "workflow.WorkflowController.createOrder",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "workflow"})
        }
      """
    )
  
    // @LINE:24
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
