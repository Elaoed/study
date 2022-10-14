package org.example.algorithm.BinarySearch;

/**
 * 二分法界的精髓
 * [0, 1] 是个很好的case
 * 二分法基本上只要关心左边的越界而不用关心右边的越界 这根平均数是向下取整有关系, 所以一般先if 右边条件 else 左边条件 就可以省去对左边条件的合法性校验?
 * 还需要注意nums=[2, 2], target=1的时候，right = mid - 1 = -1, left = mid + 1 == nums.length的情况
 * 求两数的平均值，为了防止溢出 avg = left + (right - left) / 2;
 * 是否需要区分 一定会有返回值？比如SqrtX就是一定会有返回值
 * right = middle的时候 right都是取不到的，如果类似Sqrt的向下取就需要 right - 1; 当然还有个前置条件是跳出循环left == right他两都大了
 * <p>
 * 寻找山峰的逻辑和普通二分查找的逻辑略有区别 162.Find Peak Element 1095. Find Mountain Array
 * peak可能也想用while里面 == return的方式 但是他实在是不好做
 */
public class SimpleBinarySearch {

    /**
     */
    public static int findPeak(int[] nums, int target) {

        int left = 1;
        // 因为要判断nums[mid + 1] 所以不能用nums.length 右边会越界
        // right永远取不到 如果是普通二分法 这里right = nums.length 因为普通二分法只有nums[mid]
        int right = nums.length - 1;

        while (left < right) {
            System.out.println("left: " + left + ", right: " + right);
            int mid = left + (right - left) / 2;
            // 当前mid可能是山峰 所以mid不用 - 1
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else { // 当前mid不可能是山峰 而且动只能让left动，只有right可以等于mid
                left = mid + 1;
            }
        }
        return right;
    }


    // 1 3 5
    // 1 3 5 7
    // 左开右闭区间 不知道是不是这种适用范围更广
    public static int binarySearch(int[] nums, int target) {
        // 一种是右边永远取不到系列，一种是取得到系列，取不到的容易判断
        // right和判断条件一定要一致，要么right都能取到，要么right都取不到
        // 当right = nums.length的时候 (left + right) / 2 可能
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 没有等于的时候不能一直不动 必须有 + 1
            if (nums[mid] > target) {
                right = mid; // 保证右边永远取不到
            } else {
                // 这里为 nums[mid] <= target
                // 到极限条件[0, 1] left = 0, right = 1 虽然要的是0但是因为上面的逻辑 会走到这里导致left多加了1
                left = mid + 1;
            }
        }
        // 右边永远取不到的情况下直接拿左边就好了
        return right; // 果然这里返回left - 1 和right - 1就没有区别了
        // 但是这种情况下 调用方就不知道到底取到没有 = = 需要调用方配合判断nums[res] == target

    }

    public static int binarySearch2(int[] nums, int target) {

        int left = 0, right = nums.length - 1; // left和right永远可达

        // 好奇怪 怎么又和之前的不一样了, 一样的一样的
        while (left <= right) {
            int middle = left + (right - left) / 2;
            // 总是爱先写nums[middle]
            if (target == nums[middle]) {
                return middle;
            }
            if (target < nums[middle]) {
                right = middle - 1;
            } else {
                // 不管怎么样 else总是要动的
                left = middle + 1;
            }

        }
        System.out.println("Before return -1, left: " + left + ", right: " + right);
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
        // 如果target不在列表里面 二分法最后指针(lastIndex)的位置会在大于target的第一个数上，大于最后一个数的指针 = nums.length + 1 针对于 left <= right的二分法模板

        System.out.println("=================> 入参单数个 done");
        System.out.println(binarySearch(new int[]{1, 3, 5, 7}, 1));  // 0
        System.out.println(binarySearch(new int[]{1, 3, 5, 7}, 3));  // 1
        System.out.println(binarySearch(new int[]{1, 3, 5, 7}, 5));  // 2
        System.out.println(binarySearch(new int[]{1, 3, 5, 7}, 7));  // 3
        System.out.println(binarySearch(new int[]{1, 3, 5, 7}, 0));  // 0
        System.out.println(binarySearch(new int[]{1, 3, 5, 7}, 2));  // 1
        System.out.println(binarySearch(new int[]{1, 3, 5, 7}, 4));  // 2
        System.out.println(binarySearch(new int[]{1, 3, 5, 7}, 6));  // 3
        System.out.println(binarySearch(new int[]{1, 3, 5, 7}, 8));  // 4
        System.out.println("=================> 入参偶数个 done");
    }
}
