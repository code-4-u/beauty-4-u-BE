package com.beauty4u.backend.goods.query.mapper;

import com.beauty4u.backend.goods.query.dto.ReviewQueryDTO;
import com.beauty4u.backend.goods.query.dto.ReviewSortDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewQueryMapper {
    List<ReviewQueryDTO> findAllReview();

    List<ReviewQueryDTO> findAllReviewSort(ReviewSortDTO reviewSortDTO);
}
