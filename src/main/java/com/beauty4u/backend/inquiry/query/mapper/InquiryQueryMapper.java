package com.beauty4u.backend.inquiry.query.mapper;

import com.beauty4u.backend.inquiry.query.dto.InquiryListResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InquiryQueryMapper {

    List<InquiryListResDTO> findInquiryList(
            @Param("offset") Long offset,
            @Param("count") Long count);
}
