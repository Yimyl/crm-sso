package edu.bjtu.crm.sso.domain.model;

import lombok.Data;

@Data
public class UserInfo {
    private long id;
    private String username;
    private String name;
    private String pinyin;
    private String position;
    private int isMng;
    private String email;
    private String phone;
    private String token;
}
