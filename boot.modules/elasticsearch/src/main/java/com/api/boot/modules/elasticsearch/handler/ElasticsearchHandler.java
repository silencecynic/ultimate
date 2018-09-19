package com.api.boot.modules.elasticsearch.handler;

import com.api.boot.modules.elasticsearch.service.ElasticsearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping (value = "/api/v1")
@RestController
public class ElasticsearchHandler {

  private ElasticsearchOperations elasticsearchOperations;

  private ElasticsearchService elasticsearchService;

  @Autowired
  public ElasticsearchHandler(ElasticsearchOperations elasticsearchOperations,ElasticsearchService elasticsearchService) {
    this.elasticsearchOperations = elasticsearchOperations;
    this.elasticsearchService = elasticsearchService;
  }

  @GetMapping (value = "/book",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public String search() {
    return "";
  }

}
