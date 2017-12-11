package com.robertsmieja.example.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class ReflectionAnnotationConsumer {
    public static final Class<? extends Annotation> targetAnnotation = HelloWorld.class;

    public static boolean doesClassHaveAnnotation(Class<?> clazz){
        return clazz.isAnnotationPresent(targetAnnotation);
    }

    public static boolean doesMethodHaveAnnotation(Method method){
        return method.isAnnotationPresent(targetAnnotation);
    }
}
