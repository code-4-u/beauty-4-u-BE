package com.beauty4u.backend.goods.query.service;

import com.beauty4u.backend.goods.query.dto.BrandQueryDTO;
import com.beauty4u.backend.goods.query.dto.CategoryDTO;
import com.beauty4u.backend.goods.query.dto.GoodsQueryDTO;
import com.beauty4u.backend.goods.query.dto.SubCategoryDTO;
import com.beauty4u.backend.goods.query.elasticsearch.document.GoodsDocument;
import com.beauty4u.backend.goods.query.elasticsearch.repository.GoodsSearchRepository;
import com.beauty4u.backend.goods.query.mapper.GoodsQueryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
@ConditionalOnProperty(name = "spring.data.elasticsearch.repositories.enabled", havingValue = "true", matchIfMissing = false)
public class GoodsQueryService {
    private final SqlSession sqlSession;
    private final GoodsSearchRepository goodsSearchRepository;
    private final GoodsQueryMapper goodsQueryMapper;

    // 전체 브랜드 조회
    public List<BrandQueryDTO> findAllBrand() {
        return sqlSession.getMapper(GoodsQueryMapper.class).findAllBrand();
    }

    // 조건별 상품 조회
    public List<GoodsQueryDTO> findGoods(String brandCode, String goodsName) {
        return goodsQueryMapper.findGoods(brandCode, goodsName);
    }

    // 상위 카테고리 내에 있는 하위 카테고리 조회
    public List<SubCategoryDTO> findSubCategory(String topCategoryCode) {
        return goodsQueryMapper.findSubCategory(topCategoryCode);
    }

    // 상위 카테고리 내에 있는 전체 상품 조회
    public CategoryDTO findTopCategoryGoods(String topCategoryCode) {
        GoodsQueryMapper mapper = sqlSession.getMapper(GoodsQueryMapper.class);

        List<GoodsQueryDTO> goods = mapper.findTopCategoryGoods(topCategoryCode);
        List<SubCategoryDTO> subCategory = mapper.findSubCategory(topCategoryCode);

        return new CategoryDTO(goods, subCategory);
    }

    // 하위 카테고리 내에 있는 전체 상품 조회
    public List<GoodsQueryDTO> findSubCategoryGoods(String subCategoryCode) {
        return goodsQueryMapper.findSubCategoryGoods(subCategoryCode);
    }

    // 엘라스틱서치로 상품명 검색
    public List<GoodsDocument> searchGoods(String searchGoodsName) {
        return goodsSearchRepository.findByGoodsName(searchGoodsName);
    }

    // DB 데이터를 엘라스틱서치 동기화
    @Transactional
    public void indexGoods() {
        // DB에서 모든 상품 데이터 조회
        List<GoodsQueryDTO> goods = sqlSession.getMapper(GoodsQueryMapper.class).findGoods(null, null);
        // GoodsDocument 로 변환
        List<GoodsDocument> documents = goods.stream()
                .map(GoodsDocument::from)
                .collect(Collectors.toList());
        // 엘라스틱서치에 저장
        goodsSearchRepository.saveAll(documents);

    }
}
