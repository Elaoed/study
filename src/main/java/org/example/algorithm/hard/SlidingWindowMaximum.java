package org.example.algorithm.hard;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * 剑指 Offer 59 - I. 滑动窗口的最大值
 * 仔细读题 滑动窗口中的最大值
 * 双端队列 双端队列的递减性，维持整个队列的顺序，只比较新来的元素是否比原来的老大更要大
 * 优先队列(堆) 要和以前看的串起来了 有那么一丢丢小感动
 * 这玩意儿可急不得啊, 还得一点点把数据结构弄清楚了，不然后面只能看答案了宝
 * 优先队列在这种滑动的取最大最小值的地方仿佛特别好用
 * 之所以是hard，因为要对数据结构非常了解
 * <p>
 * 思路很重要: 解题思路二，我们需要拿到最大值，最好容器是有序的，在线性时间范围内 or 在算法里面必须是要有序的
 * 其次 本就是一个滑动窗口 每次只会进来1 出去1
 * 判断比较出去的1是不是最大的，如果不是最大的，只要比较最大和新进员工就行了
 * 如果是最大的，需要比较二把手和新进员工 (难点在这里，难点也就是突破点)，因为这点所以容器必须是有序的，而不是只要最大就行，一开始我看到了还以为只要最大的
 * 有序又不一定要全有序，其实只要头部两个节点就行，又因为分析发现左侧的老二是没有的(大王子在右边，死的总比大王子早)，只有右侧的老二有用，熬过大王子自己就出头了
 * 所以只需要一个递减序列就行
 */
public class SlidingWindowMaximum {

    public static int[] maxSlidingWindow(int[] nums, int k) {

        if (k == 0 || nums.length == 0) {
            return new int[]{0};
        }

        if (nums.length < k) {
            k = nums.length;
        }

        // 1. 初始化
        int[] result = new int[nums.length - k + 1];
//        int max = nums[0];

        Deque<Integer> diaryQueen = new LinkedList<>();
        // 2. 先从第一个滑动窗口中获取最大值
        for (int i = 0; i < k; i++) {
            if (diaryQueen.getFirst() != null && diaryQueen.getFirst() < nums[i]) {
                diaryQueen.clear();
            }
            diaryQueen.addLast(nums[i]);
            // 吃的时候是没关系，但是如果后面加进来的是中间的，要把小的全部都吃掉 TODO:
//            diaryQueen.addFirst();
//            max = Math.max(max, nums[i]);
        }
//        int r = 0;
//        result[r] = max;

        // 后续窗口滑动的时候直接拿原来的最大值和新的比较就行了, 不行吗？
//        for (int i = k; i < nums.length; i++) {
        // 消失的节点 i - k + 1, 出现的节点 k
        // 可重复，尴尬
        // 这样下去只能排序
//            if (nums[i - k + 1]) {
//
//            }
        // sorry 现在我不能给出一个在线性的时间内解决的方案
//        }
        for (int i = k; i < nums.length; i++) {
            // 消失的节点 i - k + 1, 出现的节点 k 先判断消失的节点，如果没了就取老二，如果空了就取最新值
            // 在判断新加进来的节点和头相比，要吃掉哪些小的

        }

        return result;

    }

    public static int[] maxSlidingWindow2(int[] nums, int k) {

        if (k == 0 || nums.length == 0) {
            return new int[]{0};
        }

        if (nums.length < k) {
            k = nums.length;
        }

        // 为了方便判断顶对元素滑动窗口的关系，我们往队列里面放的是二元组(value, index)
        // 高啊，二元组也可以用int[]来标识 这是第一点，如果不相等则比较数值大小，如果相等则比较下标大小，太妙了
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] != o2[0] ? o2[0] - o1[0] : o2[1] - o1[1]);
        for (int i = 0; i < k; i++) {
            pq.offer(new int[]{nums[i], i});
        }

        // 初始化result数组
        int[] result = new int[nums.length - k + 1];
        // 既然上面是特殊处理先塞进去的，那返回值的第一个也要特殊处理一下
        result[0] = pq.peek()[0];

        // 这里已经开始动起来了
        for (int i = k; i < nums.length; i++) {
            // 如果最顶端的元素是最左边的从0开始
//            if (pq.peek()[1] == i - k) {
//                pq.poll(); // 干掉最顶端那个
//            } else {
//                pq.offer(new int[]{nums[i], i});
//                // 1. 这里if里面没有塞东西
//                // 2. 这里else里面没有删除东西
//            }
//            result[i - k] = pq.peek()[0];

            // 官解 官解赛高
            // 不管怎么判断都有两步 1. 每一步都要塞东西 2. 每一步都要移除东西, 只是要移除的东西要怎么找，让我们看看官解
            pq.offer(new int[]{nums[i], i});
//            pq.poll() 怎么poll呢 把比当前滑动框左侧小的都移调, 这次不移下次也就移调了，通过这种方式
            // 这应该是滑动窗口的统一移动方式吧  connection with sliding windows with priority queue
            while (pq.peek()[1] <= i - k) {
                pq.poll();
            }
            result[i - k + 1] = pq.peek()[0];

        }
        return result;

    }

    /**
     * これは　これは 单调队列
     * 空间复杂度 O(n-k吧) 时间复杂度O(n)
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow3(int[] nums, int k) {

        if (k == 0 || nums.length == 0) {
            return new int[]{0};
        }

        if (nums.length < k) {
            k = nums.length;
        }

        // 为了方便判断顶对元素滑动窗口的关系，我们往队列里面放的是二元组(value, index)
        // First到Last是从大到小;

        LinkedList<Integer> pq = new LinkedList<>();
        // 初始化result数组

        // 这里需要用到的是最小堆把？
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            // 持续比较并且删掉最小值,删光光
            while (!pq.isEmpty() && pq.peekLast() <= nums[i]) {
                pq.pollLast();
            }
            // 把值添加进去, 没删掉的都是大哥，所以只能灰溜溜的从后门进
            pq.addLast(nums[i]);
            if (i >= k - 1) {
                result[i - k + 1] = pq.peek();
                // 开始计算滑动窗口
                // 那我这个怎么取哦哥哥 删除要删除最小的，取要取最大的，那就不是优先队列而是双向队列了，用错容器了哥哥
                // 获取最大值
            }
        }
        return result;

    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
//        System.out.println(maxSlidingWindow(nums, k));
        final int[] ints = maxSlidingWindow3(nums, k);
        for (int num : ints) {
            System.out.println(num);
        }
    }

}
