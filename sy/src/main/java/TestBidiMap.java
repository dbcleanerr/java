import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

public class TestBidiMap {
    public static void main(String[] args) {
        BidiMap bidiMap = new DualHashBidiMap();
        bidiMap.put("BJ", "Beijing");
        bidiMap.put("SH", "Shanghai");
        bidiMap.put("GZ", "Guangzhou");
        bidiMap.put("CD", "Chengdu");

        System.out.println(bidiMap.get("BJ"));
        System.out.println(bidiMap.getKey("Beijing"));

        // K-VG互换
        System.out.println(bidiMap.containsKey("Beijing"));
        System.out.println(bidiMap.inverseBidiMap().containsKey("Beijing"));
    }
}
