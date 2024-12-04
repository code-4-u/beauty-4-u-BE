package com.beauty4u.backend.goods.query.controller;

import com.beauty4u.backend.common.success.CustomSuccessHandler;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.goods.query.dto.GoodsQueryDTO;
import com.beauty4u.backend.goods.query.service.GoodsQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/goods")
@Tag(name="Goods", description = "상품 조회 API")
public class GoodsQueryController {

    private final GoodsQueryService goodsQueryService;
    private final CustomSuccessHandler customSuccessHandler;

    @GetMapping("/list")
    @Operation(summary = "상품 목록 조회", description = "전체 상품 목록을 조회한다.")
    public ResponseEntity<List<GoodsQueryDTO>> findAllGoods(){
        List<GoodsQueryDTO> goodsDTOList = goodsQueryService.findAllGoods();

        return ResponseEntity.ok(goodsDTOList);
    }

    @GetMapping("/brand/{brandCode}")
    @Operation(summary = "브랜드 별 상품 조회", description = "브랜드 별 상품을 조회한다.")
    public ResponseEntity<List<GoodsQueryDTO>> findAllBrand(@PathVariable String brandCode){
        List<GoodsQueryDTO> goodsBrandList = goodsQueryService.findAllBrand(brandCode);

        return ResponseEntity.ok(goodsBrandList);
    }
}