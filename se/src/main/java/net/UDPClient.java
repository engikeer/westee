package net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class UDPClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();

        String msg = "你好！";
        byte[] buffer = msg.getBytes(StandardCharsets.UTF_8);
        InetAddress ip = InetAddress.getByName("127.0.0.1");
        int port = 9090;
        DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length, ip, port);
        socket.send(packet);
        socket.close();

    }
}
