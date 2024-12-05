package com.beauty4u.backend.elasticsearch;

import co.elastic.clients.elasticsearch._types.mapping.TypeMapping;
import co.elastic.clients.elasticsearch.indices.IndexSettings;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Component;

import java.io.IOException;

// 엘라스틱서치의 인덱스를 자동 생성하고 관리하는 클래스
@Component
public class ElasticsearchIndexInitializer {

    // 엘라스틱서치 작업 위한 핵심 클래스
    private final ElasticsearchOperations elasticsearchOperations;

    @Value("${spring.elasticsearch.index.name}")
    private String indexName;

    public ElasticsearchIndexInitializer(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @PostConstruct
    public void createIndex() throws IOException {
        // 해당 인덱스 존재하지 않을 때마나 생성
        if (!elasticsearchOperations.indexOps(IndexCoordinates.of(indexName)).exists()) {
            IndexSettings.Builder settingsBuilder = new IndexSettings.Builder()
                    .numberOfShards("1")
                    .numberOfReplicas("0");

            TypeMapping.Builder mappingBuilder = new TypeMapping.Builder()
                    .properties("goodsName",p -> p.text(t->t.analyzer("standard")));


            // 인덱스 생성
            elasticsearchOperations.indexOps(IndexCoordinates.of(indexName))
                    .create();

            // 인덱스 매핑
            elasticsearchOperations.indexOps(IndexCoordinates.of(indexName))
                    .putMapping(Document.parse(mappingBuilder.build().toString()));
        }
    }
}
