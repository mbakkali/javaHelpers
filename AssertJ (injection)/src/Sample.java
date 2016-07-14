public class Sample {

    public long fib(long n) {
        if (n < 2) {
            return n;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }

    public void run() {
        System.err.println("Hello");
        System.out.println(fib(40));
        try {
            System.out.println(fib(-10));
        } catch (IllegalArgumentException ignored) {
            System.out.println("Bien joué !");
        }
    }

    public static void main(String... args) {
        new Sample().run();
    }
}