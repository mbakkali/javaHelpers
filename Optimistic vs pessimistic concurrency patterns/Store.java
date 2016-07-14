interface Store {
  String name();
  int size();
  long at(int index);
  void add(int index, long amount);
  void substract(int index, long amount);
}
