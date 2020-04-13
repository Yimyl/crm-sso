package edu.bjtu.crm.sso.common;

import lombok.Data;

@Data
public class CrmResponse<T> {
    private int code;
    private String message;
    private T result;
}
