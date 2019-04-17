import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Transform<inT, outT> extends ETLUtil {
    protected BlockingQueue<inT> inQueue;
    protected BlockingQueue<outT> outQueue;
    protected AtomicInteger parentThreadNum;

    public void setInQueue(BlockingQueue<inT> inQueue) {
        this.inQueue = inQueue;
    }

    public void setOutQueue(BlockingQueue<outT> outQueue) {
        this.outQueue = outQueue;
    }

    public void setParentThreadNum(AtomicInteger parentThreadNum) {
        this.parentThreadNum = parentThreadNum;
    }
}
