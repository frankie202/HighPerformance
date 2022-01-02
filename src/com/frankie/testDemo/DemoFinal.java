package com.frankie.testDemo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

/**
 * DemoFinal
 *
 * @author: Frankie
 */
public class DemoFinal {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        /*Class<?> fc = Class.forName("com.frankie.testDemo.F1");

        Constructor<?>[] constructors = fc.getConstructors();

        F1 f1 = (F1) constructors[0].newInstance();
        System.out.println(f1);*/

        F1 f1 = new F1();

        Field name = F1.class.getDeclaredField("name");

        Field modifiersField = Field.class.getDeclaredField("modifiers");

        modifiersField.setAccessible(true);
        System.out.println(name.getModifiers());
        name.setAccessible(true);
        modifiersField.setInt(name,name.getModifiers() & ~Modifier.FINAL);
        System.out.println(name.getModifiers());

        name.set(f1,"Tom");
        System.out.println(f1);
    }
}

class F1 {
     public final String name = new String("Jack");

    public F1() {
    }

    @Override
    public String toString() {
        return name;
    }
}
