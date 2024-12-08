package com.beauty4u.backend.template.query.mapper;

import com.beauty4u.backend.template.query.dto.TemplateQueryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TemplateQueryMapper {
    // 전체 템플릿 목록 조회
    List<TemplateQueryDTO> findAllTemplate();
}
