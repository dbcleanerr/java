import java.util.concurrent.atomic.AtomicInteger;

public class main2 {
    public static void main(String[] args) {
        AtomicInteger threadNum = new AtomicInteger(10);
        threadNum.decrementAndGet();
        System.out.println(threadNum.get());
    }
}
