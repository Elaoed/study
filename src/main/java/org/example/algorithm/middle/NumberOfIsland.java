package org.example.algorithm.middle;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 这道题的解法 想法就跟我不谋而合
 * 先找到那个点，以他为原点开始向外延展，原先我走不下去的原因是 下右右，只考虑了右下 不知道怎么上和左
 * 有一个父类节点，确定了父类之后去找他的子类，找完了再回到父类
 * <p>
 * 看了这个代码之后其实也挺清楚的，主要是结束的条件要好好考虑
 * 剩下就是上下左右
 * 深度和广度可以是一个系列的题
 * 深度优先：发现一条路一直走到终点，再返回到最深的有分支的点再进行其他分支的搜索，直到搜索完毕。
 * 广度优先：从开始节点a找与它相邻的节点全部节点，假设有b,c,d,e，然后再找b所有未遍历的相邻的节点，然后再找c的，依次进行下去
 */
public class NumberOfIsland {

    static void dfs(char[][] grid, int r, int c) {
        int nr = grid.length;
        int nc = grid[0].length;

        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
            return;
        }

        grid[r][c] = '0';
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    public static int dfsVersion(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    ++num_islands;
                    dfs(grid, r, c);
                }
            }
        }

        return num_islands;
    }

    public static int bfsVersion(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;

        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    ++num_islands;
                    grid[r][c] = '0';
                    Queue<int[]> neighbors = new LinkedList<>();
                    neighbors.add(new int[]{r, c});
                    while (!neighbors.isEmpty()) {
                        int[] coordinate = neighbors.remove();
                        int row = coordinate[0];
                        int col = coordinate[1];
                        if (row - 1 >= 0 && grid[row - 1][col] == '1') {
                            neighbors.add(new int[]{(row - 1), col});
                            grid[row - 1][col] = '0';
                        }
                        if (row + 1 < nr && grid[row + 1][col] == '1') {
                            neighbors.add(new int[]{(row + 1), col});
                            grid[row + 1][col] = '0';
                        }
                        if (col - 1 >= 0 && grid[row][col - 1] == '1') {
                            neighbors.add(new int[]{row, col - 1});
                            grid[row][col - 1] = '0';
                        }
                        if (col + 1 < nc && grid[row][col + 1] == '1') {
                            neighbors.add(new int[]{row, col + 1});
                            grid[row][col + 1] = '0';
                        }
                    }
                }
            }
        }

        return num_islands;
    }

    public static void main(String[] args) {
        char[][] grid = new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '0', '1', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        System.out.println(bfsVersion(grid));
    }
}
