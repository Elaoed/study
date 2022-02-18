package org.example.generateClass;

public class GenerateClass {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> clazz = Class.forName("org.example.generateClass.GenerateClass");
        // clazz是class对象
        Class<?> clazz1 = GenerateClass.class;
        GenerateClass tryClass = new GenerateClass();
        Class<?> clazz2 = tryClass.getClass();

    }
}
