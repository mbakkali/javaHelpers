package fr.insalyon.tc.mid.di.props;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan("fr.insalyon.tc.mid.di.props")
@PropertySource("classpath:/myapp.properties")
@PropertySource("classpath:/other.properties")
public class MultiplePropsConfiguration {

  @Autowired
  Environment env;

  @Bean
  public String server() {
    return env.getProperty("server");
  }
}
