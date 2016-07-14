package mybank.simple;
import java.math.BigDecimal;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import mybank.frontend.*;
import java.lang.reflect.Field;


public class Main{
   public static void main(String[] args) {
     ApplicationContext context = new AnnotationConfigApplicationContext("mybank");
     BankService bankService = context.getBean(BankService.class);

     System.out.println("\nTesting");

     Session julien = bankService.login("Julien P", "hello#world");
     Session fabrice = bankService.login("Fabrice V", "ohOhohOh");

     System.out.println("-  init");
     System.out.println(">>>julien " + julien.getBalance());
     System.out.println(">>>fabrice " + fabrice.getBalance());

     julien.checkout(new BigDecimal("10.50"));
     System.out.println("-  - 10.50");
     System.out.println(">>>julien " + julien.getBalance());

     julien.deposit(new BigDecimal("6.00"));
     System.out.println("-  + 6.00");
     System.out.println(">>>julien " + julien.getBalance());

     julien.transfer(new BigDecimal("120.34"), "Fabrice V");
     System.out.println("-  <-> 120.34");
     System.out.println(">>>julien " + julien.getBalance());
     System.out.println(">>>fabrice " + fabrice.getBalance());

     julien.logout();
     fabrice.logout();
     System.out.println("\nTest 2");

     julien = bankService.login("Julien P", "hello#world");
     System.out.println("-  init");
     System.out.println(">>>julien " + julien.getBalance());
     julien.logout();

     try {
       julien.deposit(new BigDecimal("10000.00"));
     } catch (IllegalStateException expected) {
       System.out.println("Ok, session closed...");
     }
     try{
     Class pigeon = julien.getClass();
     System.out.println(pigeon.toString());
     Field haxxor = pigeon.getDeclaredField("current");
     haxxor.setAccessible(true); //
     haxxor.set(julien, true); //On modifie le champ current pour le faire passer à true

   }catch(Exception e){
     System.out.println(e);
   }
     julien.deposit(new BigDecimal("10000.00"));
     System.out.println("Tadaaa!");
     System.out.println(">>>julien " + julien.getBalance());
   }
}
