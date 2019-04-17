import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) {
        // 内存缓冲区,长度为3
        BlockingQueue<Data> queue = new LinkedBlockingQueue<>(3);

        // 生产者,三个队列
        Provider p1 = new Provider(queue);
        Provider p2 = new Provider(queue);
        Provider p3 = new Provider(queue);

        // 浪费者，二个队列
        Consumer c1 = new Consumer(queue);
//        Consumer c2 = new Consumer(queue);
//        Consumer c3 = new Consumer(queue);
//        Consumer c4 = new Consumer(queue);

        // 创建线程池，这是一个缓存的线程池，可以创建无穷大的线程
        // 没有任务的时候不创建线程，空闲线程存活时间为60秒(默认值)

        ExecutorService cachePool = Executors.newCachedThreadPool();

        cachePool.execute(p1);
        cachePool.execute(p2);
        cachePool.execute(p3);
        cachePool.execute(c1);
//        cachePool.execute(c2);
//        cachePool.execute(c3);
//        cachePool.execute(c4);

        // 模拟停止线程
        try {
            sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
