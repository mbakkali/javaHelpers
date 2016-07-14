package app;

import fr.insa.tc.greeter.Contact;
import fr.insa.tc.greeter.Greeter;
import fr.insa.tc.greeter.Greeting;
import org.apache.thrift.TException;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.server.TThreadPoolServer;

public class Server {
  private static class GreeterHandler implements Greeter.Iface {

   @Override
   public Greeting greet(Contact contact) throws TException {
     StringBuilder str = new StringBuilder()
         .append("Hello ")
         .append(contact.getFirstname())
         .append(" ")
         .append(contact.getLastname());
     if (contact.getEmail() != null) {
       str.append(" (").append(contact.getEmail()).append(")");
     }
     str.append("!");
     return new Greeting(str.toString());
   }
 }

 public void start() throws TTransportException {
   Greeter.Processor<GreeterHandler> processor = new Greeter.Processor<>(new GreeterHandler());
   TServerTransport transport = new TServerSocket(4100);
   TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(transport).processor(processor));
   server.serve();
 }
}
