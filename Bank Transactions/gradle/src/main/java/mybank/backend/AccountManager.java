package mybank.backend;

public interface AccountManager {

  boolean authenticates(String holder, String password);

  Account get(String holder);
}
