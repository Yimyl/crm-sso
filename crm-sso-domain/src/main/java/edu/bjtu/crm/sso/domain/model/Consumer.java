package edu.bjtu.crm.sso.domain.model;

import lombok.Data;

@Data
public class Consumer {
    private long id;
    private String phone;
    private String name;
    private int vipGrade;
    private double balance;
    private double consume;
    private String email;
}
