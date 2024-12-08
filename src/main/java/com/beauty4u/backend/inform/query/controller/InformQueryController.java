package com.beauty4u.backend.inform.query.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.inform.query.dto.InformListResDTO;
import com.beauty4u.backend.inform.query.service.InformQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inform")
@Tag(name = "Inform", description = "공지사항 관련 API")
public class InformQueryController {

    private final InformQueryService informQueryService;

    @Operation(summary = "공지사항 목록 조회")
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<InformListResDTO>>> findInformList(
            @RequestParam(defaultValue = "1") Long page,
            @RequestParam(defaultValue = "10") Long count) {

        List<InformListResDTO> informListResDTOS = informQueryService.findInformList(page, count);

        return ResponseUtil.successResponse(SuccessCode.INFORM_FIND_LIST_SUCCESS, informListResDTOS);
    }
}
