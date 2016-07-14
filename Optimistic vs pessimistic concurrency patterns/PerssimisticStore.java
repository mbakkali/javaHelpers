import java.util.ArrayList;
class PessimisticStore extends HippieStore implements Store{

    private final ArrayList<Object> locks;

    PessimisticStore(int size){
      super(size);
      locks = new ArrayList<Object>();
      for(int i=0; i<size; i++){
        locks.add(new Object());
      }
    }

    @Override
    public String name() {
      return "Pessimistic";
    }

    @Override
    public long at(int index) {
      return values[index];
    }

    @Override
    public void  add(int index, long amount) {
      synchronized(locks.get(index)){
        values[index] = values[index] + amount;
      }
    }

    @Override
    public void substract(int index, long amount) {
      synchronized(locks.get(index)){
        values[index] = values[index] - amount;
      }
    }
}
