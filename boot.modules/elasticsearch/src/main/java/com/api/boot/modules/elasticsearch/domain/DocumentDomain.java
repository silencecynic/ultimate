package com.api.boot.modules.elasticsearch.domain;

import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Document(indexName = "domain",type = "book" ,shards = 1,replicas = 0)
public class DocumentDomain implements Serializable {

  public DocumentDomain() {

  }

  public DocumentDomain(String id, String name, String title) {
    this.id = id;
    this.name = name;
    this.title = title;
  }

  private String id;

  private String name;

  private String title;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
