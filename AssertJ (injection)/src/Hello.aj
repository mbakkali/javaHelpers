aspect Hello {
    long start;
    pointcut rootFibCall(long n):
      call(long Sample.fib(long)) && ! withincode(long Sample.fib(long)) && args(n);

    /*before(long n): rootFibCall(n) {
      start = System.currentTimeMillis();
      System.out.println("<<< fib(" + n + ")");
      if (n < 1){
        throw new IllegalArgumentException();
      }
    }

    after(long n): rootFibCall(n) {
      System.out.println("<<< time : " + (System.currentTimeMillis() - start ));
    }
    */
    long around(long n): rootFibCall(n) {
      //On a du traitement avant de faire notre action
      long start = System.currentTimeMillis();
      //Puis on a notre action (ici, l'action d'origine mais on peut la remplacer si on veut)
      long a = proceed(n);
      //post traitement;
      System.out.println("<<< time : " + (System.currentTimeMillis() - start ));
      return a;
    }

}
