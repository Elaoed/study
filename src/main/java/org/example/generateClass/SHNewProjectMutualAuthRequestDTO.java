package org.example.generateClass;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

@Data
public class SHNewProjectMutualAuthRequestDTO implements Serializable {

    private static final long serialVersionUID = -4999196639138861419L;

    /** 人员编号 **/
    private String psn_no;
    /** 检查机构代码 **/
    private String exam_org_code;
    /** 检查机构名称 **/
    private String exam_org_name;
    /** 检查-项目代码 **/
    private String exam_item_code;
    /** 检查-项目名称 **/
    private String exam_item_name;

    @JSONField(serialize = false)
    public static String nodeName = "bilgiteminfo";

}
