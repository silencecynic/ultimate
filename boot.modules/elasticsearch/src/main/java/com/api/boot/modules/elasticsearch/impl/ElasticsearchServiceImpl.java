package com.api.boot.modules.elasticsearch.impl;


import com.api.boot.modules.elasticsearch.repository.Elasticsearch;
import com.api.boot.modules.elasticsearch.service.ElasticsearchService;
import com.api.boot.modules.elasticsearch.domain.DocumentDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElasticsearchServiceImpl implements ElasticsearchService {

  private Elasticsearch elasticsearch;

  @Autowired
  public ElasticsearchServiceImpl(Elasticsearch elasticsearch) {
    this.elasticsearch = elasticsearch;
  }

  @Override
  public List<DocumentDomain> read(String name) {
    return elasticsearch.find(name);
  }
}
