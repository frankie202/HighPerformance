package com.frankie.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class NioClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        System.out.println("Client Start");
        socketChannel.configureBlocking(false);

        socketChannel.connect(new InetSocketAddress("localhost",8080));

        while (!socketChannel.finishConnect()) {
            System.out.println(Thread.currentThread().getName() + "还没连上");
            // 没连上一直等待
            Thread.yield();
        }
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Plz input:");
        String str = scanner.nextLine();
        buffer.put(str.getBytes());

        buffer.flip();
        while (buffer.hasRemaining())
            socketChannel.write(buffer);

        buffer.clear();

        while (socketChannel.isOpen() && socketChannel.read(buffer) != 1) {
            if (buffer.position() > 0) {
                break;
            }
        }

        buffer.flip();
        byte[] msg = new byte[buffer.limit()];
        buffer.get(msg);
        System.out.println("响应信息" + new String(msg));
        socketChannel.close();
    }
}
