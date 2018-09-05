package com.api.boot.modules;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableAspectJAutoProxy (proxyTargetClass = true)
@EnableTransactionManagement (proxyTargetClass = true)
@MapperScan (basePackages = {"com.api.boot.modules.repository"})
@SpringBootApplication (scanBasePackages = {"com.api.boot.modules"})
public class Application {
  public static void main(String[] args) {
      SpringApplication.run(Application.class,args);
  }
}