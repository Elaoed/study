package org.example.algorithm.baseDatastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 380. Insert Delete GetRandom O(1)
 * Implement the RandomizedSet class:
 * <p>
 * RandomizedSet() Initializes the RandomizedSet object.
 * bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
 * bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
 * int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element exists when this method is called). Each element must have the same probability of being returned.
 * You must implement the functions of the class such that each function works in average O(1) time complexity.
 * <p>
 * 这是什么破习惯，每次都要把下次准备好了才肯认为是结束，下次搞掉这个强迫症
 * O(1)的插入，list可以做到，array的话尾部可以做到，hashMap/hashSet也可以做到
 * O(1)的查询，hashMap可以做到, 但是hashMap不支持random
 * 所以getRandom要是O1的，光hashMap可不够，需要一个array来维持, 如果hashMap里面是key, value, array里面是key 仍无法满足remove是O(1)
 * 因为从array里面找到这个key最起码也要O(logn), 所以hashMap里面存的是key, index, index指向array里面的value
 * 果然是middle, 只要想清楚了之后代码很容易写的
 */
public class InsertDeleteGetRandomO1 {

    private List<Integer> array = new ArrayList<>();
    private Map<Integer, Integer> map = new HashMap<>();
    private Random rand = new Random();

    // HashMap + LinkedList, 什么情况下用LinkedList, LRU, 什么情况下用ArrayList, 他两的条件哪里不一样了？
    public InsertDeleteGetRandomO1() {

    }

    public boolean insert(int val) {
        if (map.get(val) == null) {
            array.add(val);
            map.put(val, array.size() - 1); // 反一下size就变了，这种位置影响内容的一定要注意再注意
        }
        return true;
    }

    public boolean remove(int val) {
        // 为了让random删除实现O1, 有两步 1. 查找O1, 2. 删除O1
        Integer index = map.get(val);
        if (index == null) {
            return false; // true or false?
        }
        Integer tmp = array.get(index);
        array.set(index, array.get(array.size() - 1));
        array.set(array.size() - 1, tmp);
        array.remove(array.size() - 1);
        map.remove(val);
        return true;
    }

    public int getRandom() {
        return array.get(rand.nextInt(array.size()));
    }

    public static void main(String[] args) {
        InsertDeleteGetRandomO1 obj = new InsertDeleteGetRandomO1();
        boolean param_1 = obj.insert(1);
        boolean param_2 = obj.insert(2);
        boolean param_3 = obj.insert(1);
        boolean param_4 = obj.remove(1);
        int param_5 = obj.getRandom();
        System.out.println(param_1);
        System.out.println(param_2);
        System.out.println(param_3);
        System.out.println(param_4);
        System.out.println(param_5);

    }


}
