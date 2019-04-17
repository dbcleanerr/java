import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public abstract class ETL {
    protected Extract extract;
    protected Transform transform;
    protected Load load;

    protected BlockingQueue eOutQueue;
    protected BlockingQueue tOutQueue;

    protected AtomicInteger eThreadNum, tThreadNum, lThreadNum;

    private ConcurrentMap<String, AtomicLong> counter = new ConcurrentHashMap<>();

    protected int totalThreadNum = 32;

    protected void setup() throws Exception {

    }

    protected void cleanup() throws Exception {

    }

    public ConcurrentMap<String, AtomicLong> getCounter() {
        return counter;
    }
}
