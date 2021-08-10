package org.example.tryMapStruct;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserDO {

    private Long id;

    private String name;

    private BigDecimal amount;

    private Integer gender;

}
