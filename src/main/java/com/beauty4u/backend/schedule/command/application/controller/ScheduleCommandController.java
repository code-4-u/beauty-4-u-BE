package com.beauty4u.backend.schedule.command.application.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.common.util.CustomUserUtil;
import com.beauty4u.backend.schedule.command.application.dto.ScheduleReqDTO;
import com.beauty4u.backend.schedule.command.application.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/schedule")
@Tag(name = "Schedule", description = "일정 관련 API")
public class ScheduleCommandController {

    private final ScheduleService scheduleService;

    @Operation(summary = "일정 등록", description = "회원이 일정을 등록한다.")
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> saveSchedule(
            @RequestBody @Valid ScheduleReqDTO scheduleReqDTO) {

        String loginUserCode = CustomUserUtil.getCurrentUserCode();

        scheduleService.saveSchedule(loginUserCode, scheduleReqDTO);

        return ResponseUtil.successResponse(SuccessCode.SCHEDULE_SAVE_SUCCESS);
    }

    @Operation(summary = "일정 수정", description = "회원이 일정을 수정한다.")
    @PutMapping("/{scheduleId}")
    public ResponseEntity<ApiResponse<Void>> updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody @Valid ScheduleReqDTO scheduleReqDTO) {

        scheduleService.updateSchedule(scheduleId, scheduleReqDTO);

        return ResponseUtil.successResponse(SuccessCode.SCHEDULE_UPDATE_SUCCESS);
    }
}
