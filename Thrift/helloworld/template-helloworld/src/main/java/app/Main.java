package app;

import org.apache.thrift.TException;

public class Main {

  public static void main(String... args) throws TException {
    if (args.length == 0) {
      kill();
    }
    switch (args[0]) {
      case "server":
        new Server().start();
        break;
      case "client":
        if (args.length != 2) {
          kill();
        }
        new Client().call(args[1]);
        break;
    }
  }

  private static void kill() {
    System.err.println("Server mode: server");
    System.err.println("Client mode: client hostname");
    System.exit(-1);
  }
}
