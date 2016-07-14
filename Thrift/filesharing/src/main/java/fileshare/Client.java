package fileshare;

import fileshare.rpc.Fichier;
import fileshare.rpc.Rpc;
import java.io.File;

import java.nio.ByteBuffer;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import java.io.FileReader;
public class Client {

  public void call(String server, int port) throws TException {
    TTransport transport = new TSocket(server, port);
    transport.open();
    TProtocol protocol = new TBinaryProtocol(transport);
    Rpc.Client client = new Rpc.Client(protocol);
    Fichier afile = new Fichier();
    afile.setNom("toto.txt");
    afile.setContent("Salut toto, tu vas bien?".getBytes());

    //System.out.println("We are sending a file.");;
    client.uploadFile(afile);
    afile.setNom("tete.txt");
    afile.setContent("Salut tete, tu vas bien?".getBytes());

    //System.out.println("We are sending a file.");;
    System.out.println("Upload du fichier "+afile.getNom());
    client.uploadFile(afile);

    System.out.println("We are listing the files");
    System.out.println(client.listFichier());

    client.deleteFile("tete.txt");
    System.out.println("Deleted tete.txt"+ client.listFichier());


    try{
      Fichier downloadedfile = client.downloadFile("toto.txt");
      System.out.println("Downloaded file "+ downloadedfile.getNom());
    }catch(org.apache.thrift.TApplicationException e){
      System.out.println("File not found");
    }

    System.out.println("We are listing the files");
    System.out.println(client.listFichier());
    //close the store
    transport.close();
  }
}
