package mybank.simple;

import mybank.backend.Account;
import mybank.backend.AccountManager;

import java.math.BigDecimal;
import java.util.HashMap;
import org.springframework.stereotype.Component;

@Component
public class SimpleAccountManager implements AccountManager {

  private final HashMap<String, Account> accounts = new HashMap<String, Account>() {
    {
      put("Jerome C", new Account("Jerome C", "minister", new BigDecimal("10000000.00")));
      put("Julien P", new Account("Julien P", "regular", new BigDecimal("3221.90")));
      put("Frederic L", new Account("Frederic L", "regular", new BigDecimal("100.64")));
      put("Fabrice V", new Account("Fabrice V", "nocredit", new BigDecimal("333.21")));
    }
  };

  private final HashMap<String, String> passwords = new HashMap<String, String>() {
    {
      put("Jerome C", "lesyeuxdanslesyeux");
      put("Julien P", "hello#world");
      put("Frederic L", "breizh");
      put("Fabrice V", "ohOhohOh");
    }
  };

  @Override
  public boolean authenticates(String holder, String password) {
    return passwords.containsKey(holder) && passwords.get(holder).equals(password);
  }

  @Override
  public Account get(String holder) {
    return accounts.get(holder);
  }
}
