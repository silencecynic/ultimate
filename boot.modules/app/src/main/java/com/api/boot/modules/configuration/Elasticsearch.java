package com.api.boot.modules.configuration;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * <link>https://www.mkyong.com/spring-boot/spring-boot-spring-data-elasticsearch-example/</link>
 */
@Configuration
@EnableElasticsearchRepositories (basePackages = {"com.api.boot.modules.repository.elasticsearch"})
public class Elasticsearch {

  @PostConstruct
  private void init() {
    System.setProperty("es.set.netty.runtime.available.processors", "false");
  }

  @Bean
  public TransportClient transportClient() throws UnknownHostException {
    Settings settings = Settings.builder()
        .put("client.transport.sniff",true)
        .put("thread_pool.search.size",5)
        .put("cluster.name","elasticsearch")
        .build();
    TransportClient transportClient = new PreBuiltTransportClient(settings);
    TransportAddress transportAddress = new TransportAddress(InetAddress.getByName("127.0.0.1"),Integer.valueOf("3900"));
    transportClient.addTransportAddress(transportAddress);
    return transportClient;
  }

  @Bean
  public ElasticsearchOperations elasticsearchOperations() throws UnknownHostException {
    return new ElasticsearchTemplate(transportClient());
  }
}
