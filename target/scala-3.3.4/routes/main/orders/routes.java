// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package orders;

import router.RoutesPrefix;

public class routes {
  
  public static final orders.ReverseOrderController OrderController = new orders.ReverseOrderController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final orders.javascript.ReverseOrderController OrderController = new orders.javascript.ReverseOrderController(RoutesPrefix.byNamePrefix());
  }

}
