package com.beauty4u.backend.goods.query.controller;

import com.beauty4u.backend.goods.query.dto.ReviewQueryDTO;
import com.beauty4u.backend.goods.query.service.ReviewQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/review")
@Tag(name = "Review", description = "리뷰 조회 API")
public class ReviewQueryController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/list")
    @Operation(summary = "리뷰 목록 조회", description = "리뷰 목록 전체를 조회한다.")
    public ResponseEntity<List<ReviewQueryDTO>> findAllReview(
            @Parameter(description = "페이징/정렬")
            @PageableDefault(size = 10)
            @SortDefault(sort="review_created_date", direction = Sort.Direction.DESC) Pageable pageable) {

        List<Sort.Order> orders = pageable.getSort().stream()
                .map(order -> new Sort.Order(order.getDirection(), order.getProperty()))
                .collect(Collectors.toList());

        List<ReviewQueryDTO> reviewList = reviewQueryService.findAllReview(orders);

        return ResponseEntity.ok(reviewList);
    }
}
