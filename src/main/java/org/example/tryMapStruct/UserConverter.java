//package org.example.tryMapStruct;
//
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.Mappings;
//import org.mapstruct.Named;
//import org.mapstruct.factory.Mappers;
//
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//
//// 可以不用Component Component只是让Spring托管而已
//@Mapper(uses = {OuterUtils.class})
//public interface UserConverter {
//
//    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);
//
//    @Mappings({
//            @Mapping(source = "amount", target = "amount", qualifiedByName = "bigDecimalScale"),
//            @Mapping(source = "name", target = "name", ignore = true),
//            @Mapping(source = "weight", target = "weight", qualifiedByName = {"OuterUtils", "weightConvert"}),
//            @Mapping(source = "createTime", target = "createTime", qualifiedByName = {"OuterUtils", "getStringToDate"}),
//            @Mapping(source = "updateTime", target = "updateTime", qualifiedByName = {"OuterUtils", "getDateToString"})
//    })
//    UserDTO doToDto(UserDO userDO);
//
//    @Named("bigDecimalScale")
//    default BigDecimal bigDecimalScale(BigDecimal amount) {
//        if (amount == null) {
//            return BigDecimal.ZERO;
//        }
//        return amount.setScale(2, RoundingMode.HALF_DOWN);
//    }
//
//}
