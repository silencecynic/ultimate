package com.api.boot.modules.elasticsearch.repository;


import com.api.boot.modules.elasticsearch.domain.DocumentDomain;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;


public interface Elasticsearch extends ElasticsearchRepository<DocumentDomain,String> {

  List<DocumentDomain> find(String name);

}