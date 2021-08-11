package org.example.tryMapStruct;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TryMapStruct {

    public static void main(String[] args) {
        UserDO userDO = new UserDO();
        userDO.setId(1L);
        userDO.setName("name");
        userDO.setAmount(new BigDecimal("15.555555"));
        userDO.setGender(1);
        userDO.setWeight(1);
        userDO.setCreateTime(LocalDateTime.now());
        userDO.setUpdateTime("2021-03-21 00:00:00");

        UserDTO userDTO = UserConverter.INSTANCE.doToDto(userDO);
        System.out.println(userDTO);

    }

}
