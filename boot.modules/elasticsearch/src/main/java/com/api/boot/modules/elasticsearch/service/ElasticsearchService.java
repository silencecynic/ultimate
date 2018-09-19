package com.api.boot.modules.elasticsearch.service;

import com.api.boot.modules.elasticsearch.domain.DocumentDomain;

import java.util.List;

public interface ElasticsearchService {

  List<DocumentDomain> read(String name);
}
