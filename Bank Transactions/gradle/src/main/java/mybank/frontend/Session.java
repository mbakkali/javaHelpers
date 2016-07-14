package mybank.frontend;

import java.math.BigDecimal;

public interface Session {

  BigDecimal getBalance();

  Session checkout(BigDecimal amount);

  Session deposit(BigDecimal amount);

  Session transfer(BigDecimal amount, String toHolder);

  void logout();
}
