package com.beauty4u.backend.elasticsearch.repository;

import com.beauty4u.backend.elasticsearch.document.GoodsDocument;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ConditionalOnProperty(name = "spring.data.elasticsearch.repositories.enabled", havingValue = "true", matchIfMissing = false)
public interface GoodsSearchRepository extends ElasticsearchRepository<GoodsDocument, String> {
    @Query("""
            {
                       "bool": {
                         "should": [
                           {
                             "match": {
                               "goods_name.prefix": {
                                 "query": "?0",
                                 "boost": 2.0
                               }
                             }
                           },
                           {
                             "match": {
                               "goods_name": {
                                 "query": "?0",
                                 "boost": 1.0,
                                 "fuzziness": "AUTO",
                                 "prefix_length": 1
                               }
                             }
                           },
                           {
                             "wildcard": {
                               "goods_name": {
                                 "value": "*?0*",
                                 "boost": 0.5
                               }
                             }
                           }
                         ],
                         "minimum_should_match": 1
                       }
                     }
            """)
// 검색 단어가 포함된 상품 전체 검색
    List<GoodsDocument> findByGoodsName(String searchGoodsName);
}
