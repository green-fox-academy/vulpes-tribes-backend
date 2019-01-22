package com.tribesbackend.tribes.security;


import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.security.Key;


@Configuration
@PropertySource("classpath:db.properties")
public class AppConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource getDataSource(){
        BasicDataSource dataSource = new BasicDataSource ();
        dataSource.setDriverClassName(env.getProperty( "mysql.driver" ));
        dataSource.setUrl(env.getProperty("DATASOURCE_URL"));
        dataSource.setUsername(env.getProperty("TRIBES_DB_USERNAME"));
        dataSource.setPassword(env.getProperty("TRIBES_DB_PASSWORD"));
        return dataSource;
    }
}
