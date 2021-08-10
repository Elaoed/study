package org.example.tryMapStruct;

import lombok.Getter;

public enum SexEnum {

    MALE(1, "男"),
    FEMALE(0, "女"),
    ;

    SexEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Getter
    private Integer code;

    @Getter
    private String desc;
}
