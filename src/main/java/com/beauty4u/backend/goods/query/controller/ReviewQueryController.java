package com.beauty4u.backend.goods.query.controller;

import com.beauty4u.backend.goods.query.dto.ReviewQueryDTO;
import com.beauty4u.backend.goods.query.elasticsearch.document.ReviewDocument;
import com.beauty4u.backend.goods.query.service.ReviewQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/review")
@Tag(name = "Review", description = "리뷰 조회 API")
@ConditionalOnProperty(name = "elasticsearch.enabled", havingValue = "true", matchIfMissing = false)
public class ReviewQueryController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/list")
    @Operation(summary = "리뷰 목록 조회", description = "리뷰 목록 전체를 조회한다.")
    public ResponseEntity<List<ReviewQueryDTO>> findAllReview(
            @Parameter(description = "페이징/정렬")
            @SortDefault(sort="created_date", direction = Sort.Direction.DESC) Pageable pageable) {

        List<Sort.Order> orders = pageable.getSort().stream()
                .map(order -> new Sort.Order(order.getDirection(), order.getProperty()))
                .collect(Collectors.toList());

        List<ReviewQueryDTO> reviewList = reviewQueryService.findAllReview(orders);

        return ResponseEntity.ok(reviewList);
    }

    @GetMapping("/list/{searchReview}")
    @Operation(summary = "리뷰 검색", description = "엘라스틱 서치로 리뷰를 검색한다.")
    public ResponseEntity<List<ReviewDocument>> searchReview(@PathVariable String searchReview) {
        return ResponseEntity.ok(reviewQueryService.searchReview(searchReview));
    }


    @PostMapping("/index")
    @Operation(summary = "엘라스틱 서치 인덱스 생성", description = "DB 데이터를 엘라스틱 서치에 동기화한다.")
    public ResponseEntity<Void> indexReview(){
        reviewQueryService.indexReview();
        return ResponseEntity.ok().build();
    }
}
