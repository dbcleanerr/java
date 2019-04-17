import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class WorkerThread implements Runnable {
    private String command;

    public WorkerThread(String command) {
        this.command = command;
    }

    private void processCommand() {
        try {
            System.out.println("command:" + command);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "WorderThread{" +
                "command='" + command + '\'' +
                '}';
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Start. Time = " + LocalDateTime.now());
        processCommand();
        System.out.println(Thread.currentThread().getName() + " End. Time = " + LocalDateTime.now());

    }
}
public class ScheduledThreadPoolExecutorDemo {
    /**
     * scheduleWithFixedDelay 中的 delayTime
     * 代表每次线程任务执行完毕后，直到下一次开始执行开始之前的时间间隔。
     */
    public static void scheduleWithDelay() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        WorkerThread workerThread = new WorkerThread("do some thing");
        scheduledExecutorService.scheduleWithFixedDelay(workerThread, 3000, 3000, TimeUnit.MILLISECONDS);

    }
    public static void main(String[] args) {
        scheduleWithDelay();
    }
}
