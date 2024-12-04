package com.beauty4u.backend.goods.query.controller;

import com.beauty4u.backend.common.success.CustomSuccessHandler;
import com.beauty4u.backend.goods.query.dto.BrandQueryDTO;
import com.beauty4u.backend.goods.query.dto.GoodsQueryDTO;
import com.beauty4u.backend.goods.query.service.GoodsQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/goods")
@Tag(name = "Goods", description = "상품 조회 API")
public class GoodsQueryController {

    private final GoodsQueryService goodsQueryService;
    private final CustomSuccessHandler customSuccessHandler;

    @GetMapping("/brands")
    @Operation(summary = "브랜드 목록 조회", description = "드롭다운용 브랜드 전체 목록을 조회한다.")
    public ResponseEntity<List<BrandQueryDTO>> findAllBrand() {
        List<BrandQueryDTO> brandList = goodsQueryService.findAllBrand();

        return ResponseEntity.ok(brandList);
    }

    @GetMapping("/search")
    @Operation(summary = "상품 목록 조회", description = "조건에 따른 상품을 검색한다. 파라미터가 없으면 전체 상품을 조회한다.")
    public ResponseEntity<List<GoodsQueryDTO>> findGoods(
            @RequestParam(required = false) String brandCode,
            @RequestParam(required = false) String goodsName
    ) {
        return ResponseEntity.ok(goodsQueryService.findGoods(brandCode, goodsName));
    }
}