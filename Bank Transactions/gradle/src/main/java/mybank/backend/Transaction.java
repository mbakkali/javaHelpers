package mybank.backend;

import java.math.BigDecimal;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

public final class Transaction {

  private final long timestamp;
  private final String sourceHolder;
  private final String targetHolder;
  private final BigDecimal amount;

  public Transaction(String sourceHolder, String targetHolder, BigDecimal amount) {
    this.timestamp = System.currentTimeMillis();
    this.sourceHolder = sourceHolder;
    this.targetHolder = targetHolder;
    this.amount = amount;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public String getSourceHolder() {
    return sourceHolder;
  }

  public String getTargetHolder() {
    return targetHolder;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  @Override
  public String toString() {
    return "Transaction{" +
        "timestamp=" + timestamp +
        ", sourceHolder='" + sourceHolder + '\'' +
        ", targetHolder='" + targetHolder + '\'' +
        ", amount=" + amount +
        '}';
  }
}
