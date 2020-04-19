package edu.bjtu.crm.sso.domain.model;

import lombok.Data;

@Data
public class Product {
    private long id;
    private String name;
    private double cost;
    private double price;
    private double commissionRate;
}
