package com.api.boot.modules.infrastructure.aop.router;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

public abstract class AbstractRoute implements Route , ImportBeanDefinitionRegistrar, EnvironmentAware {


  @Override
  public void setEnvironment (Environment environment) {

  }

  @Override
  public void registerBeanDefinitions (AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

  }
}
