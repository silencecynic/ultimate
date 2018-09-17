package com.api.boot.modules.infrastructure.load.balancer.aop;

import com.api.boot.modules.infrastructure.load.balancer.annotation.TargetData;
import com.api.boot.modules.infrastructure.load.balancer.DataSourceHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Order (-1)
@Component
public class AspectDataSource {

  @Pointcut("execution(* com.api.boot.modules.*.*(..))")
  public void pointcut() { }

  @Before("@annotation(com.api.boot.modules.infrastructure.load.balancer.annotation.TargetData)")
  public void before(JoinPoint joinPoint) {
    Object target = joinPoint.getTarget();
    String method = joinPoint.getSignature().getName();
    Class<?>[] targetPoint = target.getClass().getInterfaces();
    Class<?>[] param = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
    try{
      Method methodAnnotation = targetPoint[0].getMethod(method,param);
      if (methodAnnotation != null && methodAnnotation.isAnnotationPresent(TargetData.class)) {
        TargetData targetData = methodAnnotation.getAnnotation(TargetData.class);
        DataSourceHolder.set(targetData.target());
      }
    } catch (NoSuchMethodException ex) {
      ex.printStackTrace();
    }
  }

  @After("@annotation(com.api.boot.modules.infrastructure.load.balancer.annotation.TargetData)")
  public void after() {
    DataSourceHolder.remove();
  }
}
