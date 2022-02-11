package org.example.algorithm.BinarySearch;

/**
 * 二分法界的精髓
 */
public class SimpleBinarySearch {

    public static int lastIndexOf(int[] nums, int target) {
        return -1;
    }

    public static int binarySearch(int[] nums, int target) {

        int left = 0, right = nums.length; // right 永远是不可达的
        // 好奇怪 怎么又和之前的不一样了, 一样的一样的
        while (left < right) {
            int middle = left + (right - left) / 2;
            // 总是爱先写nums[middle]
            if (target == nums[middle]) {
                return middle;
            }

            if (target < nums[middle]) {
                right = middle;
            } else {
                left = middle + 1;
            }

        }
        System.out.println("target not found: last index = " + left + ", target = " + target);
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(binarySearch(new int[]{1, 3, 5}, 1));  // 0
        System.out.println(binarySearch(new int[]{1, 3, 5}, 3));  // 1
        System.out.println(binarySearch(new int[]{1, 3, 5}, 5));  // 2
        System.out.println(binarySearch(new int[]{1, 3, 5}, 0));  // 0
        System.out.println(binarySearch(new int[]{1, 3, 5}, 2));  // 1
        System.out.println(binarySearch(new int[]{1, 3, 5}, 4));  // 2
        System.out.println(binarySearch(new int[]{1, 3, 5}, 6));  // 3
        // 如果target不在列表里面 二分法最后指针(lastIndex)的位置会在大于target的第一个数上，大于最后一个数的指针 = nums.length + 1
        // 反思 为什么之前我的right都 = nums.length - 1, 因为后面也做了相应的调整，所以搞不灵清
        // 最好最后的left要判断一下 有没有超过nums.length - 1
    }
}
