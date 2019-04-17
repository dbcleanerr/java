import java.util.concurrent.BlockingQueue;

public abstract class Extract<T> extends ETLUtil {
    // ETLUtil的基础上增加个 queue
    private BlockingQueue<T> outQueue;

    public void setOutQueue(BlockingQueue<T> outQueue) {
        this.outQueue = outQueue;
    }
}
