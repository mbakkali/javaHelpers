import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutionException;
import java.util.LinkedList;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
public class Runner {

  private static final int THREAD_COUNT = 4;
  private static final int JOB_COUNT = 5000000;
  private static final int STORE_SIZE = 4;
  private final ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
  private final LinkedList<Callable<Void>> jobs = new LinkedList<>();

  void bench(final Store store) throws InterruptedException, ExecutionException {
    for (int i = 0; i < JOB_COUNT; i++) {
      final boolean adding = i % 2 == 0;
      jobs.add(new Callable<Void>() {
        @Override
        public Void call() throws Exception {
          for (int j = 0; j < store.size(); j++) {
            if (adding) {
              store.add(j, 1);
            } else {
              store.substract(j, 1);
            }
          }
          return null;
        }
      });
    }
    long start = System.currentTimeMillis();
    for (Future<Void> future : executor.invokeAll(jobs)) {
      while (!future.isDone()) {
        // loop!
      }
    }
    long stop = System.currentTimeMillis();
    jobs.clear();
    System.out.println(store.name() + ": " + (stop - start) + "ms");
    for (int i = 0; i < store.size(); i++) {
      System.out.print(store.at(i) + " ");
    }
    System.out.println();
    System.gc();
  }

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    Runner runner = new Runner();
  /*  for (int i = 0; i < 10; i++) {
      runner.bench(new HippieStore(STORE_SIZE));
    }*/
    for (int i = 0; i < 10; i++) {
      runner.bench(new PessimisticStore(STORE_SIZE));
    }
    for (int i = 0; i < 10; i++) {
      runner.bench(new OptimisticStore(STORE_SIZE));
    }
  }
}
