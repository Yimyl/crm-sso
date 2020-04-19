package edu.bjtu.crm.sso.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordMonth {
    private long id;
    private String username;
    private String phone;
    private String name;
    private double price;
    private double income;
    private int month;
    private int year;

    public RecordMonth(Record record) {
        username = record.getUsername();
        phone = record.getPhone();
        name = record.getName();
        price = record.getRealPrice();
        income = price * record.getCommissionRate();
        month = record.getMonth();
        year = record.getYear();
    }
}
