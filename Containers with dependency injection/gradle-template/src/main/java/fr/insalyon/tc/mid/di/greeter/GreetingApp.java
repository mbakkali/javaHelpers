package fr.insalyon.tc.mid.di.greeter;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Component
public class GreetingApp {

  private final Greeter greeter;
  @Autowired
  public GreetingApp(@Qualifier("casual") Greeter greeter) {
    this.greeter = greeter;
  }

  public List<String> greet(List<String> names) {
    return names.stream()
        .map(greeter::greet)
        .collect(Collectors.toList());
  }
}
