import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public abstract class ETLUtil implements Runnable {
    private ConcurrentMap<String, AtomicLong> counter;
    private AtomicInteger threadNum;

    public void setCounter(ConcurrentMap<String, AtomicLong> counter) {
        this.counter = counter;
    }

    public void setThreadNum(AtomicInteger threadNum) {
        this.threadNum = threadNum;
    }

    public ConcurrentMap<String, AtomicLong> getCounter() {
        return counter;
    }

    /**
     * 增加内容到 counter
     * @param key
     * @param num
     */
    protected void counterAdd(String key, Long num) {
        // 如果不存在,key返回null
        // 存在返回则返回存在的值
        AtomicLong cnt = counter.putIfAbsent(key, new AtomicLong(num));
        if(cnt != null)
            cnt.addAndGet(num);
    }

    /**
     * 类似于 counterAdd(key, 1L)
     * @param key
     */
    protected void counterIncrement(String key) {
        AtomicLong cnt = counter.putIfAbsent(key, new AtomicLong(1));
        if(cnt != null)
            cnt.incrementAndGet();
    }

    /**
     * 实际要跑的内容
     */
    public abstract void process();

    public void run() {
        System.out.println("Begin.....");
        process();
        // 剩下可以跑的线程数-1
        threadNum.decrementAndGet();
        System.out.println("End.....");
    }
}
