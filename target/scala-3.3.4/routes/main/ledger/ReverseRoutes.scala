// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:25
package ledger {

  // @LINE:25
  class ReverseLedgerController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:25
    def showLedger(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "ledger")
    }
  
    // @LINE:26
    def addEntry(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "ledger")
    }
  
  }


}
