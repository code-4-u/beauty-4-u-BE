package com.beauty4u.backend.goods.query.service;

import com.beauty4u.backend.goods.query.dto.ReviewQueryDTO;
import com.beauty4u.backend.goods.query.dto.ReviewSortDTO;
import com.beauty4u.backend.goods.query.mapper.ReviewQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
@ConditionalOnProperty(name = "spring.opensearch.repositories.enabled", havingValue = "true", matchIfMissing = false)
public class ReviewQueryService {
    private final ReviewQueryMapper reviewQueryMapper;

    // 리뷰 전체 조회
    public List<ReviewQueryDTO> findAllReview() {
        return reviewQueryMapper.findAllReview();
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
