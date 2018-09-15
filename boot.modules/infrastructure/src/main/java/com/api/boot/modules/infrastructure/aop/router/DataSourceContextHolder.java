package com.api.boot.modules.infrastructure.aop.router;

public class DataSourceContextHolder {

    private final static ThreadLocal<DataType> THREAD_LOCAL = new ThreadLocal<>();

    protected static void setRoutingData(DataType type) {
        THREAD_LOCAL.set(type);
    }

    protected static DataType getRoutingData() {
        return THREAD_LOCAL.get();
    }

    protected static void removeRoutingData() {
        THREAD_LOCAL.remove();
    }

}
