package com.api.boot.modules.infrastructure.load.balancer;

public class DataSourceHolder {

  private final static ThreadLocal<TargetType> THREAD_LOCAL = new ThreadLocal<>();

  public static void set(TargetType targetType) {
    THREAD_LOCAL.set(targetType);
  }

  public static TargetType get() {
    return THREAD_LOCAL.get();
  }

  public static void remove() {
    THREAD_LOCAL.remove();
  }
}
