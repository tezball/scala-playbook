// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:27
package ledger.javascript {

  // @LINE:27
  class ReverseLedgerController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:27
    def showLedger: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "ledger.LedgerController.showLedger",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "ledger"})
        }
      """
    )
  
    // @LINE:28
    def addEntry: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "ledger.LedgerController.addEntry",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "ledger"})
        }
      """
    )
  
  }


}
