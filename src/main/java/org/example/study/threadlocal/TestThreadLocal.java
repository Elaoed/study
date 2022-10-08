package org.example.study.threadlocal;

public class TestThreadLocal {

    static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    static void print(String str) {
        System.out.println(str + " :" + threadLocal.get());
        threadLocal.remove();
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            //设置线程1中本地变量的值
            threadLocal.set("thread1 local variable");
            //打印本地变量
            print("thread1");
            System.out.println("after remove : " + threadLocal.get());
        });
        Thread t2 = new Thread(() -> {
            //设置线程1中本地变量的值
            threadLocal.set("thread2 local variable");
            //打印本地变量
            print("thread2");
            System.out.println("after remove : " + threadLocal.get());
        });
        t1.start();
        t2.start();

    }

}
