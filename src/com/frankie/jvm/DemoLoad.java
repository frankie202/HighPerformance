package com.frankie.jvm;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;

public class DemoLoad {
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, InterruptedException {
        URL url = new URL("file:D:\\");
//        URLClassLoader loader1 = new URLClassLoader(new URL[1]);
        while (true) {
            URLClassLoader loader = new URLClassLoader(new URL[]{url});

            Class clazz = loader.loadClass("DemoCLass");

            System.out.println("DemoClass使用的类加载器：" + clazz.getClassLoader());

            Object o = clazz.newInstance();
            Object test = clazz.getMethod("test").invoke(o);

            System.out.println("DemoClass的返回值 " + test);
            Thread.sleep(3000);
            System.out.println();

            o = null;
            test = null;
            loader = null;
//            System.gc();
        }
    }
}
