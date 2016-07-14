package fr.insalyon.tc.mid.di;

import fr.insalyon.tc.mid.di.greeter.Greeter;
import fr.insalyon.tc.mid.di.greeter.GreetingApp;
import fr.insalyon.tc.mid.di.greeter.PoliteGreeter;
/*
import fr.insalyon.tc.mid.di.greeter.SomeConfiguration;
import fr.insalyon.tc.mid.di.props.CollectionConfiguration;
import fr.insalyon.tc.mid.di.props.MultiplePropsConfiguration;
import fr.insalyon.tc.mid.di.props.PropsConfiguration;
import fr.insalyon.tc.mid.di.props.ServerConnection;
*/
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;


public class GreetingTest {
  private static final List<String> names = Arrays.asList(
      "John", "Didier", "Ashley", "Mary");

  @Test
  public void basic_polite_greeter_xml_config() {
    ApplicationContext context = new ClassPathXmlApplicationContext("/polite-greeter-app.xml");
    GreetingApp greetingApp =  context.getBean("app", GreetingApp.class);
    assertThat(greetingApp.greet(names))
        .hasSize(4)
        .contains("Good Morning, Mary.")
        .allMatch(greeting -> greeting.startsWith("Good Morning"));
  }
}
