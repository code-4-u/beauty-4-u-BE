package com.beauty4u.backend.goods.query.service;

import com.beauty4u.backend.goods.query.dto.GoodsQueryDTO;
import com.beauty4u.backend.goods.query.mapper.GoodsQueryMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class GoodsQueryService {
    private GoodsQueryMapper goodsQueryMapper;
    private final SqlSession sqlSession;

    // 전체 상품 조회
    public List<GoodsQueryDTO> findAllGoods() {
        List<GoodsQueryDTO> goodsDTOList = sqlSession.getMapper(GoodsQueryMapper.class).findAllGoods();
        return goodsDTOList;
    }

    // 브랜드 별 상품 조회
    public List<GoodsQueryDTO> findAllBrand(String brandCode) {
        return sqlSession.getMapper(GoodsQueryMapper.class).findAllBrand(brandCode);
    }
}
