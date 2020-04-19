package edu.bjtu.crm.sso.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Record {
    private long id;
    private String username;
    private String phone;
    private String name;
    private double price;
    private double discount;
    private double realPrice;
    private double commissionRate;
    private int day;
    private int month;
    private int year;
    private Date createTime;
}
