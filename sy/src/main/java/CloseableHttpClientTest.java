import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class CloseableHttpClientTest {
    public static void doGet() {
        CloseableHttpClient client = HttpClientBuilder.create().build();

        try {
            HttpGet get = new HttpGet("http://www.thepaper.cn");

            get.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");
            HttpResponse response = client.execute(get);
            String res =  EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void doPost() {

    }

    public static void main(String[] args) {
        doGet();
    }
}
