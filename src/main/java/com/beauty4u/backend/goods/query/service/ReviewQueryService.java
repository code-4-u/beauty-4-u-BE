package com.beauty4u.backend.goods.query.service;

import com.beauty4u.backend.goods.query.dto.ReviewQueryDTO;
import com.beauty4u.backend.goods.query.dto.ReviewSortDTO;
import com.beauty4u.backend.elasticsearch.document.ReviewDocument;
import com.beauty4u.backend.elasticsearch.repository.ReviewSearchRepository;
import com.beauty4u.backend.goods.query.mapper.ReviewQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
@ConditionalOnProperty(name = "spring.data.elasticsearch.repositories.enabled", havingValue = "true", matchIfMissing = false)
public class ReviewQueryService {
    private final ReviewQueryMapper reviewQueryMapper;
    private final ReviewSearchRepository reviewSearchRepository;

    // 리뷰 전체 조회
    public List<ReviewQueryDTO> findAllReview() {
        return reviewQueryMapper.findAllReview();
    }

    // 엘라스틱 서치로 리뷰 검색
    public List<ReviewDocument> searchReview(String searchReview) {
        return reviewSearchRepository.findByReview(searchReview);
    }

    // DB 데이터를 엘라스틱서치 동기화
    @Transactional
    public void indexReview() {
        // DB에서 모든 리뷰 데이터 조회
        List<ReviewQueryDTO> reviews = reviewQueryMapper.findAllReview();
        // ReviewDocument로 변환
        List<ReviewDocument> documents = reviews.stream()
                .map(ReviewDocument::from)
                .collect(Collectors.toList());
        // 엘라스틱서치 저장
        reviewSearchRepository.saveAll(documents);
    }

    // 리뷰 정렬 조회
    public List<ReviewQueryDTO> findAllReviewSort(ReviewSortDTO reviewSortDTO) {
        return reviewQueryMapper.findAllReviewSort(reviewSortDTO);
    }

    // 리뷰 기간별 조회
    public List<ReviewQueryDTO> findAllReviewByDate(LocalDateTime startDate, LocalDateTime endDate) {
        return reviewQueryMapper.findAllReviewByDate(startDate, endDate);
    }

    // 평점 리뷰 조회
    public List<ReviewQueryDTO> findAllReviewByScore(Integer searchScore) {
        return reviewQueryMapper.findAllReviewByScore(searchScore);
    }
}
