import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

class Ftp {
    public static  FTPClient getClient(String server, String username, String password) throws Exception {
        return getClient(server, 21, username, password);
    }

    public static  FTPClient getClient(String server, int port, String username, String password) throws Exception {
        FTPClient f = new FTPClient();
        f.connect(server, port);
        boolean login = f.login(username, password);
        if(!login) {
            throw new Exception(String.format("Login failed to FTP server: %s", server));
        }
        f.setFileTransferMode(f.BINARY_FILE_TYPE);
        // 主动模式：客户端开放端口给服务端用；
        f.enterLocalPassiveMode();
        return f;
    }
}

public class TestFTP {
    public static void main(String[] args) throws Exception {
        // 连接
        FTPClient client = Ftp.getClient("42.123.106.19", 9101, "vendortdkj_fzdx", "3TdZ4N6cesexbZXv");

        // 列出文件
        for (FTPFile line : client.listFiles()) {
            System.out.println(line.getName());
        }

        // 读文件
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        boolean success = client.retrieveFile("a.txt", byteArrayOutputStream);
        String content = "";
        if (success) {
            content = byteArrayOutputStream.toString(StandardCharsets.UTF_8.name());
        } else {
            System.out.println("download file failed.");
        }

        System.out.println(content);
    }
}
