package com.robertsmieja.example.annotations;

import static com.robertsmieja.example.annotations.ReflectionAnnotationConsumer.doesClassHaveAnnotation;
import static com.robertsmieja.example.annotations.ReflectionAnnotationConsumer.doesMethodHaveAnnotation;

@HelloWorld
public class Main {
    public static void main(String[] args) throws NoSuchMethodException {
        System.out.println("Entering Main.main()");

        System.out.println("Does Object have @HelloWorld? " + doesClassHaveAnnotation(Object.class));
        System.out.println("Does Main have @HelloWorld? " + doesClassHaveAnnotation(Main.class));

        System.out.println("Does Main.printWithoutHelloWorld have @HelloWorld? " + doesMethodHaveAnnotation(Main.class.getMethod("printWithoutHelloWorld")));
        System.out.println("Does Main.printWithHelloWorld have @HelloWorld? " + doesMethodHaveAnnotation(Main.class.getMethod("printWithHelloWorld")));

        printWithoutHelloWorld();
        printWithHelloWorld();

        System.out.println("Exiting Main.main()");
    }

    public static void printWithoutHelloWorld(){
        System.out.println("Main.printWithoutHelloWorld()");
    }

    @HelloWorld
    public static void printWithHelloWorld(){
        System.out.println("Main.printWithHelloWorld()");
    }
}
