package com.api.boot.modules.infrastructure.aop;

public class DataSourceHolder {

    private final static ThreadLocal<DataType> DATA_THREAD_LOCAL = new ThreadLocal<>();

    public static void setRoutingData(DataType type) {
        DATA_THREAD_LOCAL.set(type);
    }

    public static DataType getRoutingData() {
        return DATA_THREAD_LOCAL.get();
    }

    public static void removeRoutingData() {
        DATA_THREAD_LOCAL.remove();
    }

}
