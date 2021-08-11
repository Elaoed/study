package org.example.tryMapStruct;

import org.apache.commons.lang3.StringUtils;
import org.example.utils.DateUtils;
import org.mapstruct.Named;

import java.time.LocalDateTime;

@Named("WeightMapper")
public class OuterUtils {

    @Named("WeightConvert")
    public String weightConvert(Integer weight) {
        //kg —— 斤
        return (weight * 2) + "斤";
    }

    public String getDateToString(LocalDateTime time) {
        if (time == null) {
            return "";
        }
        return DateUtils.getDateToStr(time);
    }

    public LocalDateTime getStringToDate(String time) {
        if (StringUtils.isBlank(time)) {
            return null;
        }
        return DateUtils.getStrToDate(time);
    }

}
