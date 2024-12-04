package com.beauty4u.backend.goods.query.service;

import com.beauty4u.backend.goods.query.dto.BrandQueryDTO;
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

    // 전체 브랜드 조회
    public List<BrandQueryDTO> findAllBrand() {
        return sqlSession.getMapper(GoodsQueryMapper.class).findAllBrand();
    }

    // 전체 상품 조회
    public List<GoodsQueryDTO> findGoods(String brandcode, String goodsName) {
        return sqlSession.getMapper(GoodsQueryMapper.class).findGoods();
    }
}
