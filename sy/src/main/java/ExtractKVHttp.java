import java.util.concurrent.atomic.AtomicInteger;

public class ExtractKVHttp extends Extract<Long> {

    @Override
    public void setThreadNum(AtomicInteger threadNum) {
        threadNum.set(1);
        super.setThreadNum(threadNum);
    }

    @Override
    public void process() {
        System.out.println("干活");
    }
}
