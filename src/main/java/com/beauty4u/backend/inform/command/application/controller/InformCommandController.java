package com.beauty4u.backend.inform.command.application.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.common.util.CustomUserUtil;
import com.beauty4u.backend.inform.command.application.dto.CreateInformReqDTO;
import com.beauty4u.backend.inform.command.application.service.InformCommandService;
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
@RequestMapping("/api/v1/inform")
@Tag(name = "Inform", description = "공지사항 관련 API")
public class InformCommandController {

    private final InformCommandService informCommandService;

    @Operation(summary = "공지사항 등록", description = "관리자가 공지사항을 등록한다.")
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> saveInform(
            @RequestBody @Valid CreateInformReqDTO createInformReqDTO) {

        String loginUserCode = CustomUserUtil.getCurrentUserCode();

        informCommandService.saveInform(loginUserCode, createInformReqDTO);

        return ResponseUtil.successResponse(SuccessCode.INFORM_SAVE_SUCCESS);
    }
}
