package com.beauty4u.backend.template.query.service;

import com.beauty4u.backend.template.query.dto.TemplateQueryDTO;
import com.beauty4u.backend.template.query.mapper.TemplateQueryMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class TemplateQueryService {
    private final SqlSession sqlSession;

    public List<TemplateQueryDTO> findAllTemplate() {
        return sqlSession.getMapper(TemplateQueryMapper.class).findAllTemplate();
    }
}
