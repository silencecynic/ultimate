package com.api.boot.modules.configuration;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class RoutingData {

//
//    @Bean
//    @Primary
//    @ConfigurationProperties(value = "spring.master.datasource")
//    public DataSourceProperties masterDataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Bean
//    @ConfigurationProperties (value = "spring.slave.datasource")
//    public DataSourceProperties slaveDataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Bean (name = "masterDataSource")
//    @Primary
//    @ConfigurationProperties (prefix = "spring.master.datasource")
//    public DataSource master() {
//        return masterDataSourceProperties().initializeDataSourceBuilder().build();
//    }
//
//    @Bean (name = "slaveDataSource")
//    @ConfigurationProperties (prefix = "spring.slave.datasource")
//    public DataSource slave(){
//       return slaveDataSourceProperties().initializeDataSourceBuilder().build();
//    }

}
