package fr.insalyon.tc.mid.di.props;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ServerConnection {

  @Autowired
  private Environment env;

  public String getHostname() {
    return env.getProperty("hostname", "localhost");
  }

  public int getPort() {
    return env.getProperty("port", Integer.class, 5000);
  }
}
