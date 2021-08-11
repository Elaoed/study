package org.example.tryMapStruct;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class UserDO {

    private Long id;

    private String name;

    private BigDecimal amount;

    private Integer gender;

    private Integer weight;

    private LocalDateTime createTime;

    private String updateTime;

}
