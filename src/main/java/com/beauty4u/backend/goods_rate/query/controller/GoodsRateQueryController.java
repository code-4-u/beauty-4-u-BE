package com.beauty4u.backend.goods_rate.query.controller;

import com.beauty4u.backend.common.aggregate.PeriodType;
import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.goods_rate.query.dto.GoodsRateQueryDTO;
import com.beauty4u.backend.goods_rate.query.dto.GoodsRateResDTO;
import com.beauty4u.backend.goods_rate.query.service.GoodsRateQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/goodsRate")
@Tag(name = "GoodsRate", description = "상품 매출 조회 API")
public class GoodsRateQueryController {

    private final GoodsRateQueryService goodsRateQueryService;

    @Operation(summary = "일일 매출률 조회", description = "메인 화면 일일 매출 상승,하강률을 조회한다.")
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<GoodsRateResDTO>> findGoodsRate(@RequestParam PeriodType periodType) {
        LocalDateTime endDate = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);
        LocalDateTime startDate = calculateStartDate(periodType, endDate).withHour(0).withMinute(0).withSecond(0);

        List<GoodsRateQueryDTO> goodsRateList = goodsRateQueryService.findGoodsRate(startDate, endDate, periodType);

        GoodsRateResDTO goodsRateResDTO = GoodsRateResDTO.builder()
                .increase(goodsRateList.stream()
                        .filter(rate -> "INCREASE".equals(rate.getRateType()))
                        .collect(Collectors.toList()))
                .decrease(goodsRateList.stream()
                        .filter(rate -> "DECREASE".equals(rate.getRateType()))
                        .collect(Collectors.toList()))
                .build();

        return ResponseUtil.successResponse(SuccessCode.GOODS_FIND_LIST_RATE_SUCCESS, goodsRateResDTO);
    }

    private LocalDateTime calculateStartDate(PeriodType periodType, LocalDateTime endDate) {
        return switch (periodType) {
            case DAILY -> endDate.minusDays(1);
            case WEEKLY -> endDate.minusWeeks(1);
            case MONTHLY -> endDate.minusMonths(1);
            case QUARTER -> endDate.minusMonths(3);
            case HALF -> endDate.minusMonths(6);
            case YEARLY -> endDate.minusYears(1);
        };
    }
}
