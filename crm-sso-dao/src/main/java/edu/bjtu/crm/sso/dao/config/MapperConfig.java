package edu.bjtu.crm.sso.dao.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages="edu.bjtu.crm.sso.dao.mapper", sqlSessionFactoryRef="sessionFactory")
public class MapperConfig {
}
