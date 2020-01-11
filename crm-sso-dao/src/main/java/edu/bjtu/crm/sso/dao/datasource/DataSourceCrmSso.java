package edu.bjtu.crm.sso.dao.datasource;

import com.sun.xml.internal.bind.v2.util.DataSourceSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
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
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(dbDriver);
        dataSource.setJdbcUrl(dbUrl);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        return dataSource;
    }
}
