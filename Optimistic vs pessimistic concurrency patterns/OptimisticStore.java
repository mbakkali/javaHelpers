import java.util.concurrent.atomic.AtomicLongArray;

class OptimisticStore implements Store {

  private final int size;
  private final AtomicLongArray atomicValues;

  OptimisticStore(int size) {
    this.size = size;
    atomicValues = new AtomicLongArray(size);
  }

  @Override
  public String name() {
    return "Optimistic";
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public long at(int index) {
    return atomicValues.get(index);
  }

  @Override
  public void add(int index, long amount) {
    while(atomicValues.compareAndSet(index, atomicValues.get(index) + amount, atomicValues.get(index) + amount));
  }

  @Override
  public void substract(int index, long amount) {
    while(atomicValues.compareAndSet(index, atomicValues.get(index) - amount, atomicValues.get(index) - amount));
  }
}
