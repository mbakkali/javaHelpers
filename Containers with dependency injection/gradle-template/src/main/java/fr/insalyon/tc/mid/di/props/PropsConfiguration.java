package fr.insalyon.tc.mid.di.props;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("fr.insalyon.tc.mid.di.props")
@PropertySource("classpath:/myapp.properties")
public class PropsConfiguration {

}
