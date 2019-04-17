import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public abstract class BaseAPI {
    // httpclient
    protected static CloseableHttpClient httpClient;

    static {
        // 连接池
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(100);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(10);
        SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(1000 * 5).build();
        poolingHttpClientConnectionManager.setDefaultSocketConfig(socketConfig);

        httpClient = HttpClientBuilder.create()
                .setConnectionManager(poolingHttpClientConnectionManager)
                .disableCookieManagement()
                .build();
    }
}
