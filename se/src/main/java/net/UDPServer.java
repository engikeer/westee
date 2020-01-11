package net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

public class UDPServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(9090);

        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length);

        socket.receive(packet);     // 阻塞接收
        String msg = new String(packet.getData(), 0, packet.getLength(), StandardCharsets.UTF_8);
        System.out.println("收到消息：" + msg);

    }
}
