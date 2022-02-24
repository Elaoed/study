package org.example.functional;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class AfterChangedTemplate {

//    public String getNumber() {
//
//        System.out.println("getting number...");
//        return "number";
//
//    }

    public void judge() {
        System.out.println("Hey i'm doing the judge....");

    }

    public void execute(Consumer<String> handler, Supplier<String> getNumber) {

//        String number = getNumber();
        String number = getNumber.get();

        // 这里的定义是做什么用的？ 入参吗
        handler.accept(number);

        judge();

    }

    // 不需要为每一个业务实现一个方法
    public void draw() {
        // 这个a和number的区别又在哪里呢
        execute(number -> System.out.println("Draw number: " + number), () -> {
            String number = "00110";
            System.out.println("new number: " + number);
            return number;
        });
    }

    // shift + 左键 = 可以设置运行到的时候查看trace以及输出指定格式的字符串
    // 不同的地方shift + 左键效果不一样 或者是先左键再右键
    private static boolean isInterested(int i) {
        return i % 2 == 0;
    }

    public static void main(String[] args) {
//        AfterChangedTemplate afterChangedTemplate = new AfterChangedTemplate();
//        afterChangedTemplate.draw();
        // 在断点stream的时候用到
        Object[] res = Stream.of(1, 2, 3, 4, 5, 6, 7, 8).filter(i -> i % 2 == 0).filter(i -> i > 3).toArray();
        System.out.println(Arrays.toString(res));

        ThreadLocalRandom random = ThreadLocalRandom.current();
        int count = 0;
        for (int i = 0; i < 5; i++) {
            if (isInterested(random.nextInt(10))) {
                count++;
            }
        }
        System.out.printf("Found %d interested values%n", count);
    }

}
