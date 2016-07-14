package mybank.backend;

import java.math.BigDecimal;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;


public final class Account {

  private final String holder;
  private final String classification;
  private BigDecimal balance;

  public Account(String holder, String classification, BigDecimal initialBalance) {
    this.holder = holder;
    this.classification = classification;
    this.balance = initialBalance;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public String getHolder() {
    return holder;
  }

  public String getClassification() {
    return classification;
  }

  public Account credit(BigDecimal amount) {
    balance = balance.add(amount);
    return this;
  }

  public Account debit(BigDecimal amount) {
    balance = balance.subtract(amount);
    return this;
  }

  @Override
  public String toString() {
    return "Account{" +
        "holder='" + holder + '\'' +
        ", classification='" + classification + '\'' +
        ", balance=" + balance +
        '}';
  }
}
