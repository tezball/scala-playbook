// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package products;

import router.RoutesPrefix;

public class routes {
  
  public static final products.ReverseProductController ProductController = new products.ReverseProductController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final products.javascript.ReverseProductController ProductController = new products.javascript.ReverseProductController(RoutesPrefix.byNamePrefix());
  }

}
