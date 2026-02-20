// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package cart;

import router.RoutesPrefix;

public class routes {
  
  public static final cart.ReverseCartController CartController = new cart.ReverseCartController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final cart.javascript.ReverseCartController CartController = new cart.javascript.ReverseCartController(RoutesPrefix.byNamePrefix());
  }

}
