package fileshare;

import fileshare.rpc.Fichier;
import fileshare.rpc.Rpc;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import org.apache.thrift.TException;
import java.io.IOException;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.server.TThreadPoolServer;

//pour communiquer avec les autres servers
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
///

public class Server {
   private static ArrayList<TSocket> slaves = new ArrayList<TSocket>();
   private static Map<String,Fichier> files = new HashMap<String,Fichier>();
   private int clock;
   private static class RpcHandler implements Rpc.Iface {


   @Override
   public Fichier downloadFile(String filename){
       return files.get(filename);
   }

   @Override
   public void uploadFile(Fichier file) throws TException {
     try{
       FileOutputStream fout = new FileOutputStream(file.getNom());
       fout.write(file.getContent());
       fout.close();
       files.put(file.getNom(),file);
       System.out.println("Added "+file.getNom());

       // Send the order to other servers
       for(TSocket slave : slaves){
         slave.open();
         TProtocol protocol = new TBinaryProtocol(slave);
         Rpc.Client client = new Rpc.Client(protocol);

         client.uploadFile(file);
         //we're done for this one.
         slave.close();
       }
     }
     catch(IOException e){
       System.out.println("Error !\n");
     }
   }
   @Override
   public void deleteFile(String filename) {
        files.remove(filename);
        try{
          File todelete = new File(filename);
          todelete.delete();
          System.out.println("Deleted " + filename);
          // Send the order to other servers
          for(TSocket slave : slaves){
            slave.open();
            TProtocol protocol = new TBinaryProtocol(slave);
            Rpc.Client client = new Rpc.Client(protocol);

            client.deleteFile(filename);
            //we're done for this one.
            slave.close();
          }

        }catch(Exception e){
          System.out.println("No such file...");
        }
   }

   @Override
   public String listFichier(){
     return files.keySet().toString();
   }
 }

 public void start(Object... args) throws TTransportException {
   clock =0;
   Rpc.Processor<RpcHandler> processor = new Rpc.Processor<>(new RpcHandler());
   TServerTransport transport = new TServerSocket(Integer.parseInt((String)args[1]));


    // We need to create the server network given in parameter
    if (args.length > 2){
      if (args[2].equals("file-server")){
          for(int i=3; i<args.length-1; i=i+2){
            slaves.add(new TSocket((String) args[i],Integer.parseInt((String) args[i+1])));
          }
      }
    }
   TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(transport).processor(processor));
   server.serve();
 }
}
