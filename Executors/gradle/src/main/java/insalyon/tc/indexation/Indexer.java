package insalyon.tc.indexation;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.Executors;

public class Indexer {
  public static void main(String[] args) {
      new Thread(new IndexJob(new File(args[0]), new ConcurrentHashMap<String, ConcurrentSkipListSet<String>>(), Executors.newFixedThreadPool(4))).start();


  }
}
