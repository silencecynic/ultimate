package com.api.boot.modules.configuration;

import com.api.boot.modules.infrastructure.aop.router.DataType;
import com.api.boot.modules.infrastructure.aop.router.RoutingDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
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
        return masterDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean (name = "slaveDataSource")
    @ConfigurationProperties (prefix = "spring.slave.datasource")
    public DataSource slave(){
       return slaveDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean (name = "routeDataSource")
    public DataSource routeDataSource() {
      Map<Object,Object> targetDataSource = new HashMap<>();
      RoutingDataSource routingData = new RoutingDataSource();
      targetDataSource.put(DataType.master,master());
      targetDataSource.put(DataType.slave,slave());
      routingData.setTargetDataSources(targetDataSource);
      return routingData;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory () throws Exception  {
      SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
      sqlSessionFactoryBean.setDataSource(routeDataSource());
      sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/com/api/boot/modules/mapper/*.xml"));
      return sqlSessionFactoryBean.getObject();
    }


    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
      return new SqlSessionTemplate(sqlSessionFactory());
    }


    @Bean
    public PlatformTransactionManager transactionManager() {
      return new DataSourceTransactionManager(routeDataSource());
    }
}
