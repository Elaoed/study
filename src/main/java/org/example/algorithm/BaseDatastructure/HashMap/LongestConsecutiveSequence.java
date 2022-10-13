package org.example.algorithm.BaseDatastructure.HashMap;

import java.util.HashMap;

/**
 * 128. Longest Consecutive Sequence
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 * You must write an algorithm that runs in O(n) time.
 * 害，告诉我用哈希了，就比较简单，和那个two sum的性质有点像
 * 好好看题目，没有写不重复的数字就是可能重复而且，入参里面也给了，太不仔细了
 * 我也知道这里比较慢，发现一个可以优化的地方是，这种永远只会取两端点的case, 更新的时候只要更新两个端点就行了
 * 到底是num还是num的下标 还是要注意的
 */
public class LongestConsecutiveSequence {

    /**
     * 真的简洁了不止一个档次啊
     * @param nums
     * @return
     */
    public int newVersion(int[] nums) {

        int max = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.get(num) != null) {
                continue;
            }

            int left = map.getOrDefault(num - 1, 0);
            int right = map.getOrDefault(num + 1, 0);
            int consecutive = left + right + 1;
            max = Math.max(max, consecutive);
            // 更新两端点值，不然就会出现值有问题 **
            map.put(num, consecutive);
            map.put(num + right, consecutive);
            map.put(num - left, consecutive);
        }

        return max;

    }

    public static int longestConsecutive(int[] nums) {
        HashMap<Integer, Integer> cache = new HashMap<>();
        for (int num : nums) {
            if (cache.get(num) != null) {
                continue;
            }
            Integer higherValue = cache.getOrDefault(num + 1, 0); // 对应的是2
            Integer lowerValue = cache.getOrDefault(num - 1, 0);
            int value = 1 + higherValue + lowerValue;
            if (higherValue > 0) {
                int consecutive = higherValue;  // consecutive = 2 higherValue = 1, diff = 1, conse = 2, higher = 0, diff = 2
                cache.put(num + consecutive, value);
            }
            if (lowerValue > 0) {
                int consecutive = lowerValue;  // consecutive = 2 higherValue = 1, diff = 1, conse = 2, higher = 0, diff = 2
                cache.put(num - consecutive, value);
            }
            cache.put(num, value);
        }
        int max = 0;
        for (Integer value : cache.values()) {
            if (value > max)  {
                max = value;
            }
        }
        return max;

    }

    public static void main(String[] args) {

//        System.out.println(longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
//        System.out.println(longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));
        System.out.println(longestConsecutive(new int[]{1,2,0,1}));

    }

}
