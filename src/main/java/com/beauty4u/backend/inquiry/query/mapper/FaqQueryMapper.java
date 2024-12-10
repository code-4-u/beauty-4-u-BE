package com.beauty4u.backend.inquiry.query.mapper;

import com.beauty4u.backend.inquiry.query.dto.FaqListResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FaqQueryMapper {

    List<FaqListResDTO> findFaqList(
            @Param("offset") Long offset,
            @Param("count") Long count);
}
