package com.beauty4u.backend.goods.query.mapper;

import com.beauty4u.backend.goods.query.dto.ReviewQueryDTO;
import com.beauty4u.backend.goods.query.dto.ReviewSortDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ReviewQueryMapper {
    // 리뷰 전체 조회
    List<ReviewQueryDTO> findAllReview();

    // 리뷰 정렬
    List<ReviewQueryDTO> findAllReviewSort(ReviewSortDTO reviewSortDTO);

    // 리뷰 기간별 조회
    List<ReviewQueryDTO> findAllReviewByDate(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
