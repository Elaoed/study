package org.example.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// https://juejin.cn/post/6844903501772881928 AQS的核心代码部分
// 精准的控制某个条件队列，如果是synchronized唤醒的话可能会消费者唤醒的还是消费者这样
public class TryCondition {

    private Lock lock = new ReentrantLock();

    private Condition consumerList = lock.newCondition();

    private Condition producerList = lock.newCondition();

    final Object[] items = new Object[5];

    volatile int putptr, takeptr, count;

    public void put(Object x) {
        lock.lock();
        try {
            while (count == items.length) {
                // 满了 收手吧 生产者
                producerList.await();
            }
            // 有空了 把对象放到最新的地方 如果到顶了把putptr变成0 why 因为只有全部消耗光了才会被signal吗
            items[putptr] = x;
            if (++putptr == items.length) {
                putptr = 0;
            }
            ++count;
            // 出来yaxi了 消费者们
            consumerList.signal();
            System.out.println(Thread.currentThread().getName() + " put " + x);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public Object take() {
        lock.lock();
        try {
            // 空了 收手吧 消费者
            while (count == 0) {
                consumerList.await(); // put current thread into consumer
            }
            Object x = items[takeptr];
            if (++takeptr == items.length) {
                takeptr = 0;
            }
            --count;
            // 出来干活了 生产者们
            producerList.signal();
            System.out.println(Thread.currentThread().getName() + " take " + x);
            return x;

        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            lock.unlock();
        }

    }

    public static class Producer implements Runnable {

        private TryCondition tryCondition;

        public Producer(TryCondition tryCondition) {
            this.tryCondition = tryCondition;
        }

        @Override
        public void run() {
            while (true) {
                tryCondition.put(new Object());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static class Consumer implements Runnable {

        private TryCondition tryCondition;

        public Consumer(TryCondition tryCondition) {
            this.tryCondition = tryCondition;
        }

        @Override
        public void run() {
            while (true) {
                tryCondition.take();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) {
        final TryCondition tryCondition = new TryCondition();
        final Consumer consumer = new Consumer(tryCondition);
        final Producer producer = new Producer(tryCondition);

        final Thread t1 = new Thread(producer, "生产者1");
        final Thread t2 = new Thread(producer, "生产者2");

        final Thread t4 = new Thread(consumer, "消费者1");
        final Thread t5 = new Thread(consumer, "消费者2");

        t1.start();
        t2.start();
        t4.start();
        t5.start();
    }

}

