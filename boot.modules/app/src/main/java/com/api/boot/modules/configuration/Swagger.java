package com.api.boot.modules.configuration;

import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
@ComponentScan (basePackages = {"com.api.boot.modules"})
public class Swagger  {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .produces(Sets.newHashSet(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .consumes(Sets.newHashSet(MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE))
                .protocols(Sets.newHashSet("http","https"))
                .apiInfo(api())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.api.boot.modules.handler"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo api() {
        return new ApiInfoBuilder()
                .title("Api Help")
                .version("v 0.1")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }

}
