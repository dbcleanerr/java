import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public class C3 {
    public static void main(String[] args) {
        ConcurrentMap<String, AtomicLong> counter = new ConcurrentHashMap<>();
        AtomicLong cnt;

        cnt = counter.putIfAbsent("abc", new AtomicLong(100));
        cnt = counter.putIfAbsent("abc", new AtomicLong(200));
        System.out.println(cnt.get());
        cnt.addAndGet(200);

        for (String s : counter.keySet()) {
            System.out.println(counter.get(s));
        }
    }
}
