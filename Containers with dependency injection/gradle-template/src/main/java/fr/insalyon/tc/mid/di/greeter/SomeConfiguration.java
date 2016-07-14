package fr.insalyon.tc.mid.di.greeter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

@Configuration
@ComponentScan("fr.insalyon.tc")
public class SomeConfiguration {

  @Bean
  public CommonAnnotationBeanPostProcessor commonAnnotationBeanPostProcessor() {
    return new CommonAnnotationBeanPostProcessor();
  }
}
