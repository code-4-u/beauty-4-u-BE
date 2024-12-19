package com.beauty4u.backend.goods_rate.query.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.goods.query.dto.GoodsQueryDTO;
import com.beauty4u.backend.goods_rate.query.dto.GoodsRateQueryDTO;
import com.beauty4u.backend.goods_rate.query.service.GoodsRateQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/goodsRate")
@Tag(name="GoodsRate", description = "상품 매출 조회 API")
public class GoodsRateQueryController {

    private final GoodsRateQueryService goodsRateQueryService;

    @Operation(summary = "매출 상승률 조회", description = "메인 화면 매출 상승률을 조회한다.")
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<GoodsRateQueryDTO>>> findGoodsRateIncrease() {

        List<GoodsRateQueryDTO> goodsRateList = goodsRateQueryService.findGoodsRateIncrease();

        return ResponseUtil.successResponse(SuccessCode.GOODS_FIND_LIST_INCREASE_RATE_SUCCESS, goodsRateList);
    }

}
