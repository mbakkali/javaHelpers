aspect Println {
    pointcut callPrintln(java.io.PrintStream stream):
      call(void  java.io.PrintStream+.println(..)) && target(stream);

      void around(java.io.PrintStream stream): callPrintln(stream) {
        if (stream == System.out) {
          stream.print(":3 ");
        }
      proceed(stream);
    }
}
