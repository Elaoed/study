package org.example.algorithm.middle;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 审题 审题 兄弟
 * 后面出现的温度如果低于当前的则不管，如果高于当前的则进行日期差值相减
 * 原先我想的是有214的那种，还以为只会把2消掉 1就不知道了，实际上会把21全部消掉
 * 所以整个模型呈递减趋势(孤家寡人)
 * hint: 后面做题目的时候可以 简单的弄几个数字，搞个简单的模型，n=1 n=2的情况
 * 但是模型一定要弄对才行
 * 递减队列decreasing Queue不就是 这也是一个重要的概念/常用
 * 除了从前到后的动态规划，还可以试试从后到前的斐波那契呀，模型上面体现一下
 * 对于递减队列 消数据的时候一定是从右消到左
 */

public class DailyTemperatures {

    public static int[] version1(int[] nums) {
        int[] result = new int[nums.length];
        // value, index  なるほど 我是把要比较的元素在栈顶栈尾搞反了，所以才会出现有一堵墙的情况
        // 他妈的我根本没有弄反，中间肯定还有我没注意到的玄机
        // 玄机就是如果跟下标有关系，比如result或者差值，必须一定要放下标在里面
        LinkedList<int[]> decreasingQueue = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            if (decreasingQueue.isEmpty() || decreasingQueue.peekLast()[0] > nums[i]) {
                decreasingQueue.offerLast(new int[]{nums[i], i});
                continue;
            }
//            int days = 0;
            while (!decreasingQueue.isEmpty() && decreasingQueue.peekLast()[0] < nums[i]) {
//                days++;
                int[] ints = decreasingQueue.pollLast();
                result[ints[1]] = i - ints[1];
            }
            if (!decreasingQueue.isEmpty()) {
                // 留下的需要每个在个子的计数器上 + days 现在数据都有了就是不知道要怎么组装起来
                // 而且这个代码巨丑，一点也不符合算法的逻辑审美 so. get out!!!! 让我们去看看正确的解法
            }
            decreasingQueue.offerLast(new int[]{nums[i], i});
        }
        return result;

    }

    public static int[] version2(int[] nums) {
        int[] result = new int[nums.length];
        result[result.length - 1] = 0;
        for (int i = nums.length - 2; i >= 0; i--) {
            // 转一下，以后有这种特殊值 直接在前面赋值就行了
            if (i == nums.length - 1) {
                result[i] = 0;
            }
            if (nums[i] > nums[i + 1]) {
//                result[i] = result[i + 1] + 1;
                // 这里不对 前面那个是矮 但是可能矮子前面还有介于他两中间的 所以矮子的绝对定位失效了
                // 所以比较的是他前面那个更高的 还需要一个for
                for (int i1 = i + 2; i1 < nums.length; i1++) {
                    if (nums[i1] > nums[i]) {
                        result[i] = i1 - i;
                        break;
                    } else if (nums[i1] == nums[i]) {
                        result[i] = result[i1] + i1 - i;
                        break;
                    }
                    // 注意注意!!!!!! 一行写的地方
                    result[i] = 0;
                }
            } else if (nums[i] == nums[i + 1]){
                result[i] = result[i + 1] + 1;
            } else {
                result[i] = 1;
            }
            // 考虑怎么把自己的思维变得如此精简, 一点点的性能没关系的！ consider more!
            // j += result[j] 如果当前的j比目标值小的话只要找比目标值大的j就行了 soul!
//            for (int j = i + 1; j < nums.length; j += result[j]) {
//                if (nums[i] < nums[j]) {
//                    result[i] = j - i;
//                    break;
//             // break不能忘;
//                } else if (result[j] == 0) {
//                    result[i] = 0;
//                    break;
//                }
//            }
            // 思维方式不一样

        }
        return result;
    }

    /**
     * 维护一个递减栈，老哥 不要连别人的话都听不懂
     * 比如这个递减栈是什么场景下能用呢
     * @param nums
     * @return
     */
    public static int[] version3(int[] nums) {
//        int[] result = new int[nums.length];
//        Deque<Integer> stack = new LinkedList<>();
//        for (int i = 0; i < nums.length; i++) {
//            if (stack.pollLast())
//        }
        return null;
    }

    public static void main(String[] args) {
        int[] ints = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        ints = version1(ints);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }

}
