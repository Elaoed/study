package org.example.algorithm.baseDatastructure;

import java.util.Arrays;

/**
 * 299. Bulls and Cows
 * You are playing the Bulls and Cows game with your friend.
 * You write down a secret number and ask your friend to guess what the number is. When your friend makes a guess, you provide a hint with the following info:
 * The number of "bulls", which are digits in the guess that are in the correct position.
 * The number of "cows", which are digits in the guess that are in your secret number but are located in the wrong position. Specifically, the non-bull digits in the guess that could be rearranged such that they become bulls.
 * Given the secret number secret and your friend's guess guess, return the hint for your friend's guess.
 * The hint should be formatted as "xAyB", where x is the number of bulls and y is the number of cows. Note that both secret and guess may contain duplicate digits.
 * 那啥 bulls的简单 两个放在一起一个指针，一样的就是bull
 * 剩下就是cows
 * 但是这里也没说清楚，给出的数字都不正确的应该是什么，cows还是什么都不算✅ -> 仔细审题
 * version1: succeed
 * version2: 因为是数字的比较，而且是取的里面小的那个，可以用数组，两个数组的方式，存储下标为0-9的数组出现的个数，把所有的值相加之后减去bulls = cows
 * 真牛 我想不到，现在要想想怎么才能想到、正确审题
 *
 * 如果以后内容只涉及到数字的对比，可以考虑用这种方案
 * 一样的条件排除，是不是真得第一次就排除，可以用减的方式把这部分减去吗？ 要考虑
 * 应该是针对于要成对消除的那种，可以不用消除，用累计相减法
 */
public class BullsAndCows {

    public static String getHint2(String secret, String guess) {
        int[] a = new int[10], b = new int[10];
        int bulls = 0, cows = 0;
        Arrays.fill(a, 0);
        Arrays.fill(b, 0);
        // we only concern about secret in guess, so no need to care about guess
        for (int i = 0; i < secret.length(); i++) {
            a[secret.charAt(i) - '0']++;
            b[guess.charAt(i) - '0']++;
            if (secret.charAt(i) == guess.charAt(i)) {
                bulls++;
            }
        }
        for (int i = 0; i < 10; i++) {
            cows += Math.min(a[i], b[i]);
        }

        return new StringBuilder().append(bulls).append('A').append(cows - bulls).append('B').toString();

    }

    public static String getHint(String secret, String guess) {
        // 不管怎么样先想一种方案
        StringBuilder newSecret = new StringBuilder(secret);
        StringBuilder newGuess = new StringBuilder(guess);

        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < Math.min(newSecret.length(), newGuess.length()); i++) {
            if (newSecret.charAt(i) == newGuess.charAt(i)) {
                bulls++;
                newSecret.delete(i, i + 1);
                newGuess.delete(i, i + 1);
                i--;
            }
        }
        for (int i = 0; i < Math.min(newSecret.length(), newGuess.length()); i++) {
            int charInGuessPos = newGuess.indexOf(String.valueOf(newSecret.charAt(i)));
            if (charInGuessPos > -1) {
                cows++;
                newSecret.delete(i, i + 1);
                newGuess.delete(charInGuessPos, charInGuessPos + 1);
                i--;
            }
        }


//        return bulls + "A" + cows + "B";
        // 上下两种写法差5ms 牛啊
        return new StringBuilder().append(bulls).append('A').append(cows).append('B').toString();
    }

    public static void main(String[] args) {

        System.out.println(getHint2("1807", "7810"));
    }
}
