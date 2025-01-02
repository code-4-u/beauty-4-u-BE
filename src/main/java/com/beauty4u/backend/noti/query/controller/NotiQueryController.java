package com.beauty4u.backend.noti.query.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.common.util.CustomUserUtil;
import com.beauty4u.backend.noti.query.dto.NotiListResDTO;
import com.beauty4u.backend.noti.query.service.NotiQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/noti")
@Tag(name = "Notification", description = "알림 관련 API")
public class NotiQueryController {

    private final NotiQueryService notiQueryService;

    @Operation(summary = "헤더 알림 조회", description = "헤더 알림을 조회한다.")
    @GetMapping
    public ResponseEntity<ApiResponse<List<NotiListResDTO>>> findNotiList() {

        String loginUserCode = CustomUserUtil.getCurrentUserCode();

        List<NotiListResDTO> listResDTOS = notiQueryService.findNotiList(loginUserCode);

        return ResponseUtil.successResponse(SuccessCode.NOTI_FIND_LIST_SUCCESS, listResDTOS);
    }
}
