package fr.insalyon.tc.mid.di;

import fr.insalyon.tc.mid.di.greeter.Greeter;
import fr.insalyon.tc.mid.di.greeter.GreetingApp;
import fr.insalyon.tc.mid.di.greeter.PoliteGreeter;

import fr.insalyon.tc.mid.di.greeter.SomeConfiguration;
//import fr.insalyon.tc.mid.di.props.CollectionConfiguration;
//import fr.insalyon.tc.mid.di.props.MultiplePropsConfiguration;
import fr.insalyon.tc.mid.di.props.PropsConfiguration;
import fr.insalyon.tc.mid.di.props.ServerConnection;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;


public class ConfigurationTest {
      @Test
      public void properties_loading() {
        ApplicationContext context = new AnnotationConfigApplicationContext(PropsConfiguration.class);

        ServerConnection conn = context.getBean(ServerConnection.class);

        assertThat(conn)
            .hasFieldOrPropertyWithValue("hostname", "192.168.0.10")
            .hasFieldOrPropertyWithValue("port", 3000);
      }

}
