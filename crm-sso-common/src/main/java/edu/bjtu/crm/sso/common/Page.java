package edu.bjtu.crm.sso.common;

import lombok.Data;

@Data
public class Page {
    int pageSize;
    int total;
    int offset;
}
