// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package ledger;

import router.RoutesPrefix;

public class routes {
  
  public static final ledger.ReverseLedgerController LedgerController = new ledger.ReverseLedgerController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final ledger.javascript.ReverseLedgerController LedgerController = new ledger.javascript.ReverseLedgerController(RoutesPrefix.byNamePrefix());
  }

}
