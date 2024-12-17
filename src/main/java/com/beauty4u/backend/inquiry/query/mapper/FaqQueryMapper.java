package com.beauty4u.backend.inquiry.query.mapper;

import com.beauty4u.backend.inquiry.query.dto.FaqDetailResDTO;
import com.beauty4u.backend.inquiry.query.dto.FaqListDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FaqQueryMapper {

    List<FaqListDTO> findFaqList(
            @Param("faqTitle") String faqTitle,
            @Param("publishStatus") String publishStatus,
            @Param("sort") String sort,
            @Param("order") String order,
            @Param("offset") Long offset,
            @Param("count") Long count);

    FaqDetailResDTO findFaqDetail(
            @Param("faqId") Long faqId);

    Long findFaqListTotalCount(
            @Param("faqTitle") String faqTitle);
}
