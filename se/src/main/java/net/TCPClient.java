package net;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Socket 实现即时通信：客户端
 */
public class TCPClient {
    public static void main(String[] args) throws Exception {
//        chat();
        upload();
    }

    /**
     * 发送消息
      * @throws IOException：关闭对象时可能抛出
     */
    public static void chat() throws IOException {
        Socket socket = null;
        OutputStream out = null;
        BufferedWriter writer = null;
        try {
            // 1. 连接到服务端
            InetAddress ip = InetAddress.getByName("127.0.0.1");
            int port = 8800;
            socket = new Socket(ip, port);
            // 2. 发送消息
            out = socket.getOutputStream();
            writer = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
            writer.write("明天又是好日子");
            writer.newLine();
            writer.write("心想的事儿都能成");

            System.out.println("=======客户端执行完成========");

        } catch (IOException e) {
            System.out.println("发生异常：" + e.getMessage());
        } finally {
            if (writer != null)
                writer.close();
            if (out != null)
                out.close();
            if (socket != null)
                socket.close();

        }
    }

    /**
     * 上传文件
     * @throws Exception：描述
     */
    public static void upload() throws Exception {
        // 省去了类型判断和异常捕获

        // 1. 建立连接
        Socket socket = new Socket("127.0.0.1", 8800);

        // 2. 读入文件
        FileInputStream fin = new FileInputStream("se/img/cache.jpg");

        // 3. 发送文件
        OutputStream out = socket.getOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fin.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        // 如果不关闭输出流，服务器中的 in.read 会一直等待数据，因为输出流没有末尾，服务器中 in.read(buffer)) 永远不会等于 -1
        socket.shutdownOutput();

        // 4. 确认服务器收到文件
        InputStream in = socket.getInputStream();
        BufferedReader reader = new BufferedReader( new InputStreamReader(in, StandardCharsets.UTF_8));
        String msg = reader.readLine();
        System.out.println(msg);

        // 5. 关闭资源
        out.close();
        fin.close();
        socket.close();
        System.out.println("=======客户端上传完成=======");

    }

}
