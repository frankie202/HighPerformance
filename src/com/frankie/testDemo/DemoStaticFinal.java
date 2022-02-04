package com.frankie.testDemo;

/**
 * DemoStaticFinal
 *
 * @author: Frankie
 */
public class DemoStaticFinal {

    /*
    * 引用常量的类型与是否初始化类的关系：
    * 1、常量为基本类型：不会初始化该类
    * 2、常量为字符串常量：不会初始化该类
    * 3、常量为引用类型： 初始化
    *
    * */
    public static void main(String[] args) {
        System.out.println(DemoClass.STR);
    }
}

class DemoClass {
//    public static final int NUM = 1234;
//    public static final String STR = "彦哲";
    public static final String STR = new String("彦哲");
    static {
        System.out.println(DemoClass.class.getName() + "~~~~~~~~~");
    }
}
