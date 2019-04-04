import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    // 共享缓存区
    private BlockingQueue<Data> queue;

    // 随机对象
    private static Random r = new Random();

    public Consumer(BlockingQueue<Data> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            // 获取数据
            try {
                // 获取数据
                Data data = this.queue.take();
                // 数据处理，休眠0-1000毫秒模拟
                Thread.sleep(r.nextInt(1000));
                //
                System.out.println("当前线程:" + Thread.currentThread().getName() +
                        ", 浪费成功,浪费数据为id:" + data.getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
