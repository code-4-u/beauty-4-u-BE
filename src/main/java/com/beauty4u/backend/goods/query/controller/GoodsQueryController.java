package com.beauty4u.backend.goods.query.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.goods.query.dto.*;
import com.beauty4u.backend.opensearch.document.GoodsDocument;
import com.beauty4u.backend.goods.query.service.GoodsQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/goods")
@Tag(name = "Goods", description = "상품 조회 API")
@ConditionalOnProperty(name = "spring.opensearch.repositories.enabled", havingValue = "true", matchIfMissing = false)
public class GoodsQueryController {

    private final GoodsQueryService goodsQueryService;

    @GetMapping("/brands")
    @Operation(summary = "브랜드 목록 조회", description = "드롭다운용 브랜드 전체 목록을 조회한다.")
    public ResponseEntity<ApiResponse<List<BrandQueryDTO>>> findAllBrand() {
        List<BrandQueryDTO> brandList = goodsQueryService.findAllBrand();

        return ResponseUtil.successResponse(SuccessCode.BRAND_FIND_LIST_SUCCESS, brandList);
    }

    @GetMapping("/brands/{brandCode}")
    @Operation(summary = "선택 브랜드 상품 조회", description = "선택한 브랜드에 해당하는 상품 목록을 조회한다.")
    public ResponseEntity<ApiResponse<List<GoodsQueryDTO>>> findAllBrandGoods(@PathVariable String brandCode) {
        return ResponseUtil.successResponse(SuccessCode.BRAND_FIND_LIST_GOODS_SUCCESS, goodsQueryService.findAllBrandGoods(brandCode));
    }

    @GetMapping("/search")
    @Operation(summary = "전체 상품 목록 조회", description = "조건에 따른 상품을 검색한다. 파라미터가 없으면 전체 상품을 조회한다.")
    public ResponseEntity<ApiResponse<GoodsListResDTO>> findGoods(
            @RequestParam(required = false) String brandCode,
            @RequestParam(required = false) String goodsName,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "8") @Min(1) Long count
    ) {
        return ResponseUtil.successResponse(SuccessCode.GOODS_FIND_LIST_SUCCESS, goodsQueryService.findGoods(brandCode, goodsName, page, count));
    }

    @GetMapping("category/top")
    @Operation(summary = "상위 카테고리 내 하위 카테고리 목록 조회", description = "상위 카테고리에 해당하는 하위 카테고리를 드롭다운으로 조회한다.")
    public ResponseEntity<ApiResponse<List<SubCategoryDTO>>> findSubCategory(
            @RequestParam String topCategoryCode) {
        return ResponseUtil.successResponse(SuccessCode.GOODS_FIND_LIST_SUBCATEGORYLIST_SUCCESS, goodsQueryService.findSubCategory(topCategoryCode));
    }

    @GetMapping("/category/{topCategoryCode}")
    @Operation(summary = "상위 카테고리 상품과 해당 하위 카테고리 조회", description = "상위 카테고리에 해당하는 상품을 메인화면에서 조회한다.")
    public ResponseEntity<ApiResponse<CategoryDTO>> findsTopCategoryGoods(@PathVariable String topCategoryCode) {
        return ResponseUtil.successResponse(SuccessCode.GOODS_FIND_LIST_TOPCATEGORY_GOODS_AND_SUBCATEGORY, goodsQueryService.findTopCategoryGoods(topCategoryCode));
    }

    @GetMapping("/category/sub/{SubCategoryCode}")
    @Operation(summary = "하위 카테고리 상품 조회", description = "하위 카테고리에 해당하는 상품을 조회한다.")
    public ResponseEntity<ApiResponse<List<GoodsQueryDTO>>> findSubCategoryGoods(@PathVariable String SubCategoryCode) {
        return ResponseUtil.successResponse(SuccessCode.GOODS_FIND_LIST_SUBCATEGORYLIST_SUCCESS, goodsQueryService.findSubCategoryGoods(SubCategoryCode));
    }

    @GetMapping("/search/{searchGoodsName}")
    @Operation(summary = "상품명 검색", description = "엘라스틱 서치로 상품명을 검색한다.")
    public ResponseEntity<ApiResponse<List<GoodsDocument>>> searchGoods(@PathVariable String searchGoodsName) {
        return ResponseUtil.successResponse(SuccessCode.GOODS_FIND_ELASTICSEARCH_SUCCESS, goodsQueryService.searchGoods(searchGoodsName));
    }

    @Operation(summary = "상위 카테고리 조회", description = "상위 카테고리 조회")
    @GetMapping("/topCategory")
    public ResponseEntity<ApiResponse<List<TopCategoryResDTO>>> findTopCategoryList() {


        List<TopCategoryResDTO> topCategoryResDTOS = goodsQueryService.findTopCategoryList();

        return ResponseUtil.successResponse(SuccessCode.TOP_CATEGORY_LIST_FIND_SUCCESS, topCategoryResDTOS);
    }

    @Operation(summary = "필터링 조건에 따른 제품 조회", description = "필터링 조건에 따라서 제품 목록을 조회한다.")
    @GetMapping()
    public ResponseEntity<ApiResponse<List<GoodsQueryDTO>>> findFilterGoodsList(
            GoodsFilterReqDTO goodsFilterReqDTO
    ) {

        List<GoodsQueryDTO> goodsQueryDTOS
                = goodsQueryService.findFilterGoodsList(goodsFilterReqDTO);

        return ResponseUtil.successResponse(SuccessCode.GOODS_FIND_LIST_SUCCESS, goodsQueryDTOS);
    }

    @PostMapping("/index")
    @Operation(summary = "엘라스틱 서치 인덱스 생성", description = "DB 데이터를 엘라스틱 서치에 동기화한다.")
    public ResponseEntity<Void> indexGoods() {
        goodsQueryService.indexGoods();
        return ResponseEntity.ok().build();
    }
}