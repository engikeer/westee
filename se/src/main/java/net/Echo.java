package net;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Echo {
    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("127.0.0.1", 8189)){
            InputStream inputStream = socket.getInputStream();
            Scanner in = new Scanner(inputStream, "UTF-8");
            System.out.println(in.nextLine());
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8), true);
            try (Scanner scanner = new Scanner(System.in)) {
                while (true) {
                    String s = scanner.nextLine();
                    writer.println(s);
                    if ("BYE".equals(s)) {
                        System.out.println("关闭连接");
                        return;
                    }
                    System.out.println(in.nextLine());
                }
            }
        }

    }
}
