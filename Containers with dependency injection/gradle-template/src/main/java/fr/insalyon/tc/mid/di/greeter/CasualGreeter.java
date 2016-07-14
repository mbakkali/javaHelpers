package fr.insalyon.tc.mid.di.greeter;

import fr.insalyon.tc.mid.di.greeter.Greeter;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Qualifier;

@Component
@Qualifier("casual")
public class CasualGreeter implements Greeter {

  @Override
  public String greet(String who) {
    return String.format("Hey %s!", who);
  }
}
