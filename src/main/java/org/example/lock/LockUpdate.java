package org.example.lock;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.PropertyConfigurator;
import org.openjdk.jol.info.ClassLayout;

@Slf4j
public class LockUpdate {

    public static void main(String[] args) {

        PropertyConfigurator.configure("src/main/resources/log4j2.xml");
        log.info("111");
        Object obj = new Object();
        Object array = new int[]{};
        System.out.println("未进入同步块的对象，MarkWord 为：");
        System.out.println("未进入同步块的对象，MarkWord 为：");
        System.out.println(ClassLayout.parseInstance(array).toPrintable());
        synchronized (array) {
            System.out.println("进入同步块，MarkWord 为：");
            System.out.println(ClassLayout.parseInstance(array).toPrintable());
        }

    }




}
