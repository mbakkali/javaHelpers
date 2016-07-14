package mybank.simple;

import mybank.backend.Account;
import mybank.backend.AccountManager;
import mybank.backend.Transaction;
import mybank.backend.TransactionManager;


import java.util.LinkedList;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class SimpleTransactionManager implements TransactionManager {

  private final AccountManager accountManager;
  private final LinkedList<Transaction> transactions = new LinkedList<>();

  @Autowired
  public SimpleTransactionManager(AccountManager accountManager) {
    this.accountManager = accountManager;
  }

  @Override
  public TransactionManager perform(Transaction transaction) {
    Account source = accountManager.get(transaction.getSourceHolder());
    if (source == null) {
      throw new IllegalArgumentException("There is no account for: " + source);
    }
    Account target = accountManager.get(transaction.getTargetHolder());
    if (target == null) {
      throw new IllegalArgumentException("There is no account for: " + target);
    }
    source.debit(transaction.getAmount());
    target.credit(transaction.getAmount());
    transactions.push(transaction);
    return this;
  }
}
