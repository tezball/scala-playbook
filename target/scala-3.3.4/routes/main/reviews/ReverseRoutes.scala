// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:22
package reviews {

  // @LINE:22
  class ReverseReviewController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:22
    def showForm(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "reviews")
    }
  
    // @LINE:23
    def addReview(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "reviews")
    }
  
  }


}
