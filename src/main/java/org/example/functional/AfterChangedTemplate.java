package org.example.functional;

import java.util.function.Consumer;
import java.util.function.Supplier;

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

    public static void main(String[] args) {
        AfterChangedTemplate afterChangedTemplate = new AfterChangedTemplate();
        afterChangedTemplate.draw();
    }

}
