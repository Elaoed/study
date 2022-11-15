package org.example.algorithm.other;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TaoSeries {

    private static WeakHashMap<String, Content> cache = new WeakHashMap<>();
    public static void multiThreadRead(String[] args) {
        // 往concurrentHashMap里面塞
        List<String> filePaths = new ArrayList<>();

        // 顺便线程池的用法
        // 开线程
        int cpuNums = Runtime.getRuntime().availableProcessors();
        ExecutorService pool = new ThreadPoolExecutor(cpuNums, cpuNums, 10L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100), r -> {
            System.out.println();
            return new Thread(r);
        });
        Executor ttlExecutor = TtlExecutors.getTtlExecutor(pool);
        // 读取文件 父子线程之间传递变量要么通过全局变量？ 要么通过Transmitted-Thread-Local的方式
        ThreadLocal<String> TTL = new TransmittableThreadLocal<>();
        for (int i = 0; i < cpuNums; i++) {
            final int fileNum = filePaths.size() / cpuNums;
            TTL.set(String.valueOf(i));
            ttlExecutor.execute(() -> {
                String s = TTL.get();
                int base = Integer.parseInt(s);
                for (int k = fileNum * base; k < fileNum * (base + 1); k++) {
//                    Stream<String> lines = Files.lines(Paths.get(filePaths.get(k)));
                    try (
                            BufferedInputStream bin = new BufferedInputStream(Files.newInputStream(Paths.get(filePaths.get(k))));
                            BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(filePaths.get(k)));
                    ) {
                        int b;
                        while ((b = bin.read()) != -1) {
                            bout.write(b);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        // 会等待所有的线程完成之后清理资源并关闭线程池
        pool.shutdown();
//        new Thread(() -> {}).start();
        // Thread.currentThread().join();

    }


    public static class Content {

        // 这种情况下连key都不需要
        private String key;
        private Object value;
        private long expiredAt;
        public Content(String key, Object value, long expiredAt) {
            this.key = key;
            this.value = value;
            this.expiredAt = expiredAt;
        }
    }
    /**
     * 设计一个缓存不能有oom，并且能够支持过期时间
     */
    public static void weakReferencePut(String key, Object value, long expireTime) {
        long expiredAt = System.currentTimeMillis() + expireTime;
        cache.put(key, new Content(key, value, expiredAt));
    }

    public static Object weakReferenceGet(String key) {
        Content content = cache.get(key);
        if (content == null || content.expiredAt < System.currentTimeMillis()) {
            cache.remove(key);
            return null;
        }
        return content.value;
    }

}
