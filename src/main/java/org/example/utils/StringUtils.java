package org.example.utils;

import java.util.Arrays;
import java.util.Comparator;

public class StringUtils {

    public static void main(String[] args) {

        String s = "A|B|C|A|B|C";
        // [)
        System.out.println(s.substring(0, 2));
//        HashMap<Character, Integer> map = new HashMap<>();
//        System.out.println(map);
//        System.out.println(Arrays.toString(s.split("\\|")));
        System.out.println(Integer.MAX_VALUE);

        Arrays.sort(new int[][]{{0, 1}, {1, 2}}, Comparator.comparingInt(o -> o[0]));

    }
}