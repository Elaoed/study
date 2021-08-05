package org.example.multi;

import java.util.concurrent.Callable;

public class InterruptedThread extends Thread {

    @Override
    public void run() {
        System.out.println("hey im running.......");
    }

    public static void main(String[] args) throws Exception {
        // 一个接口的默认实现Impl.method(){} 里面只有一个method method里面的代码
        Runnable runnable = () -> System.out.println("");
        runnable.run();

        // caller优于runnable可以检测结果 可以取现 runnable优于Thread 接口优于继承 方便扩展
        Callable<String> callable = () -> "b";
        callable.call();
        // park -> wait interrupt

        // 在哪里interrupt 在执行代码的地方立马抛出interruptException 然后做收尾操作
        // 只有类似sleep的函数才能捕捉interruptedException?
        // java中断响应是描述当一个线程或方法A处于运行()、阻塞或死锁状态时
        // 中断协议只是setThread.interrupted = true
        // 然后interruptedException是Thread.sleep自己判断之后抛出来的
        Runnable runnable1 = () -> {

            while (!currentThread().isInterrupted() && true) {

                System.out.println("hey this is in thread1, interrupted: " + currentThread().isInterrupted());

//                try {
//                    System.out.println("hey im in the thread1 loop");
//                    // 收到interrupted之后
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    System.out.println("hey this is error of thread1, interrupted: " + Thread.interrupted());
//                    e.printStackTrace();
//                    return;
//                }

            }
            System.out.println("hey this is end of thread1, interrupted: " + currentThread().isInterrupted());
        };

        // 只有继承自Thread的才能被主线程interrupt 兄弟线程行不行
        InterruptedThread testThread = new InterruptedThread();
        // run是直接运行 start会新开线程
        testThread.start();
//        testThread.interrupt();

        // 正确开启线程的方式
        Thread rightWayOfInitThread = new Thread(runnable1);
        rightWayOfInitThread.start();
        Thread.sleep(100);
        rightWayOfInitThread.interrupt();
        // 等呗
//        rightWayOfInitThread.join();
        // 虽然走到这里了 常理上主进程已经over了 不知道从真正的进程层面有没有问题
        System.out.println("the main thread is over");

    }
}
