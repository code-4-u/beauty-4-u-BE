package com.beauty4u.backend.goods.query.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.goods.query.dto.*;
import com.beauty4u.backend.goods.query.mapper.CategoryQueryMapper;
import com.beauty4u.backend.goods.query.mapper.GoodsQueryMapper;
import com.beauty4u.backend.goods.query.opensearch.document.GoodsDocument;
import com.beauty4u.backend.goods.query.opensearch.repository.GoodsSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.opensearch.client.RestHighLevelClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class GoodsQueryService {

    private final SqlSession sqlSession;
    private final GoodsQueryMapper goodsQueryMapper;
    private final CategoryQueryMapper categoryQueryMapper;
    private final RestHighLevelClient opensearchClient;
    private final GoodsSearchRepository goodsSearchRepository;

    // 전체 브랜드 조회
    public List<BrandQueryDTO> findAllBrand() {
        return goodsQueryMapper.findAllBrand();
    }

    // 선택 브랜드 내 상품 목록 조회
    public List<GoodsQueryDTO> findAllBrandGoods(String brandCode) {
        return goodsQueryMapper.findAllBrandGoods(brandCode);
    }

    // 조건별 상품 조회
    public GoodsListResDTO findGoods(String brandCode, String goodsName, int page, Long count) {
        Long offset = (long) page * count;

        List<GoodsQueryDTO> goodsList = goodsQueryMapper.findGoods(
                brandCode,
                goodsName,
                offset,
                count
        );

        Long totalCount = goodsQueryMapper.findGoodsTotalCount(brandCode, goodsName);

        GoodsListResDTO goodsListResDTO = new GoodsListResDTO();
        goodsListResDTO.setTotalCount(totalCount);
        goodsListResDTO.setGoodsList(goodsList);

        return goodsListResDTO;
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

    // OpenSearch로 상품명 검색
    public List<GoodsDocument> searchGoods(String searchGoodsName) {
        return goodsSearchRepository.findByGoodsName(searchGoodsName);
    }

    // DB 데이터를 OpenSearch에 동기화
    @Transactional
    public void indexGoods() throws IOException {
        // DB에서 모든 상품 데이터 조회
        List<GoodsQueryDTO> goods = goodsQueryMapper.findGoods(null, null, null, null);

        log.debug("Found {} goods from database", goods.size()); // 디버깅용 로그

        if (goods.isEmpty()) {
            log.warn("No goods found in database");
            return;
        }

        // GoodsDocument로 변환
        List<GoodsDocument> documents = goods.stream()
                .filter(g -> g != null && g.getGoodsName() != null && !g.getGoodsName().trim().isEmpty())
                .map(g -> {
                    GoodsDocument doc = new GoodsDocument();
                    doc.setGoodsName(g.getGoodsName());
                    return doc;
                })
                .collect(Collectors.toList());

        log.debug("Converted {} goods to documents", documents.size()); // 디버깅용 로그

        if (documents.isEmpty()) {
            log.warn("No valid documents to index after conversion");
            return;
        }

        // 오픈서치 저장
        goodsSearchRepository.saveAll(documents);

        log.info("Successfully completed indexing {} documents", documents.size());
    }

    public List<TopCategoryResDTO> findTopCategoryList() {

        return categoryQueryMapper.findAllTopCategoryList();
    }

    // 필터링 조건에 따른 제품 조회
    public List<GoodsQueryDTO> findFilterGoodsList(GoodsFilterReqDTO goodsFilterReqDTO) {

        Long offset = (goodsFilterReqDTO.getPage() - 1) * goodsFilterReqDTO.getCount();

        List<GoodsQueryDTO> goodsQueryDTOS = null;

        try {
            goodsQueryDTOS = goodsQueryMapper.findFilterGoodsList(
                    goodsFilterReqDTO.getSearchWord(),
                    goodsFilterReqDTO.getTopCategoryCode(),
                    goodsFilterReqDTO.getSubCategoryCode(),
                    goodsFilterReqDTO.getMinPrice(),
                    goodsFilterReqDTO.getMaxPrice(),
                    goodsFilterReqDTO.getSort(),
                    goodsFilterReqDTO.getOrder(),
                    offset,
                    goodsFilterReqDTO.getCount()
            );
        } catch (Exception e) {
            throw new CustomException(ErrorCode.NOT_FOUND_GOODS_LIST);
        }

        return goodsQueryDTOS;
    }
}
