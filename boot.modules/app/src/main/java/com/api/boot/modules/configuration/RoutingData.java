package com.api.boot.modules.configuration;


import com.api.boot.modules.infrastructure.load.balancer.RoutingDataSource;
import com.api.boot.modules.infrastructure.load.balancer.TargetType;
import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
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
    @ConfigurationProperties (prefix = "spring.master.datasource")
    public DataSourceProperties masterDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties (prefix = "spring.slave.datasource")
    public DataSourceProperties slaveDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean (name = "masterDataSource")
    @Primary
    @ConfigurationProperties (prefix = "spring.master.datasource")
    public DataSource master() {
        return masterDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean (name = "slaveDataSource")
    @ConfigurationProperties (prefix = "spring.slave.datasource")
    public DataSource slave(){
       return slaveDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    public DataSource routingDataSource() {
      Map<Object,Object> multiple = new HashMap<>();
      multiple.put(TargetType.master,master());
      multiple.put(TargetType.slave,slave());
      RoutingDataSource routingDataSource = new RoutingDataSource();
      routingDataSource.setTargetDataSources(multiple);
      routingDataSource.setDefaultTargetDataSource(master());
      return routingDataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean () {
       SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
       sqlSessionFactory.setDataSource(routingDataSource());
       return sqlSessionFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
      return new DataSourceTransactionManager(routingDataSource());
    }
}
