package org.example.algorithm.TwoPointer;

/**
 * 125. Valid Palindrome
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.
 * Given a string s, return true if it is a palindrome, or false otherwise.
 * 原来回文或者字符串的处理可以使用Character的build-in function, 和stringBuffer
 *
 */
public class ValidPalindrome {

    public static boolean isPalindrome(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetterOrDigit(s.charAt(i))) {
                stringBuilder.append(Character.toLowerCase(s.charAt(i)));
            }
        }

        System.out.println(stringBuilder);
        // 这段代码还是要好好记住, 错了宝 right都一样，left可能要-1
        int right = stringBuilder.length() / 2;
        int left = stringBuilder.length() % 2 == 1 ? stringBuilder.length() / 2 : stringBuilder.length() / 2 - 1;
        for (; right < stringBuilder.length(); left--, right++) {
            if (stringBuilder.charAt(left) != stringBuilder.charAt(right)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(isPalindrome("race a car"));
    }
}
