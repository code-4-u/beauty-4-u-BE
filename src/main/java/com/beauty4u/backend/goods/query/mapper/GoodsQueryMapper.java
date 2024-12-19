package com.beauty4u.backend.goods.query.mapper;

import com.beauty4u.backend.goods.query.dto.BrandQueryDTO;
import com.beauty4u.backend.goods.query.dto.GoodsQueryDTO;
import com.beauty4u.backend.goods.query.dto.SubCategoryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsQueryMapper {
    // 전체 브랜드 조회
    List<BrandQueryDTO> findAllBrand();

    // 조건별 상품 목록 조회
    List<GoodsQueryDTO> findGoods(
            @Param("brandCode") String brandCode,
            @Param("goodsName") String goodsName
    );

    // 상위 카테고리에 내에 있는 하위 카테고리 조회
    List<SubCategoryDTO> findSubCategory(String topCategoryCode);

    // 상위 카테고리에 해당하는 상품 메인화면에 전체 조회
    List<GoodsQueryDTO> findTopCategoryGoods(@Param("topCategoryCode") String topCategoryCode);

    // 하위 카테고리에 해당하는 상품 조회
    List<GoodsQueryDTO> findSubCategoryGoods(String subCategoryCode);

    // 선택 브랜드 내 상품 목록 조회
    List<GoodsQueryDTO> findAllBrandGoods(@Param("brandCode") String brandCode);

    List<GoodsQueryDTO> findFilterGoodsList(
            @Param("topCategoryCode") String topCategoryCode,
            @Param("subCategoryCode") String subCategoryCode,
            @Param("minPrice") Long minPrice,
            @Param("maxPrice") Long maxPrice,
            @Param("sort") String sort,
            @Param("order") String order,
            @Param("offset") Long offset,
            @Param("count") Long count
    );
}