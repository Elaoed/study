package org.example.algorithm.baseDatastructure.Stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * 735. Asteroid Collision
 * We are given an array asteroids of integers representing asteroids in a row.
 * For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
 * 笑死 还有个更简单的方案 一边循环就行了 for 0 - size - 2, 判断i和i+1的相撞程度, 剩下的都赋值成0 最后把0的都干掉, 因为只有都撞完了才知道 剩下有几个
 */
public class AsteroidCollision {

    public static int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        for (int asteroid : asteroids) {
            if (stack.isEmpty() || asteroid > 0) {
                // stack为空或者新陨石向右
                stack.push(asteroid);
            } else {
                // stack不为空 并且两个陨石方向相反
                // 判断两个数方向相同应该还可以用位运算
                boolean isDestroyed = false;
                while (!stack.isEmpty() && asteroid < 0) {
                    if (stack.peek() < 0) {
                        break;
                    } else if (stack.peek() == -asteroid) {
                        stack.pop();
                        isDestroyed = true;
                        break;
                    } else if (stack.peek() > -asteroid) {
                        isDestroyed = true;
                        break;
                    } else {
                        stack.pop();
                    }
                }
                if (!isDestroyed) {
                    stack.push(asteroid);
                }
            }
        }

        while (!stack.isEmpty()) {
            stack2.push(stack.pop());
        }
        int[] res = new int[stack2.size()];
        int i = 0;
        while (!stack2.isEmpty()) {
            res[i] = stack2.pop();
            i++;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(asteroidCollision(new int[]{5, 10, -5})));
        System.out.println(Arrays.toString(asteroidCollision(new int[]{8, -8})));
        System.out.println(Arrays.toString(asteroidCollision(new int[]{10, 2, -5})));
    }


}
