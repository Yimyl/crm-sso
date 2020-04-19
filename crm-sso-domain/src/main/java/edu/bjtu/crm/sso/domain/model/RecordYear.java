package edu.bjtu.crm.sso.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordYear {
    private long id;
    private String username;
    private String phone;
    private String name;
    private double price;
    private double income;
    private int year;

    public RecordYear(Record record) {
        username = record.getUsername();
        phone = record.getPhone();
        name = record.getName();
        price = record.getRealPrice();
        income = price * record.getCommissionRate();
        year = record.getYear();
    }
}
