package org.example.algorithm.baseDatastructure.HashMap;

import java.util.HashMap;
import java.util.Random;

/**
 * 380. Insert Delete GetRandom O(1)
 * // 还是建议一点点想，最后再把条件整合。考虑边界
 */
public class RandomizedSet {

    // 这题主要的问题就是getRrandom 时间是O(1) 其他情况没法满足，正常就会想到用random(len)从中取一个，然后从列表中返回
    // 但是问题来了，列表中的数据如何保证连续有效，通过把random值pop出去之后，由最后一个位置的数据覆盖这个位置
    // 通过index指针要比size更可靠
    private int[] nums = new int[200000];
    // 很多操作需要反向 通过set里面的数据去删掉nums
    private HashMap<Integer, Integer> map = new HashMap<>();
    // 指针永远指向队尾下一个空位置
    private int index = 0;
    private Random random = new Random();

    public RandomizedSet() {

    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        } else {
            map.put(val, index);
            nums[index++] = val;
            return true;
        }

    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        } else {
            // 这里就适合考虑边界条件
            int pos = map.get(val);
            map.remove(val);

            index--; // 现在index指向最后一个数
            if (pos != index) {
                nums[pos] = nums[index];
                map.put(nums[pos], pos);
            }
            return true;
        }

    }

    public int getRandom() {
        return nums[random.nextInt(index)];
    }
}
