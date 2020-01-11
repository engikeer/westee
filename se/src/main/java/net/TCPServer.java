package net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


/**
 * Socket 实现即时通信：服务端
 */
public class TCPServer {

    public static void main(String[] args) throws Exception {
//        chat();
        upload();
    }

    /**
     * 接收消息
     * @throws IOException：关闭对象时可能抛出
     */
    public static void chat() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8800)){
            // 1. 启动服务器端


            while(true) {
                // 2. 等待客户端连接
                Socket socket = serverSocket.accept();
                // 3. 读取客户端发送的消息
                InputStream in = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in ,StandardCharsets.UTF_8));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }

                reader.close();
                in.close();
                socket.close();
            }
        } catch (IOException e) {
            System.out.println("发生异常：" + e.getMessage());
        }
    }

    public static void upload() throws Exception {
        // 省去了类型判断和异常捕获

        // 1. 启动服务端

        try (ServerSocket serverSocket = new ServerSocket(8800)) {
            int i = 1;
            while (true) {
                // 2. 等待连接
                Socket socket = serverSocket.accept();

                // 3. 读入文件
                InputStream in = socket.getInputStream();

                // 4. 保存文件
                FileOutputStream fout = new FileOutputStream("se/cache/" + i + ".jpg");
                byte[] buffer = new byte[1024];
                int len;
                // 只有客户端输出流关闭后服务器输入流才有末尾，in.read(buffer) 才会等于 -1
                // 否则，当没有可读数据时，in.read 会一直阻塞
                while ((len = in.read(buffer)) != -1) {
                    fout.write(buffer, 0, len);
                }

                System.out.println(i + ".jpg 保存完成");
                i++;

                // 5. 通知客户端上传完成
                OutputStream out = socket.getOutputStream();
                out.write("文件已收到".getBytes(StandardCharsets.UTF_8));

                // 5. 关闭资源，将缓存的数据发送
                out.close();
                fout.close();
                in.close();
                socket.close();
            }
        }
    }
}
