package com.beauty4u.backend.elasticsearch;

import co.elastic.clients.elasticsearch._types.mapping.TypeMapping;
import co.elastic.clients.elasticsearch.indices.IndexSettings;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Component;

import java.io.IOException;

// 엘라스틱서치의 인덱스를 자동 생성하고 관리하는 클래스
@Component
@Slf4j
@ConditionalOnProperty(name = "spring.data.elasticsearch.repositories.enabled", havingValue = "true", matchIfMissing = false)
public class ElasticsearchIndexInitializer {

    // 엘라스틱서치 작업 위한 핵심 클래스
    private final ElasticsearchOperations elasticsearchOperations;

    // 상품 검색
    @Value("${spring.elasticsearch.goods.index.name}")
    private String goods;

    // 리뷰 검색
    @Value("${spring.elasticsearch.reviews.index.name}")
    private String reviews;

    public ElasticsearchIndexInitializer(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @PostConstruct
    public void createIndex() throws IOException {
        // 상품 인덱스 초기화
        createGoodsIndex();
        // 리뷰 인덱스 초기화
        createReviewIndex();
    }

    // 상품 인덱스 생성 메서드
    private void createGoodsIndex() {
        try {
            // 해당 인덱스 존재하지 않을 때마다 생성
            if (!elasticsearchOperations.indexOps(IndexCoordinates.of(goods)).exists()) {
                IndexSettings.Builder settingsBuilder = new IndexSettings.Builder()
                        .numberOfShards("1")
                        .numberOfReplicas("0");

                TypeMapping.Builder mappingBuilder = new TypeMapping.Builder()
                        .properties("goodsName", p -> p.text(t -> t.analyzer("standard")));


                // 인덱스 생성
                elasticsearchOperations.indexOps(IndexCoordinates.of(goods))
                        .create();

                // 인덱스 매핑
                elasticsearchOperations.indexOps(IndexCoordinates.of(goods))
                        .putMapping(Document.parse(mappingBuilder.build().toString()));
            }
        } catch (Exception e) {
            log.error("Goods Elasticsearch 초기화 실패", e);
        }
    }

    // 리뷰 인덱스 생성 메서드
    private void createReviewIndex() {
        try {
            // 해당 인덱스 존재하지 않을 때마다 생성
            if (!elasticsearchOperations.indexOps(IndexCoordinates.of(reviews)).exists()) {
                IndexSettings.Builder settingsBuilder = new IndexSettings.Builder()
                        .numberOfShards("1")
                        .numberOfReplicas("0");

                TypeMapping.Builder mappingBuilder = new TypeMapping.Builder()
                        .properties("reviewContent", p -> p.text(t -> t.analyzer("standard")));


                // 인덱스 생성
                elasticsearchOperations.indexOps(IndexCoordinates.of(reviews))
                        .create();

                // 인덱스 매핑
                elasticsearchOperations.indexOps(IndexCoordinates.of(reviews))
                        .putMapping(Document.parse(mappingBuilder.build().toString()));
            }
        } catch (Exception e) {
            log.error("reviews Elasticsearch 초기화 실패", e);
        }
    }
}
