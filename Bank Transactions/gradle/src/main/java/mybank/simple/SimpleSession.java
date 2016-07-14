package mybank.simple;
import mybank.backend.*;
import java.math.BigDecimal;
import mybank.frontend.Session;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

public class SimpleSession implements Session {

  private Account account;
  private TransactionManager transactionManager;
  private Boolean current = true;

  public SimpleSession(Account account, TransactionManager transactionManager) {
    this.account = account;
    this.transactionManager = transactionManager;
  }

  private void checkCurrent() {
    if (!current) {
      throw new IllegalStateException("The session has been closed");
    }
  }

  @Override
  public BigDecimal getBalance() {
    checkCurrent();
    return account.getBalance();
  }

  @Override
  public Session transfer(BigDecimal amount, String toHolder) {
    checkCurrent();
    transactionManager.perform(new Transaction(account.getHolder(), toHolder, amount));
    return this;
  }

  @Override
  public void logout() {
    current = false;
  }

  @Override
  public Session deposit(BigDecimal amount){
    checkCurrent();
    account.credit(amount);
    return this;
  }

  @Override
  public Session checkout(BigDecimal amount){
    checkCurrent();
    account.debit(amount);
    return this;
  }
  // (...) the remainder of the methods set
}
