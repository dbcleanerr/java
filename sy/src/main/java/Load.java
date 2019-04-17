import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Load<T> extends ETLUtil {
    protected BlockingQueue<T> inQueue;
    protected AtomicInteger parentThreadNum;

    public void setInQueue(BlockingQueue<T> inQueue) {
        this.inQueue = inQueue;
    }

    public void setParentThreadNum(AtomicInteger parentThreadNum) {
        this.parentThreadNum = parentThreadNum;
    }
}
