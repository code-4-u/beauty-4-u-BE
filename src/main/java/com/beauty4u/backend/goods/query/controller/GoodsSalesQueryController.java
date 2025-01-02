package com.beauty4u.backend.goods.query.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.goods.query.dto.*;
import com.beauty4u.backend.goods.query.service.GoodsSalesQueryService;
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
@Tag(name = "GoodsSales", description = "상품 매출 조회 API")
public class GoodsSalesQueryController {


    private final GoodsSalesQueryService goodsSalesQueryService;

    @Operation(summary = "제품의 전년 대비 같은 월 비교", description = "제품의 전년 대비 같은 월 매출액 비교")
    @GetMapping("/sales/{goodsCode}")
    public ResponseEntity<ApiResponse<GoodsSalesResDTO>> findSalesGoods(
            @PathVariable("goodsCode") String goodsCode,
            GoodsSalesFilterDTO goodsSalesFilterDTO
    ) {

        GoodsSalesResDTO goodsSalesResDTO
                = goodsSalesQueryService.findSalesGoods(goodsCode, goodsSalesFilterDTO);

        return ResponseUtil.successResponse(SuccessCode.GOODS_SALES_FIND_SUCCESS, goodsSalesResDTO);
    }

    @Operation(summary = "제품의 해당 연도 모든 매출액 조회", description = "제품의 해당 연도의 모든 매출액 목록을 조회한다.")
    @GetMapping("/sales/list/{goodsCode}")
    public ResponseEntity<ApiResponse<List<GoodsSalesMonthlyListResDTO>>> findSalesGoodsMonthlyList(
            @PathVariable("goodsCode") String goodsCode,
            GoodsSalesMonthlyListFilterDTO goodsSalesMonthlyListFilterDTO
    ) {

        List<GoodsSalesMonthlyListResDTO> goodsSalesMonthlyListResDTO
                = goodsSalesQueryService.findSalesGoodsMonthlyList(goodsCode, goodsSalesMonthlyListFilterDTO);

        return ResponseUtil.successResponse(SuccessCode.GOODS_SALES_MONTHLY_LIST_FIND_SUCCESS, goodsSalesMonthlyListResDTO);
    }

    @Operation(summary = "제품의 해당 연월에 대한 연령별 매출액 조회", description = "제품의 해당 연월에 대한 연령별 매출액을 전연도와 비교해서 조회한다.")
    @GetMapping("/sales/age/{goodsCode}")
    public ResponseEntity<ApiResponse<List<GoodsSalesAgeListResDTO>>> findSalesGoodsAge(
            @PathVariable String goodsCode,
            GoodsSalesAgeListFilterDTO goodsSalesAgeListFilterDTO
    ) {

        List<GoodsSalesAgeListResDTO> goodsSalesAgeListResDTO
                = goodsSalesQueryService.findSalesGoodsAge(goodsCode, goodsSalesAgeListFilterDTO);

        return ResponseUtil.successResponse(SuccessCode.GOODS_SALES_AGE_LIST_FIND_SUCCESS, goodsSalesAgeListResDTO);
    }
}
