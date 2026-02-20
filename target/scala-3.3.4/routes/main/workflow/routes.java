// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package workflow;

import router.RoutesPrefix;

public class routes {
  
  public static final workflow.ReverseWorkflowController WorkflowController = new workflow.ReverseWorkflowController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final workflow.javascript.ReverseWorkflowController WorkflowController = new workflow.javascript.ReverseWorkflowController(RoutesPrefix.byNamePrefix());
  }

}
