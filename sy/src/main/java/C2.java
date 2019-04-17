import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class C2 {
    public static void main(String[] args) {
        Map<Integer, String> TAG_MAP = new LinkedHashMap<Integer, String>(){{
            put(0, "detail");
            put(7, "imxi");
            put(3000, "click");
            put(3001, "blacklist");
        }};

        Set<Integer> keys = TAG_MAP.keySet();
        for (Integer key : keys) {
            System.out.println(key);
        }
    }
}
