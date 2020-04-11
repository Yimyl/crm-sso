package model;

import lombok.Data;

@Data
public class UserInfo {
    private long id;
    private String username;
    private String name;
    private String position;
    private boolean isMng;
    private String email;
    private String phone;
    private String token;
}
