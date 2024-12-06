package com.beauty4u.backend.goods.query.controller;

import com.beauty4u.backend.goods.query.dto.ReviewQueryDTO;
import com.beauty4u.backend.goods.query.service.ReviewQueryService;
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
@RequestMapping("/api/v1/review")
@Tag(name="Review", description="리뷰 조회 API")
public class ReviewQueryController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/list")
    @Operation(summary = "리뷰 목록 조회", description = "리뷰 목록 전체를 조회한다.")
    public ResponseEntity<List<ReviewQueryDTO>> findAllReview(){
        List<ReviewQueryDTO> reviewList = reviewQueryService.findAllReview();

        return ResponseEntity.ok(reviewList);
    }

}
