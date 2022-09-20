package org.example.study;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestComparator {

    public static void main(String[] args) {

        User user1 = new User("dingli", 25, true);
        User user2 = new User("huxiaojuam", 24, false);
        User user3 = new User("xxx", 24, false);
        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        Collections.sort(list, (o1, o2) -> o1.getName().compareTo(o2.getName()));
        Collections.sort(list, Comparator.comparing(User::getName));

    }

}
