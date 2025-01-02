package com.beauty4u.backend.schedule.query.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.common.util.CustomUserUtil;
import com.beauty4u.backend.schedule.query.dto.ScheduleListResDTO;
import com.beauty4u.backend.schedule.query.service.ScheduleQueryService;
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
@RequestMapping("/api/v1/schedule")
@Tag(name = "Schedule", description = "일정 관련 API")
public class ScheduleQueryController {

    private final ScheduleQueryService scheduleQueryService;

    @Operation(summary = "일정 목록 조회", description = "일정 목록을 조회한다.")
    @GetMapping()
    public ResponseEntity<ApiResponse<List<ScheduleListResDTO>>> findScheduleList() {

        String loginUserCode = CustomUserUtil.getCurrentUserCode();

        List<ScheduleListResDTO> scheduleListResDTOS = scheduleQueryService.findScheduleList(loginUserCode);

        return ResponseUtil.successResponse(SuccessCode.SCHEDULE_FIND_LIST_SUCCESS, scheduleListResDTOS);
    }
}
