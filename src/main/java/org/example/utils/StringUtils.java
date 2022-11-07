package org.example.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class StringUtils {


    public static StringBuilder reverseString(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        return sb;
    }

    public static void main(String[] args) {

//        String s = "A|B|C|A|B|C";
        // [)
//        System.out.println(s.substring(0, 2));
//        HashMap<Character, Integer> map = new HashMap<>();
//        System.out.println(map);
//        System.out.println(Arrays.toString(s.split("\\|")));
//        System.out.println(Integer.MAX_VALUE);
//        StringBuilder sb = new StringBuilder();
//        sb.append("cb");
//
//        StringBuilder sb2 = new StringBuilder();
//        sb2.append(sb);
//        sb2.append(sb);
//        System.out.println(sb2);
//        String[] s1 = "dog cat cat dog".split(" ");
//        System.out.println(Arrays.toString(s1));
//        System.out.println(s1[1]);
//        s = s.replace("|", " ");
//        s = s.replace("A", "dog");
//        System.out.println(s);
//        List<String> col = new ArrayList<>();
//        String c = String.join(".", col);
//        col.remove()
        Map<Integer, Integer> map = new HashMap<>();
        String s = "Greetings  big world";
        // "dlrow gib sgniteerG"

//        String[] split = s.split(" ");
//        System.out.println(Arrays.toString(split));
//        System.out.println(Arrays.toString(s.split()));
//        System.out.println(String.join(" ", s.split(" ")));
        // 第几个位置 有几个空格

        StringBuilder sb = new StringBuilder();
        String[] split = s.split(" ");
        System.out.println(Arrays.toString(split));
        for (int i = split.length - 1; i >= 0; i--) {
            if (split[i].isEmpty()) {
                sb.append(" ");
            } else {
                sb.append(split[i]).append(" ");
            }
        }
//        return sb.toString();
//        System.out.println(sb.deleteCharAt(sb.length() - 1));

//        List<Integer> ints = new Arrays.asList();
//        HashMap<Integer, Integer> m = new HashMap<>();
//        for (Integer i: ints) {
//            //
//            m.put(i, m.getOrDefault(i, 0) + 1);
//        }
//        int ss = 0;
//        for (int i = ints.size() - 1; i >= 0; i++) {
//            if (m.get(ints.get(i)) > 1) {
//                ints.remove(i);
//                m.put(i, m.get(i) - 1);
//            }
//        }
        System.out.println(System.currentTimeMillis());



//        Arrays.sort(new int[][]{{0, 1}, {1, 2}}, Comparator.comparingInt(o -> o[0]));

    }
}
