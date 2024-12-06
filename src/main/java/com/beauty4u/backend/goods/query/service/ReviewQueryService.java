package com.beauty4u.backend.goods.query.service;

import com.beauty4u.backend.goods.query.dto.ReviewQueryDTO;
import com.beauty4u.backend.goods.query.mapper.ReviewQueryMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ReviewQueryService {
    private final SqlSession sqlSession;
    private final ReviewQueryMapper reviewQueryMapper;

    public List<ReviewQueryDTO> findAllReview() {
        return sqlSession.getMapper(ReviewQueryMapper.class).findAllReview();
    }
}
