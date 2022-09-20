package org.example;

import org.example.study.User;

public class Test {

    public static void printBinary(int a) {
        for (int i = 31; i >= 0; i--) {
            System.out.print(a >> i & 1);
        }
    }

    public static void main(String[] args) {
        User user = new User("111", 12, true);
        final int hashCode = user.hashCode();
        System.out.println(hashCode);
        printBinary(hashCode);
        System.out.println();
//        System.out.println(hashCode ^ (hashCode >>> 16));
        // >>> 是位运算符 把高位的拉下来 和低位的进行异或 增加随机性
        printBinary(hashCode >>> 16);
        System.out.println();
        // -1 = 65535 * 65536 = 2 <<< 32
        int n = -1 >>> Integer.numberOfLeadingZeros(10 - 1);
        System.out.println("n: " + n);
        System.out.println(-1 >>> 16);
    }
}
