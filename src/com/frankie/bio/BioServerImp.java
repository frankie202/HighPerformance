package com.frankie.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioServerImp {
    public static void main(String[] args) throws IOException {
        ExecutorService exe = Executors.newCachedThreadPool();

        System.out.println("Bio server start");
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(8080));
        while (true) {
            Socket socket = serverSocket.accept();
            exe.submit(()-> {
                System.out.println("当前的连接：" + socket.getPort());
                InputStream is = null;
                byte[] buffer = new byte[1024];
                int len = 0;
                try {
                    is = socket.getInputStream();
                    len = is.read(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String s = new String(buffer, 0, len);
                System.out.println(s);
            });
        }
    }
}
