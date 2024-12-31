package com.beauty4u.backend.opensearch.repository;

import com.beauty4u.backend.opensearch.document.GoodsDocument;
import org.opensearch.action.bulk.BulkRequest;
import org.opensearch.action.bulk.BulkResponse;
import org.opensearch.action.index.IndexRequest;
import org.opensearch.action.search.SearchRequest;
import org.opensearch.action.search.SearchResponse;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.common.unit.Fuzziness;
import org.opensearch.common.xcontent.XContentType;
import org.opensearch.index.query.BoolQueryBuilder;
import org.opensearch.index.query.QueryBuilders;
import org.opensearch.search.builder.SearchSourceBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@ConditionalOnProperty(name = "spring.opensearch.repositories.enabled", havingValue = "true", matchIfMissing = false)
public class GoodsSearchRepository {
    private final RestHighLevelClient restHighLevelClient;

    public GoodsSearchRepository(RestHighLevelClient client) {
        this.restHighLevelClient = client;
    }

    public List<GoodsDocument> findByGoodsName(String searchGoodsName) {
        try {
            SearchRequest searchRequest = new SearchRequest("goods");
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery()
                    .should(QueryBuilders.matchQuery("goods_name.prefix", searchGoodsName).boost(2.0f))
                    .should(QueryBuilders.matchQuery("goods_name", searchGoodsName).fuzziness(Fuzziness.AUTO).prefixLength(1).boost(1.0f))
                    .should(QueryBuilders.wildcardQuery("goods_name", "*" + searchGoodsName + "*").boost(0.5f))
                    .minimumShouldMatch(1);

            searchSourceBuilder.query(boolQuery);
            searchRequest.source(searchSourceBuilder);

            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

            return Arrays.stream(searchResponse.getHits().getHits())
                    .map(hit -> {
                        Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                        GoodsDocument document = new GoodsDocument();
                        // sourceAsMap의 값들을 GoodsDocument에 매핑
                        // 예: document.setGoodsName((String) sourceAsMap.get("goods_name"));
                        return document;
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Failed to search goods", e);
        }
    }

    public void saveAll(List<GoodsDocument> documents) {
        try {
            BulkRequest bulkRequest = new BulkRequest();
            for (GoodsDocument document : documents) {
                IndexRequest indexRequest = new IndexRequest("goods")
                        .id(document.getId())  // document의 ID 필드 사용
                        .source(convertDocumentToMap(document), XContentType.JSON);
                bulkRequest.add(indexRequest);
            }

            BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
            if (bulkResponse.hasFailures()) {
                throw new RuntimeException("Bulk indexing failed: " + bulkResponse.buildFailureMessage());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to index documents", e);
        }
    }

    private Map<String, Object> convertDocumentToMap(GoodsDocument document) {
        // GoodsDocument 객체를 Map으로 변환
        Map<String, Object> map = new HashMap<>();
        map.put("id", document.getId());
        map.put("goods_name", document.getGoodsName());
        // 필요한 다른 필드들도 추가
        return map;
    }


}