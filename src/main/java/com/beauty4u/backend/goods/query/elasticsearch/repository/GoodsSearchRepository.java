package com.beauty4u.backend.goods.query.elasticsearch.repository;

import com.beauty4u.backend.goods.query.elasticsearch.document.GoodsDocument;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsSearchRepository extends ElasticsearchRepository<GoodsDocument, String> {
    @Query("{\"wildcard\": {\"goods_name\": \"*?0*\"}}")
// 검색 단어가 포함된 상품 전체 검색
    List<GoodsDocument> findByGoodsName(String searchGoodsName);
}
