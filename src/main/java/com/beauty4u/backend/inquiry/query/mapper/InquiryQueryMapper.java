package com.beauty4u.backend.inquiry.query.mapper;

import com.beauty4u.backend.inquiry.query.dto.InquiryDetailResDTO;
import com.beauty4u.backend.inquiry.query.dto.InquiryListDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InquiryQueryMapper {

    List<InquiryListDTO> findInquiryList(
            @Param("inquiryTitle") String inquiryTitle,
            @Param("publishStatus") String publishStatus,
            @Param("sort") String sort,
            @Param("order") String order,
            @Param("offset") Long offset,
            @Param("count") Long count);

    Long findInquiryListTotalCount(
            @Param("inquiryTitle") String inquiryTitle,
            @Param("publishStatus") String publishStatus,
            @Param("sort") String sort,
            @Param("order") String order);

    InquiryDetailResDTO findInquiryDetail(
            @Param("inquiryId") Long inquiryId);
}
