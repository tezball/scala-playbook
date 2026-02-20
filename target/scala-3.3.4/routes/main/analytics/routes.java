// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package analytics;

import router.RoutesPrefix;

public class routes {
  
  public static final analytics.ReverseAnalyticsController AnalyticsController = new analytics.ReverseAnalyticsController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final analytics.javascript.ReverseAnalyticsController AnalyticsController = new analytics.javascript.ReverseAnalyticsController(RoutesPrefix.byNamePrefix());
  }

}
