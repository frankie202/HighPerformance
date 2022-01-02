package com.frankie.nio;

import java.nio.ByteBuffer;

public class BufferDemo {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocateDirect(4);
        System.out.println("before write capacity:" + buffer.capacity() + " limit:" + buffer.limit() +
                " position:" + buffer.position());

        buffer.put((byte) 1);
        buffer.put((byte) 2);
        buffer.put((byte) 3);
        System.out.println("after write capacity:" + buffer.capacity() + " limit:" + buffer.limit() +
                " position:" + buffer.position());

        buffer.flip();
        byte b = buffer.get();
        System.out.println(b);
        byte b2 = buffer.get();
        System.out.println(b2);
        System.out.println("after read 2 byte capacity:" + buffer.capacity() + " limit:" + buffer.limit() +
                " position:" + buffer.position());

        buffer.compact();/*清除已读取的数据*/
//        buffer.clear();
        System.out.println("compact  capacity:" + buffer.capacity() + " limit:" + buffer.limit() +
                " position:" + buffer.position());
        buffer.put((byte) 4);
        buffer.put((byte) 5);
        buffer.put((byte) 6);
        System.out.println("final capacity:" + buffer.capacity() + " limit:" + buffer.limit() +
                " position:" + buffer.position());
        buffer.flip();
        buffer.get();
        byte bb = buffer.get();
        System.out.println(bb);
        buffer.clear();
        System.out.println("--- capacity:" + buffer.capacity() + " limit:" + buffer.limit() +
                " position:" + buffer.position());
    }
}
