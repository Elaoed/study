package org.example.invocationA;

import lombok.Data;

@Data
public class Jay implements Star {

    private String name;

    private Long id;

    @Override
    public void sing() {
        System.out.println("梦为努力浇了水, 啊唱错了");
    }

    @Override
    public void dance() {
        System.out.println("沙滩 衬衫 老年disco");

    }

    public void rap() {
        System.out.println("嚯嚯嚯嚯嚯嚯嚯嚯嚯嚯");
    }


    public Jay(String name) {
        this.name = name;
    }
}
