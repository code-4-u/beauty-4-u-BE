package com.beauty4u.backend.elasticsearch;

import co.elastic.clients.elasticsearch._types.mapping.TypeMapping;
import co.elastic.clients.elasticsearch.indices.IndexSettings;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.index.Settings;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

// 엘라스틱서치의 인덱스를 자동 생성하고 관리하는 클래스
@Component
@Slf4j
@ConditionalOnProperty(name = "spring.data.elasticsearch.repositories.enabled", havingValue = "true", matchIfMissing = false)
public class ElasticsearchIndexInitializer {

    // 엘라스틱서치 작업 위한 핵심 클래스
    private final ElasticsearchOperations elasticsearchOperations;
    private final ResourceLoader resourceLoader;

    // 상품 검색
    @Value("${spring.elasticsearch.goods.index.name}")
    private String goods;

    // 리뷰 검색
    @Value("${spring.elasticsearch.reviews.index.name}")
    private String reviews;

    public ElasticsearchIndexInitializer(ElasticsearchOperations elasticsearchOperations, ResourceLoader resourceLoader) {
        this.elasticsearchOperations = elasticsearchOperations;
        this.resourceLoader = resourceLoader;
    }

    @PostConstruct
    public void createIndex() throws IOException {
        // 상품 인덱스 초기화
        updateGoodsIndex();
        // 리뷰 인덱스 초기화
        createReviewIndex();
    }

    // 상품 인덱스 생성 메서드
    private void updateGoodsIndex() {
        try {
            IndexOperations indexOperations = elasticsearchOperations.indexOps(IndexCoordinates.of(goods));

            // 엘라스틱서치 settings,mappings 적용 위한 코드
            Resource settingsResource = resourceLoader.getResource("classpath:/elasticsearch/settings/settings.json");
            String settings = StreamUtils.copyToString(settingsResource.getInputStream(), StandardCharsets.UTF_8);
            log.info("Settings content: {}", settings);

            Resource mappingsResource = resourceLoader.getResource("classpath:/elasticsearch/mappings/mappings.json");
            String mappings = StreamUtils.copyToString(mappingsResource.getInputStream(), StandardCharsets.UTF_8);
            log.info("Mappings content: {}", mappings);

            // 해당 인덱스 존재하지 않을 경우 생성
            if (!indexOperations.exists()) {
                log.info("Creating new index with settings and mappings");
                indexOperations.create(Settings.parse(settings));
                indexOperations.putMapping(Document.parse(mappings));
            } else{
                log.info("Index already exists");
            }
        } catch (Exception e) {
            log.error("Goods Elasticsearch 초기화 실패", e);
            throw new RuntimeException("Elasticsearch 인덱스 업데이트 실패", e);
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
