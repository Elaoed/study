package org.example.algorithm.baseDatastructure;

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
 * 但是这里也没说清楚，给出的数字都不正确的应该是什么，cows还是什么都不算
 */
public class BullsAndCows {

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


        return bulls + "A" + cows + "B";
    }

    public static void main(String[] args) {

        System.out.println(getHint("1807", "7810"));
    }
}
