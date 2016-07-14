import java.util.Enumeration;
import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ExecutorService;
import java.util.TreeSet;
import java.util.Set;


public class IndexJob implements Runnable {

  private final File file;
  private final ConcurrentHashMap<String, ConcurrentSkipListSet<String>> index;
  private final ExecutorService executorService;

  public IndexJob(File file,
                  ConcurrentHashMap<String, ConcurrentSkipListSet<String>> index,
                  ExecutorService executorService) {
    this.file = file;
    this.index = index;
    this.executorService = executorService;
  }

  @Override
  public void run() {
    //System.out.println("Indexing " + file);
    if (file.isDirectory()) {
      for(File subfile:file.listFiles()){
        //utilisation des Futures?
        executorService.submit(new IndexJob(subfile, index, executorService));
      }
    } else if (file.isFile()) {
        index.put(file.getPath(),new ConcurrentSkipListSet(wordsInFile(file)));
    }
  }

  private Set<String> wordsInFile(File file) {
    TreeSet<String> words = new TreeSet<>();
    try (BufferedReader reader = new BufferedReader(
                                 new InputStreamReader(
                                 new FileInputStream(file))) ) {
      String line = reader.readLine();
      while (line != null) {
        for (String word : wordsInLine(line)) {
          words.add(word);
        }
        line = reader.readLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return words;
  }

  private String[] wordsInLine(String line) {
    return line.trim().split("(\\s|\\p{Punct})+");
  }

  public void containWord(String word) {
    for (Enumeration<String> e = index.keys(); e.hasMoreElements();){
      String path = e.nextElement();
      if(index.get(path).contains(word)){
        System.out.println(path + " contient le mot " + word);
      }
    }
  }
}
