package com.beauty4u.backend.analysis.query.controller;

import com.beauty4u.backend.analysis.command.domain.aggregate.Analysis;
import com.beauty4u.backend.analysis.query.dto.request.*;
import com.beauty4u.backend.analysis.query.dto.response.*;
import com.beauty4u.backend.analysis.query.service.AnalysisQueryService;
import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.shaded.com.google.protobuf.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name="Analysis", description = "분석 대시보드 그래프 관련 값 조회 API")
@RequestMapping("/api/v1/analysis")
public class AnalysisQueryController {

    private final AnalysisQueryService analysisQueryService;

    @Operation(summary = "고객 비율 분석 데이터 조회")
    @GetMapping("/age-group-radio")
    public ResponseEntity<ApiResponse<AnalysisAgeGroupRadioResDTO>> selectAnalysisAgeGroupRadio() {

        /* 고객 비율 분석 데이터 조회 */
        AnalysisAgeGroupRadioResDTO object = analysisQueryService.selectAnalysisAgeGroupRadio();

        return ResponseUtil.successResponse(SuccessCode.ANAL_FIND_AGE_GROUP_RADIO_SUCCESS, object);
    }

    @Operation(summary = "연령별 구매 비율 데이터 조회")
    @GetMapping("/purchases-by-age")
    public ResponseEntity<ApiResponse<AnalysisPurchasesByAgeResDTO>> selectAnalysisPurchasesByAge
            (@RequestBody AnalysisCircleGraphReqDTO analysisCircleGraphRequestDTO) {

        /* 연령별 구매 비율 데이터 조회 */
        AnalysisPurchasesByAgeResDTO object =
                analysisQueryService.selectAnalysisPurchasesByAge(
                        analysisCircleGraphRequestDTO.getStartDate(),
                        analysisCircleGraphRequestDTO.getEndDate()
                );

        return ResponseUtil.successResponse(SuccessCode.ANAL_FIND_PUR_BY_AGE_SUCCESS, object);
    }

    @Operation(summary = "연령별 브랜드 별 구매 횟수 조회")
    @GetMapping("/purchases-by-brand")
    public ResponseEntity<ApiResponse<List<AnalysisPurchasesByBrandResDTO>>> selectAnalysisPurchasesByBrand(
            @RequestBody AnalysisStickGraphReqDTO analysisStickGraphReqDTO){

        /* 연령별 브핸드 별 구매 횟수 조회 */
        List<AnalysisPurchasesByBrandResDTO> list =
                analysisQueryService.selectAnalysisPurchasesByBrand(
                        analysisStickGraphReqDTO.getStartAge(),
                        analysisStickGraphReqDTO.getEndAge(),
                        analysisStickGraphReqDTO.getStartDate(),
                        analysisStickGraphReqDTO.getEndDate()
                );

        return ResponseUtil.successResponse(SuccessCode.ANAL_FIND_PUR_BY_BRAND_SUCCESS, list);
    }

    @Operation(summary = "해당 브랜드의 제품별 구매 횟수 조회")
    @GetMapping("/purchases-by-brand-product")
    public ResponseEntity<ApiResponse<List<AnalysisPurchasesByBrandProductResDTO>>> selectAnalysisPurchasesByBrandProduct(
            @RequestBody AnalysisStickGraphProductReqDTO analysisStickGraphProductReqDTO) {

        /* 해당 브랜드의 제품별 구매 횟수 조회 */
        List<AnalysisPurchasesByBrandProductResDTO> list =
                analysisQueryService.selectAnalysisPurchasesByBrandProduct(
                        analysisStickGraphProductReqDTO.getBrandCode(),
                        analysisStickGraphProductReqDTO.getStartAge(),
                        analysisStickGraphProductReqDTO.getEndAge(),
                        analysisStickGraphProductReqDTO.getStartDate(),
                        analysisStickGraphProductReqDTO.getEndDate()
                );

        return ResponseUtil.successResponse(SuccessCode.ANAL_FIND_PUR_BY_BRAND_PRODUCT_SUCCESS, list);
    }

