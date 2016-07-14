package mybank.frontend;

public interface BankService {
  Session login(String holder, String password);
}
