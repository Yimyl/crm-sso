package edu.bjtu.crm.sso.web.constant;

/**
 * @author: Yimyl
 * @date: 2020/1/31 13:35
 * @description:
 * @modified By:
 * @version:
 */
public enum RegisterStatusEnum {
    Success("success"),
    ValidcodeError("validcodeError"),
    ParamError("paramError");

    private String value;
    RegisterStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
