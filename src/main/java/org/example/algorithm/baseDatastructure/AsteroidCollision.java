package org.example.algorithm.baseDatastructure;

import java.util.Arrays;
import java.util.Stack;

/**
 * 735. Asteroid Collision
 * We are given an array asteroids of integers representing asteroids in a row.
 * For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
 */
public class AsteroidCollision {

    public static int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> s1 = new Stack<>();

        for (int asteroid : asteroids) {
            if (s1.isEmpty()) {
                s1.push(asteroid);
            } else {
                // 处理两颗行星collision, while停止条件两颗行星同方向或者stack为空
                boolean allCollision = false;
                while (!s1.isEmpty() && (asteroid < 0 && s1.peek() > 0)) {
                    Integer lastAsteroid = s1.pop();
                    if (lastAsteroid + asteroid == 0) {
                        allCollision = true;
                        break;
                    }

                    if (Math.abs(lastAsteroid) > Math.abs(asteroid)) {
                        asteroid = lastAsteroid;
                    }
                }
                if (!allCollision) {
                    s1.push(asteroid);
                }
            }
        }

        int[] res = new int[s1.size()];
        int i = s1.size() - 1;
        while (!s1.isEmpty()) {
           res[i] = s1.pop();
           i--;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(asteroidCollision(new int[]{5, 10, -5})));
        System.out.println(Arrays.toString(asteroidCollision(new int[]{8, -8})));
        System.out.println(Arrays.toString(asteroidCollision(new int[]{10, 2, -5})));
    }


}
