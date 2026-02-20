// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:20
package reviews {

  // @LINE:20
  class ReverseReviewController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:20
    def showForm(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "reviews")
    }
  
    // @LINE:21
    def addReview(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "reviews")
    }
  
  }


}
