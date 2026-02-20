// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package users;

import router.RoutesPrefix;

public class routes {
  
  public static final users.ReverseUserController UserController = new users.ReverseUserController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final users.javascript.ReverseUserController UserController = new users.javascript.ReverseUserController(RoutesPrefix.byNamePrefix());
  }

}
