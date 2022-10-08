package org.example.lock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

//@Slf4j
public class TrySemaphore {

    private final static int threadCount = 15;

    private static final Logger log = LogManager.getLogger(TrySemaphore.class);

    public static void main(String[] args) throws Exception {

        ExecutorService exec = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    //获取一个许可
                    semaphore.acquire(2);
                    test(threadNum);
                    //释放一个许可
                    semaphore.release(2);
                } catch (Exception e) {
                    log.error("exception", e);
                }
            });
        }
        exec.shutdown();
    }

    private static void test(int threadNum) throws Exception {
        // 模拟请求的耗时操作
        Thread.sleep(1000);
        log.info("线程数量 {}", threadNum);
    }

}
