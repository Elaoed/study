package org.example.tryMapStruct;
import java.math.BigDecimal;

public class TryMapStruct {

    public static void main(String[] args) {
        UserDO userDO = new UserDO();
        userDO.setId(1L);
        userDO.setName("name");
        userDO.setAmount(new BigDecimal("15.555555"));
        userDO.setGender(1);

        UserDTO userDTO = UserConverter.INSTANCE.doToDto(userDO);
        System.out.println(userDTO);

    }

}
