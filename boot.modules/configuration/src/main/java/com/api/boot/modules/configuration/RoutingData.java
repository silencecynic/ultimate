package com.api.boot.modules.configuration;

import com.api.boot.modules.infrastructure.aop.RoutingDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class RoutingData {


    @Bean
    @Primary
    @ConfigurationProperties(value = "spring.master.datasource")
    public DataSourceProperties masterDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties (value = "spring.slave.datasource")
    public DataSourceProperties slaveDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean (name = "masterDataSource")
    @Primary
    @ConfigurationProperties (prefix = "spring.master.datasource")
    public DataSource master() {
        return masterDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean (name = "slaveDataSource")
    @ConfigurationProperties (prefix = "spring.slave.datasource")
    public DataSource slave(){
       return slaveDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean (name = "routeDataSource")
    public DataSource routeDataSource() {
      Map<Object,Object> targetDataSource = new HashMap<>();
      RoutingDataSource routingData = new RoutingDataSource();
      targetDataSource.put("master",master());
      targetDataSource.put("slave",slave());
      routingData.setTargetDataSources(targetDataSource);
      routingData.setDefaultTargetDataSource(master());
      return routingData;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
      return new DataSourceTransactionManager(routeDataSource());
    }
}
