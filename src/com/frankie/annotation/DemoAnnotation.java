package com.frankie.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;

@MyAnnotation(name = "me")
public class DemoAnnotation {

    public static void main(String[] args) {
        try {
            Class<?> clazz = Class.forName("com.frankie.annotation.DemoAnnotation");
            Annotation[] types = clazz.getDeclaredAnnotations();
            for (Annotation tt: types) {
                if (tt instanceof MyAnnotation) {
                    MyAnnotation annotation = clazz.getAnnotation(MyAnnotation.class);

                    System.out.println(annotation.name());
                    System.out.println(tt);

                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
