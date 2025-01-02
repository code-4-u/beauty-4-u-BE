package com.beauty4u.backend.goods.query.mapper;

import com.beauty4u.backend.goods.query.dto.TopCategoryResDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryQueryMapper {

    List<TopCategoryResDTO> findAllTopCategoryList();
}
