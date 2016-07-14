package mybank.backend;

public interface TransactionManager {

  TransactionManager perform(Transaction transaction);
}
