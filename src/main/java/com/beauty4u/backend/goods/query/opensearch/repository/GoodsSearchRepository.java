package com.beauty4u.backend.goods.query.opensearch.repository;

import com.beauty4u.backend.goods.query.opensearch.document.GoodsDocument;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Slf4j
public class GoodsSearchRepository {

    private final RestHighLevelClient restHighLevelClient;
    private static final String INDEX_NAME = "goods";

    public void saveAll(List<GoodsDocument> documents) throws IOException {
        if (documents == null || documents.isEmpty()) {
            throw new IllegalArgumentException("문서 리스트가 null이거나 비어있을 수 없습니다");
        }

        BulkRequest bulkRequest = new BulkRequest();

        for (GoodsDocument document : documents) {
            if (document == null || document.getGoodsName() == null) {
                continue;
            }

            String jsonString = "{\"goods_name\":\"" + document.getGoodsName() + "\"}";
            byte[] jsonBytes = jsonString.getBytes(StandardCharsets.UTF_8);

            IndexRequest indexRequest = new IndexRequest(INDEX_NAME)
                    .source(jsonBytes, XContentType.JSON);

            if (document.getId() != null && !document.getId().trim().isEmpty()) {
                indexRequest.id(document.getId());
            }

            bulkRequest.add(indexRequest);
        }

        if (bulkRequest.numberOfActions() > 0) {
            log.info("벌크 요청 전송 중... (문서 수: {})", bulkRequest.numberOfActions());

            BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);

            if (bulkResponse.hasFailures()) {
                String failureMessage = bulkResponse.buildFailureMessage();
                log.error("벌크 인덱싱 실패: {}", failureMessage);
                throw new RuntimeException("벌크 인덱싱 실패: " + failureMessage);
            }

            log.info("{}개의 문서 인덱싱 완료", bulkResponse.getItems().length);
        } else {
            log.warn("인덱싱할 유효한 문서가 없습니다");
        }
    }

    public List<GoodsDocument> findByGoodsName(String searchGoodsName) {
        try {
            SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery()
                    .should(QueryBuilders.matchQuery("goods_name.prefix", searchGoodsName).boost(2.0f))
                    .should(QueryBuilders.matchQuery("goods_name", searchGoodsName)
                            .fuzziness(Fuzziness.AUTO)
                            .prefixLength(1)
                            .boost(1.0f))
                    .minimumShouldMatch(1);

            searchSourceBuilder.query(boolQuery);
            searchRequest.source(searchSourceBuilder);

            SearchResponse searchResponse = restHighLevelClient.search(searchRequest,
                    RequestOptions.DEFAULT);

            return Arrays.stream(searchResponse.getHits().getHits())
                    .map(hit -> GoodsDocument.builder()
                            .id(hit.getId())
                            .goodsName((String) hit.getSourceAsMap().get("goods_name"))
                            .build())
                    .collect(Collectors.toList());

        } catch (IOException e) {
            log.error("Failed to search goods", e);
            throw new RuntimeException("Failed to search goods", e);
        }
    }
}