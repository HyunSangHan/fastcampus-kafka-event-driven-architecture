package com.fastcampus.kafkahandson.ugc.postsearch.config;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.util.List;

@EnableElasticsearchRepositories
@Configuration
public class ElasticsearchConfig {

    @Value("${spring.data.elasticsearch.host}")
    private String esHost;

    @Value("${spring.data.elasticsearch.port}")
    private Integer esPort;

    @Bean
    public RestClient getRestClient() {
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        return RestClient.builder(new HttpHost(esHost, esPort))
            .setHttpClientConfigCallback(httpClientBuilder -> {
                httpClientBuilder.disableAuthCaching();
                httpClientBuilder.setDefaultHeaders(
                    List.of(
                        new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                    )
                );
                httpClientBuilder.addInterceptorLast((HttpResponseInterceptor)
                    (response, context) -> response.addHeader("X-Elastic-Product", "Elasticsearch")
                );
                return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            }).build();
    }
}
