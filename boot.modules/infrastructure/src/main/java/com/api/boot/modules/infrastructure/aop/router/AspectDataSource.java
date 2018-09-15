package com.api.boot.modules.infrastructure.aop.router;

import com.api.boot.modules.infrastructure.aop.annotation.TargetDataSource;
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
@Order(-1)
@Component
public class AspectDataSource {

    @Pointcut("execution(* com.api.boot.modules.service*.*(..))")
    public void pointcut() { }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        Object target = joinPoint.getTarget();
        String method = joinPoint.getSignature().getName();
        Class<?>[] targetPoint = target.getClass().getInterfaces();
        Class<?>[] param = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
        try{
            Method methodAnnotation = targetPoint[0].getMethod(method,param);
            if (methodAnnotation != null && methodAnnotation.isAnnotationPresent(TargetDataSource.class)) {
                TargetDataSource targetDataSource = methodAnnotation.getAnnotation(TargetDataSource.class);
                DataSourceContextHolder.setRoutingData(targetDataSource.target());
            }
        } catch (NoSuchMethodException ex) {
            ex.printStackTrace();
        }
    }

    @After("pointcut()")
    public void after() {
        DataSourceContextHolder.removeRoutingData();
    }
}
