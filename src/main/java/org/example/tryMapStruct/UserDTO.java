package org.example.tryMapStruct;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class UserDTO {

    private Long id;

    private String name;

    private BigDecimal amount;

    private Integer gender;

    private String weight;

    private String createTime;

    private LocalDateTime updateTime;

}
