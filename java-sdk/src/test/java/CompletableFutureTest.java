import java.util.concurrent.CompletableFuture;

/**
 * @author chenfh
 * @date 2020-08-13 11:28
 */
public class CompletableFutureTest {

    public static void main(String[] args) {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(s->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s.toUpperCase();
        });
        System.out.println(cf.getNow("null"));
        cf.join();
        System.out.println(cf.getNow("null"));


    }
}
