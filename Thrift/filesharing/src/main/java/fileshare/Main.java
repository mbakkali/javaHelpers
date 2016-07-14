package fileshare;

import org.apache.thrift.TException;
import java.net.InetAddress;
import java.net.UnknownHostException;
public class Main {

  public static void main(String... args) throws TException, UnknownHostException {
    //System.out.println("My IP add "+ InetAddress.getLocalHost().toString());
    if (args.length == 0) {
      kill();
    }
    switch (args[0]) {
      case "server":
        if (args.length >= 2)
          new Server().start(args);
        break;
      case "client":
        if (args.length != 3) {
          kill();
        }
        new Client().call(args[1], Integer.parseInt(args[2]));
        break;
    }
  }

  private static void kill() {
    System.err.println("Server mode: server");
    System.err.println("Client mode: client hostname port");
    System.exit(-1);
  }
}
