import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Main {
    public static void main(String[] args) {
        ConcurrentMap<Integer, String> concurrentMap = new ConcurrentHashMap<>();

        concurrentMap.put(1,"abc");
        concurrentMap.put(2,"def");

        for (Integer integer : concurrentMap.keySet()) {
            System.out.println(concurrentMap.get(integer));
        }
    }
}
