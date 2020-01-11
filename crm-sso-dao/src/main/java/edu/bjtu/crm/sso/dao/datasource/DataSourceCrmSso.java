package edu.bjtu.crm.sso.dao.datasource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource({"classpath:db.properties"})
public class DataSourceCrmSso {
    @Value("${datasource.jdbc.crm.sso.driverClassName}")
    private String dbDriver;

    @Value("${datasource.jdbc.crm.sso.url}")
    private String dbUrl;

    @Value("${datasource.jdbc.crm.sso.username}")
    private String dbUsername;

    @Value("${datasource.jdbc.crm.sso.password}")
    private String dbPassword;

    @Bean
    public DataSource getDateSource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(dbDriver);
        dataSourceBuilder.url(dbUrl);
        dataSourceBuilder.username(dbUsername);
        dataSourceBuilder.password(dbPassword);
        return dataSourceBuilder.build();
    }

}
