package com.api.boot.modules.infrastructure.load.balancer.annotation;



import com.api.boot.modules.infrastructure.load.balancer.TargetType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TargetData {
  TargetType target() default TargetType.master;
}
