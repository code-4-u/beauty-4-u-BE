package com.beauty4u.backend.goods.query.mapper;

import com.beauty4u.backend.goods.query.dto.ReviewQueryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Sort;

import java.util.List;

@Mapper
public interface ReviewQueryMapper {
    List<ReviewQueryDTO> findAllReview(List<Sort.Order> orders);
}
