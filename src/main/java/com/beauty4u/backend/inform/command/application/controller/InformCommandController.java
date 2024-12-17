package com.beauty4u.backend.inform.command.application.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.common.util.CustomUserUtil;
import com.beauty4u.backend.inform.command.application.dto.InformReqDTO;
import com.beauty4u.backend.inform.command.application.dto.UpdateInformViewcount;
import com.beauty4u.backend.inform.command.application.service.InformService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inform")
@Tag(name = "Inform", description = "공지사항 관련 API")
public class InformCommandController {

    private final InformService informService;

    @Operation(summary = "공지사항 등록", description = "관리자가 공지사항을 등록한다.")
    @PostMapping
    public ResponseEntity<ApiResponse<Long>> saveInform(
            @RequestBody @Valid InformReqDTO informReqDTO) {

        String loginUserCode = CustomUserUtil.getCurrentUserCode();

        Long informId = informService.saveInform(loginUserCode, informReqDTO);

        return ResponseUtil.successResponse(SuccessCode.INFORM_SAVE_SUCCESS, informId);
    }

    @Operation(summary = "공지사항 수정", description = "관리자가 공지사항을 수정한다.")
    @PutMapping("/{informId}")
    public ResponseEntity<ApiResponse<Void>> updateInform(
            @PathVariable Long informId,
            @RequestBody @Valid InformReqDTO informReqDTO) {

        informService.updateInform(informId, informReqDTO);

        return ResponseUtil.successResponse(SuccessCode.INFORM_UPDATE_SUCCESS);
    }

    @Operation(summary = "공지사항 삭제", description = "관리자가 공지사항을 삭제한다.")
    @DeleteMapping("/{informId}")
    public ResponseEntity<ApiResponse<Void>> deleteInform(
            @PathVariable Long informId) {

        informService.deleteInform(informId);

        return ResponseUtil.successResponse(SuccessCode.INFORM_DELETE_SUCCESS);
    }

    @Operation(summary = "조회 수 수정", description = "조회 수를 수정한다.")
    @PutMapping("/{informId}/informViewcount")
    public ResponseEntity<ApiResponse<Void>> updateInformViewcount(
            @PathVariable("informId") Long informId,
            @RequestBody UpdateInformViewcount updateInformViewcount) {

        informService.updateInformViewCount(informId, updateInformViewcount);

        System.out.println(updateInformViewcount.getInformViewcount());
        System.out.println(informId);
        return ResponseUtil.successResponse(SuccessCode.INFORM_UPDATE_VIEWCOUNT_SUCCESS);
    }
}
