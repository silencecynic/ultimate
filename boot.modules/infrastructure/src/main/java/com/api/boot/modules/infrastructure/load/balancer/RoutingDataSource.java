package com.api.boot.modules.infrastructure.load.balancer;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingDataSource extends AbstractRoutingDataSource {

  @Override
  protected Object determineCurrentLookupKey() {
    return DataSourceHolder.get();
  }
}
