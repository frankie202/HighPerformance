package com.frankie.testDemo;

import java.io.*;

public class DemoSerializable {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        User user = new User();
        user.setName("Frankie");
        user.setAge(28);
        user.setStore("Hello World");

        ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("D:\\java\\HighPerformance\\out\\user.txt"));

        oos.writeObject(user);

        oos.close();
        System.out.println("before：" + user );
        ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("D:\\java\\HighPerformance\\out\\user.txt"));

        User object = (User) ois.readObject();

        ois.close();
        System.out.println(object);

    }
}
