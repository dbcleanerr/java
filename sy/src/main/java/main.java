import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class main {
    public static void main(String[] args) {
        C1 c1 = new C1();

        ExecutorService cachePool = Executors.newCachedThreadPool();
        cachePool.execute(c1);

        cachePool.shutdown();

        while (!cachePool.isTerminated()) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
