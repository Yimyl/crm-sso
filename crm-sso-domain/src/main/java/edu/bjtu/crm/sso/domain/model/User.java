package edu.bjtu.crm.sso.domain.model;

import lombok.Data;

import java.util.Date;

/**
 * @author: Yimyl
 * @date: 2020/1/27 14:50
 * @description:
 * @modified By:
 * @version:
 */
@Data
public class User {
    private long id;
    private String username;
    private String password;
    private Date createTime;
    private Date modifyTime;
}
