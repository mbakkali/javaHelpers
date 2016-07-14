package fr.insalyon.tc.mid.di.greeter;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Component
@Qualifier("polite")
public class PoliteGreeter implements Greeter {

  @Override
  public String greet(String who) {
    return String.format("Good Morning, %s.", who);
  }
  private static final Log LOG = LogFactory.getLog(PoliteGreeter.class);

  @PostConstruct
  public void kickoff() {
    LOG.info("Kicking off!");
  }

  @PreDestroy
  public void byebye() {
    LOG.info("Bye Bye!");
  }
}
