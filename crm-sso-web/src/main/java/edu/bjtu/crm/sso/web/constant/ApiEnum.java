package edu.bjtu.crm.sso.web.constant;

public enum ApiEnum {
    SUCCESS(200, "success"),
    ERROR(500, "服务端异常"),
    PARAM_INVALID(400, "参数错误"),
    TOKEN_INVALID(401, "token不合法"),
    PERMISSION_DENIED(403, "无访问权限");

    private int code;
    private String value;
    ApiEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