    @Operation(summary = "등급별 구매 비율 조회")
    @GetMapping("/grade-group-radio")
    public ResponseEntity<ApiResponse<AnalysisGradeGroupRadioResDTO>> selectAnalysisGradeGroupRadio(
            @RequestBody AnalysisCircleGraphReqDTO analysisCircleGraphReqDTO) {

        AnalysisGradeGroupRadioResDTO object = analysisQueryService.selectAnalysisGradeGroupRadio(
                analysisCircleGraphReqDTO.getStartDate(),
                analysisCircleGraphReqDTO.getEndDate()
        );

        return ResponseUtil.successResponse(SuccessCode.ANAL_FIND_GRADE_GROUP_RADIO_SUCCESS, object);
    }

    @Operation(summary = "등급별 브랜드 구매 횟수 조회")
    @GetMapping("/grade-by-brand")
    public ResponseEntity<ApiResponse<List<AnalysisGradeByBrandResDTO>>> selectAnalysisGradeByBrand(
            @RequestBody AnalysisGradeCircleReqDTO analysisGradeCircleReqDTO) {

        List<AnalysisGradeByBrandResDTO> list =
                analysisQueryService.selectAnalysisGradeByBrand(
                        analysisGradeCircleReqDTO.getGrade(),
                        analysisGradeCircleReqDTO.getStartDate(),
                        analysisGradeCircleReqDTO.getEndDate()
                );

        return ResponseUtil.successResponse(SuccessCode.ANAL_FIND_GRADE_BY_BRAND_SUCCESS);
    }

    @Operation(summary = "등급별 브랜드 별 제품 구매 횟수 조회")
    @GetMapping("/grade-by-brand-product")
    public ResponseEntity<ApiResponse<List<AnalysisGradeByBrandProductResDTO>>> selectAnalysisGradeByBrandProduct(
            @RequestBody AnalysisGradeStickGraphReqDTO analysisGradeStickGraphReqDTO) {

        List<AnalysisGradeByBrandProductResDTO> list =
                analysisQueryService.selectAnalysisGradeByBrandProduct(
                        analysisGradeStickGraphReqDTO.getBrandCode(),
                        analysisGradeStickGraphReqDTO.getGrade(),
                        analysisGradeStickGraphReqDTO.getStartDate(),
                        analysisGradeStickGraphReqDTO.getEndDate()
                );

        return ResponseUtil.successResponse(SuccessCode.ANAL_FIND_GRADE_SALES_BY_BRAND_PRODUCT_SUCCESS, list);
    }

    @Operation(summary = "연령별 매출 비율 조회")
    @GetMapping("/age-sales-radio")
    public ResponseEntity<ApiResponse<AnalysisAgeSalesRadioResDTO>> selectAnalysisAgeSalesRadio(
            @RequestBody AnalysisCircleGraphReqDTO analysisCircleGraphReqDTO) {

        AnalysisAgeSalesRadioResDTO object = analysisQueryService.selectAnalysisAgeSalesRadio(
                analysisCircleGraphReqDTO.getStartDate(),
                analysisCircleGraphReqDTO.getEndDate()
        );

        return ResponseUtil.successResponse(SuccessCode.ANAL_FIND_GRADE_BY_BRAND_PRODUCT_SUCCESS, object);
    }

    @Operation(summary = "연령별 브랜드 매출 조회")
    @GetMapping("/age-sales-by-brand")
    public ResponseEntity<ApiResponse<List<AnalysisAgeSalesByBrandResDTO>>> selectAnalysisAgeSalesByBrand(
        @RequestBody AnalysisStickGraphReqDTO analysisStickGraphReqDTO) {

        List<AnalysisAgeSalesByBrandResDTO> list =
                analysisQueryService.selectAnalysisAgeSalesByBrand(
                        analysisStickGraphReqDTO.getStartAge(),
                        analysisStickGraphReqDTO.getEndAge(),
                        analysisStickGraphReqDTO.getStartDate(),
                        analysisStickGraphReqDTO.getEndDate()
                );

        return ResponseUtil.successResponse(SuccessCode.ANAL_FIND_AGE_SALES_BY_BRAND_SUCCESS, list);
    }

