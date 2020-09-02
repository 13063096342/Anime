package main.java;

/**
 * @author chenfh
 * @date 2020-04-14 10:38
 */
public class ExceptionTest {
    public static void main(String[] args) {
        System.out.println(Math.pow(1.01,365));
        /*try {
            doSomethingWhichThrowsException();
            System.out.println("OK");   // incorrect "OK" message is printed
        } catch (RuntimeException e) {
            System.out.println("ERROR");  // this message is not shown
        }*/
    }

    public static void doSomethingWhichThrowsException() {
        try {
            throw new RuntimeException();
        } finally {
            int q = 2;
            for (int i = 0; i < 10; i ++) {
                //...
                if (q == i) {
                    break; // ignored
                }
            }

            /* ... */
            return;      // Noncompliant - prevents the RuntimeException from being propagated
        }
    }
}
