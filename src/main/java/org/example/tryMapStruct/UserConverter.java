package org.example.tryMapStruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Mapper
public interface UserConverter {

    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    @Mappings({
            @Mapping(source = "amount", target = "amount", qualifiedByName = "bigDecimalScale")
    })
    UserDTO doToDto(UserDO userDO);

    @Named("bigDecimalScale")
    default BigDecimal bigDecimalScale(BigDecimal amount) {
        if (amount == null) {
            return BigDecimal.ZERO;
        }
        return amount.setScale(2, RoundingMode.HALF_DOWN);
    }

}
