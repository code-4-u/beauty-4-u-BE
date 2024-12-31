package com.beauty4u.backend.opensearch.common;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.client.indices.CreateIndexRequest;
import org.opensearch.client.indices.GetIndexRequest;
import org.opensearch.common.settings.Settings;
import org.opensearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

// 오픈서치의 인덱스를 자동 생성하고 관리하는 클래스
@Component
@Slf4j
@ConditionalOnProperty(name = "spring.opensearch.repositories.enabled", havingValue = "true", matchIfMissing = false)
public class OpenSearchIndexInitializer {

    // 오픈서치 작업 위한 핵심 클래스
    private final RestHighLevelClient restHighLevelClient;
    private final ResourceLoader resourceLoader;

    // 상품 검색
    @Value("${spring.opensearch.goods.index.name}")
    private String goods;

    public OpenSearchIndexInitializer(RestHighLevelClient restHighLevelClient, ResourceLoader resourceLoader) {
        this.restHighLevelClient = restHighLevelClient;
        this.resourceLoader = resourceLoader;
    }

    @PostConstruct
    public void createIndex() throws IOException {
        // 상품 인덱스 초기화
        updateGoodsIndex();
    }

    // 상품 인덱스 생성 메서드
    private void updateGoodsIndex() {
        try {
            GetIndexRequest getRequest = new GetIndexRequest(goods);
            boolean exists = restHighLevelClient.indices().exists(getRequest, RequestOptions.DEFAULT);

            if (!exists) {
                CreateIndexRequest createRequest = new CreateIndexRequest(goods);

                Resource settingsResource = resourceLoader.getResource("classpath:/elasticsearch/settings/settings.json");
                String settingsJson = StreamUtils.copyToString(settingsResource.getInputStream(), StandardCharsets.UTF_8);
                log.info("goods Settings content: {}", settingsJson);

                Resource mappingsResource = resourceLoader.getResource("classpath:/elasticsearch/mappings/mappings.json");
                String mappingsJson = StreamUtils.copyToString(mappingsResource.getInputStream(), StandardCharsets.UTF_8);
                log.info("goods Mappings content: {}", mappingsJson);

                createRequest.settings(Settings.builder()
                        .loadFromSource(settingsJson, XContentType.JSON));
                createRequest.mapping(mappingsJson, XContentType.JSON);

                restHighLevelClient.indices().create(createRequest, RequestOptions.DEFAULT);
                log.info("Created new goods index with settings and mappings");
            } else {
                log.info("goods Index already exists");
            }
        } catch (Exception e) {
            log.error("Goods OpenSearch 초기화 실패", e);
            throw new RuntimeException("OpenSearch 인덱스 업데이트 실패", e);
        }
    }
}
