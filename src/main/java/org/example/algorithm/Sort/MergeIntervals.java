package org.example.algorithm.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 56. Merge Intervals 合并区间 还算简单的一道题
 */
public class MergeIntervals {

    public static int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }

        // 感觉吧看起来很简单 只要排序排完了之后 前后两个数前数的second和后数的first比较 就有几种情况就行了
        // 他妈的排序可以用自带的排序来呀
        List<int[]> sortedList = Arrays.stream(intervals)
                .sorted(Comparator.comparingInt(k -> k[0]))
                .collect(Collectors.toList());
        List<int[]> intResult = new ArrayList<>();
        for (int[] ints : sortedList) {
            if (intResult.isEmpty() || intResult.get(intResult.size() - 1)[1] < ints[0]) {
                intResult.add(ints);
            } else {
                intResult.get(intResult.size() - 1)[1] = Math.max(intResult.get(intResult.size() - 1)[1], ints[1]);
            }
        }

//        int[] insert = null;
//        while (i < sortedList.size()) {
//            int[] curr = sortedList.get(i);
//            if (insert == null) {
//                insert = curr;
//            } else {
//                if (curr[0] > insert[1]) {
//                    intResult.add(insert);
//                    insert = curr;
//                } else {
//                    insert[1] = Math.max(insert[1], curr[1]);
//                }
//            }
//            i++;
//        }
//        intResult.add(insert);

//        int[][] ints = new int[intResult.size()][2];
//        for (int i1 = 0; i1 < intResult.size(); i1++) {
//            ints[i1] = intResult.get(i1);
//        }
//        return ints;

        return intResult.toArray(new int[intResult.size()][]);


    }

    public static void main(String[] args) {
        int[][] input = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
//        int[][] input = new int[][]{{1, 4}, {0, 4}};
        int[][] result = merge(input);
        for (int[] ints : result) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