    @Operation(summary = "연령별 브랜드 제품별 매출 조회")
    @GetMapping("/age-sales-by-brand-product")
    public ResponseEntity<ApiResponse<List<AnalysisAgeSalesByBrandProductResDTO>>> selectAnalysisAgeSalesByBrandProduct(
            @RequestBody AnalysisStickGraphProductReqDTO analysisStickGraphProductReqDTO) {

        List<AnalysisAgeSalesByBrandProductResDTO> list =
                analysisQueryService.selectAnalysisAgeSalesByBrandProduct(
                        analysisStickGraphProductReqDTO.getBrandCode(),
                        analysisStickGraphProductReqDTO.getStartAge(),
                        analysisStickGraphProductReqDTO.getEndAge(),
                        analysisStickGraphProductReqDTO.getStartDate(),
                        analysisStickGraphProductReqDTO.getEndDate()
                );

        return ResponseUtil.successResponse(SuccessCode.ANAL_FIND_AGE_SALES_BY_BRAND_PRODUCT_SUCCESS, list);
    }

    @Operation(summary="등급별 매출 비율 조회")
    @GetMapping("/grade-sales-radio")
    public ResponseEntity<ApiResponse<AnalysisGradeSalesRadioDTO>> selectAnalysisGradeSalesRadio(
            @RequestBody AnalysisGradeCircleReqDTO analysisGradeCircleReqDTO) {

        AnalysisGradeSalesRadioDTO object = analysisQueryService.selectAnalysisGradeSalesRadio(
                analysisGradeCircleReqDTO.getStartDate(),
                analysisGradeCircleReqDTO.getEndDate()
        );

        return ResponseUtil.successResponse(SuccessCode.ANAL_FIND_GRADE_SALES_RADIO_SUCCESS, object);
    }

    @Operation(summary="등급별 브랜드 매출 조회")
    @GetMapping("/grade-sales-by-brand")
    public ResponseEntity<ApiResponse<List<AnalysisGradeSalesByBrandResDTO>>> selectAnalysisGradeSalesByBrand(
            @RequestBody AnalysisGradeBrandCircleReqDTO analysisGradeBrandCircleReqDTO) {

        List<AnalysisGradeSalesByBrandResDTO> list =
                analysisQueryService.selectAnalysisGradeSalesByBrand(
                        analysisGradeBrandCircleReqDTO.getBrandCode(),
                        analysisGradeBrandCircleReqDTO.getStartDate(),
                        analysisGradeBrandCircleReqDTO.getEndDate()
                );

        return ResponseUtil.successResponse(SuccessCode.ANAL_FIND_GRADE_SALES_BY_BRAND_SUCCESS, list);
    }

    @Operation(summary="등급별 브랜드별 제품 매출 조회")
    @GetMapping("/grade-sales-by-brand-product")
    public ResponseEntity<ApiResponse<List<AnalysisGradeSalesByBrandProductResDTO>>> selectAnalysisGradeSalesByBrandProduct(
            @RequestBody AnalysisGradeStickGraphReqDTO analysisGradeStickGraphReqDTO) {

        List<AnalysisGradeSalesByBrandProductResDTO> list =
                analysisQueryService.selectAnalysisGradeSalesByBrandProduct(
                        analysisGradeStickGraphReqDTO.getBrandCode(),
                        analysisGradeStickGraphReqDTO.getGrade(),
                        analysisGradeStickGraphReqDTO.getStartDate(),
                        analysisGradeStickGraphReqDTO.getEndDate()
                );

        return ResponseUtil.successResponse(SuccessCode.ANAL_FIND_GRADE_SALES_BY_BRAND_PRODUCT_SUCCESS, list);
    }
}
