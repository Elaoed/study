package org.example.algorithm.baseDatastructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 973. K Closest Points to Origin
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).
 * The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
 * You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).
 * 计算是真的很简单,x^2 + y^2比大小，但是问题是怎么存储这些值并且进行排序
 * 我这的确是一种方案，另外一种最快的就是直接对points进行排序
 * 然后看看大根堆，这个heap怎么整
 * 这种本质就是一个排序，根据一个值
 *
 *
 */
public class KClosestPoints {

    public static int[][] kClosest2(int[][] points, int k) {
        Arrays.sort(points, (p1, p2) -> Math.abs(p1[0]) * Math.abs(p1[0]) + Math.abs(p1[1]) * Math.abs(p1[1]) - Math.abs(p2[0]) * Math.abs(p2[0]) - Math.abs(p2[1]) * Math.abs(p2[1]));
        return Arrays.copyOfRange(points, 0, k);
    }

    // 另外就是手写一个小顶堆 大小为k, 判断如果插入之后数量大于k 则要移除一个最小项目, 移除的时间是O(1)，但是插入需要上浮

  public static int[][] kClosest(int[][] points, int k) {
        // 现在有两个元素 int[] 的坐标，和x^2 + y^2的距离，是根据距离进行排序
        PriorityQueue<Integer> pQueue = new PriorityQueue<>(points.length);
        Map<Integer, List<int[]>> cache = new HashMap<>();
        for (int[] point : points) {
            int key = Math.abs(point[0]) * Math.abs(point[0]) + Math.abs(point[1]) * Math.abs(point[1]);
            if(!cache.containsKey(key)) {
                cache.put(key, new ArrayList<>());
            }
            cache.get(key).add(point);
            if (!pQueue.contains(key)) {
                pQueue.add(key);
            }
        }

        int[][] res = new int[k][2];
        while (k > 0) {
            Integer key = pQueue.poll();
            for (int[] ints : cache.get(key)) {
                res[k - 1] = ints;
                k--;
            }
        }

        return res;

    }

    public static void main(String[] args) {

        int[][] params = new int[][]{{3, 3}, {5, -1}, {-2, 4}};
        int k = 2;
//        int[][] params = new int[][]{{1, 3}, {-2, 2}};
//        int k = 1;
        for (int[] ints : kClosest(params, k)) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
