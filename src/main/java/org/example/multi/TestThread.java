package org.example.multi;

import java.util.concurrent.Callable;

public class TestThread {
    public static void main(String[] args) throws Exception {
        // 一个接口的默认实现Impl.method(){} 里面只有一个method method里面的代码
        Runnable runnable = () -> System.out.println("");
        runnable.run();

        // caller优于runnable可以检测结果 可以取现 runnable优于Thread 接口优于继承 方便扩展
        Callable<String> callable = () -> "b";
        callable.call();
        // park -> wait interrupt
    }
}
