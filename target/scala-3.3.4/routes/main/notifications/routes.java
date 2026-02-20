// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package notifications;

import router.RoutesPrefix;

public class routes {
  
  public static final notifications.ReverseNotificationController NotificationController = new notifications.ReverseNotificationController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final notifications.javascript.ReverseNotificationController NotificationController = new notifications.javascript.ReverseNotificationController(RoutesPrefix.byNamePrefix());
  }

}
