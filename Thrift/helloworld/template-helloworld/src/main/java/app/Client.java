package app;

import fr.insa.tc.greeter.Contact;
import fr.insa.tc.greeter.Greeter;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class Client {

  public void call(String server) throws TException {
    TTransport transport = new TSocket(server, 4100);
    transport.open();
    TProtocol protocol = new TBinaryProtocol(transport);
    Greeter.Client client = new Greeter.Client(protocol);
    System.out.println(
        client.greet(new Contact("John", "Fritz").setEmail("john.fritz@yahoo.com")));
  }
}
