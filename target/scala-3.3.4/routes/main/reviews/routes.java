// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package reviews;

import router.RoutesPrefix;

public class routes {
  
  public static final reviews.ReverseReviewController ReviewController = new reviews.ReverseReviewController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final reviews.javascript.ReverseReviewController ReviewController = new reviews.javascript.ReverseReviewController(RoutesPrefix.byNamePrefix());
  }

}
