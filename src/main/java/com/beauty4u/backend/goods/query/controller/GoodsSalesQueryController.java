package com.beauty4u.backend.goods.query.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.goods.query.dto.GoodsSalesFilterDTO;
import com.beauty4u.backend.goods.query.dto.GoodsSalesResDTO;
import com.beauty4u.backend.goods.query.service.GoodsSalesQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/goods")
@Tag(name = "GoodsSales", description = "상품 매출 조회 API")
public class GoodsSalesQueryController {


    private final GoodsSalesQueryService goodsSalesQueryService;

    @Operation(summary = "제품의 전년 대비 같은 월 비교", description = "제품의 전년 대비 같은 월 매출액 비교")
    @GetMapping("/sales/{goodsCode}")
    public ResponseEntity<ApiResponse<GoodsSalesResDTO>> findSalesGoods(
            @PathVariable String goodsCode,
            GoodsSalesFilterDTO goodsSalesFilterDTO
    ) {

        GoodsSalesResDTO goodsSalesResDTO
                = goodsSalesQueryService.findSalesGoods(goodsCode, goodsSalesFilterDTO);

        return ResponseUtil.successResponse(SuccessCode.GOODS_SALES_FIND_SUCCESS, goodsSalesResDTO);
    }
}
