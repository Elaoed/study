package org.example.algorithm.BinarySearch;

/**
 * 33. Search in Rotated Sorted Array
 * There is an integer array nums sorted in ascending order (with distinct values).
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
 * You must write an algorithm with O(log n) runtime complexity.
 * 给我整蒙了，回头再想
 * 还是属于没考虑清楚
 * // 首先rotate在重新计算index的时候 一定是index - pivot + length这种形式
 * // 二分法的本体贼简单
 * while 里面<= 还是<的条件要分析清楚
 * 每当有 +1 -1的表达式的时候要记得判断边界条件
 *
 * 我这个还是比较稳健... 更好的办法是找到pivot 判断target属于哪一边，然后只二分那一边就行了
 * 之前也是 明明是一边的内容 或者只要关心两边节点的内容，我很容易小题大做到要关心整一个列表
 *
 * 这种题解就是我不喜欢 但是的确人家脑子比较清醒的
 * class Solution {
 * public:
 *     int search(vector<int>& nums, int target) {
 *         int l=0,r=nums.size()-1,mid;
 *         while(l<r){//稍微改一下二分的算法就行，有手就行的题目
 *             mid=(l+r)>>1;
 *             if(nums[mid]==target)return mid;
 *             else if(nums[mid]<nums[r]){
 *                 if(nums[mid]<target&&nums[r]>=target){
 *                     l=mid+1;
 *                 }else{
 *                     r=mid;
 *                 }
 *             }else{
 *                 if(nums[mid]>target&&nums[l]<=target){
 *                     r=mid;
 *                 }else{
 *                     l=mid+1;
 *                 }
 *             }
 *         }
 *         return nums[l]==target?l:-1;
 *     }
 * };
 *
 */
public class SearchInRotatedSortedArray {

    public static int getPivot(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            // 这是正常的，left要向前推, nums unique, so no need care about ==
            // 每次left前进一半
            int middle = (left + right) / 2;
            if (middle > 0 && nums[middle - 1] > nums[middle]) {
                return middle;
            }
            if (middle < nums.length - 1 && nums[middle] > nums[middle + 1]) {
                return middle + 1;
            }
            if (nums[middle] < nums[right]) {
                right = middle;
                // 如果出现逆转, 回退
            } else if (nums[middle] > nums[right]){
                left = middle;
            } else {
                // 最终相遇了还是没结果
                break;
            }
        }
        //
        return 0;
    }

    // 3, 5, 1, 现在要取 1的位置 应该拿0
    // 1, 3, 5 -> 3, 5, 1 index 向右移动了2
    // 0 -> 2, 1 -> 0, 2 -> 1, index + pivot > length ? - length
    // Question: 现在传入的index是newIndex还是oldIndex 应该是newIndex才对呀 那不就要反着来 -pivot
    // 看来传入的是oldIndex.... 裂开了
    // 入参 total = 3, index = 1, pivot = 2 返回要0
    // 好奇怪啊 为什么啊 不管了先吃先喝
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
