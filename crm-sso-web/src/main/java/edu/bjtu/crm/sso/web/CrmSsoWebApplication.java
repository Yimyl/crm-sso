package edu.bjtu.crm.sso.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class},scanBasePackages="edu.bjtu")
public class CrmSsoWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrmSsoWebApplication.class, args);
    }

}
