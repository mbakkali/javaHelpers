package mybank.simple;

import mybank.frontend.BankService;
import mybank.backend.AccountManager;
import mybank.backend.TransactionManager;
import mybank.frontend.Session;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import java.lang.reflect.Proxy;


@Component
public class SimpleBankService implements BankService {

  private AccountManager accountManager;
  private TransactionManager transactionManager;

  @Autowired
  public SimpleBankService(AccountManager accountManager, TransactionManager transactionManager) {
    this.accountManager = accountManager;
    this.transactionManager = transactionManager;
  }

  @Override
  public Session login(String holder, String password) {
    if (accountManager.authenticates(holder, password)) {
      SimpleSession component = new SimpleSession(accountManager.get(holder), transactionManager);
      Session sessionProxy = (Session) Proxy.newProxyInstance(
                                  Session.class.getClassLoader(),
                                  new Class[] { Session.class },
                                  (proxy, method, args) -> {
                                    // Plus comportement en plus
                                  Object returnValue = method.invoke(component, args);
                                  return returnValue;
                                  });

    }
    throw new IllegalArgumentException("Could not authenticate: " + holder + " (password: " + password + ")");
  }
}
