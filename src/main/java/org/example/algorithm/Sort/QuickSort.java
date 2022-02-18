package org.example.algorithm.Sort;

/**
 * 终于要接触一下快排了
 * 使用的是分支 divide and conquer
 * 最核心的代码在于getIndex, 给定一个数字 default=list[0], 找到他在列表中的位置
 * 保证他左边的数值都小于他(可乱序), 保证他右边的数值都大于他(可乱序), 然后对左右列表再次重复快速排序
 * 流程1. tmp = arr[low]; 引入三方变量
 * 2. 从大到小找到比基准小的数字，放入arr[low]
 * 3. 从小到大找到比基准大的数字，放入arr[high]
 * 反复循环，最终他两相遇的p就是tmp基准数的位置
 * Question: 为什么快排这么快 跟冒泡和选择相比，时间和空间复杂度都小得多，前者是通过两次循环O(n^2)的复杂度来运行的，后者是O(nlogn) 怎么算的？
 * nlogn: n代表调用栈的高度，logn代表每层完成的时间，每层完成的时间都是。 n^2是一共有n个，要来n次，现在完美二分了之后，次数就少了，只要n的根号二次就行也就是log2
 * 计算复杂度的时候但凡从两层变成一层 * 2 都应该这么计算
 * 如果基准数很差，每次都是最大值或者最小值，每次都需要遍历一次才能把复杂度-1，所以需要O(n^2)，暗示有序列表
 * 如果基准数选的很好，每次都是中间值，遍历一边下来能交换很多数字的位置
 * 每次除以二除以二除以二 (假设完美二分) 那一个16长度的数组只要四次就行 所以2^n = N, n = log2N 这是层数 每一层完成的时间还是最大还是n 因为要循环n次
 * 我就说 之前一直记的都是O(logN) 但是不对啊 用二分法在有序数组里面查找一个数都要O(logN)的时间，排序怎么可能
 * 果然是我记错了 是NlogN 所谓栈的高度也不要被吓到了 其实就是递归的深度
 * 快排中递归的深度最好就是每次/2，一共是logN，最坏的时候每次左边的列表只有一个数，右边是剩下的就变成N了 再 * 有N个数 = N^2
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {49, 38, 65, 97, 23, 22, 76, 1, 5, 8, 0, 2, -1, 22};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("排序后: ");
        for (int i : arr) {
            System.out.println(i);
        }
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (high <= low) {
            return;
        }
        // 找寻基准数据的正确索引
        int index = getIndex(arr, low, high);
        // 根据索引把列表分成左右两列, 分而治之
        // 保证右边的一定都比基准大，左边的都比基准小
        quickSort(arr, low, index - 1);
        quickSort(arr, index + 1, high);
    }

    public static int getIndex(int[] arr, int low, int high) {
        int tmp = arr[low];
        while (low < high) {
            while (low < high && arr[high] >= tmp) {
                high--;
            }
            arr[low] = arr[high];
            while (low < high && arr[low] <= tmp) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = tmp;
        return low;
    }
}
