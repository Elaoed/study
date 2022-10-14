package org.example.algorithm.BinarySearch;

/**
 * 33. Search in Rotated Sorted Array // 81. II 多了一个数字会重复
 * There is an integer array nums sorted in ascending order (with distinct values).
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
 * You must write an algorithm with O(log n) runtime complexity.
 * 首先rotate在重新计算index的时候 一定是index - pivot + length这种形式
 * while 里面 <= 还是 < 的条件要分析清楚
 * 每当有 +1 -1的表达式的时候要记得判断边界条件
 * <p>
 * 我这个还是比较稳健... 更好的办法是找到pivot 判断target属于哪一边，然后只二分那一边就行了
 * 之前也是 明明是一边的内容 或者只要关心两边节点的内容，我很容易小题大做到要关心整一个列表
 * 二刷感言: 同样都是找到pivot 以前想的是把这个链表拼起来然后二分，现在可能是看了题解，找到对应的那个有序子数组二分就行了 = =
 * 看图就知道拿左端或者右端来判断 如果当前点小于左端 说明太右了，大于左端说明太左了
 * <p>
 * 这种题解就是我不喜欢 但是的确人家脑子比较清醒的。DBQ 不喜欢只是因为自己感觉被侮辱了 实际上现在我二刷用的也是这个
 * 因为嫌弃一刷的那种太麻烦了
 */
public class SearchInRotatedSortedArray {

    /**
     * 一步到位版本
     * 1. 判断哪侧区间是有序的，判断target在不在有序区间上
     */
    public static int oneStepFinish(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;
        int mid;
        // 因为会出现right - 1 < left的情况
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[left] == nums[mid]) {
                // 无法判断哪个区间是增序的 总是担心nums[mid] == nums[right]怎么办啊等等 二分法好像都不太管右边的(吃瓜
                ++left; // 反正移动之后还要判断的嘛
            } else if (nums[mid] <= nums[right]) {
                // 右侧区间是有序的
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (target < nums[mid] && target >= nums[left]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }

    public static int getPivot(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            // 这是正常的，left要向前推, nums unique, so no need care about ==
            // 每次left前进一半
            int middle = left + (right - left) / 2;
            if (middle > 0 && nums[middle - 1] > nums[middle]) {
                return middle;
            }
            if (middle < nums.length - 1 && nums[middle] > nums[middle + 1]) {
                return middle + 1;
            }
            if (nums[middle] < nums[right]) {
                right = middle;
                // 如果出现逆转, 回退
            } else if (nums[middle] > nums[right]) {
                left = middle;
            } else {
                // 最终相遇了还是没结果
                break;
            }
        }
        //
        return 0;
    }

    public static int rotateIndex(int total, int index, int pivot) {
        int newIndex = index + pivot;
        return newIndex >= total ? newIndex - total : newIndex;
    }

    // the index is a trick
    public static int binarySearch(int[] nums, int target, int pivot) {

        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (nums[rotateIndex(nums.length, middle, pivot)] > target) {
                right = middle - 1;
            } else if (nums[rotateIndex(nums.length, middle, pivot)] < target) {
                left = middle + 1;
            } else {
                return rotateIndex(nums.length, middle, pivot);
            }
        }
        return -1;

    }

    public static int search(int[] nums, int target) {
        // 1. find the pivot index, binary search
        // 2. rotate as if index >= pivot -> index - pivot else index + pivot
        // 3. binary search

        if (nums == null || nums.length < 1) {
            return -1;
        }

        int pivot = getPivot(nums);
        return binarySearch(nums, target, pivot);

    }

    public static void main(String[] args) {

//        System.out.println(search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
//        System.out.println(search(new int[]{0, 1, 2}, 0));
//        System.out.println(search(new int[]{2, 1, 0}, 0));
//        System.out.println(search(new int[]{1}, 1));
        System.out.println(search(new int[]{3, 5, 1}, 5));
//        System.out.println(rotateIndex(3, 1, 2));

    }

}
