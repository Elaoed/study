package org.example.studyLock;

import java.util.concurrent.locks.ReentrantLock;

public class TryReentrantLock {

    private final ReentrantLock lock = new ReentrantLock();

    public void tryLock() {

        lock.lock();  // block until condition holds
        try {
            System.out.println("1");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        TryReentrantLock tryReentrantLock = new TryReentrantLock();
        tryReentrantLock.tryLock();
    }

}
