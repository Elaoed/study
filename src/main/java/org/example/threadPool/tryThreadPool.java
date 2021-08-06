package org.example.threadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class tryThreadPool {
    // 池子的作用: 避免生成和销毁时候的开销 哪些开销 创建一个对象时候的加载 销毁时候的GC
    // 获取的速度快
    // 防止创造出无限的线程浪费资源，并且对一个池子里的线程可以统一分配和管理监控
    // 线程池具备可拓展性，允许加入更多的功能 最典型的就是丢弃策略和定时执行
    // 线程池解决的核心问题是资源管理问题
    // Executor的核心思想，把线程的提交和执行解耦 ExecutorService提供了线程池的管理能力 比如停止线程池的运行
    // 针对于任务的运行，有有了任务然后进行线程分配的，也有空了的线程从任务获取中心获取线程的
    // 问题1：Java的线程池说一下，各个参数的作用，如何进行的?
    // 问题2：按线程池内部机制，当提交新任务时，有哪些异常要考虑。 Reject
    // 问题3：线程池都有哪几种工作队列？BlockingQueue Array/Linked DelayedWorkQueue SynchronousQueue
    // 问题4：使用无界队列的线程池会导致内存飙升吗？
    // 问题5：说说几种常见的线程池及使用场景?
    // 定长线程池的大小最好根据系统资源进行设置如Runtime.getRuntime().availableProcessors()。
    // newCachedThreadPool 无core，直接提交。SynchronousQueue，无内部空间直接提交 速度快 浪费资源 60s
    // newFixedThreadPool 核心线程数和最大线程数保持一致 fixed是特点 不会释放线程 但是比较快
    // newSingleThreadExecutor 固定一个线程FIFO
    // newScheduledThreadPool DelayedWorkQueue()
    // https://www.cnblogs.com/williamjie/p/9485723.html
    // 为啥要区分核心线程和非核心线程: 直接的说就是正式工和临时工 一般的活正式工 + blockingQueue就能解决了，不需要开非核心线程，也是在合理的等待范围之内减少创建和销毁线程的开销
    // 为啥要有任务队列: 为核心线程和非核心线程中的一个缓冲 不至于任务一多就创建线程 损耗很大需要拿全局锁，通过系统调用的JNI切换到内核态进行创建。第二，为了提供线程处理不过来的时候的缓冲，承担业务压力。但是这里就要考虑是否会积累太多任务导致 OOM
    // 为啥是队列满了之后再生成非核心线程: 设计的本意是靠核心线程 + 任务队列就解决所有大部分问题 非核心线程只是一个保底
    // https://www.zhihu.com/question/412524104/answer/1390541676

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(20, 40, 10L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100), r -> {
            System.out.println("in new Thread");
//            r.run();
            return new Thread(r);
        }, new ThreadPoolExecutor.AbortPolicy());

        Future<?> future = threadPoolExecutor.submit(() -> "11");
        Object o = future.get();
        // 这玩意为什么get不到一直阻塞 因为任务没执行吗 笑死了 因为返回的Thread里面就根本没有包含runnable的入参 大意了
        System.out.println(o);
        threadPoolExecutor.execute(() -> System.out.println("1111"));
        threadPoolExecutor.shutdown();


//        Executors.newCachedThreadPool();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<String> submit = executorService.submit(() -> "2222");
        System.out.println("submit.get(): " + submit.get());
//        Executors.newSingleThreadExecutor();
        // execute和submit的区别 在于返回值
        // Executors转runnable为callable 也是假的callable 就算转过来的还是不会抛异常和正常结果的返回值呀
        Callable<Object> callable = Executors.callable(() -> System.out.println("11"));

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.scheduleAtFixedRate(() -> System.out.println("111"), 1, 3, TimeUnit.SECONDS);

        FutureTask<String> stringFutureTask = new FutureTask<>(() -> "1");
        stringFutureTask.run();
        // 多少个线程比较合适 如果是CPU密集型的 cpuNum + 1, 如果是IO密集型的 通过cat和skyWalking等
        // 查看整体的耗时和IO的耗时 IO / 整体 - IO 就行了得到一个理论值 然后根据实际情况微调

        CompletableFuture<String> stringCompletableFuture = new CompletableFuture<>();
        stringCompletableFuture.complete("Future's Result Here Manually");
        String s = stringCompletableFuture.get();

        CompletableFuture<Void> oo = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            System.out.println("运行在一个单独的线程当中");
        });
        Void unused = oo.get();

        CompletableFuture<String> supplyResult = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            System.out.println("yeeah supply async");
            return "我有返回值";
        });
        String s1 = supplyResult.get();
        System.out.println(s1);

        CompletableFuture.supplyAsync(() -> "111")
                .thenApply(r -> r + "222")
                .thenAccept(System.out::println);
        // 为啥我的代码里面没有什么地方用到异步的


    }

}
