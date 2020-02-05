package edu.bjtu.crm.sso.web.constant;

/**
 * @author: Yimyl
 * @date: 2020/1/31 13:35
 * @description:
 * @modified By:
 * @version:
 */
public enum LoginStatusEnum {
    Success("success"),
    ValidcodeError("validcodeError"),
    UsernamepasswordError("usernamepasswordError");

    private String value;
    LoginStatusEnum (String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
