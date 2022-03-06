package org.example.algorithm.DynamicProgramming;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 542. 01 Matrix
 * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
 * The distance between two adjacent cells is 1.
 * 没有觉得是个很典型的例子
 * 第X直觉也是双重循环 第一次全部遍历 把所有的0都给set上 以他们为基准 第二次遍历 把0范围内不为0的都设成1 (上下左右对比) 应该是v + 1然后和那个位置目前的相比
 * 可以用广度优先，其实也就是把所有的0 每个都遍历一边 也不是不可以嗷
 * 对于 「Tree 的 BFS」 （典型的「单源 BFS」） 大家都已经轻车熟路了：
 * 首先把 root 节点入队，再一层一层无脑遍历就行了。
 * 对于 「图 的 BFS」 （「多源 BFS」） 做法其实也是一样滴～，与 「Tree 的 BFS」的区别注意以下两条就 ok 辣～
 * Tree 只有 1 个 root，而图可以有多个源点，所以首先需要把多个源点都入队；
 * Tree 是有向的因此不需要标识是否访问过，而对于无向图来说，必须得标志是否访问过哦！并且为了防止某个节点多次入队，需要在其入队之前就将其设置成已访问！【 看见很多人说自己的 BFS 超时了，坑就在这里哈哈哈
 * 一个礼拜没动算法之后，脑子里的东西可能也就渐渐成熟了
 * 现在看到这道题 第一个想的就是把所有的0都加到队列里面，挨个的做四周扩散，这就是图的扩散，要跟上面写的一样，需要标记每个节点是否访问过，但是访问过又如何本来就要重新访问重新计算，取最小的那个
 * // 成熟的bps
 * 动态规划和我之前想的感觉一样 大部分都一样
 * 总结还是有两种，dp是周围的点影响自己 着重是从左上方和右下方开始
 * bfs是自己影响周围的点，把他们加到队列里面去
 */

public class Matrix542 {

    public static int[][] bfsVersion(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return new int[][]{};
        }

        int m = mat.length;
        int n = mat[0].length;
        LinkedList<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = mat[i][j] == 0 ? 0 : -1;
                if (mat[i][j] == 0) {
                    queue.offer(new int[]{i, j});
//                    mat[i][j] = 0;
                } else {
                    mat[i][j] = 10000;
                }
                // important
            }
        }

        int[][] vectors = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

        while (!queue.isEmpty()) {
            // 图的bfs需要标识是否已经访问过，可以把是否访问过和比大小放在一起吗
            int[] point = queue.pollFirst();
            int x = point[0];
            int y = point[1];

            // 把周围四个点都加进去
            // 用vector的方式简化四个for循环
            for (int[] vector : vectors) {
                int newX = x + vector[0];
                int newY = y + vector[1];

                // 根据周围四个点进行判断 Math.min();
                // 判断周围的点是否越界
                if (newX >= 0 && newX < m && newY >= 0 && newY < n) {
                    if (mat[newX][newY] == 10000) {
                        queue.offer(new int[]{newX, newY});
                    }
                    // TODO: 为什么他这样也可以呢
                    // 我感觉这里有问题 是-1才往里面塞，是不是-1都要判断老点 + 1和自身的区别
                    mat[newX][newY] = Math.min(mat[newX][newY], mat[x][y] + 1);
                }
            }
        }

        return mat;
    }

    public static int[][] dpVersion(int[][] mat) {

        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return new int[][]{};
        }

        int m = mat.length;
        int n = mat[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // important
                dp[i][j] = mat[i][j] == 0 ? 0 : 10000;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 考虑边界
                if (i - 1 >= 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                }
                if (j - 1 >= 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
                }
            }
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i + 1 <= m - 1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + 1);
                }
                if (j + 1 <= n - 1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j + 1] + 1);
                }
            }
        }

        return dp;
    }


    public static int[][] updateMatrix(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return new int[][]{};
        }
        int[] co = new int[2];
        int[][] res = new int[mat.length][mat[0].length];
        for (int i = 0; i < res.length; i++) {
            for (int i1 = 0; i1 < res[0].length; i1++) {
                res[i][i1] = mat[i][i1] == 0 ? 0 : Integer.MAX_VALUE - 1;
                if (mat[i][i1] == 0) {
                    co[0] = i;
                    co[1] = i1;
                }
            }
        }

        // 不应该从最左边开始 0, 0 而是要找一个0点开始
        for (int i = co[0]; i < res.length; i++) {
            for (int i1 = co[1]; i1 < res[0].length; i1++) {
                // 比较改变四个邻居的大小 注意边界条件
                // TODO: 与其考虑自己对四个边界的影响 判断四种边界条件 不如从一边开始考虑别人对自己的影响
                if (i - 1 >= 0) {
                    res[i - 1][i1] = Math.min(res[i - 1][i1], res[i][i1] + 1);
                }
                if (i + 1 < res.length) {
                    res[i + 1][i1] = Math.min(res[i + 1][i1], res[i][i1] + 1);
                }
                if (i1 - 1 >= 0) {
                    res[i][i1 - 1] = Math.min(res[i][i1 - 1], res[i][i1] + 1);
                }
                if (i1 + 1 < res[0].length) {
                    res[i][i1 + 1] = Math.min(res[i][i1 + 1], res[i][i1] + 1);
                }
            }
        }

        for (int i = co[0]; i >= 0; i--) {
            for (int i1 = co[1]; i1 >= 0; i1--) {
                // 比较改变四个邻居的大小 注意边界条件
                if (i - 1 >= 0) {
                    res[i - 1][i1] = Math.min(res[i - 1][i1], res[i][i1] + 1);
                }
                if (i + 1 < res.length) {
                    res[i + 1][i1] = Math.min(res[i + 1][i1], res[i][i1] + 1);
                }
                if (i1 - 1 >= 0) {
                    res[i][i1 - 1] = Math.min(res[i][i1 - 1], res[i][i1] + 1);
                }
                if (i1 + 1 < res[0].length) {
                    res[i][i1 + 1] = Math.min(res[i][i1 + 1], res[i][i1] + 1);
                }
            }
        }

        return res;
    }

    // 崩了 完全崩了
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(bfsVersion(new int[][]{{1, 1, 0}, {1, 1, 0}, {1, 1, 1}})));
    }

}
