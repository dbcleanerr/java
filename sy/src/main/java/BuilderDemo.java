import lombok.Builder;

import java.io.BufferedReader;

@Builder
class DemoBean {
    private String id;
    private String name;
    @Builder.Default
    private String city = "default value";

    @Override
    public String toString() {
        return "DemoBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}

public class BuilderDemo {
    public static void main(String[] args) {
        DemoBean demoBean = DemoBean.builder().id("001").name("zyy").build();
        System.out.println(demoBean.toString());

        demoBean = DemoBean.builder().id("001").name("zyy").city("shanghai").build();
        System.out.println(demoBean.toString());
    }
}
