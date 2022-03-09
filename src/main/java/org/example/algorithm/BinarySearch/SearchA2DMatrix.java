package org.example.algorithm.BinarySearch;

/**
 * 74. Search a 2D Matrix ***** Important -> 这题我找到了二分法的中间控制条件变量的一个分析的方法
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 * Good Idea: 正解是把二维数组看成一个一维数组，和rotate一样，就好做了
 * // 二分法的套路 while(left < right)
 * getMiddle, if (target = nums[middle]) return 靠这个找到二分法的数，如果不存在，只能靠left < right打破循环，并且这时候left = right = right, 满足right > target
 * if target < nums[middle]: right = middle
 * else: !!!!!!!!!!!!! left = middle + 1
 *
 * left <= right 千万不能和 left = middle + 1 一起使用, 原先靠着left + 1 == right去打破循环，现在left <= right无法打破循环
 * 那什么时候用left <= right呢？ 当最后left == right的时候如果额外加一个判断 就可以了，但是和现在没区别的，这个判断只是加在外面了
 *
 */
public class SearchA2DMatrix {

    public static int[] mappingIndex(int middle, int column) {
        return new int[]{middle / column, middle % column};
    }

    // 优美 开始总结了
    public static boolean searchMatrix2(int[][] matrix, int target) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int left = 0, right = matrix.length * matrix[0].length - 1;
        while (left < right) {
            int middle = left + (right - left) / 2;
            final int[] coordinate = mappingIndex(middle, matrix[0].length);
            int val = matrix[coordinate[0]][coordinate[1]];
            if (target == val) {
                return true;
            }
            if (target < val){
                right = middle;
            } else {
                left = middle + 1;
            }
        }

        final int[] coordinate = mappingIndex(left, matrix[0].length);
        return matrix[coordinate[0]][coordinate[1]] == target;

    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        if (target < matrix[0][0] || target > matrix[matrix.length - 1][matrix[0].length - 1]) {
            return false;
        }

        // binarySearch row[0]
        int left = 0, right = matrix.length - 1;
        int middle = -1;
        // 左区间和右区间不一样的地方在于 /2的时候左取件永远是可以取到的，但是右区间取不到 对，这就衍生了后面是否要+1的不同
        // 这里又会遇到一个问题 如果target在最后一行, 用现在的写法相当于直接把他的权利给剥夺了 我重新整过把
        // left == right的时候
        while (left <= right) {
            middle = left + (right - left) / 2;
            int middleRowFirst = matrix[middle][0];
            if (middleRowFirst == target) {
                return true;
            }
            // 我之前总是加太多的条件了，其实在循环框架里面都是可以处理了的(大部分
            // 首先遇到了什么问题 right = left + 1, middle = left, 判断一个条件之后 left = middle 这种写法是绝对错误的
            // left = middle + 1是不是也有问题呢, 原以为会丢掉left, 实际上不用担心 left == right 之后就跳出循环了
            if (target < middleRowFirst) {
                right = middle;
            } else { // target > middleRowFirst 不能武断的说left = middle + 1, 应该进行更深层次的判断 可以武断的left = middle + 1, 加了再说, break 之后middle 还是left
//                if (target < matrix[middle + 1][0]) {
//                    break;
//                }
                left = middle + 1;
                // where 条件就跟这个条件冲突了 正常是 left < right, left = middle + 1; 如何在他两之间找到一个平衡
            }
        }

        int row = middle;

        // middle的历史地位, 什么鬼 我在说什么话啊, 有点被困魔压身了
        // 我回来了宝
        left = 0;
        right = matrix[0].length - 1;
        while (left < right) {
            middle = left + (right - left) / 2;
            if (matrix[row][middle] == target) {
                return true;
            }
            // 可以middle + 1的时候当然middle也行？？ 吗 不是的 如果while条件是left <= right的时候 必须是middle +/- 也不是
            // 又跟left和right有关了 如果left, right = middle 而不加任何数
            // 可能会存在 right = left + 1 并且 target在nums[left], nums[right]中间, 就会死循环, 这时候不能赋值给right = middle - 1 好像可以哎 还是要看具体的情况
//            if (target < matrix[row][middle]){
//                right = middle; // 是不是可能那个数在middle - 1, middle中间 会不会影响？ 当然会
//
//                // 因为middle会==left, 所以让其他条件right=middle就行了, 所以是不是right=middle要写在else里面
//            } else {
//                // 因为条件的关系 要找的数在middle和middle + 1中间
//                if (target < matrix[row][middle + 1]) {
//                    return false;
//                }
//                left = middle;
//                // 救命啊 left = 0, right = 1, middle = 0, target > nums[middle] && target == nums[middle + 1]
//            }

            // 关键点来了, left = middle + 1
            if (target > matrix[row][middle]) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        // 什么情况下left == right?

        // 当left == right的最后一个数的时候
        return matrix[row][left] == target;
        // 二分法找到一个数和二分法找不到一个数的区别

    }

    public static void main(String[] args) {
        System.out.println(searchMatrix2(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 3));
//        System.out.println(searchMatrix(new int[][]{{1}, {3}}, 3));
    }

}
