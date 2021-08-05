package org.example.tryClass;

public class TryClass {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> clazz = Class.forName("org.example.tryClass.TryClass");
        // clazz是class对象
        Class<?> clazz1 = TryClass.class;
        TryClass tryClass = new TryClass();
        Class<?> clazz2 = tryClass.getClass();

    }
}
