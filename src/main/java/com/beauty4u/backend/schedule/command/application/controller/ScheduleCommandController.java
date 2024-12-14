package com.beauty4u.backend.schedule.command.application.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.common.util.CustomUserUtil;
import com.beauty4u.backend.schedule.command.application.dto.CreateScheduleReqDTO;
import com.beauty4u.backend.schedule.command.application.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/schedule")
@Tag(name = "Schedule", description = "일정 관련 API")
public class ScheduleCommandController {

    private final ScheduleService scheduleService;

    @Operation(summary = "일정 등록", description = "회원이 일정을 등록한다.")
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> saveSchedule(
            @RequestBody @Valid CreateScheduleReqDTO createScheduleReqDTO) {

        String loginUserCode = CustomUserUtil.getCurrentUserCode();

        scheduleService.saveSchedule(loginUserCode, createScheduleReqDTO);

        return ResponseUtil.successResponse(SuccessCode.SCHEDULE_SAVE_SUCCESS);
    }
}
