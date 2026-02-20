// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:27
package ledger {

  // @LINE:27
  class ReverseLedgerController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:27
    def showLedger(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "ledger")
    }
  
    // @LINE:28
    def addEntry(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "ledger")
    }
  
  }


}
