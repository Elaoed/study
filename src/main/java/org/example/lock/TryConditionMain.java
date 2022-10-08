package org.example.lock;

import lombok.SneakyThrows;

public class TryConditionMain {

    private static TryCondition tryCondition = new TryCondition();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i ++) {
            new PutThread("p" + i, i).start();
            new TakeThread("p" + i).start();
        }

    }

    static class PutThread extends Thread {

        private int num;

        public PutThread(String name, int num) {
            super(name);
            this.num = num;
        }

        @SneakyThrows
        public void run() {
            Thread.sleep(1000);
            tryCondition.put(num);
        }
    }

    static class TakeThread extends Thread {

        public TakeThread(String name) {
            super(name);
        }

        @SneakyThrows
        public void run() {
            Thread.sleep(1000);
            Integer num = (Integer) tryCondition.take();
        }
    }
}
