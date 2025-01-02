package com.beauty4u.backend.inquiry.query.mapper;

import com.beauty4u.backend.inquiry.query.dto.InquiryReplyResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface InquiryReplyQueryMapper {

    InquiryReplyResDTO findInquiryReplyDetail(
            @Param("inquiryId") Long inquiryId
    );
}
