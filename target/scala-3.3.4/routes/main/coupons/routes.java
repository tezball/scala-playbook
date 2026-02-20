// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package coupons;

import router.RoutesPrefix;

public class routes {
  
  public static final coupons.ReverseCouponController CouponController = new coupons.ReverseCouponController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final coupons.javascript.ReverseCouponController CouponController = new coupons.javascript.ReverseCouponController(RoutesPrefix.byNamePrefix());
  }

}
