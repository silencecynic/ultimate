package com.api.boot.modules.infrastructure.aop.router;

public class DataSourceHolder {

    private final static ThreadLocal<DataType> DATA_THREAD_LOCAL = new ThreadLocal<>();

    protected static void setRoutingData(DataType type) {
        DATA_THREAD_LOCAL.set(type);
    }

    protected static DataType getRoutingData() {
        return DATA_THREAD_LOCAL.get();
    }

    protected static void removeRoutingData() {
        DATA_THREAD_LOCAL.remove();
    }

}
