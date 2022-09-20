package org.example.study;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Comparator;

@Data
@AllArgsConstructor
public class User implements Comparator<User> {

    private String name;
    private int age;
    private boolean sex;

    @Override
    public int compare(User o1, User o2) {
        return o1.age - o2.age;
    }

}
