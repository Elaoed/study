package org.example.study;

public class Synchronizeda {

    private final static Object mutex = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized(mutex) {
                System.out.println("in thread1");
                try {
                    mutex.wait();
//                    mutex.notify();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("111");
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized(mutex) {
                System.out.println("in thread2");
                try {
//                    mutex.notify();
                    mutex.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("222");
            }
        });

        t1.start();
        t2.start();
        synchronized (mutex) {
            mutex.notifyAll();
        }

    }

}
