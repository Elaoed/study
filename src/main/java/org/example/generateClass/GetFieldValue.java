package org.example.generateClass;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GetFieldValue {

    public static void main(String[] args) {

//        SHNewProjectMutualAuthRequestDTO obj = new SHNewProjectMutualAuthRequestDTO();
//        Class aClass = obj.getClass();
//        try {
//            Field nodeName = aClass.getField("nodeName");
//            System.out.println(">>>>>>>>>>>>>> " + nodeName.getName());
//            System.out.println(">>>>>>>>>>>>>> " + nodeName.get(obj));
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
        System.out.println(LocalDateTime.parse("2020-01-02 00:00:00", DateTimeFormatter.ISO_LOCAL_DATE));

    }

}
