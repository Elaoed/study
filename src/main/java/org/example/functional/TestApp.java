package org.example.functional;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TestApp {

    Runnable r1 = () -> System.out.println(this);
    Runnable r2 = () -> System.out.println(toString());
    Runnable r3 = new Runnable() {
        @Override
        public void run() {
            System.out.println(this);
        }
    };

    public static void main(String[] args) throws Exception {

        // 在接口类的上方加函数式变编程的注解
        // () -> "done" 是一个匿名函数 可以理解为一个类的实例里面调用了这个匿名函数
        // 编译器负责推倒lambda类型的表达式
        // 一个函数式编程的接口类里面只能有一个函数
        // 如果不加@FunctionalInterface也没关系 但是如果定义了两个函数 用的时候会报错
        // 函数式编程: 接受一个匿名函数作为一个变量 所以这个变量只是一个函数？ 不是的
        // 这个变量是包有这个函数的一个类的实例 这个类需要加@FunctionInterface 当这个变量执行的时候就会执行刚刚写的匿名函数 从而得到结果
        // 函数式编程里面的this就指的是外面的class 如果是用new 覆盖的方法的话this就不知道指的是哪个了 实际上指的是匿名类而不是调用匿名类的东西
        Callable<String> c = () -> "done";
        String call = c.call();
        System.out.println(call);
        Runnable b = () -> System.out.println("1");
        ActionListener d = () -> System.out.println(c);

        new TestApp().r1.run();
        new TestApp().r2.run();
        new TestApp().r3.run();

        // effective final: 一个变量初始化之后就没更改过 也可被lambda引用
        // 不支持在内部对外部变量修改
        // lambda expressions close over values, not variables
        Integer i = 1;
//        i = 2;
        Arrays.asList(1, 2, 3).forEach(k -> {
//            i += 1;
            System.out.println(i);
        });

        List<People> peopleList = Arrays.asList(new People("1"), new People("2"), new People("3"));
        Collections.sort(peopleList, new Comparator<People>() {
            @Override
            public int compare(People o1, People o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        Collections.sort(peopleList, (People x, People y) -> {return x.getName().compareTo(y.getName());});
        Collections.sort(peopleList, (x, y) -> x.getName().compareTo(y.getName()));
        Collections.sort(peopleList, Comparator.comparing(People::getName));
        peopleList.sort(Comparator.comparing(People::getName).reversed());
        // internal && external iteration: means for and foreach
        // internal iteration makes optimization possible 允许类库执行各种优化
        List<People> nonNullList = peopleList.stream().filter(people -> {
            return people != null;
        }).collect(Collectors.toList());
        // Q1 filter的动作在哪里做的
        // Supplier Consumer Bi就是两个参数

    }

    @Override
    public String toString() {
        return "hello this is TestApp toString >>>>>>>>>> welcome";
    }

    @Data
    @AllArgsConstructor
    public static class People {
        private String name;
    }
}
