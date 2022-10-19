package org.example.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class printFormat {

    public static HashMap<String, Object> makeRoot() {

        HashMap<String, Object> c11 = new HashMap<>();
        c11.put("name", "c11");
        c11.put("children", new ArrayList<>());

        HashMap<String, Object> c12 = new HashMap<>();
        c12.put("name", "c12");
        c12.put("children", new ArrayList<>());

        HashMap<String, Object> c21 = new HashMap<>();
        c21.put("name", "c21");
        c21.put("children", new ArrayList<>());

        HashMap<String, Object> c22 = new HashMap<>();
        c22.put("name", "c22");
        c22.put("children", new ArrayList<>());

        HashMap<String, Object> c1 = new HashMap<>();
        c1.put("name", "c1");
        c1.put("children", Arrays.asList(c11, c12));

        HashMap<String, Object> c2 = new HashMap<>();
        c2.put("name", "c2");
        c2.put("children", Arrays.asList(c21, c22));

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("name", "root");
        hashMap.put("children", Arrays.asList(c1, c2));

        return hashMap;
    }


    public static void getName(HashMap<String, Object> map, List<String> res) {
        res.add((String) map.get("name"));
        @SuppressWarnings("unchecked")
        List<HashMap<String, Object>> childrenList = (List<HashMap<String, Object>>) map.get("children");
        if (childrenList == null) {
            return;
        }
        for (HashMap<String, Object> child : childrenList) {
            getName(child, res);
        }
    }

    public static void getNameVersion2() {
        HashMap<String, Object> map = makeRoot();
        List<String> res = new ArrayList<>();
        Stack<HashMap<String, Object>> stack = new Stack<>();
        stack.push(map);
        while (!stack.isEmpty()) {
            HashMap<String, Object> pop = stack.pop();
            res.add((String) pop.get("name"));
            List<HashMap<String, Object>> childList = (List<HashMap<String, Object>>) pop.get("children");
            if (childList == null) {
                continue;
            }
            for (int i = childList.size() - 1; i >= 0; i--) {
                stack.push(childList.get(i));
            }
        }

        System.out.println(res);

    }

    public static void main(String[] args) {

//        HashMap<String, Object> map = makeRoot();
//        List<String> res = new ArrayList<>();
//        getName(map, res);
//        System.out.println(res);
//        getNameVersion2();
//        ArrayList<Object> objects = new ArrayList<>();
//        objects.get(0);
        LinkedList<Integer> objects1 = new LinkedList<>();
        objects1.offer(null);
        System.out.println(objects1.poll());
    }

}
