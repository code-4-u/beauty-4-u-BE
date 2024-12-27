package com.beauty4u.backend.elasticsearch.repository;

import com.beauty4u.backend.elasticsearch.document.ReviewDocument;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ConditionalOnProperty(name = "spring.data.elasticsearch.repositories.enabled", havingValue = "true", matchIfMissing = false)
public interface ReviewSearchRepository extends ElasticsearchRepository<ReviewDocument, String> {
    @Query("{\"wildcard\": {\"review_content\": \"*?0*\"}}")
    // 검색 단어가 포함된 리뷰 전체 검색
    List<ReviewDocument> findByReview(String searchReview);
}
