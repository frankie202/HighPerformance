package com.frankie.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class NioServer {
    public static void main(String[] args) throws IOException {
        /*创建服务通道*/
        ServerSocketChannel ssc = ServerSocketChannel.open();
        System.out.println("Server start");
        /*切换到异步非阻塞*/
        ssc.configureBlocking(false);
        /*绑定连接*/
        ssc.bind(new InetSocketAddress(8080));
        /*获取选择器*/
        Selector selector = Selector.open();
        /*将通道注册到选择器中，指定监听事件*/
        ssc.register(selector, SelectionKey.OP_ACCEPT);
//      轮询获取已准备就绪的事件
        while (selector.select() > 0) {
            /*获取当前选择器中所有已经注册的选择键*/
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
//                获取准备就绪的事件
                SelectionKey selectionKey = it.next();

                if (selectionKey.isAcceptable()) {
                    SocketChannel channel = ssc.accept();
                    channel.configureBlocking(false);
                    channel.register(selector,SelectionKey.OP_READ);
                    System.out.println("收到请求：" + channel.getRemoteAddress());
                } else if (selectionKey.isReadable()) {
                    SocketChannel client = (SocketChannel) selectionKey.channel();
                    try {

                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        while (client.isOpen() && client.read(buffer) != 1) {
                            if (buffer.position() > 0) {
                                break;
                            }
                        }
                        buffer.flip();
                        byte[] msg = new byte[buffer.limit()];
                        buffer.get(msg);
                        System.out.println("请求信息" + new String(msg));

                        String resp = "Get It";
                        ByteBuffer respBuffer = ByteBuffer.wrap(resp.getBytes());
                        buffer.flip();
                        while (respBuffer.hasRemaining()) {
                            client.write(respBuffer);
                        }
                    } finally {
                        client.close();
                    }
                }
            }
            it.remove();
        }
    }
}
