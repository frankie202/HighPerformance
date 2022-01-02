package com.frankie.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BioServer {
    public static void main(String[] args) throws IOException {
        System.out.println("Bio server start");
        ServerSocket serverSocket = new ServerSocket(8080);

        while (true) {
            /*等待连接*/
            Socket socket = serverSocket.accept();
            System.out.println("当前的连接：" + socket.getPort());
            InputStream is = socket.getInputStream();
            byte[] buffer = new byte[1024];
            int len = is.read(buffer);
            String s = new String(buffer, 0, len);
            System.out.println(s);
        }
    }
}
