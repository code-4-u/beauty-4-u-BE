package com.beauty4u.backend.goods.query.controller;

import com.beauty4u.backend.goods.query.dto.ReviewQueryDTO;
import com.beauty4u.backend.goods.query.dto.ReviewSortDTO;
import com.beauty4u.backend.goods.query.elasticsearch.document.ReviewDocument;
import com.beauty4u.backend.goods.query.service.ReviewQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/review")
@Tag(name = "Review", description = "리뷰 조회 API")
//@ConditionalOnProperty(name = "elasticsearch.repositories.enabled", havingValue = "true", matchIfMissing = false)
public class ReviewQueryController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/list")
    @Operation(summary = "리뷰 목록 조회", description = "리뷰 목록 전체를 조회한다.")
    public ResponseEntity<List<ReviewQueryDTO>> findAllReview() {
        List<ReviewQueryDTO> reviewList = reviewQueryService.findAllReview();

        return ResponseEntity.ok(reviewList);
    }

    @GetMapping("/list/sort")
    @Operation(summary = "리뷰 정렬 조회", description = "리뷰 목록을 정렬 조회한다.")
    public ResponseEntity<List<ReviewQueryDTO>> findAllReviewSort(ReviewSortDTO reviewSortDTO) {
        List<ReviewQueryDTO> reviewList = reviewQueryService.findAllReviewSort(reviewSortDTO);

        return ResponseEntity.ok(reviewList);
    }

    @GetMapping("/list/date")
    @Operation(summary = "기간별 리뷰 조회", description = "기간별 리뷰 목록을 조회한다.")
    public ResponseEntity<List<ReviewQueryDTO>> findAllReviewByDate(
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate){

        LocalDateTime startDateTime = startDate != null ? startDate.atStartOfDay() : null;
        LocalDateTime endDateTime = endDate != null ? endDate.plusDays(1).atStartOfDay() : null;

        List<ReviewQueryDTO> reviewList = reviewQueryService.findAllReviewByDate(startDateTime, endDateTime);

        return ResponseEntity.ok(reviewList);
    }

    @GetMapping("/list/{searchReview}")
    @Operation(summary = "리뷰 검색", description = "엘라스틱 서치로 리뷰를 검색한다.")
    public ResponseEntity<List<ReviewDocument>> searchReview(@PathVariable String searchReview) {
        return ResponseEntity.ok(reviewQueryService.searchReview(searchReview));
    }


    @PostMapping("/index")
    @Operation(summary = "엘라스틱 서치 인덱스 생성", description = "DB 데이터를 엘라스틱 서치에 동기화한다.")
    public ResponseEntity<Void> indexReview() {
        reviewQueryService.indexReview();
        return ResponseEntity.ok().build();
    }
}
