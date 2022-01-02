package com.frankie.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class BioClient {
    public static void main(String[] args) throws IOException {
        System.out.println("Client start");

        Socket socket = new Socket("127.0.0.1", 8080);
        OutputStream os = socket.getOutputStream();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Plz input:");
        String str = scanner.nextLine();
        os.write(str.getBytes());
        os.close();
    }
}
