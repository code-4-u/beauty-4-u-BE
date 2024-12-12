package com.beauty4u.backend.goods.query.service;

import com.beauty4u.backend.goods.query.dto.ReviewQueryDTO;
import com.beauty4u.backend.goods.query.elasticsearch.document.ReviewDocument;
import com.beauty4u.backend.goods.query.elasticsearch.repository.ReviewSearchRepository;
import com.beauty4u.backend.goods.query.mapper.ReviewQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
@ConditionalOnProperty(name = "elasticsearch.repositories.enabled", havingValue = "true", matchIfMissing = false)
public class ReviewQueryService {
    private final ReviewQueryMapper reviewQueryMapper;
    private final ReviewSearchRepository reviewSearchRepository;

    public List<ReviewQueryDTO> findAllReview(List<Sort.Order> orders) {
        return reviewQueryMapper.findAllReview(orders);
    }

    // 엘라스틱 서치로 리뷰 검색
    public List<ReviewDocument> searchReview(String searchReview) {
        return reviewSearchRepository.findByReview(searchReview);
    }

    // DB 데이터를 엘라스틱서치 동기화
    @Transactional
    public void indexReview() {
        // DB에서 모든 리뷰 데이터 조회
        List<ReviewQueryDTO> reviews = reviewQueryMapper.findAllReview(null);
        // ReviewDocument로 변환
        List<ReviewDocument> documents = reviews.stream()
                .map(ReviewDocument::from)
                .collect(Collectors.toList());
        // 엘라스틱서치 저장
        reviewSearchRepository.saveAll(documents);
    }
}
