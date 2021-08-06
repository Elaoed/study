package org.example.studyLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// https://juejin.cn/post/6844903501772881928 AQS的核心代码部分
public class TryCondition {

    private Lock lock = new ReentrantLock();

    private Condition consumerList = lock.newCondition();

    private Condition producerList = lock.newCondition();

    final Object[] items = new Object[5];

    int putptr, takeptr, count;

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

}

